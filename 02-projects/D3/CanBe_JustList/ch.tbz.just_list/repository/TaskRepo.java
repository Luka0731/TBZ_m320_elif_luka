package repository;

import model.Task;

public interface TaskRepo {
    public void saveToFile();
    
    public void load();

    public void delete(String title);

    public void save(Task task);




}
