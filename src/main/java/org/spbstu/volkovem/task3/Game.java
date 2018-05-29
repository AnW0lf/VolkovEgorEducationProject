package org.spbstu.volkovem.task3;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

class Game extends JFrame {
    private static final int SIZE = 8;
    private static final String[] ALPHABET = {"A", "B", "C", "D", "E", "F", "G", "H"};
    private Field field;
    private JButton[][] buttons;
    private Piece.Color stage = Piece.Color.WHITE;

    Game() {
        super("Chess");
    }

    void start() {
        createGameField();
    }

    private void createGameField() {
        field = new Field(this);
        stage = Piece.Color.WHITE;
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 9, 0, 0));
        buttons = new JButton[SIZE][SIZE];

        for (int i = 0; i <= SIZE; i++) {
            if (i == 0) {
                panel.add(new JLabel());
                continue;
            }
            panel.add(new JLabel(String.valueOf(i), JLabel.CENTER));
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j <= SIZE; j++) {
                if (j == 0) {
                    panel.add(new JLabel(ALPHABET[i], JLabel.CENTER));
                    continue;
                }
                buttons[i][j - 1] = new JButton();
                buttons[i][j - 1].addActionListener(new ActionSelect(new Pair<>(i, j - 1)));
                buttons[i][j - 1].setEnabled(false);
                panel.add(buttons[i][j - 1]);
            }
        }

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(720, 720);
        setContentPane(panel);
        setVisible(true);
        updateField();
    }

    void nextStage() {
        stage = stage == Piece.Color.WHITE ? Piece.Color.BLACK : Piece.Color.WHITE;
        if(checkGameOver()) gameOver();
    }

    void updateField() {
        for (JButton[] buttonLine : buttons) {
            for (JButton button : buttonLine) {
                button.setEnabled(false);
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String cell = field.getSymbol(new Pair<>(i, j));
                buttons[i][j].setText(cell);
                if (!cell.isEmpty() && field.checkCellMovable(new Pair<>(i, j), stage)) {
                    buttons[i][j].setEnabled(true);
                }
            }
        }
    }

    void updateField(LinkedList<Pair<Integer, Integer>> cells) {
        for (JButton[] buttonLine : buttons) {
            for (JButton button : buttonLine) {
                button.setEnabled(false);
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++)
                buttons[i][j].setText(field.getSymbol(new Pair<>(i, j)));
        }
        for (Pair<Integer, Integer> cell : cells)
            buttons[cell.getKey()][cell.getValue()].setEnabled(true);
    }

    private class ActionSelect implements ActionListener {
        private Pair<Integer, Integer> coordinate;

        ActionSelect(Pair<Integer, Integer> coordinate) {
            this.coordinate = coordinate;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            field.selection(coordinate);
        }
    }

    private boolean checkGameOver() {
        LinkedList<Pair<Integer, Integer>> movements = new LinkedList<>();
        Pair<Integer, Integer> king = null;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Pair<Integer, Integer> cell = new Pair<>(i, j);
                if (!field.getSymbol(cell).isEmpty()) {
                    if (field.getPieceClass(cell).equals(King.class) && !field.getColor(cell, stage).equals(stage))
                        king = cell;
                    else if (field.getColor(cell, stage).equals(stage))
                        movements.addAll(field.getMovableCells(cell));
                }
            }
        }
        return movements.contains(king);
    }

    private void gameOver() {
        JPanel panel = new JPanel();
        String message = (stage == Piece.Color.WHITE ? "White": "Black") + " player wins";
        JLabel label = new JLabel(message);
        JButton reset = new JButton("Play again");
        reset.addActionListener(new ActionReset());
        panel.setLayout(new GridLayout(2, 1, 0, 0));
        panel.add(label);
        panel.add(reset);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(240, 240);
        setContentPane(panel);
        setVisible(true);
    }

    private class ActionReset implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            createGameField();
        }
    }
}
