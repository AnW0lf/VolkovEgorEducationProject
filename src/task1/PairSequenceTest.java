package task1;

import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PairSequenceTest {
    private PairSequence ps;

    @Test
    public void createEmpty() {
        ps = new PairSequence();
        assertEquals(null, ps.getHead());
        assertEquals(null, ps.getTail());
    }

    @Test
    public void createByCoordinate() {
        double x = 1.0;
        double y = 2.0;
        Pair pair = new Pair(x, y);
        ps = new PairSequence(x, y);
        assertEquals(pair, ps.getHead().getItem());
        assertEquals(pair, ps.getTail().getItem());
    }

    @Test
    public void createByPoint() {
        double x = 1.0;
        double y = 2.0;
        Pair pair = new Pair(x, y);
        ps = new PairSequence(pair);
        assertEquals(pair, ps.getHead().getItem());
        assertEquals(pair, ps.getTail().getItem());
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
    public void addPoint() {
        double x1 = 1.0;
        double y1 = 2.0;
        double x2 = 3.0;
        double y2 = 4.0;
        Pair pair1 = new Pair(x1, y1);
        Pair pair2 = new Pair(x2, y2);
        ps = new PairSequence();
        ps.add(pair1);
        ps.add(pair2);
        assertEquals("[3.0=4.0, 1.0=2.0]", ps.toString());
    }

    @Test
    public void appendCoordinate() {
        double x1 = 1.0;
        double y1 = 2.0;
        double x2 = 3.0;
        double y2 = 4.0;
        ps = new PairSequence();
        ps.append(x1, y1);
        ps.append(x2, y2);
        assertEquals("[1.0=2.0, 3.0=4.0]", ps.toString());
    }

    @Test
    public void appendPoint() {
        double x1 = 1.0;
        double y1 = 2.0;
        double x2 = 3.0;
        double y2 = 4.0;
        Pair pair1 = new Pair(x1, y1);
        Pair pair2 = new Pair(x2, y2);
        ps = new PairSequence();
        ps.append(pair1);
        ps.append(pair2);
        assertEquals("[1.0=2.0, 3.0=4.0]", ps.toString());
    }

    @Test
    public void removeEmpty() {
        ps = new PairSequence();
        ps.remove(1.0);
        assertEquals("[]", ps.toString());
    }

    @Test
    public void removeOne() {
        ps = new PairSequence(1.0, 2.0);
        ps.remove(1.0);
        assertEquals("[]", ps.toString());
    }

    @Test
    public void remove() {
        ps = new PairSequence(1.0, 2.0);
        ps.add(2.0, 3.0);
        ps.remove(1.0);
        assertEquals("[2.0=3.0]", ps.toString());
    }

    @Test
    public void searchNotExists() {
        ps = new PairSequence(1.0, 2.0);
        ps.add(2.0, 3.0);
        assertEquals(null, ps.search(10.0));
    }

    @Test
    public void searchExists() {
        ps = new PairSequence(1.0, 2.0);
        ps.add(2.0, 3.0);
        assertEquals("2.0=3.0", ps.search(2.0).toString());
    }

    @Test
    public void searchOrDefaultNotExists() {
        ps = new PairSequence(1.0, 2.0);
        ps.add(2.0, 3.0);
        assertEquals("0.0=0.0", ps.searchOrDefault(10.0, new Pair(0.0, 0.0)).toString());
    }

    @Test
    public void searchOrDefaultExists() {
        ps = new PairSequence(1.0, 2.0);
        ps.add(2.0, 3.0);
        assertEquals("1.0=2.0", ps.searchOrDefault(1.0, new Pair(0.0, 0.0)).toString());
    }

    @Test
    public void searchClosedNotExists() {
        ps = new PairSequence(1.0, 2.0);
        ps.add(2.0, 3.0);
        assertEquals("2.0=3.0", ps.searchClosed(10.0).toString());
    }

    @Test
    public void searchClosedExists() {
        ps = new PairSequence(1.0, 2.0);
        ps.add(2.0, 3.0);
        assertEquals("1.0=2.0", ps.searchClosed(1.0).toString());
    }

    @Test
    public void interpolateNotExists() {
        ps = new PairSequence(1.0, 2.0);
        ps.add(2.0, 3.0);
        ps.add(3.0, 4.0); // прямая
        assertEquals(5.0, ps.interpolate(4.0));
    }

    @Test
    public void interpolateExists() {
        ps = new PairSequence(1.0, 2.0);
        ps.add(2.0, 3.0);
        ps.add(3.0, 4.0); // прямая
        assertEquals(4.0, ps.interpolate(3.0));
    }

}
