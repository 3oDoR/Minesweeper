package controller;

import hexagon.Hexagon;
import hexagon.HexagonPanel;
import listener.MinerListener;
import model.Desk;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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

        hexagonPanel.repaint();


    }

    public static int getSizeOfLine() {
        String input = JOptionPane.showInputDialog(null, "Enter a desk size (columns): ");
        return Integer.parseInt(input);
    }


    public final void run() {
        JFrame topLevelContainer = new JFrame("Miner 1.0");
        topLevelContainer.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        topLevelContainer.setSize(new Dimension(800, 800));
        JPanel panel = new JPanel();
        Desk desk = new Desk(lines, columns);
        ArrayList<Hexagon> hexagons = new ArrayList<>();
        DescController controller = new DescController(desk);


        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                hexagons.add(desk.getField(i, j).getHexagon());
            }
        }

        HexagonPanel hexagonPanel = new HexagonPanel(hexagons);

        hexagonPanel.addMouseListener(new MinerListener(controller,topLevelContainer,hexagonPanel,desk));

        panel.add(hexagonPanel);
        panel.setBackground(new Color(100, 143, 110));
        topLevelContainer.add(panel);
        topLevelContainer.setVisible(true);
    }
}


