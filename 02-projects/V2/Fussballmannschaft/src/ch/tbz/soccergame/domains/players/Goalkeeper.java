package ch.tbz.soccergame.domains.players;

import ch.tbz.soccergame.domains.Person;
import ch.tbz.soccergame.util.Meter;

public class Goalkeeper extends Player {
    private final Meter reflexes;

    public Goalkeeper(Person person, int overall) {
        super(person, overall);
        this.reflexes = new Meter(5.0);
    }


    // |--- overriding methods ---|

    @Override
    public void train(int minutes) {
        double fitnessGain = BASE_TRAINING_GAIN * (minutes / 90.0);
        double reflexesGain = BASE_TRAINING_GAIN * (minutes / 60.0);
        this.getFitness().add(fitnessGain);
        reflexes.add(reflexesGain);
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
                        "\nReflexes Level: " + reflexes.toSting() +
                        "\nMarket Value: " + this.getMarketValue()
        );
    }

    @Override
    public double getMarketValue() {
        return 50 * (int) (this.getFitness().getValue() * reflexes.getValue() * reflexes.getValue());
    }
}
