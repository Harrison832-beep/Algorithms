import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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

        ArrayList<String> teams = (ArrayList<String>) b.teams();
        assertNotNull(teams);
        assertEquals(teams.get(0), "Atlanta");
        assertEquals(teams.get(1), "Philadelphia");
        assertEquals(teams.get(2), "New_York");
        assertEquals(teams.get(3), "Montreal");

        assertEquals(b.wins("Atlanta"), 83);
        assertEquals(b.wins("Philadelphia"), 80);
        assertEquals(b.wins("New_York"), 78);
        assertEquals(b.wins("Montreal"), 77);

        assertEquals(b.losses("Atlanta"), 71);
        assertEquals(b.losses("Philadelphia"), 79);
        assertEquals(b.losses("New_York"), 78);
        assertEquals(b.losses("Montreal"), 82);

        assertEquals(b.remaining("Atlanta"), 8);
        assertEquals(b.remaining("Philadelphia"), 3);
        assertEquals(b.remaining("New_York"), 6);
        assertEquals(b.remaining("Montreal"), 3);

        assertEquals(b.against("Atlanta", "Atlanta"), 0);
        assertEquals(b.against("Atlanta", "Philadelphia"), 1);
        assertEquals(b.against("Atlanta", "New_York"), 6);
        assertEquals(b.against("Atlanta", "Montreal"), 1);
    }

    public void testIsEliminated_teams4() {
        BaseballElimination b = new BaseballElimination("testfiles/teams4.txt");

        assertFalse(b.isEliminated("Atlanta"));
        assertFalse(b.isEliminated("Philadelphia"));
        assertFalse(b.isEliminated("New_York"));
        assertTrue(b.isEliminated("Montreal"));
    }

    // a(R) = (w(R) + g(R)) / |R| should be greater than w(x) + r(x), where x is the team eliminated
    public void testCertificateOfElimination_teams4() {
        BaseballElimination b = new BaseballElimination("testfiles/teams4.txt");

        assertNull(b.certificationOfElimination("Atlanta"));
        assertNull(b.certificationOfElimination("Philadelphia"));
        assertNull(b.certificationOfElimination("New_York"));
        Iterable<String> certificates2 = b.certificationOfElimination("Montreal");

        int wins = 0;
        int games = 0;
        for (String team1 : certificates2) {
            wins += b.wins(team1);

            for (String team2 : certificates2) {
                games += b.against(team1, team2);
            }
        }


    }
}
