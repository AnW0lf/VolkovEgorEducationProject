package org.spbstu.volkovem.task1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PairSequenceTest {
    private PairSequence ps;

    @Test
    void createEmpty() {
        ps = new PairSequence();
        assertEquals("", ps.toString());
    }

    @Test
    void addCoordinate() {
        double x1 = 1.0;
        double y1 = 2.0;
        double x2 = 3.0;
        double y2 = 4.0;
        ps = new PairSequence();
        ps.add(x1, y1);
        ps.add(x2, y2);
        assertEquals("(1,000000, 2,000000)\n(3,000000, 4,000000)\n", ps.toString());
    }

    @Test
    void removeEmpty() {
        ps = new PairSequence();
        ps.remove(1.0);
        assertEquals("", ps.toString());
    }

    @Test
    void removeOne() {
        double x = 1.0;
        double y = 2.0;
        ps = new PairSequence();
        ps.add(x, y);
        ps.remove(1.0);
        assertEquals("", ps.toString());
    }

    @Test
    void remove() {
        double x1 = 1.0;
        double y1 = 2.0;
        double x2 = 3.0;
        double y2 = 4.0;
        ps = new PairSequence();
        ps.add(x1, y1);
        ps.add(x2, y2);
        ps.remove(1.0);
        assertEquals("(3,000000, 4,000000)\n", ps.toString());
    }

    @Test
    void searchNotExists() {
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
    void searchExists() {
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
    void searchOrDefaultNotExists() {
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
    void searchOrDefaultExists() {
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
    void searchClosedEmpty() {
        double x = 1.0;
        ps = new PairSequence();
        assertEquals(0d, (double) ps.searchClosed(x));
    }

    @Test
    void searchClosedNotExists() {
        double x1 = 1.0;
        double y1 = 2.0;
        double x2 = 3.0;
        double y2 = 4.0;
        ps = new PairSequence();
        ps.add(x1, y1);
        ps.add(x2, y2);
        assertEquals((Double) y2, ps.searchClosed(10));
    }

    @Test
    void searchClosedExists() {
        double x1 = 1.0;
        double y1 = 2.0;
        double x2 = 3.0;
        double y2 = 4.0;
        ps = new PairSequence();
        ps.add(x1, y1);
        ps.add(x2, y2);
        assertEquals((Double) y1, ps.searchClosed(x1));
    }

    @Test
    void interpolateNotExists() {
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
    void interpolateExists() {
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
