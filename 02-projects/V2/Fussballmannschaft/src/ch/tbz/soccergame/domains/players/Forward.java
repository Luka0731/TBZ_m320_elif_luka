package ch.tbz.soccergame.domains.players;

import ch.tbz.soccergame.domains.Person;
import ch.tbz.soccergame.util.Meter;

public class Forward extends Player {
    private final Meter finishing;

    public Forward(Person person, int overall) {
        super(person, overall);
        this.finishing = new Meter(5.0);
    }


    // |--- overriding methods ---|

    @Override
    public void train(int minutes) {
        double fitnessGain = BASE_TRAINING_GAIN * (minutes / 50.0);
        double finishingGain = BASE_TRAINING_GAIN * (minutes / 70.0);
        this.getFitness().add(fitnessGain);
        finishing.add(finishingGain);
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
                        "\nFinishing Level: " + finishing.toSting() +
                        "\nMarket Value: " + this.getMarketValue()
        );
    }

    @Override
    public double getMarketValue() {
        return 105 * (int) (this.getFitness().getValue() * finishing.getValue());
    }
}
