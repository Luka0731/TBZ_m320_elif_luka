package ch.tbz.soccergame.engine;

import ch.tbz.soccergame.domains.Person;
import ch.tbz.soccergame.domains.Team;
import ch.tbz.soccergame.domains.players.Midfielder;
import ch.tbz.soccergame.domains.players.Player;

import java.util.ArrayList;
import java.util.List;

public class TeamService {

    // |--- factory methods ---|

    public Team createTeam(String teamName, String trainerName, int trainerAge) {
        return new Team(teamName, new Person(trainerName, trainerAge));
    }

    // |--- utility methods ---|

    public void addPlayerToTeam(Team team, Player player) {
        team.addPlayer(player);
    }

    public void removePlayerFromTeam(Team team, Player player) {
        team.removePlayer(player);
    }

    public void trainTeam(Team team, int minutes) {
        team.train(minutes);
    }

    public void printTeamReport(Team team) {
        team.printReport();
    }

    // |--- getters ---|

    public List<Midfielder> getMidfielders(Team team) {
        List<Midfielder> mids = new ArrayList<>();
        for (Player p : team.getRoster()) {
            if (p instanceof Midfielder m) {
                mids.add(m);
            }
        }
        return mids;
    }
}
