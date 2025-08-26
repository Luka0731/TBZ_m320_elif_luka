package ch.tbz;

public class Mammal extends Animal {
    private final boolean hasFur;

    protected Mammal(String name, int age, boolean hasFur) {
        super(name, age);
        this.hasFur = hasFur;
    }

    @Override
    public String speak() { return "Mamal sound"; }

    public boolean hasFur() {
        return hasFur;
    }
}