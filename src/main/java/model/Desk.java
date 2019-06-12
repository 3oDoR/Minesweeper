package model;

import hexagon.Hexagon;

import java.awt.*;
import java.util.ArrayList;

public class Desk {
    private int lines;
    private int columns;
    private Field[][] fields;
    private int countOfBombs;
    private int bombsOnBoard;

    public Desk(final int lines, final int columns) {
        this.lines = lines;
        this.columns = columns;
        this.fields = new Field[lines][columns];

        init();
    }

    private void init() {
        countOfBombs = 0;
        int x = 10;
        int y = 39;

        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                x = x + 46;
                fields[i][j] = new Field(needBomb(), false, true,
                        0, new FieldAddres(j, i), new Hexagon(new Point(y, x), 26));

                if (i % 2 == 0 &&  j == lines - 1) {

                    x = 32;
                    y += 39;

                } else if (i % 2 != 0 && j == lines - 1) {

                    x = 10;
                    y += 39;
                }
            }
        }

        int count = 0;

        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                if (getField(i,j).isBomb()) {
                    bombsOnBoard++;
                }
                for (FieldAddres fieldAddres : getNeighbords(j, i)) {
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
        if (y >= lines && x >= columns && y < 0 && x < 0) {
            return new FieldAddres[0];
        }

        ArrayList<FieldAddres> result = new ArrayList<>();

        if (y + 1 >= 0 && y + 1 < lines) {
            result.add(new FieldAddres(x, y + 1));
        }
        if (y - 1 >= 0) {
            result.add(new FieldAddres(x, y - 1));
        }
        if (y >= 0 && x + 1 < columns) {
            result.add(new FieldAddres(x + 1, y));
        }
        if (x - 1 >= 0 && y >= 0) {
            result.add(new FieldAddres(x - 1, y));
        }

        if (y % 2 != 0) {

            if (y + 1 >= 0 && y + 1 < lines && x + 1 < columns) {
                result.add(new FieldAddres(x + 1, y + 1));
            }
            if (x + 1 < columns && y - 1 >= 0) {
                result.add(new FieldAddres(x + 1, y - 1));
            }

        } else if (y % 2 == 0) {

            if (x - 1 >= 0 && y - 1 >= 0) {
                result.add(new FieldAddres(x - 1, y - 1));
            }
            if (x - 1 >= 0 && y + 1 >= 0 && y + 1 < lines) {
                result.add(new FieldAddres(x - 1, y + 1));
            }
        }
        return result.toArray(new FieldAddres[result.size()]);
    }


    public FieldAddres[] getNeighbords(final FieldAddres fieldAddres) {
        return getNeighbords(fieldAddres.getX(), fieldAddres.getY());
    }

    public Field getField(final int x, final int y) {
        return fields[y][x];
    }

    public Field getField(final FieldAddres fieldAddres) {
        return fields[fieldAddres.getY()][fieldAddres.getX()];
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

    public int getBombsOnBoard() {
        return bombsOnBoard;
    }
}