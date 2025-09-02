package ch.tbz.soccergame.domains.players;

import ch.tbz.soccergame.domains.Person;
import ch.tbz.soccergame.util.Meter;

public abstract class Player {
    protected final int BASE_TRAINING_GAIN = 6;
    private final Person person;
    private int overallNumber;
    private final Meter fitness;

    protected Player(Person person, int overall) {
        this.person = person;
        this.overallNumber = overall;
        fitness = new Meter(100, 20);
    }

    public abstract void printSummary();


    // |--- training ---|

    public void train(int minutes) {
        double gain = BASE_TRAINING_GAIN * (minutes / 60.0);
        fitness.add(gain);
    }

    public void train() { train(60); }


    // |--- getters & setters ---|

    public int getOverallNumber() {
        return overallNumber;
    }

    public Meter getFitness() {
        return fitness;
    }

    public abstract double getMarketValue();

    public String getName() {
        return person.getName();
    }

    public int getAge() {
        return person.getAge();
    }

    public void changeOverallNumber(int overallNumber) {
        this.overallNumber = overallNumber;
    }
}
