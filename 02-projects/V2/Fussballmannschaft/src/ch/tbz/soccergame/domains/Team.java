package ch.tbz.soccergame.domains;

import ch.tbz.soccergame.domains.players.Player;
import ch.tbz.soccergame.util.frontendSamples.Output;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Team {
    private final String name;
    private final Person trainer;
    private final List<Player> roster;

    public Team(String name, Person trainer) {
        this.name = name;
        this.trainer = trainer;
        roster = new ArrayList<>();
    }

    public void addPlayer(Player player){
        if (roster.size() < 12){
            roster.add(player);
        } else {
            Output.printlnError("The team '" + name + "' already reached its player max of 11");
        }
    }

    public void removePlayer(Player player) {
        if (roster.contains(player)) {
            roster.remove(player);
        } else {
            Output.printlnError( "is not in the team", "Person: " + player.getName() + ", " + player.getAge());
        }
    }

    public void train(int minutes){
        for (Player player : roster) player.train(minutes);
    }

    public void printReport() {
        System.out.print("////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("Team " + name + ", lead by " + trainer.getName() + ", Kader: ");
        sortRoster();
        roster.forEach(Player::printSummary);
        System.out.println("\n////////////////////////////////////////////////////////////////////////////////////");
    }


    // |--- helper methods ---|

    private void sortRoster(){
        roster.stream()
                .sorted(Comparator.comparingInt(p -> -((Player)p).getOverallNumber()))
                .limit(11).toList();
    }


    // |--- getters & setters ---|

    public String getName() {
        return name;
    }

    public List<Player> getRoster() {
        sortRoster();
        return roster;
    }
}