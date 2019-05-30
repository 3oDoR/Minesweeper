package model;


import Hex.Hexagon;
import Hex.HexagonPanel;

public class Field {
    private boolean isBomb;
    private boolean isMarked;
    private boolean isHidden;
    private int countOfBombs;
    private FieldAddres fieldAddres;
    private Hexagon hexagon;

    public Field(boolean isBomb, boolean isMarked, boolean isHidden, int countOfBombs, FieldAddres fieldAddres,Hexagon hexagon) {
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

    public void setBomb(boolean bomb) {
        isBomb = bomb;
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

    public void setCountOfBombs(int countOfBombs) {
        this.countOfBombs = countOfBombs;
    }

    public FieldAddres getFieldAddres() {
        return fieldAddres;
    }

    public void setFieldAddres(FieldAddres fieldAddres) {
        this.fieldAddres = fieldAddres;
    }

    public Hexagon getHexagon() {
        return hexagon;
    }

    public void setHexagon(Hexagon hexagon) {
        this.hexagon = hexagon;
    }

}
