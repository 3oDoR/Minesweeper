package controller;

import Hex.Hexagon;
import Hex.HexagonPanel;
import model.Desk;
import view.MinerMouseListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static void repaint(HexagonPanel hexagonPanel) {
        hexagonPanel.updateUI();
    }

    public static int getSizeOfLine() {
        String input = JOptionPane.showInputDialog(null, "Enter a desk size (columns): ");
        return Integer.parseInt(input);
    }


    public final void run() {
        JFrame topLevelContainer = new JFrame("Miner 1.0");

        topLevelContainer.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        topLevelContainer.setSize(new Dimension(600, 600));

        JPanel panel = new JPanel();

        Desk desk = new Desk(lines, columns);

        DescController controller = new DescController(desk);

        ArrayList<Hexagon> list = new ArrayList<>();

        for (int i = 0;i < lines;i++) {
            for (int j = 0;j < columns;j++) {

                list.add(desk.getField(i,j).getHexagon());

            }


        }

        HexagonPanel button = new HexagonPanel(list);

//        button.addMouseListener(new MinerMouseListener(i, j, controller, topLevelContainer));

        panel.add(button);





        panel.setBackground(new Color(100,143,110));

        topLevelContainer.add(panel);

        topLevelContainer.setVisible(true);

    }


}


