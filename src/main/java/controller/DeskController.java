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


    DeskController(Desk desk, HexagonPanel hexagonPanel) {
        this.desk = desk;
        this.hexagonPanel = hexagonPanel;
        this.unHidden = 0;
    }

    private void setMarked(final int x, final int y) {

        if (desk.getField(x, y).isHidden()) {
            desk.getField(x, y).setMarked(true);
        }

        marked++;
    }

    private void setUnmarked(final int x, final int y) {

        if (desk.getField(x, y).isHidden()) {
            desk.getField(x, y).setMarked(false);
        }


        marked--;
    }

    public GameResult remarked(final int x, final int y) {
        Field field = desk.getField(x, y);

        if (field.isMarked()) {
            setUnmarked(x, y);
            Game.repaint(hexagonPanel);
            return GameResult.NONE;
        } else {
            setMarked(x, y);
            Game.repaint(hexagonPanel);
        }
        return game(field);
    }


    private void openFreeSpace(final int x, final int y) {
        Field field = desk.getField(x, y);

        if (!field.isBomb() && field.isHidden() && !field.isMarked()) {
            field.setHidden(false);

            unHidden++;

            for (FieldAddres fieldAddres : desk.getNeighbords(field.getFieldAddres())) {
                if (desk.getField(fieldAddres).getCountOfBombs() == 0) {
                    openFreeSpace(fieldAddres.getX(), fieldAddres.getY());
                }

            }
            Game.repaint(hexagonPanel);
        }
    }


    public GameResult touch(final int x, final int y) {
        if (x >= desk.getColumns() && x < 0 && y < 0 && y >= desk.getLines()) {
            throw new IllegalArgumentException();
        }

        final Field field = desk.getField(x, y);

        openFreeSpace(field.getFieldAddres().getX(), field.getFieldAddres().getY());
        return game(field);
    }

    private GameResult game(final Field field) {
        if (field.isBomb() && !field.isMarked()) {
            return GameResult.LOSE;
        }

        if (unHidden + marked < desk.getCountOfElements()) {
            return GameResult.NONE;

        }

        if (marked == desk.getCountOfBombs() && marked + unHidden == desk.getCountOfElements()) {
            return GameResult.WIN;
        }

        return GameResult.NONE;
    }

    public enum GameResult {
        WIN, LOSE, NONE
    }
}
