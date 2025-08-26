package ch.tbz;

class Cat extends Mammal {

    public Cat(String name, int age, boolean hasFur) {
        super(name, age, hasFur);
    }

    @Override
    public String speak() {
        return "Miau";
    }
}