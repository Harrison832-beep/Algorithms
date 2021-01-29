import edu.princeton.cs.algs4.In;

/************************************************************************************
 * % cat teams4.txt
 * 4
 * Atlanta       83 71  8  0 1 6 1
 * Philadelphia  80 79  3  1 0 0 2
 * New_York      78 78  6  6 0 0 0
 * Montreal      77 82  3  1 2 0 0
 *
 * % cat teams5.txt
 * 5
 * New_York    75 59 28   0 3 8 7 3
 * Baltimore   71 63 28   3 0 2 7 7
 * Boston      69 66 27   8 2 0 0 3
 * Toronto     63 72 27   7 7 0 0 3
 * Detroit     49 86 27   3 7 3 3 0
 *
 * *****************************************************************************************
 *
 * % java-algs4 BaseballElimination teams4.txt
 * Atlanta is not eliminated
 * Philadelphia is eliminated by the subset R = { Atlanta New_York }
 * New_York is not eliminated
 * Montreal is eliminated by the subset R = { Atlanta }
 *
 * % java-algs4 BaseballElimination teams5.txt
 * New_York is not eliminated
 * Baltimore is not eliminated
 * Boston is not eliminated
 * Toronto is not eliminated
 * Detroit is eliminated by the subset R = { New_York Baltimore Boston Toronto }
 *****************************************************************************************/

public class BaseballElimination {
    private int numberOfTeams;
    private String[] teams;
    private int[][] g;
    private int[] wins;
    private int[] losses;
    private int[] remains;

    public BaseballElimination(String filename) {
        In in = new In(filename);
        this.numberOfTeams = in.readInt();
        this.teams = new String[numberOfTeams];
        this.g = new int[numberOfTeams][numberOfTeams];
        this.wins = new int[numberOfTeams];
        this.losses = new int[numberOfTeams];
        this.remains = new int[numberOfTeams];

        int i = 0;
        while (!in.isEmpty()) {
            this.teams[i] = in.readString();
            this.wins[i] = in.readInt(); // wins
            this.losses[i] = in.readInt(); // losses
            this.remains[i] = in.readInt(); // left

            for (int j = 0; j < numberOfTeams; j++) {
                g[i][j] = in.readInt();
            }

            i++;
        }
    }

    public int numberOfTeams() {
        return numberOfTeams;
    }

    public Iterable<String> teams() {
        return null;
    }

    public int wins(String team) {
        return 0;
    }

    public int losses(String team) {
        return 0;
    }

    public int remaining(String team) {
        return 0;
    }

    public int against(String team1, String team2) {
        return 0;
    }

    public boolean isEliminated(String team) {
        return false;
    }

    public Iterable<String> certificationOfElimination(String team) {
        return null;
    }

    private void validateTeam(String team) {
        if (false)
            throw new IllegalArgumentException();
    }

    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination(args[0]);
        /*
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
