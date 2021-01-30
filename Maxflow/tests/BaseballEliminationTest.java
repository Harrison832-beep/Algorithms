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
    public void testIsEliminated_teams4a() {
        BaseballElimination b = new BaseballElimination("testfiles/teams4a.txt");

        assertFalse(b.isEliminated("CIA"));
        assertTrue(b.isEliminated("Ghaddafi"));
        assertTrue(b.isEliminated("Bin_Ladin"));
        assertFalse(b.isEliminated("Obama"));
    }

    @Test
    public void testIsEliminated_teams5() {
        BaseballElimination b = new BaseballElimination("testfiles/teams5.txt");

        assertFalse(b.isEliminated("New_York"));
        assertFalse(b.isEliminated("Baltimore"));
        assertFalse(b.isEliminated("Boston"));
        assertFalse(b.isEliminated("Toronto"));
        assertTrue(b.isEliminated("Detroit"));
    }

    @Test
    public void testIsEliminated_teams7() {
        BaseballElimination b = new BaseballElimination("testfiles/teams7.txt");

        assertFalse(b.isEliminated("U.S.A."));
        assertFalse(b.isEliminated("England"));
        assertFalse(b.isEliminated("France"));
        assertFalse(b.isEliminated("Germany"));
        assertTrue(b.isEliminated("Ireland"));
        assertFalse(b.isEliminated("Belgium"));
        assertTrue(b.isEliminated("China"));
    }

    private double a(BaseballElimination b, Iterable<String> R) {
        int wins = 0;
        int games = 0;
        int RSize = 0;
        for (String team1 : R) {
            wins += b.wins(team1);
            RSize++;
            for (String team2 : R) {
                games += b.against(team1, team2);
            }
        }

        return (wins + games) / Math.abs(RSize);
    }

    private String peek(Iterable<String> R) {
        for (String team : R)
            return team;
        return null;
    }

    @Test
    // a(R) = (w(R) + g(R)) / |R| should be greater than w(x) + r(x), where x is the team eliminated
    public void testCertificateOfElimination_teams4() {
        BaseballElimination b = new BaseballElimination("testfiles/teams4.txt");

        assertNull(b.certificationOfElimination("Atlanta"));

        Iterable<String> certificates1 = b.certificationOfElimination("Philadelphia");
        assertTrue(a(b, certificates1) > (b.wins("Philadelphia") + b.remaining("Philadelphia")));

        assertNull(b.certificationOfElimination("New_York"));

        Iterable<String> certificates2 = b.certificationOfElimination("Montreal");
        String firstItem = peek(certificates2);
        assertEquals(firstItem, "Atlanta");
    }


}
