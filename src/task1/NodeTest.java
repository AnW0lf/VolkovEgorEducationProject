package task1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NodeTest {
    private Node node;

    @Test
    public void createNullLink() {
        node = new Node(0);
        assertEquals(null, node.getLink());
        assertTrue(node.getLink() == null);
    }

    @Test
    public void createNotNullLink() {
        node = new Node(0, new Node(1));
        assertTrue(node.getLink() != null);
    }

    @Test
    public void getItem() {
        node = new Node(0);
        assertTrue((Integer)node.getItem() == 0);
    }

    @Test
    public void itemToString() {
        String str = "qwe";
        node = new Node(str);
        assertEquals(str, node.toString());
    }

}
