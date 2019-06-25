package model;


import hexagon.Hexagon;

public class Field {
    private boolean isBomb;
    private boolean bombHit;
    private boolean isMarked;
    private boolean isHidden;
    private int countOfBombs;
    private FieldAddres fieldAddres;
    private Hexagon hexagon;

    Field(boolean isBomb,boolean bombHit, boolean isMarked, boolean isHidden, int countOfBombs, FieldAddres fieldAddres, Hexagon hexagon) {
        this.isBomb = isBomb;
        this.bombHit = bombHit;
        this.isMarked = isMarked;
        this.isHidden = isHidden;
        this.countOfBombs = countOfBombs;
        this.fieldAddres = fieldAddres;
        this.hexagon = hexagon;


    }

    public boolean isBomb() {
        return isBomb;
    }

    public boolean isBombHit() {
        return bombHit;
    }

    public void setBombHit(boolean bombHit) {
        this.bombHit = bombHit;
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
