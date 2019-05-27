package model;

import Hex.Hexagon;
import Hex.HexagonPanel;

import java.awt.*;
import java.util.ArrayList;

public class Desk extends HexagonPanel {
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

    private void init () {
        countOfBombs = 0;

        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                fields[i][j] = new Field(needBomb(),
                        false, true,
                        0, new FieldAddres(j, i),
                        new HexagonPanel(new Hexagon(new Point(25,25),24)));

            }
        }

        int count = 0;

        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                for (FieldAddres fieldAddres: getNeighbords(j, i)) {
                    if (fieldAddres != null) {
                        if (getField(fieldAddres).isBomb()) {
                            count++;
                        }
                    }


                }
                if ( fields[i][j] != null) {
                    fields[i][j].setCountOfBombs(count);
                }
            }
            count = 0;
        }
    }

    private boolean needBomb () {
        boolean bombIsNeeding = Math.random() < 0.15;

        if (bombIsNeeding) {
            countOfBombs++;
        }

        return bombIsNeeding;
    }

    public FieldAddres[] getNeighbords (final int x, final int y) {
        if (y >= lines && x >= columns && y < 0 && x < 0 || fields[x][y] == null) {
            return new FieldAddres[0];
        }

        ArrayList<FieldAddres> result = new ArrayList<>();

        if (x - 1 >= 0 && y - 1 >= 0) {
            result.add(new FieldAddres(x - 1, y - 1));
        }

        if (x >= 0 && y - 1 >= 0) {
            result.add(new FieldAddres(x, y - 1));
        }

        if (x + 1 < columns
                && x + 1 >= 0 && y - 1 >= 0) {
            result.add(new FieldAddres(x + 1, y - 1));
        }

        if (x - 1 >= 0 && y >= 0) {
            result.add(new FieldAddres(x -1,y));
        }

        if (x + 1 >= 0 && y >= 0 && x + 1 < columns) {
            result.add(new FieldAddres(x + 1, y));
        }

        if (x - 1 >= 0 && y + 1 >= 0 && y +1  < lines) {
            result.add(new FieldAddres(x - 1, y + 1));
        }

        if (x >= 0 && y + 1 >= 0 && y + 1 < lines) {
            result.add(new FieldAddres(x,y+ 1));
        }

        if (x  + 1 >= 0 && y + 1 >= 0 && y + 1 < lines && x + 1 < columns) {
            result.add(new FieldAddres(x  + 1,y + 1));
        }

        return result.toArray(new FieldAddres[result.size()]);
    }

    public FieldAddres[] getNeighbords (final FieldAddres fieldAddres) {
        return getNeighbords(fieldAddres.getX(), fieldAddres.getY());
    }

    public Field getField (final int x, final int y) {
        return fields[y][x];
    }

    public Field getField (final FieldAddres fieldAddres) {
        return fields[fieldAddres.getY()][fieldAddres.getX()];
    }

    public int getLines() {
        return lines;
    }

    public int getColumns() {
        return columns;
    }

    public int getCountOfElements () {
        return lines * columns;
    }

    public int getCountOfBombs () {
        return countOfBombs;
    }

    public Field[][] getFields () {
        return fields;
    }


}