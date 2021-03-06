package controller;


import hexagon.HexagonPanel;
import model.Desk;
import model.Field;
import model.FieldAddres;


public class DeskController {
    private Desk desk;
    private int unHidden;
    private int marked;
    private HexagonPanel hexagonPanel;


    public DeskController(Desk desk, HexagonPanel hexagonPanel) {
        this.desk = desk;
        this.hexagonPanel = hexagonPanel;
        this.unHidden = 0;
    }

    private void setMarked(final int x, final int y) {

        if (desk.getField(x, y).isHidden()) {
            desk.getField(x, y).setMarked(true);
            marked++;
        }
    }

    private void setUnmarked(final int x, final int y) {

        if (desk.getField(x, y).isHidden()) {
            desk.getField(x, y).setMarked(false);
            marked--;
        }
    }

    private void repaint(HexagonPanel hexagonPanel) {
        hexagonPanel.repaint();
    }

    public GameResult remarked(final int x, final int y) {
        Field field = desk.getField(x, y);

        if (field.isMarked()) {
            setUnmarked(x, y);
            repaint(hexagonPanel);
            return GameResult.NONE;
        } else {
            setMarked(x, y);
        }
        return game(field);
    }


    private void openFreeSpace(final int x, final int y) {
        Field field = desk.getField(x , y);

        if (!field.isBomb() && field.isHidden() && !field.isMarked()) {
            field.setHidden(false);
            unHidden++;

            for (FieldAddres fieldAddres : desk.getNeighbords(field.getFieldAddres())) {
                if (desk.getField(fieldAddres).getCountOfBombs() != 0 && desk.getField(fieldAddres).isHidden()) {
                    desk.getField(fieldAddres).setHidden(false);
                    unHidden++;
                }
                if (desk.getField(fieldAddres).getCountOfBombs() == 0 && desk.getField(fieldAddres).isHidden()) {
                    openFreeSpace(fieldAddres.getX(), fieldAddres.getY());
                }
            }
        }
    }


    public GameResult touch(final int x, final int y) {
        if (x >= desk.getColumns() && x < 0 && y < 0 && y >= desk.getLines()) {
            throw new IllegalArgumentException();
        }

        final Field field = desk.getField(x, y);

        if (field.getCountOfBombs() != 0) {
            field.setHidden(false);
            unHidden++;
        }
        if (field.getCountOfBombs() == 0) {
            openFreeSpace(field.getFieldAddres().getX(), field.getFieldAddres().getY());
        }
        return game(field);
    }

    private void openBombs(){
        for (int i = 0; i < desk.getLines(); i++) {
            for (int j = 0; j < desk.getColumns(); j++) {
                if (desk.getField(i,j).isBomb()) {
                    desk.getField(i,j).setBombHit(true);
                }
            }
        }
    }

    private GameResult game(final Field field) {
        if (field.isBomb() && !field.isMarked()) {
            openBombs();
            repaint(hexagonPanel);
            return GameResult.LOSE;
        }

        if (unHidden + marked < desk.getCountOfElements()) {
            repaint(hexagonPanel);
            return GameResult.NONE;

        }

        if (marked == desk.getCountOfBombs() && marked + unHidden == desk.getCountOfElements()) {
            repaint(hexagonPanel);
            return GameResult.WIN;
        }
        repaint(hexagonPanel);
        return GameResult.NONE;
    }

    public enum GameResult {
        WIN, LOSE, NONE
    }
}
