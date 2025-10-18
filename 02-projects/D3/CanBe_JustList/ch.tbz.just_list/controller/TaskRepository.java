package controller;

import java.nio.file.*;
import java.io.*;
import java.util.*;

import exception.DataSaveException;
import exception.LoadingException;
import exception.NotFoundException;
import model.Task;
import repository.TaskRepo;

public class TaskRepository implements TaskRepo {
    private final Path dataPath = Paths.get("data", "tasks.csv");
    private final List<Task> tasks = new ArrayList<>();

    public TaskRepository() {
        load();
    }

    @Override
    public void save(Task task) {
        tasks.add(task);
        saveToFile();
    }

    @Override
    public void delete(String title) {
        boolean removed = tasks.removeIf(task -> task.getTitle().equalsIgnoreCase(title));
        if (!removed) {
            throw new NotFoundException("No task found with title: " + title);
        }
        saveToFile();

    }

    @Override
    public void saveToFile() {
        try {
            Files.createDirectories(dataPath.getParent());

            try (BufferedWriter writer = Files.newBufferedWriter(dataPath)) {
                writer.write("category;level;title;description;helpful_resources\n");
                for (Task t : tasks) {
                    writer.write(String.join(";",
                            escape(t.getCategory()),
                            escape(t.getLevel()),
                            escape(t.getTitle()),
                            escape(t.getDescription()),
                            escape(t.getHelpful_ressources())));
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            throw new DataSaveException("Task failes to save", e); // use your own exception
        }
    }

    @Override
    public void load() {
        tasks.clear();
        if (!Files.exists(dataPath)) return;

        try (BufferedReader reader = Files.newBufferedReader(dataPath)) {
            String line = reader.readLine(); // skip header
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", -1);
                if (parts.length == 5) {
                    tasks.add(new Task(
                            unescape(parts[0]),
                            unescape(parts[1]),
                            unescape(parts[2]),
                            unescape(parts[3]),
                            unescape(parts[4])
                    ));
                }
            }
        } catch (IOException e) {
            throw new LoadingException("Failes to load data", e); // use your custom exception
        }
    }

    private String escape(String text) {
        return text == null ? "" : text.replace(";", "\\;");
    }

    private String unescape(String text) {
        return text == null ? "" : text.replace("\\;", ";");
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }
}
