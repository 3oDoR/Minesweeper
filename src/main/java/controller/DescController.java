package controller;


import model.Desk;
import model.Field;
import model.FieldAddres;



public class DescController {
    private Desk desk;
    private int unHidden;
    private int marked;


    DescController(Desk desk) {
        this.desk = desk;
        this.unHidden = 0;
    }


    private void setMarked(final int x, final int y) {

        desk.getField(x, y).setMarked(true);
        //        desk.getField(x, y).setText("B");
        //        desk.getField(x, y).getHexagonPanel().updateUI();

        marked++;
    }

    private void setUnmarked(final int x, final int y) {

        desk.getField(x, y).setMarked(false);
//      desk.getField(x, y).setText("");
//        desk.getField(x, y).getHexagonPanel().updateUI();

        marked--;
    }

    public GameResult remarked(final int x, final int y) {
        Field field = desk.getField(x, y);

        if (field.isMarked()) {
            setUnmarked(x, y);
        } else {
            setMarked(x, y);
        }
        return game(field);
    }


    private void openFreeSpace(final int x, final int y) {
        Field field = desk.getField(x, y);

        if (!field.isBomb() && field.isHidden() && !field.isMarked()) {
            field.setHidden(false);
//          field.setText(String.valueOf(field.getCountOfBombs()));

            unHidden++;

            for (FieldAddres fieldAddres : desk.getNeighbords(field.getFieldAddres())) {

                if (desk.getField(fieldAddres).getCountOfBombs() == 0) {
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
