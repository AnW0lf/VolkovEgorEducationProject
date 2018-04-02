package task1;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class PairSequence {
    private TreeMap<Double, Double> map;

    /**
     * Empty constructor
     */
    public PairSequence() {
        map = new TreeMap<>();
    }

    /**
     * Add coordinates to the sequence
     *
     * @param x first coordinate
     * @param y second coordinate
     */
    public void add(double x, double y) {
        if (!map.containsKey(x))
            map.put(x, y);
    }

    /**
     * remove coordinates (if it contains) by x coordinate
     *
     * @param x first coordinate
     */
    public void remove(double x) {
        if (map.containsKey(x))
            map.remove(x);
    }

    /**
     * return y coordinate by x coordinate
     *
     * @param x first coordinate
     * @return searched pair or null (if it not contains)
     */
    public Double search(double x) {
        if (map.containsKey(x))
            return map.get(x);
        return null;
    }

    /**
     * return y coordinate by x coordinate or default value (def)
     *
     * @param x   first coordinate
     * @param def default value
     * @return searched y coordinate or default value (def) (if searched not contains)
     */
    public Double searchOrDefault(double x, double def) {
        if (map.containsKey(x))
            return map.get(x);
        return def;
    }

    /**
     * search closed y coordinate by x coordinate
     *
     * @param x first coordinate
     * @return closed y coordinate or null (if sequence is empty)
     */
    public Double searchClosed(double x) {
        if (x >= map.lastKey()) return map.lastEntry().getValue();
        if (x <= map.firstKey()) return map.firstEntry().getValue();
        double distance = Math.abs(map.lastKey() - map.firstKey());
        double result = (Double) map.values().toArray()[0];
        for (Double key : map.keySet()) {
            if (Math.abs(key - x) < distance) {
                distance = Math.abs(key - x);
                result = map.get(key);
            }
        }
        return result;
    }

    /**
     * interpolate the function by the Lagrange method
     *
     * @param x first coordinate of searched ones
     * @return approximate value of the function
     */
    public double interpolate(double x) {
        double result = 0;

        for (Map.Entry<Double, Double> iEntry : map.entrySet()) {
            double basicsPolynomial = 1.0;
            for (Map.Entry<Double, Double> jEntry : map.entrySet()) {
                if (!Objects.equals(jEntry.getKey(), iEntry.getKey())){
                    basicsPolynomial *= (x - jEntry.getKey()) / (iEntry.getKey() - jEntry.getKey());
                }
            }
            result += basicsPolynomial * iEntry.getValue();
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Double,Double> entry: map.entrySet()) {
            sb.append(String.format("(%f, %f)\n", entry.getKey(), entry.getValue()));
        }
        return sb.toString();
    }
}
