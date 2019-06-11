package hexagon;


import model.Desk;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HexagonPanel extends JPanel {

    private List<Hexagon> hexagon;
    private Desk desk;

    public HexagonPanel(List<Hexagon> hexagon, Desk desk) {
        this.hexagon = hexagon;
        this.desk = desk;

        this.setPreferredSize(new Dimension(800, 800));
        this.setBackground(new Color(100, 143, 110));

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Font font = new Font("Verdana", Font.PLAIN, 20);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(5));
        g2d.setFont(font);

        for (Hexagon hex : hexagon) {

            for (int i = 0; i < desk.getColumns(); i++) {
                for (int j = 0; j < desk.getLines(); j++) {

                    if (desk.getField(i, j).isHidden() && !desk.getField(i, j).isMarked()) {
                        g2d.setColor(Color.GRAY);
                        g2d.fillPolygon(hex.getPolygon());
                        g2d.setColor(Color.BLACK);
                        g2d.drawPolygon(hex.getPolygon());
                    }

                    if (!desk.getField(i, j).isHidden()
                            && !desk.getField(i, j).isMarked()
                            && !desk.getField(i, j).isBomb()) {

                        g2d.setColor(Color.WHITE);
                        g2d.fillPolygon(desk.getField(i, j).getHexagon().getPolygon());
                        g2d.setColor(Color.BLACK);
                        g2d.drawPolygon(desk.getField(i, j).getHexagon().getPolygon());
                        g2d.drawString(desk.getField(i, j).getCountOfBombs() + "",
                                desk.getField(i, j).getHexagon().getCenter().x - 5,
                                desk.getField(i, j).getHexagon().getCenter().y + 5);
                    }
                     if (desk.getField(i, j).isHidden() && desk.getField(i, j).isMarked()) {

                        g2d.setColor(Color.WHITE);
                        g2d.fillPolygon(desk.getField(i, j).getHexagon().getPolygon());
                        g2d.setColor(Color.BLACK);
                        g2d.drawPolygon(desk.getField(i, j).getHexagon().getPolygon());
                        g2d.drawString("B",
                                desk.getField(i, j).getHexagon().getCenter().x - 5,
                                desk.getField(i, j).getHexagon().getCenter().y + 5);
                    }
                }
            }
        }
    }



    public List<Hexagon> getHexagons() {
        return hexagon;
    }
}