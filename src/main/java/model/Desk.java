package model;

import hexagon.Hexagon;

import java.awt.*;
import java.util.ArrayList;

public class Desk {
    private int lines;
    private int columns;
    private Field[][] fields;
    private int countOfBombs;

    public Desk(final int lines, final int columns) {
        this.lines = lines;
        this.columns = columns;
        this.fields = new Field[lines][columns];

        init();
    }

    private void init() {
        countOfBombs = 0;
        int y = 10;
        int x = 39;

        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                y = y + 45;
                fields[i][j] = new Field(needBomb(), false, false, true,
                        0, new FieldAddres(i, j), new Hexagon(new Point(x, y), 26));

                if (i % 2 == 0 && j == columns - 1) {

                    y = 32;
                    x += 39;

                } else if (i % 2 != 0 && j == columns - 1) {

                    y = 10;
                    x += 39;
                }
            }

        }

        int count = 0;

        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                for (FieldAddres fieldAddres : getNeighbords(i, j)) {
                    if (getField(fieldAddres).isBomb()) {
                        count++;
                    }
                }

                fields[i][j].setCountOfBombs(count);

                count = 0;
            }
        }
    }

    private boolean needBomb() {
        boolean bombIsNeeding = Math.random() < 0.15;

        if (bombIsNeeding) {
            countOfBombs++;
        }

        return bombIsNeeding;
    }

    FieldAddres[] getNeighbords(final int x, final int y) {

        ArrayList<FieldAddres> result = new ArrayList<>();

        if (x + 1 < lines) {
            result.add(new FieldAddres(x + 1, y));
        }
        if (x - 1 >= 0) {
            result.add(new FieldAddres(x - 1, y));
        }
        if (y + 1 < columns) {
            result.add(new FieldAddres(x, y + 1));
        }
        if (y - 1 >= 0) {
            result.add(new FieldAddres(x, y - 1));
        }

        if (x % 2 != 0) {

            if (x + 1 >= 0 && x + 1 < lines && y + 1 < columns) {
                result.add(new FieldAddres(x + 1, y + 1));
            }
            if (y + 1 < columns && x - 1 >= 0) {
                result.add(new FieldAddres(x - 1, y + 1));
            }

        } else if (x % 2 == 0) {

            if (y - 1 >= 0 && x - 1 >= 0) {
                result.add(new FieldAddres(x - 1, y - 1));
            }
            if (y - 1 >= 0 && x + 1 < lines) {
                result.add(new FieldAddres(x + 1, y - 1));
            }
        }
        return result.toArray(new FieldAddres[0]);
    }


    public FieldAddres[] getNeighbords(final FieldAddres fieldAddres) {
        return getNeighbords(fieldAddres.getX(), fieldAddres.getY());
    }

    public Field getField(final int x, final int y) {
        return fields[x][y];
    }

    public Field getField(final FieldAddres fieldAddres) {
        return fields[fieldAddres.getX()][fieldAddres.getY()];
    }

    public int getLines() {
        return lines;
    }

    public int getColumns() {
        return columns;
    }

    public int getCountOfElements() {
        return lines * columns;
    }

    public int getCountOfBombs() {
        return countOfBombs;
    }

}