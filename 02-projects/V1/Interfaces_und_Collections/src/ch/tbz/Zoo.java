package ch.tbz;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Zoo extends ArrayList<Animal> {
    public List<Animal> sortByName() {
        List<Animal> copy = new ArrayList<>(this);
        copy.sort(Comparator.comparing(Animal::getName));
        return copy;
    }
}