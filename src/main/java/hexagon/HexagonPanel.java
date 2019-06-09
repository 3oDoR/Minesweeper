package hexagon;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HexagonPanel extends JPanel {

    ArrayList<Hexagon> hexagon;

    public HexagonPanel(ArrayList<Hexagon> hexagon) {
        this.hexagon = hexagon;
        this.setPreferredSize(new Dimension(800, 800));
        this.setBackground(new Color(100, 143, 110));

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(5));

        for (Hexagon hex : hexagon) {
            g2d.setColor(Color.GRAY);
            g2d.fillPolygon(hex.getHexagon());
            g2d.setColor(Color.BLACK);
            g2d.drawPolygon(hex.getHexagon());
        }
    }


    public ArrayList<Hexagon> getHexagon() {
        return hexagon;
    }
}