package org.spbstu.volkovem.task3;

import javafx.util.Pair;

import java.util.LinkedList;

public class King extends Piece {
    public static final String SYMBOL_WHITE = "♔";
    public static final String SYMBOL_BLACK = "♚";

    public King(Pair<Integer, Integer> coordinate, Color color) {
        super(color == Color.WHITE ? SYMBOL_WHITE : SYMBOL_BLACK, coordinate, color);
    }

    @Override
    public LinkedList<Pair<Integer, Integer>> movablePoints(Field field) {
        return new LinkedList<>();
    }
}
