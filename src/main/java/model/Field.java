package model;


import Hex.HexagonPanel;

public class Field extends HexagonPanel {
    private boolean isBomb;
    private boolean isMarked;
    private boolean isHidden;
    private int countOfBombs;
    private FieldAddres fieldAddres;
    private HexagonPanel hexagonPanel;

    public Field(boolean isBomb, boolean isMarked, boolean isHidden, int countOfBombs, FieldAddres fieldAddres, HexagonPanel hexagonPanel) {
        this.isBomb = isBomb;
        this.isMarked = isMarked;
        this.isHidden = isHidden;
        this.countOfBombs = countOfBombs;
        this.fieldAddres = fieldAddres;
        this.hexagonPanel = hexagonPanel;

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

    public HexagonPanel getHexagonPanel() {
        return hexagonPanel;
    }

    public void setHexagonPanel(HexagonPanel hexagonPanel) {
        this.hexagonPanel = hexagonPanel;
    }

}
