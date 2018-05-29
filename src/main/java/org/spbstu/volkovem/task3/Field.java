package org.spbstu.volkovem.task3;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;

public class Field {
    private static final int SIZE = 8;
    private Piece[][] field;
    private Game game;
    private Pair<Integer, Integer> selectedCoordinate = null;

    public Field(Game game) {
        this.game = game;
        field = new Piece[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = new Piece("", new Pair<>(i, j), Piece.Color.WHITE);
            }
        }

        for (int i = 0; i < SIZE; i++) {
            field[1][i] = new Pawn(new Pair<>(1, i), Piece.Color.WHITE);
            field[6][i] = new Pawn(new Pair<>(6, i), Piece.Color.BLACK);
        }
        field[0][0] = new Rook(new Pair<>(0, 0), Piece.Color.WHITE);
        field[0][1] = new Knight(new Pair<>(0, 1), Piece.Color.WHITE);
        field[0][2] = new Bishop(new Pair<>(0, 2), Piece.Color.WHITE);
        field[0][3] = new Queen(new Pair<>(0, 3), Piece.Color.WHITE);
        field[0][4] = new King(new Pair<>(0, 4), Piece.Color.WHITE);
        field[0][5] = new Bishop(new Pair<>(0, 5), Piece.Color.WHITE);
        field[0][6] = new Knight(new Pair<>(0, 6), Piece.Color.WHITE);
        field[0][7] = new Rook(new Pair<>(0, 7), Piece.Color.WHITE);
        field[7][0] = new Rook(new Pair<>(7, 0), Piece.Color.BLACK);
        field[7][1] = new Knight(new Pair<>(7, 1), Piece.Color.BLACK);
        field[7][2] = new Bishop(new Pair<>(7, 2), Piece.Color.BLACK);
        field[7][3] = new King(new Pair<>(7, 3), Piece.Color.BLACK);
        field[7][4] = new Queen(new Pair<>(7, 4), Piece.Color.BLACK);
        field[7][5] = new Bishop(new Pair<>(7, 5), Piece.Color.BLACK);
        field[7][6] = new Knight(new Pair<>(7, 6), Piece.Color.BLACK);
        field[7][7] = new Rook(new Pair<>(7, 7), Piece.Color.BLACK);
    }

    private void moveFromTo(Pair<Integer, Integer> oldCoordinate, Pair<Integer, Integer> newCoordinate) {
        field[newCoordinate.getKey()][newCoordinate.getValue()] =
                field[oldCoordinate.getKey()][oldCoordinate.getValue()];
        field[newCoordinate.getKey()][newCoordinate.getValue()].setCoordinate(newCoordinate);
        field[oldCoordinate.getKey()][oldCoordinate.getValue()] = null;
        if (field[newCoordinate.getKey()][newCoordinate.getValue()].getClass().equals(Pawn.class)
                && (newCoordinate.getKey() == 0 || newCoordinate.getKey() == SIZE - 1))
            field[newCoordinate.getKey()][newCoordinate.getValue()] = new Queen(
                    field[newCoordinate.getKey()][newCoordinate.getValue()].coordinate,
                    field[newCoordinate.getKey()][newCoordinate.getValue()].color);
    }

    public String getSymbol(Pair<Integer, Integer> coordinate) {
        return (inRange(coordinate) && field[coordinate.getKey()][coordinate.getValue()] != null) ?
                field[coordinate.getKey()][coordinate.getValue()].toString() : "";
    }

    public Piece.Color getColor(Pair<Integer, Integer> coordinate, Piece.Color color) {
        return (inRange(coordinate) && field[coordinate.getKey()][coordinate.getValue()] != null) ?
                field[coordinate.getKey()][coordinate.getValue()].color : color;
    }

    public boolean checkCellMovable(Pair<Integer, Integer> coordinate) {
        return field[coordinate.getKey()][coordinate.getValue()].isMovable(this);
    }

    public LinkedList<Pair<Integer, Integer>> getMovableCells(Pair<Integer, Integer> coordinate) {
        return field[coordinate.getKey()][coordinate.getValue()].movablePoints(this);
    }

    public void selection(Pair<Integer, Integer> coordinate) {
        if (selectedCoordinate == null) {
            selectedCoordinate = coordinate;
            LinkedList<Pair<Integer, Integer>> cells = getMovableCells(selectedCoordinate);
            cells.add(selectedCoordinate);
            game.updateField(cells);
        } else if (selectedCoordinate.equals(coordinate)) {
            selectedCoordinate = null;
            game.updateField();
        } else {
            moveFromTo(selectedCoordinate, coordinate);
            selectedCoordinate = null;
            game.updateField();
        }
    }

    public static boolean inRange(Pair<Integer, Integer> coordinate) {
        return coordinate.getKey() >= 0 && coordinate.getKey() < SIZE
                && coordinate.getValue() >= 0 && coordinate.getValue() < SIZE;
    }
}
