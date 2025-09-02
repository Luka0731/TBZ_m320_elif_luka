package ch.tbz.soccergame.domains.players;

import ch.tbz.soccergame.domains.Person;
import ch.tbz.soccergame.util.Meter;
import ch.tbz.soccergame.util.Side;

public class Midfielder extends Player {
    private final Meter vision;
    private Side side;

    public Midfielder(Person person, int overall, Side side) {
        super(person, overall);
        this.vision = new Meter(5.0);
        this.side = side;
    }

    public void switchSide() {
        if (side == Side.LEFT) {
            this.side = Side.RIGHT;
        } else {
            this.side = Side.LEFT;
        }
    }


    // |--- overriding methods ---|

    @Override
    public void train(int minutes) {
        double fitnessGain = BASE_TRAINING_GAIN * (minutes / 40.0);
        double visionGain = BASE_TRAINING_GAIN * (minutes / 90.0);
        this.getFitness().add(fitnessGain);
        vision.add(visionGain);
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
                        "\nVision Level: " + vision.toSting() +
                        "\nMarket Value: " + this.getMarketValue()
        );
    }

    @Override
    public double getMarketValue() {
        return 90 * (int) (this.getFitness().getValue() * vision.getValue());
    }
}
