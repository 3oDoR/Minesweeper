package Hex;
import javax.swing.*;
import java.awt.*;

 public class HexagonPanel extends JPanel {

    private Hexagon hexagon;

    public HexagonPanel(Hexagon hexagon) {
        this.hexagon = hexagon;
        this.setPreferredSize(new Dimension(49, 49));
    }

    public HexagonPanel() {
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.drawPolygon(hexagon.getHexagon());




    }
}