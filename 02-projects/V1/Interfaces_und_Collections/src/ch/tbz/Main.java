package ch.tbz;

import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Zoo zoo = new Zoo();
        zoo.add(new Dog("Bello", 4, true));
        zoo.add(new Cat("Minka", 2, false));
        zoo.add(new Dog("Arko", 6, true));

        Collections.sort(zoo);
        for (Animal animal : zoo) {
            System.out.println(animal.getInfo());
        }

        System.out.println("—");

        for (Animal animal : zoo.sortByName()) {
            System.out.println(animal.getInfo());
        }
    }
}