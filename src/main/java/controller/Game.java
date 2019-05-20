package controller;

import model.Desk;
import model.Field;
import view.MinerMouseListener;

import javax.swing.*;
import java.awt.*;

public class Game {
    private int lines;
    private int columns;

    public Game(int size) {
        this.lines = size;
        this.columns = size;
    }

    public static void main(String[] args) {
        new Game(getSizeOfLine()).run();
    }

    public static void repaint(Field[][] fields) {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[i].length; j++) {
                fields[i][j].updateUI();
            }
        }
    }

    public static int getSizeOfLine() {
        String input = JOptionPane.showInputDialog(null, "Enter a desk size (columns): ");
        return Integer.parseInt(input);
    }


    public final void run() {
        JFrame topLevelContainer = new JFrame("Miner 1.0");

        topLevelContainer.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        topLevelContainer.setSize(new Dimension(400, 400));

        JPanel panel = new JPanel();

        GridLayout gridLayout = new GridLayout(lines, columns);

        panel.setLayout(gridLayout);

        Desk desk = new Desk(lines, columns);

        DescController controller = new DescController(desk);

        JButton button;
        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                button = desk.getField(i, j);

                button.addMouseListener(new MinerMouseListener(i, j, controller, topLevelContainer));

                panel.add(button);
            }
        }

        topLevelContainer.add(panel);

        topLevelContainer.setVisible(true);
    }
}


