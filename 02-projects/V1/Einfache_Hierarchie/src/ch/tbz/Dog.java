package ch.tbz;

class Dog extends Mammal {

    public Dog(String name, int age, boolean hasFur) {
        super(name, age, hasFur);
    }

    @Override
    public String speak() {
        return "Wuff";
    }
}