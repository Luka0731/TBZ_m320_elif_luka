package service;

import linguistic.ParseInput;
import model.Task;
import controller.TaskRepository;

import java.util.Optional;
import java.util.Scanner;

public class TaskService {

    TaskRepository tp = new TaskRepository();
    Scanner sc = new Scanner(System.in);

    public void CreateTask(String title, String description,
                           final String helpful_ressources,
                           final String category, final String level) {

        ParseInput pInput = new ParseInput();
        final String final_title = pInput.ParseInput(title);
        final String final_description = pInput.ParseInput(description);
        Task task = new Task(final_title, final_description, helpful_ressources, category, level);
        tp.save(task);

    }

    public void Delete(String title) {
        tp.delete(title);
    }

    public void ShowAllTasks() {
        tp.load(); // ensure latest data
        for (Task task : tp.getAllTasks()) {
            System.out.println("📌 " + task.getTitle() +
                    " [" + task.getCategory() + " - " + task.getLevel() + "]\n" +
                    "   " + task.getDescription() +
                    "\n   Helpful resources: " + task.getHelpful_ressources() + "\n");
        }
    }


}
