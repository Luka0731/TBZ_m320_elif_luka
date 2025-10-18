package model;

public class Task {

    private final String category;
    private final String level;
    private final String title;
    private final String description;
    private final String helpful_ressources;


    public Task(String category, String level, String name, String description, String helpfulRessources) {
        this.category = category;
        this.level = level;
        this.title = name;
        this.description = description;
        this.helpful_ressources = helpfulRessources;
    }

    public String getCategory() {
        return category;
    }

    public String getLevel() {
        return level;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getHelpful_ressources() {
        return helpful_ressources;
    }
}
