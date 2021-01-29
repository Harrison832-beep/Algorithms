import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;

/************************************************************************************
 % cat teams4.txt
 4
 Atlanta       83 71  8  0 1 6 1
 Philadelphia  80 79  3  1 0 0 2
 New_York      78 78  6  6 0 0 0
 Montreal      77 82  3  1 2 0 0

 % cat teams5.txt
 5
 New_York    75 59 28   0 3 8 7 3
 Baltimore   71 63 28   3 0 2 7 7
 Boston      69 66 27   8 2 0 0 3
 Toronto     63 72 27   7 7 0 0 3
 Detroit     49 86 27   3 7 3 3 0
 *****************************************************************************************

 % java-algs4 BaseballElimination teams4.txt
 Atlanta is not eliminated
 Philadelphia is eliminated by the subset R = { Atlanta New_York }
 New_York is not eliminated
 Montreal is eliminated by the subset R = { Atlanta }

 % java-algs4 BaseballElimination teams5.txt
 New_York is not eliminated
 Baltimore is not eliminated
 Boston is not eliminated
 Toronto is not eliminated
 Detroit is eliminated by the subset R = { New_York Baltimore Boston Toronto }
 *****************************************************************************************/

public class BaseballElimination {
    private final int numberOfTeams;
    private final HashMap<String, Integer> teams;
    private final int[][] g;
    private final int[] wins;
    private final int[] losses;
    private final int[] remains;

    /**
     * create a baseball division from given filename
     *
     * @param filename filename
     */
    public BaseballElimination(String filename) {
        In in = new In(filename);
        this.numberOfTeams = in.readInt();
        this.teams = new HashMap<>();
        this.g = new int[numberOfTeams][numberOfTeams];
        this.wins = new int[numberOfTeams];
        this.losses = new int[numberOfTeams];
        this.remains = new int[numberOfTeams];

        int i = 0;
        while (!in.isEmpty()) {
            this.teams.put(in.readString(), i);
            this.wins[i] = in.readInt(); // wins
            this.losses[i] = in.readInt(); // losses
            this.remains[i] = in.readInt(); // left

            for (int j = 0; j < numberOfTeams; j++) {
                g[i][j] = in.readInt();
            }

            i++;
        }
    }

    /**
     * number of teams
     *
     * @return integer represents the total number of teams
     */
    public int numberOfTeams() {
        return numberOfTeams;
    }

    /**
     * all teams
     *
     * @return an iterable contains all teams in this division
     */
    public Iterable<String> teams() {
        return teams.keySet();
    }

    /**
     * number of wins for given team
     *
     * @param team String representation of a team
     * @return number of wins of that team
     */
    public int wins(String team) {
        int teamIndex = teams.get(team);
        return wins[teamIndex];
    }

    /**
     * number of losses for given team
     *
     * @param team String representation of a team
     * @return number of losses of that team
     */
    public int losses(String team) {
        int teamIndex = teams.get(team);
        return losses[teamIndex];
    }

    /**
     * number of remaining games for given team
     *
     * @param team String representation of a team
     * @return number of remaining games of that team
     */
    public int remaining(String team) {
        int teamIndex = teams.get(team);
        return remains[teamIndex];
    }

    /**
     * Number of remaining games between team1 and team2
     *
     * @param team1 String representation of a team1
     * @param team2 String representation of a team2
     * @return number of remaining games between team1 and team2
     */
    public int against(String team1, String team2) {
        int inx1 = teams.get(team1);
        int inx2 = teams.get(team2);
        return g[inx1][inx2];
    }

    /**
     * Is given team eliminated?
     *
     * @param team String representation of a team
     * @return boolean if the team is eliminated
     */
    public boolean isEliminated(String team) {
        constructFordFulkerson(teams.get(team));
        return false;
    }

    private void constructFordFulkerson(int teamInx) {
        int v = 1;
        int s = 0;
        int V = combinations(numberOfTeams - 1, 2) + numberOfTeams - 1 + 2; // Combinations, teams, s, t (exclude given team)
        int t = V - 1;
        FlowNetwork G = new FlowNetwork(V);

        StdOut.println(t);

        for (int i = 0; i < numberOfTeams - 1; i++) {
            for (int j = i + 1; j < numberOfTeams - 1; j++) {
                FlowEdge e = new FlowEdge(s, v, g[i][j]);
                G.addEdge(e);
                // TODO connect game between teams to teams
            }
        }


    }

    private int combinations(int n, int k) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    private int factorial(int n) {
        int fac = 1;

        for (int i = 1; i <= n; i++)
            fac *= i;
        return fac;
    }

    /**
     * Subset R of teams that eliminates given team; null if not eliminated
     *
     * @param team String representation of a team
     * @return Iterable contains all teams that eliminates given team
     */
    public Iterable<String> certificationOfElimination(String team) {
        return null;
    }

    private void validateTeam(String team) {
        if (!teams.containsKey(team))
            throw new IllegalArgumentException();
    }

    public static void main(String[] args) {
        /*
        BaseballElimination division = new BaseballElimination(args[0]);

        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificationOfElimination(team)) {
                    StdOut.print(t + " ");
                }
            } else {
                StdOut.println(team + " is not eliminated");
            }
        }
         */
    }

}
