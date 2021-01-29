import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseballEliminationTest {
    @Test
    public void testfields() {
        /*
            4
            Atlanta       83 71  8  0 1 6 1
            Philadelphia  80 79  3  1 0 0 2
            New_York      78 78  6  6 0 0 0
            Montreal      77 82  3  1 2 0 0
         */
        BaseballElimination b = new BaseballElimination("testfiles/teams4.txt");

        assertEquals(b.numberOfTeams(), 4);

        for (String team : b.teams()) {
            StdOut.println(team);
        }


        assertEquals(83, b.wins("Atlanta"));
        assertEquals(80, b.wins("Philadelphia"));
        assertEquals(78, b.wins("New_York"));
        assertEquals(77, b.wins("Montreal"));

        assertEquals(71, b.losses("Atlanta"));
        assertEquals(79, b.losses("Philadelphia"));
        assertEquals(78, b.losses("New_York"));
        assertEquals(82, b.losses("Montreal"));

        assertEquals(8, b.remaining("Atlanta"));
        assertEquals(3, b.remaining("Philadelphia"));
        assertEquals(6, b.remaining("New_York"));
        assertEquals(3, b.remaining("Montreal"));

        assertEquals(0, b.against("Atlanta", "Atlanta"));
        assertEquals(1, b.against("Atlanta", "Philadelphia"));
        assertEquals(6, b.against("Atlanta", "New_York"));
        assertEquals(1, b.against("Atlanta", "Montreal"));
    }

    @Test
    public void testIsEliminated_teams4() {
        BaseballElimination b = new BaseballElimination("testfiles/teams4.txt");

        assertFalse(b.isEliminated("Atlanta"));
        assertTrue(b.isEliminated("Philadelphia"));
        assertFalse(b.isEliminated("New_York"));
        assertTrue(b.isEliminated("Montreal"));
    }

    @Test
    // a(R) = (w(R) + g(R)) / |R| should be greater than w(x) + r(x), where x is the team eliminated
    public void testCertificateOfElimination_teams4() {
        BaseballElimination b = new BaseballElimination("testfiles/teams4.txt");

        assertNull(b.certificationOfElimination("Atlanta"));

        Iterable<String> certificates1 = b.certificationOfElimination("Philadelphia");

        int wins1 = 0;
        int games1 = 0;
        int R1 = 0;
        for (String team1 : certificates1) {
            wins1 += b.wins(team1);
            R1++;
            for (String team2 : certificates1) {
                games1 += b.against(team1, team2);
            }
        }

        assertTrue((wins1 + games1) / Math.abs(R1) > (b.wins("Philadelphia") + b.remaining("Philadelphia")));

        assertNull(b.certificationOfElimination("New_York"));

        Iterable<String> certificates2 = b.certificationOfElimination("Montreal");

        int wins2 = 0;
        int games2 = 0;
        int R2 = 0;
        for (String team1 : certificates2) {
            wins2 += b.wins(team1);
            R2++;
            for (String team2 : certificates2) {
                games2 += b.against(team1, team2);
            }
        }

        assertTrue((wins2 + games2) / Math.abs(R2) > (b.wins("Montreal") + b.remaining("Montreal")));


    }
}
