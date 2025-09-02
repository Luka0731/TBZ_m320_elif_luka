package ch.tbz.soccergame.engine;

import ch.tbz.soccergame.domains.Person;
import ch.tbz.soccergame.domains.players.*;
import ch.tbz.soccergame.util.Side;

public class PlayerService {

    // |--- factory methods ---|

    public Goalkeeper createGoalkeeper(String name, int age, int overall) {
        return new Goalkeeper(new Person(name, age), overall);
    }

    public Defender createDefender(String name, int age, int overall) {
        return new Defender(new Person(name, age), overall);
    }

    public Midfielder createMidfielder(String name, int age, int overall, Side side) {
        return new Midfielder(new Person(name, age), overall, side);
    }

    public Forward createForward(String name, int age, int overall) {
        return new Forward(new Person(name, age), overall);
    }

    // |--- utility methods ---|

    public void trainPlayer(Player player, int minutes) {
        player.train(minutes);
    }

    public void printPlayer(Player player) {
        player.printSummary();
    }

    public void switchMidfielderSide(Midfielder midfielder) {
        midfielder.switchSide();
    }
}
