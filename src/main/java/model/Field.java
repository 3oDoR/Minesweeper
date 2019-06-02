package model;


import Hex.Hexagon;

public class Field {
    private boolean isBomb;
    private boolean isMarked;
    private boolean isHidden;
    private int countOfBombs;
    private FieldAddres fieldAddres;
    private Hexagon hexagon;

    Field(boolean isBomb, boolean isMarked, boolean isHidden, int countOfBombs, FieldAddres fieldAddres, Hexagon hexagon) {
        this.isBomb = isBomb;
        this.isMarked = isMarked;
        this.isHidden = isHidden;
        this.countOfBombs = countOfBombs;
        this.fieldAddres = fieldAddres;
        this.hexagon = hexagon;



    }

    public boolean isBomb() {
        return isBomb;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public int getCountOfBombs() {
        return countOfBombs;
    }

    void setCountOfBombs(int countOfBombs) {
        this.countOfBombs = countOfBombs;
    }

    public FieldAddres getFieldAddres() {
        return fieldAddres;
    }

    public Hexagon getHexagon() {
        return hexagon;
    }

}
