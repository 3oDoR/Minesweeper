package hexagon;


import java.awt.*;


public class Hexagon {
    private final int radius;

    private final Point center;

    private final Polygon polygon;

    public Hexagon(Point center, int radius) {
        this.center = center;
        this.radius = radius;
        this.polygon = createHexagon();


    }

    public Polygon createHexagon() {
        Polygon polygon = new Polygon();


        for (int i = 0; i < 6; i++) {
            int xval = (int) (center.x + radius
                    * Math.cos(i * 2 * Math.PI / 6D));
            int yval = (int) (center.y + radius
                    * Math.sin(i * 2 * Math.PI / 6D));
            polygon.addPoint(xval, yval);
        }

        return polygon;
    }

    Point getCenter() {
        return center;
    }

    Polygon getPolygon() {
        return polygon;
    }



}
