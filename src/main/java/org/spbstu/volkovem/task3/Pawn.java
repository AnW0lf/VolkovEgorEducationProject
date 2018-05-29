package org.spbstu.volkovem.task3;

import javafx.util.Pair;

import java.util.LinkedList;

public class Pawn extends Piece {
    private static final String SYMBOL_WHITE = "♙";
    private static final String SYMBOL_BLACK = "♟";

    Pawn(Pair<Integer, Integer> coordinate, Color color) {
        super(color == Color.WHITE ? SYMBOL_WHITE : SYMBOL_BLACK, coordinate, color);
    }

    @Override
    public LinkedList<Pair<Integer, Integer>> movablePoints(Field field) {
        LinkedList<Pair<Integer, Integer>> points = new LinkedList<>();
        Pair<Integer, Integer> movePoint;
        Pair<Integer, Integer> firstMovePoint;
        Pair<Integer, Integer> capturePointRight;
        Pair<Integer, Integer> capturePointLeft;
        switch (color) {
            case WHITE:
                movePoint = new Pair<>(coordinate.getKey() + 1, coordinate.getValue());
                firstMovePoint = new Pair<>(coordinate.getKey() + 2, coordinate.getValue());
                capturePointRight = new Pair<>(coordinate.getKey() + 1, coordinate.getValue() + 1);
                capturePointLeft = new Pair<>(coordinate.getKey() + 1, coordinate.getValue() - 1);
                if (Field.inRange(movePoint) && field.getSymbol(movePoint).isEmpty())
                {
                    points.add(movePoint);
                    if (move == 0 && Field.inRange(firstMovePoint) && field.getSymbol(firstMovePoint).isEmpty())
                        points.add(firstMovePoint);
                }
                if (Field.inRange(capturePointRight)
                        && !field.getSymbol(capturePointRight).isEmpty()
                        && !field.getColor(capturePointRight, color).equals(color))
                    points.add(capturePointRight);
                if (Field.inRange(capturePointLeft)
                        && !field.getSymbol(capturePointLeft).isEmpty()
                        && !field.getColor(capturePointLeft, color).equals(color))
                    points.add(capturePointLeft);
                break;
            case BLACK:
                movePoint = new Pair<>(coordinate.getKey() - 1, coordinate.getValue());
                firstMovePoint = new Pair<>(coordinate.getKey() - 2, coordinate.getValue());
                capturePointRight = new Pair<>(coordinate.getKey() - 1, coordinate.getValue() + 1);
                capturePointLeft = new Pair<>(coordinate.getKey() - 1, coordinate.getValue() - 1);
                if (Field.inRange(movePoint) && field.getSymbol(movePoint).isEmpty())
                {
                    points.add(movePoint);
                    if (move == 0 && Field.inRange(firstMovePoint) && field.getSymbol(firstMovePoint).isEmpty())
                        points.add(firstMovePoint);
                }
                if (Field.inRange(capturePointRight)
                        && !field.getSymbol(capturePointRight).isEmpty()
                        && !field.getColor(capturePointRight, color).equals(color))
                    points.add(capturePointRight);
                if (Field.inRange(capturePointLeft)
                        && !field.getSymbol(capturePointLeft).isEmpty()
                        && !field.getColor(capturePointLeft, color).equals(color))
                    points.add(capturePointLeft);
                break;
            default:
                break;
        }
        return points;
    }

    @Override
    public boolean isMovable(Field field) {
        return !movablePoints(field).isEmpty();
    }
}
