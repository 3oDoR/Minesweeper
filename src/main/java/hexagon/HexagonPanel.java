package hexagon;


import model.Desk;
import model.Field;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HexagonPanel extends JPanel {

    private List<Hexagon> hexagon;
    private Desk desk;

    public HexagonPanel(List<Hexagon> hexagon, Desk desk) {
        this.hexagon = hexagon;
        this.desk = desk;

        int width = (desk.getLines() + 1) * 39;
        int height = (desk.getColumns() + 1) * 51 + 32;

        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(100, 143, 110));

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Font font = new Font("Verdana", Font.PLAIN, 20);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        int width = (desk.getLines() + 1) * 39;

        if (desk.getLines() > 1) {
            g2d.drawString("Bombs: " + desk.getCountOfBombs(), width / 2 - 45, 25);
        } else if (desk.getLines() == 1) {
            g2d.drawString("B: " + desk.getCountOfBombs(), width / 2 - 20, 25);
        }


        for (int i = 0; i < desk.getLines(); i++) {
            for (int j = 0; j < desk.getColumns(); j++) {


                Field field = desk.getField(i, j);
                Hexagon hex = desk.getField(i, j).getHexagon();
                Polygon polygon = desk.getField(i, j).getHexagon().getPolygon();

                if (field.isHidden() && !field.isMarked()) {

                    g2d.setColor(Color.GRAY);
                    g2d.fillPolygon(hex.getPolygon());
                    g2d.setColor(Color.BLACK);
                    g2d.drawPolygon(hex.getPolygon());
                }

                if (!field.isHidden() && !field.isMarked() && !field.isBomb()) {

                    g2d.setColor(Color.WHITE);
                    g2d.fillPolygon(polygon);
                    g2d.setColor(Color.BLACK);
                    g2d.drawPolygon(polygon);

                    if (field.getCountOfBombs() == 1) {
                        g2d.setColor(Color.BLUE);
                    } else if (field.getCountOfBombs() == 2) {
                        g2d.setColor(Color.GREEN);
                    } else if (field.getCountOfBombs() == 3) {
                        g2d.setColor(Color.RED);
                    } else if (field.getCountOfBombs() == 4) {
                        g2d.setColor(new Color(0x00045F));
                    } else if (field.getCountOfBombs() == 5) {
                        g2d.setColor(new Color(0x350200));
                    } else if (field.getCountOfBombs() == 6) {
                        g2d.setColor(new Color(0xFF131A));
                    }
                    g2d.drawString(field.getCountOfBombs() + "",
                            field.getHexagon().getCenter().x - 5,
                            field.getHexagon().getCenter().y + 5);
                }

                if (field.isHidden() && field.isMarked()) {

                    g2d.setColor(Color.LIGHT_GRAY);
                    g2d.fillPolygon(polygon);
                    g2d.setColor(Color.BLACK);
                    g2d.drawPolygon(polygon);
                    g2d.drawString("B",
                            field.getHexagon().getCenter().x - 5,
                            field.getHexagon().getCenter().y + 5);
                }
                if (field.isBombHit()) {

                    g2d.setColor(Color.RED);
                    g2d.fillPolygon(polygon);
                    g2d.setColor(Color.BLACK);
                    g2d.drawPolygon(polygon);
                }
                if (field.isBombHit() && field.isMarked()) {

                    g2d.setColor(Color.RED);
                    g2d.fillPolygon(polygon);
                    g2d.setColor(Color.BLACK);
                    g2d.drawPolygon(polygon);
                    g2d.drawString("B",
                            field.getHexagon().getCenter().x - 5,
                            field.getHexagon().getCenter().y + 5);
                }
            }
        }
    }


    public List<Hexagon> getHexagons() {
        return hexagon;
    }
}