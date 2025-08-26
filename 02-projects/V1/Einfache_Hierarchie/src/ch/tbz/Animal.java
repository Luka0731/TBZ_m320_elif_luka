package ch.tbz;

class Animal {
    private final String name;
    private final int age;

    protected Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String speak() {
        return "Animal sound";
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getInfo() {
        return getClass().getSimpleName() + "[" + name + ", " + age + "]";
    }
}