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

    @Test
    public void testIsEliminated_teams24() {
        BaseballElimination b = new BaseballElimination("testfiles/teams24.txt");

        assertTrue(b.isEliminated("Team13"));
    }

    @Test
    public void testIsEliminated_teams32() {
        BaseballElimination b = new BaseballElimination("testfiles/teams32.txt");

        assertTrue(b.isEliminated("Team25"));
        assertTrue(b.isEliminated("Team29"));
    }

    @Test
    public void testIsEliminated_teams36() {
        BaseballElimination b = new BaseballElimination("testfiles/teams36.txt");

        assertTrue(b.isEliminated("Team21"));
    }

    @Test
    public void testIsEliminated_teams42() {
        BaseballElimination b = new BaseballElimination("testfiles/teams42.txt");

        assertTrue(b.isEliminated("Team6"));
        assertTrue(b.isEliminated("Team15"));
        assertTrue(b.isEliminated("Team25"));
    }

    @Test
    public void testIsEliminated_teams48() {
        BaseballElimination b = new BaseballElimination("testfiles/teams48.txt");

        assertTrue(b.isEliminated("Team6"));
        assertTrue(b.isEliminated("Team23"));
        assertTrue(b.isEliminated("Team47"));
    }

    @Test
    public void testIsEliminated_teams54() {
        BaseballElimination b = new BaseballElimination("testfiles/teams54.txt");

        assertTrue(b.isEliminated("Team3"));
        assertTrue(b.isEliminated("Team29"));
        assertTrue(b.isEliminated("Team37"));
        assertTrue(b.isEliminated("Team50"));
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

        return (double) (wins + games) / Math.abs(RSize);
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

    @Test
    // a(R) = (w(R) + g(R)) / |R| should be greater than w(x) + r(x), where x is the team eliminated
    public void testCertificateOfElimination_teams5() {
        BaseballElimination b = new BaseballElimination("testfiles/teams5.txt");

        Iterable<String> certificates = b.certificationOfElimination("Detroit");
        assertTrue(a(b, certificates) > (b.wins("Detroit") + b.remaining("Detroit")));
    }

    @Test
    // a(R) = (w(R) + g(R)) / |R| should be greater than w(x) + r(x), where x is the team eliminated
    public void testCertificateOfElimination_teams7() {
        BaseballElimination b = new BaseballElimination("testfiles/teams7.txt");

        Iterable<String> certificates = b.certificationOfElimination("Ireland");
        assertTrue(a(b, certificates) > (b.wins("Ireland") + b.remaining("Ireland")));
    }

    @Test
    // a(R) = (w(R) + g(R)) / |R| should be greater than w(x) + r(x), where x is the team eliminated
    public void testCertificateOfElimination_teams24() {
        BaseballElimination b = new BaseballElimination("testfiles/teams24.txt");

        Iterable<String> certificates = b.certificationOfElimination("Team13");
        assertTrue(a(b, certificates) > (b.wins("Team13") + b.remaining("Team13")));
    }

    @Test
    // a(R) = (w(R) + g(R)) / |R| should be greater than w(x) + r(x), where x is the team eliminated
    public void testCertificateOfElimination_teams32() {
        BaseballElimination b = new BaseballElimination("testfiles/teams32.txt");

        Iterable<String> certificates = b.certificationOfElimination("Team25");
        assertTrue(a(b, certificates) > (b.wins("Team25") + b.remaining("Team25")));

        certificates = b.certificationOfElimination("Team29");
        assertTrue(a(b, certificates) > (b.wins("Team29") + b.remaining("Team29")));
    }

    @Test
    // a(R) = (w(R) + g(R)) / |R| should be greater than w(x) + r(x), where x is the team eliminated
    public void testCertificateOfElimination_teams36() {
        BaseballElimination b = new BaseballElimination("testfiles/teams36.txt");

        Iterable<String> certificates = b.certificationOfElimination("Team21");
        assertTrue(a(b, certificates) > (b.wins("Team21") + b.remaining("Team21")));
    }

    @Test
    // a(R) = (w(R) + g(R)) / |R| should be greater than w(x) + r(x), where x is the team eliminated
    public void testCertificateOfElimination_teams42() {
        BaseballElimination b = new BaseballElimination("testfiles/teams42.txt");

        Iterable<String> certificates = b.certificationOfElimination("Team6");
        assertTrue(a(b, certificates) > (b.wins("Team6") + b.remaining("Team6")));

        certificates = b.certificationOfElimination("Team15");
        assertTrue(a(b, certificates) > (b.wins("Team15") + b.remaining("Team15")));

        certificates = b.certificationOfElimination("Team25");
        assertTrue(a(b, certificates) > (b.wins("Team25") + b.remaining("Team25")));
    }

    @Test
    // a(R) = (w(R) + g(R)) / |R| should be greater than w(x) + r(x), where x is the team eliminated
    public void testCertificateOfElimination_teams48() {
        BaseballElimination b = new BaseballElimination("testfiles/teams48.txt");

        Iterable<String> certificates = b.certificationOfElimination("Team6");
        assertTrue(a(b, certificates) > (b.wins("Team6") + b.remaining("Team6")));

        certificates = b.certificationOfElimination("Team23");
        assertTrue(a(b, certificates) > (b.wins("Team23") + b.remaining("Team23")));

        certificates = b.certificationOfElimination("Team47");
        assertTrue(a(b, certificates) > (b.wins("Team47") + b.remaining("Team47")));
    }

    @Test
    // a(R) = (w(R) + g(R)) / |R| should be greater than w(x) + r(x), where x is the team eliminated
    public void testCertificateOfElimination_teams54() {
        BaseballElimination b = new BaseballElimination("testfiles/teams54.txt");

        Iterable<String> certificates = b.certificationOfElimination("Team3");
        assertTrue(a(b, certificates) > (b.wins("Team3") + b.remaining("Team3")));

        certificates = b.certificationOfElimination("Team29");
        assertTrue(a(b, certificates) > (b.wins("Team29") + b.remaining("Team29")));

        certificates = b.certificationOfElimination("Team37");
        assertTrue(a(b, certificates) > (b.wins("Team37") + b.remaining("Team37")));

        certificates = b.certificationOfElimination("Team50");
        assertTrue(a(b, certificates) > (b.wins("Team50") + b.remaining("Team50")));
    }

}
