package ch.tbz.soccergame.domains.players;

import ch.tbz.soccergame.domains.Person;
import ch.tbz.soccergame.util.Meter;

public class Defender extends Player {
    private final Meter tackling;

    public Defender(Person person, int overall) {
        super(person, overall);
        this.tackling = new Meter(5.0);
    }


    // |--- overriding methods ---|

    @Override
    public void train(int minutes) {
        double fitnessGain = BASE_TRAINING_GAIN * (minutes / 80.0);
        double tacklingGain = BASE_TRAINING_GAIN * (minutes / 60.0);
        this.getFitness().add(fitnessGain);
        tackling.add(tacklingGain);
    }

    @Override
    public void printSummary() {
        System.out.printf(
                "\n-------------------------------------------------" +
                "\nName: " + this.getName() +
                "\nAge: " + this.getAge() +
                "\nOverall Number: " + this.getOverallNumber() +
                "\nPosition: " + this.getClass().getSimpleName() +
                "\nFitness Level: " + this.getFitness().toSting() +
                "\nTackling Level: " + tackling.toSting() +
                "\nMarket Value: " + this.getMarketValue()
        );
    }

    @Override
    public double getMarketValue() {
        return 95 * (int) (this.getFitness().getValue() * tackling.getValue());
    }
}
