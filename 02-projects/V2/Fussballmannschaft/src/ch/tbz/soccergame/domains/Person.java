package ch.tbz.soccergame.domains;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }


    // |--- getters & setters ---|

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
