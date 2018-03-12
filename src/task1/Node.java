package task1;

public class Node<I> {
    private I item;
    private Node link;

    public Node(I item) {
        this.item = item;
        link = null;
    }

    public Node(I item, Node link) {
        this.item = item;
        this.link = link;
    }

    public I getItem() {
        return item;
    }

    public Node getLink() {
        return link;
    }

    public void setLink(Node link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return item.toString();
    }
}
