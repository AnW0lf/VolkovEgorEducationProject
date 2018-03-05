package Project_1;

import java.util.LinkedList;

public class PairSequence {
    private Node<Pair<Double, Double>> head;
    private Node<Pair<Double, Double>> tail;

    public PairSequence() {
    }

    public PairSequence(double x, double y) {
        head = new Node(new Pair(x, y));
        tail = head;
    }

    public PairSequence(Pair<Double, Double> pair) {
        head = new Node(pair);
        tail = head;
    }

    /**
     * head (first element of sequencer) getter
     * @return link to head
     */
    public Node<Pair<Double, Double>> getHead() {
        return head;
    }

    /**
     * tail (last element of sequencer) getter
     * @return link to tail
     */
    public Node<Pair<Double, Double>> getTail() {
        return tail;
    }

    /**
     * add pair to the beginning of the sequence
     * @param x first element of pair
     * @param y second element of pair
     */
    public void add(double x, double y) {
        head = new Node(new Pair(x, y), head);
        if (tail == null) tail = head;
    }

    /**
     * add pair to the beginning of the sequence
     * @param pair added element
     */
    public void add(Pair<Double, Double> pair) {
        head = new Node(pair, head);
        if (tail == null) tail = head;
    }

    /**
     * add pair to the end of the sequence
     * @param x first element of pair
     * @param y second element of pair
     */
    public void append(double x, double y) {
        if (tail == null) {
            tail = new Node(new Pair(x, y));
        } else {
            tail.setLink(new Node(new Pair(x, y)));
            tail = tail.getLink();
        }
        if (head == null) head = tail;
    }

    /**
     * add pair to the end of the sequence
     * @param pair added element
     */
    public void append(Pair<Double, Double> pair) {
        if (tail == null) {
            tail = new Node(pair);
        } else {
            tail.setLink(new Node(pair));
            tail = tail.getLink();
        }
        if (head == null) head = tail;
    }

    /**
     * remove pair (if it contains) by first element of pair
     * @param x first element of pair
     */
    public void remove(double x) {
        if (head == null) return;
        if (head == tail) {
            head = null;
            tail = null;
            return;
        }
        if (head.getItem().getFirst() == x) {
            head = head.getLink();
            return;
        }
        Node<Pair<Double, Double>> node = head;
        while (node.getLink() != null) {
            Pair<Double, Double> pair = (Pair<Double, Double>) node.getLink().getItem();
            if (pair.getFirst() == x) {
                if (tail == node.getLink())
                    tail = node;
                node.setLink(node.getLink().getLink());
                return;
            }
            node = node.getLink();
        }
    }

    /**
     * return pair by first element of pair
     * @param x first element of pair
     * @return searched pair or null (if it not contains)
     */
    public Pair<Double, Double> search(double x) {
        Node<Pair<Double, Double>> node = head;
        while (node != null) {
            if (node.getItem().getFirst() == x) {
                return node.getItem();
            }
            node = node.getLink();
        }
        return null;
    }

    /**
     * return pair by first element of pair
     * @param x first element of pair
     * @param def default pair
     * @return searched pair or default pair (if searched not contains)
     */
    public Pair<Double, Double> searchOrDefault(double x, Pair<Double, Double> def) {
        Pair<Double, Double> result = def;
        Node<Pair<Double, Double>> node = head;
        while (node != null) {
            if (node.getItem().getFirst() == x) {
                return node.getItem();
            }
            node = node.getLink();
        }
        return result;
    }

    /**
     * search closed pair by first element of pair
     * @param x first element of pair
     * @return closed pair or null (if sequence is empty)
     */
    public Pair<Double, Double> searchClosed(double x) {
        if (head == null)
            return null;
        if (x >= max().getFirst()) return max();
        if (x <= min().getFirst()) return min();
        double distance = Math.abs(max().getFirst() - min().getFirst());
        Node<Pair<Double, Double>> node = head;
        Pair<Double, Double> result = node.getItem();
        while (node != null) {
            if (Math.abs(node.getItem().getFirst() - x) < distance) {
                distance = Math.abs(node.getItem().getFirst() - x);
                result = node.getItem();
            }
            node = node.getLink();
        }
        return result;
    }

    /**
     * @return pair with min first element or null (if sequence is empty)
     */
    public Pair<Double, Double> min() {
        if (head == null) return null;
        Node<Pair<Double, Double>> node = head;
        double min = node.getItem().getFirst();
        while (node != null) {
            if (node.getItem().getFirst() < min)
                min = node.getItem().getFirst();
            node = node.getLink();
        }
        return search(min);
    }

    /**
     * @return pair with max first element or null (if sequence is empty)
     */
    public Pair<Double, Double> max() {
        if (head == null) return null;
        Node<Pair<Double, Double>> node = head;
        double max = node.getItem().getFirst();
        while (node != null) {
            if (node.getItem().getFirst() > max)
                max = node.getItem().getFirst();
            node = node.getLink();
        }
        return search(max);
    }

    /**
     * interpolate the function by the Lagrange method
     * @param x first element of searched pair
     * @return approximate value of the function
     */
    public double interpolate(double x) {
        double result = 0;
        LinkedList<Pair<Double, Double>> list = toLinkedList();
        int size = list.size();

        for (int i = 0; i < size; i++) {
            double basicsPolynom = 1;
            for (int j = 0; j < size; j++) {
                if (j != i) {
                    basicsPolynom *= (x - list.get(j).getFirst()) / (list.get(i).getFirst() - list.get(j).getFirst());
                }
            }
            result += basicsPolynom * list.get(i).getSecond();
        }
        return result;
    }

    /**
     * @return sequence as LinkedList
     */
    public LinkedList<Pair<Double, Double>> toLinkedList() {
        LinkedList<Pair<Double, Double>> list = new LinkedList<>();
        Node<Pair<Double, Double>> node = head;
        while (node != null) {
            list.add(node.getItem());
            node = node.getLink();
        }
        return list;
    }

    @Override
    public String toString() {
        return toLinkedList().toString();
    }
}
