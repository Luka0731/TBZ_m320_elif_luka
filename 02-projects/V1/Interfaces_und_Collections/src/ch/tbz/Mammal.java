package ch.tbz;

abstract class Mammal extends Animal {
    private final boolean hasFur;

    protected Mammal(String name, int age, boolean hasFur) {
        super(name, age);
        this.hasFur = hasFur;
    }

    public boolean hasFur() {
        return hasFur;
    }
}