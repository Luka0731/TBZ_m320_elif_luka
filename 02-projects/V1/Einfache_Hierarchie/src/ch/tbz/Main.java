package ch.tbz;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Animal> zoo = new ArrayList<>();
        zoo.add(new Dog("Bello", 4, true));
        zoo.add(new Cat("Minka", 2, false));

        for (Animal animal : zoo) {
            System.out.println(animal.getInfo());
            System.out.println(animal.speak());

            if (animal instanceof Mammal m) {
                System.out.println("Fell: " + m.hasFur());
            }

            System.out.println("—");
        }
    }
}