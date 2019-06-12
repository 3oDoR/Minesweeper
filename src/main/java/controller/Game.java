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
    private int size;


    public Game(int size) {
        this.lines = size;
        this.columns = size;
        this.size = size;
    }

    public static void main(String[] args) {
        new Game(getSizeOfLine()).run();

    }

    public static int getSizeOfLine() {
        String input = JOptionPane.showInputDialog(null, "Enter a desk size (columns): ");
        return Integer.parseInt(input);

    }


    public final void run() {
        JFrame topLevelContainer = new JFrame("Miner 1.0");
        topLevelContainer.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        topLevelContainer.setSize(new Dimension((size + 1) * 39, (size + 1) * 51 + 32));
        JPanel panel = new JPanel();
        Desk desk = new Desk(lines, columns);
        ArrayList<Hexagon> hexagons = new ArrayList<>();
        HexagonPanel hexagonPanel = new HexagonPanel(hexagons,desk);
        DeskController controller = new DeskController(desk,hexagonPanel);


        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                hexagons.add(desk.getField(i, j).getHexagon());
            }
        }

        hexagonPanel.addMouseListener(new MinerListener(controller,topLevelContainer,hexagonPanel,desk));

        topLevelContainer.setLocationRelativeTo(null);
        topLevelContainer.setLayout(new BorderLayout());


        panel.add(hexagonPanel);
        panel.setBackground(new Color(100, 143, 110));
        topLevelContainer.add(panel);
        topLevelContainer.setVisible(true);

    }


}


