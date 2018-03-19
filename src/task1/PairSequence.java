package task1;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class PairSequence {
    private Map<Double, Double> map;

    public PairSequence() {
        map = new TreeMap();
    }

    /**
     * add pair to the beginning of the sequence
     *
     * @param x first element of pair
     * @param y second element of pair
     */
    public void add(double x, double y) {
        if (!map.containsKey(x))
            map.put(x, y);
    }

    /**
     * remove pair (if it contains) by first element of pair
     *
     * @param x first element of pair
     */
    public void remove(double x) {
        if (map.containsKey(x))
            map.remove(x);
    }

    /**
     * return pair by first element of pair
     *
     * @param x first element of pair
     * @return searched pair or null (if it not contains)
     */
    public Double search(double x) {
        if (map.containsKey(x))
            return map.get(x);
        return null;
    }

    /**
     * return pair by first element of pair
     *
     * @param x   first element of pair
     * @param def default pair
     * @return searched pair or default pair (if searched not contains)
     */
    public Double searchOrDefault(double x, double def) {
        if (map.containsKey(x))
            return map.get(x);
        return def;
    }

    /**
     * search closed pair by first element of pair
     *
     * @param x first element of pair
     * @return closed pair or null (if sequence is empty)
     */
    public Double searchClosed(double x) {
        if (x >= max()) return max();
        if (x <= min()) return min();
        double distance = Math.abs(max() - min());
        double result = (Double) map.keySet().toArray()[0];
        for (Double key : map.keySet()) {
            if (Math.abs(key - x) < distance) {
                distance = Math.abs(key - x);
                result = map.get(key);
            }
        }
        return result;
    }

    /**
     * @return pair with min first element or null (if sequence is empty)
     */
    public Double min() {
        if (map.size() == 0) return null;
        double min = (Double) map.keySet().toArray()[0];
        for (Double key : map.keySet()) {
            if (min > key)
                min = key;
        }
        return min;
    }

    /**
     * @return pair with max first element or null (if sequence is empty)
     */
    public Double max() {
        if (map.size() == 0) return null;
        double max = (Double) map.keySet().toArray()[0];
        for (Double key : map.keySet()) {
            if (max > key)
                max = key;
        }
        return max;
    }

    /**
     * interpolate the function by the Lagrange method
     *
     * @param x first element of searched pair
     * @return approximate value of the function
     */
    public double interpolate(double x) {
        double result = 0;
        LinkedList<Pair<Double, Double>> list = toLinkedList();
        int size = list.size();

        for (int i = 0; i < size; i++) {
            double basicsPolynom = 1.0;
            for (int j = 0; j < size; j++) {
                if (j != i) {
                    basicsPolynom *= (x - list.get(j).getKey()) / (list.get(i).getKey() - list.get(j).getKey());
                }
            }
            result += basicsPolynom * list.get(i).getValue();
        }
        return result;
    }

    /**
     * @return sequence as LinkedList
     */
    public LinkedList<Pair<Double, Double>> toLinkedList() {
        LinkedList<Pair<Double, Double>> list = new LinkedList();
        for (Double key : map.keySet())
            list.add(new Pair(key, map.get(key)));
        return list;
    }

    @Override
    public String toString() {
        return toLinkedList().toString();
    }
}
