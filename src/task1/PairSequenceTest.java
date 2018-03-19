package task1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PairSequenceTest {
    private PairSequence ps;

    @Test
    public void createEmpty() {
        ps = new PairSequence();
        assertEquals("[]", ps.toString());
    }

    @Test
    public void addCoordinate() {
        double x1 = 1.0;
        double y1 = 2.0;
        double x2 = 3.0;
        double y2 = 4.0;
        ps = new PairSequence();
        ps.add(x1, y1);
        ps.add(x2, y2);
        assertEquals("[3.0=4.0, 1.0=2.0]", ps.toString());
    }

    @Test
    public void removeEmpty() {
        ps = new PairSequence();
        ps.remove(1.0);
        assertEquals("[]", ps.toString());
    }

    @Test
    public void removeOne() {
        double x = 1.0;
        double y = 2.0;
        ps = new PairSequence();
        ps.add(x, y);
        ps.remove(1.0);
        assertEquals("[]", ps.toString());
    }

    @Test
    public void remove() {
        double x1 = 1.0;
        double y1 = 2.0;
        double x2 = 3.0;
        double y2 = 4.0;
        ps = new PairSequence();
        ps.add(x1, y1);
        ps.add(x2, y2);
        ps.remove(1.0);
        assertEquals("[2.0=3.0]", ps.toString());
    }

    @Test
    public void searchNotExists() {
        double x1 = 1.0;
        double y1 = 2.0;
        double x2 = 3.0;
        double y2 = 4.0;
        ps = new PairSequence();
        ps.add(x1, y1);
        ps.add(x2, y2);
        assertEquals(null, ps.search(10.0));
    }

    @Test
    public void searchExists() {
        double x1 = 1.0;
        double y1 = 2.0;
        double x2 = 3.0;
        double y2 = 4.0;
        ps = new PairSequence();
        ps.add(x1, y1);
        ps.add(x2, y2);
        assertTrue(ps.search(x2) == y2);
    }

    @Test
    public void searchOrDefaultNotExists() {
        double x1 = 1.0;
        double y1 = 2.0;
        double x2 = 3.0;
        double y2 = 4.0;
        double def = 0.0;
        ps = new PairSequence();
        ps.add(x1, y1);
        ps.add(x2, y2);
        assertTrue(ps.searchOrDefault(10.0, def) == def);
    }

    @Test
    public void searchOrDefaultExists() {
        double x1 = 1.0;
        double y1 = 2.0;
        double x2 = 3.0;
        double y2 = 4.0;
        ps = new PairSequence();
        ps.add(x1, y1);
        ps.add(x2, y2);
        assertTrue(ps.searchOrDefault(x1, 0.0) == y1);
    }

    @Test
    public void searchClosedNotExists() {
        double x1 = 1.0;
        double y1 = 2.0;
        double x2 = 3.0;
        double y2 = 4.0;
        ps = new PairSequence();
        ps.add(x1, y1);
        ps.add(x2, y2);
        assertTrue(ps.searchClosed(10.0) == y2);
    }

    @Test
    public void searchClosedExists() {
        double x1 = 1.0;
        double y1 = 2.0;
        double x2 = 3.0;
        double y2 = 4.0;
        ps = new PairSequence();
        ps.add(x1, y1);
        ps.add(x2, y2);
        assertTrue(ps.searchClosed(x1) == y1);
    }

    @Test
    public void interpolateNotExists() {
        double x1 = 1.0;
        double y1 = 2.0;
        double x2 = 2.0;
        double y2 = 3.0;
        double x3 = 3.0;
        double y3 = 4.0;
        ps = new PairSequence();
        ps.add(x1, y1);
        ps.add(x2, y2);
        ps.add(x3, y3);
        assertTrue(ps.interpolate(10.0) == 11.0);
    }

    @Test
    public void interpolateExists() {
        double x1 = 1.0;
        double y1 = 2.0;
        double x2 = 2.0;
        double y2 = 3.0;
        double x3 = 3.0;
        double y3 = 4.0;
        ps = new PairSequence();
        ps.add(x1, y1);
        ps.add(x2, y2);
        ps.add(x3, y3);
        assertTrue(ps.searchClosed(x2) == y2);
    }

}
