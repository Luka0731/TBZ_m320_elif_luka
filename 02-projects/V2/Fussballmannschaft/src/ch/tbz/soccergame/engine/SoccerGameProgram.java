package ch.tbz.soccergame.engine;

import ch.tbz.soccergame.domains.Team;
import ch.tbz.soccergame.domains.players.Midfielder;
import ch.tbz.soccergame.domains.players.Player;
import ch.tbz.soccergame.util.Side;
import ch.tbz.soccergame.util.frontendSamples.ConsoleStyle;
import ch.tbz.soccergame.util.frontendSamples.Input;
import ch.tbz.soccergame.util.frontendSamples.Output;

import java.util.ArrayList;
import java.util.List;

public class SoccerGameProgram {
    private List<Team> teams;
    private PlayerService playerService;
    private TeamService teamService;

    public void start() {
        teams = new ArrayList<>();
        playerService = new PlayerService();
        teamService = new TeamService();

        Output.printlnStyled("Welcome to SoccerGame!", ConsoleStyle.BRIGHT_GREEN);

        boolean running = true;
        while (running) {
            Output.printlnStyled("\nMain Menu", ConsoleStyle.BRIGHT_CYAN);
            System.out.println("1) Create Team");
            System.out.println("2) Add Player to Team");
            System.out.println("3) Train Team");
            System.out.println("4) Switch Midfielder Side");
            System.out.println("5) Team Report");
            System.out.println("0) Exit");

            int choice = Input.getInteger("Select option: ", 0, 5);
            switch (choice) {
                case 1 -> createTeamFlow();
                case 2 -> addPlayerFlow();
                case 3 -> trainTeamFlow();
                case 4 -> switchMidfielderSideFlow();
                case 5 -> printTeamReportFlow();
                case 0 -> running = false;
                default -> Output.printlnStyled("Unknown option", ConsoleStyle.RED);
            }
        }

        Output.printlnStyled("Goodbye!", ConsoleStyle.BRIGHT_YELLOW);
    }

    // |--- flow ---|

    private void createTeamFlow() {
        String teamName = Input.getString("Team name: ");
        String coachName = Input.getString("Coach name: ");
        int coachAge = Input.getInteger("Coach age: ", 18, 90);

        Team team = teamService.createTeam(teamName, coachName, coachAge);
        teams.add(team);
        Output.printlnStyled("Team created: " + teamName, ConsoleStyle.GREEN);
    }

    private void addPlayerFlow() {
        Team team = pickTeam();
        if (team == null) return;

        System.out.println("Choose player type:");
        System.out.println("1) Goalkeeper");
        System.out.println("2) Defender");
        System.out.println("3) Midfielder");
        System.out.println("4) Forward");
        int type = Input.getInteger("Type: ", 1, 4);

        String name = Input.getString("Player name: ");
        int age = Input.getInteger("Age: ", 16, 50);
        int overall = Input.getInteger("Overall (1-99): ", 1, 99);

        Player player = null;
        switch (type) {
            case 1 -> player = playerService.createGoalkeeper(name, age, overall);
            case 2 -> player = playerService.createDefender(name, age, overall);
            case 3 -> {
                System.out.println("Side: 1) LEFT  2) RIGHT");
                int sideOption = Input.getInteger("Choose side: ", 1, 2);
                Side side = sideOption == 1 ? Side.LEFT : Side.RIGHT;
                player = playerService.createMidfielder(name, age, overall, side);
            }
            case 4 -> player = playerService.createForward(name, age, overall);
        }

        teamService.addPlayerToTeam(team, player);
        Output.printlnStyled("Player added to " + team.getName(), ConsoleStyle.GREEN);
    }

    private void trainTeamFlow() {
        Team team = pickTeam();
        if (team == null) return;

        int minutes = Input.getInteger("Train minutes (15-180): ", 15, 180);
        teamService.trainTeam(team, minutes);
        Output.printlnStyled("Team trained for " + minutes + " minutes.", ConsoleStyle.GREEN);
    }

    private void switchMidfielderSideFlow() {
        Team team = pickTeam();
        if (team == null) return;

        List<Player> mids = new ArrayList<>(teamService.getMidfielders(team));
        if (mids.isEmpty()) {
            Output.printlnStyled("No midfielders in team.", ConsoleStyle.YELLOW);
            return;
        }
        printPlayersIndexed(mids);

        int idx = Input.getInteger("Select midfielder #: ", 1, mids.size()) - 1;
        Player p = mids.get(idx);
        if (p instanceof Midfielder m) {
            playerService.switchMidfielderSide(m);
            Output.printlnStyled("Switched side.", ConsoleStyle.GREEN);
        } else {
            Output.printlnStyled("Selected player is not a midfielder.", ConsoleStyle.RED);
        }
    }

    private void printTeamReportFlow() {
        Team team = pickTeam();
        if (team == null) return;
        teamService.printTeamReport(team);
    }

    // |--- helper methods ---|

    private Team pickTeam() {
        if (teams.isEmpty()) {
            Output.printlnStyled("No teams yet. Create one first.", ConsoleStyle.YELLOW);
            return null;
        }
        System.out.println("Teams:");
        for (int i = 0; i < teams.size(); i++) {
            System.out.printf("%d) %s%n", (i + 1), teams.get(i).getName());
        }
        int choice = Input.getInteger("Select team #: ", 1, teams.size());
        return teams.get(choice - 1);
    }

    private void printPlayersIndexed(List<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            System.out.print((i + 1) + ") ");
            players.get(i).printSummary();
        }
    }
}
