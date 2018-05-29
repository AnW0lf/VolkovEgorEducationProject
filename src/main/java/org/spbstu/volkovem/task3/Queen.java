package org.spbstu.volkovem.task3;

import javafx.util.Pair;

import java.util.LinkedList;

public class Queen extends Piece {
    private static final String SYMBOL_WHITE = "♕";
    private static final String SYMBOL_BLACK = "♛";

    public Queen(Pair<Integer, Integer> coordinate, Color color) {
        super(color == Color.WHITE ? SYMBOL_WHITE : SYMBOL_BLACK, coordinate, color);
    }

    @Override
    public LinkedList<Pair<Integer, Integer>> movablePoints(Field field) {
        LinkedList<Pair<Integer, Integer>> points = new LinkedList<>();
        points.addAll(castRay(field, 1, 0));
        points.addAll(castRay(field, 0, 1));
        points.addAll(castRay(field, -1, 0));
        points.addAll(castRay(field, 0, -1));
        points.addAll(castRay(field, 1, 1));
        points.addAll(castRay(field, -1, 1));
        points.addAll(castRay(field, 1, -1));
        points.addAll(castRay(field, -1, -1));
        return points;
    }

    @Override
    public boolean isMovable(Field field) {
        return !movablePoints(field).isEmpty();
    }

    private LinkedList<Pair<Integer, Integer>> castRay(Field field, int vertical, int horizontal) {
        LinkedList<Pair<Integer, Integer>> points = new LinkedList<>();
        Pair<Integer, Integer> point = new Pair<>(coordinate.getKey() + vertical, coordinate.getValue() + horizontal);
        while (Field.inRange(point)) {
            if (field.getSymbol(point).isEmpty())
                points.add(point);
            else {
                if (!field.getColor(point, color).equals(color))
                    points.add(point);
                break;
            }
            point = new Pair<>(point.getKey() + vertical, point.getValue() + horizontal);
        }
        return points;
    }
}
