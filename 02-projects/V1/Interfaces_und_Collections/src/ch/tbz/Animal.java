package ch.tbz;

abstract class Animal implements Comparable<Animal> {
    private final String name;
    private final int age;

    protected Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    abstract public String speak();

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getInfo() {
        return getClass().getSimpleName() + "[" + name + ", " + age + "]";
    }

    @Override
    public int compareTo(Animal o) {
        int c = Integer.compare(age, o.age);
        return c != 0 ? c : name.compareTo(o.name);
    }
}