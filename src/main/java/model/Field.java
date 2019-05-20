package model;

import javax.swing.*;

public class Field extends JButton {
    private boolean isBomb;
    private boolean isMarked;
    private boolean isHidden;
    private int countOfBombs;
    private FieldAddres fieldAddres;

    public Field(boolean isBomb, boolean isMarked, boolean isHidden, int countOfBombs, FieldAddres fieldAddres) {
        this.isBomb = isBomb;
        this.isMarked = isMarked;
        this.isHidden = isHidden;
        this.countOfBombs = countOfBombs;
        this.fieldAddres = fieldAddres;
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
}
