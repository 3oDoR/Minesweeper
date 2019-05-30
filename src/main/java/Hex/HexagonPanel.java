package Hex;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HexagonPanel extends JPanel {

    ArrayList<Hexagon> hexagon;
    public HexagonPanel(ArrayList<Hexagon> hexagon) {
        this.hexagon = hexagon;
        this.setPreferredSize(new Dimension(800, 800));

    }





    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
            for (Hexagon hex: hexagon) {
                g.setColor(Color.BLACK);
                g.drawPolygon(hex);
        }

    }
}