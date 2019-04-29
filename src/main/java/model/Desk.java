package model;

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

    private void init () {
        countOfBombs = 0;

        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                fields[i][j] = new Field(needBomb(), false, true, 0, new FieldAddres(j, i));
            }
        }

        int count = 0;

        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                for (FieldAddres fieldAddres: getNeighbords(j, i)) {
                    if (getField(fieldAddres).isBomb()) {
                        count++;
                    }
                }

                fields[i][j].setCountOfBombs(count);

                count = 0;
            }
        }
    }

    private boolean needBomb () {
        boolean bombIsNeeding = Math.random() < 0.15;

        if (bombIsNeeding) {
            countOfBombs++;
        }

        return bombIsNeeding;
    }

    public FieldAddres[] getNeighbords(final int x, final int y) {
        if (y >= lines && x >= columns && y < 0 && x < 0) {
            return new FieldAddres[0];
        }

        ArrayList<FieldAddres> result = new ArrayList<>();

        if (x - 1 >= 0 && y - 1 >= 0) {
            result.add(new FieldAddres(x - 1, y - 1));
        }

        if (x >= 0 && y - 1 >= 0) {
            result.add(new FieldAddres(x, y - 1));
        }

        if (x + 1 < columns && x + 1 >= 0 && y - 1 >= 0) {
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


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("model.Desk{");
        for (int i = 0; i < lines; i++) {
            sb.append("\n");

            for (int j = 0; j < columns; j++) {
                sb.append(fields[i][j].getCountOfBombs());
                if (fields[i][j].isBomb()) {
                    sb.append("*");
                }
                if (fields[i][j].isHidden()) {
                    sb.append("h");
                }
                if (fields[i][j].isMarked()) {
                    sb.append("m");
                }

                sb.append(" ");
            }
        }

        sb.append("};");

        return sb.toString();
    }

    public int getCountOfElements () {
        return lines * columns;
    }

    public int getCountOfBombs () {
        return countOfBombs;
    }
}