package controller;


import model.Desk;
import model.Field;
import model.FieldAddres;


import java.util.ArrayList;

public class DescController {
    private Desk desk;
    private int unHidden;
    private int marked;
    private ArrayList<FieldAddres> modifiedFields;


    public DescController(Desk desk) {
        this.desk = desk;
        this.unHidden = 0;
        modifiedFields = new ArrayList<>();
    }


    public void setMarked(final int x, final int y) {

        desk.getField(x, y).setMarked(true);
        // desk.getField(x, y).setText("B");
        desk.getField(x, y).updateUI();

        marked++;
    }

    public void setUnmarked(final int x, final int y) {

        desk.getField(x, y).setMarked(false);
//      desk.getField(x, y).setText("");
        desk.getField(x, y).updateUI();

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


    public void openFreeSpace(final int x, final int y) {
        Field field = desk.getField(x, y);

        if (!field.isBomb() && field.isHidden() && !field.isMarked()) {
            field.setHidden(false);
//          field.setText(String.valueOf(field.getCountOfBombs()));
            modifiedFields.add(new FieldAddres(x, y));

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
            GameResult result = GameResult.NONE;

            for (FieldAddres fieldAddres : modifiedFields) {
                result.addAddres(fieldAddres);
            }

            modifiedFields.clear();

            return GameResult.NONE;
        }

        if (marked == desk.getCountOfBombs() && marked + unHidden == desk.getCountOfElements()) {
            return GameResult.WIN;
        }

        return GameResult.NONE;
    }

    public enum GameResult {
        WIN, LOSE, NONE;

        private ArrayList<FieldAddres> modifiedFields;

        GameResult() {
            this.modifiedFields = new ArrayList<>();
        }

        private void addAddres(FieldAddres fieldAddres) {
            modifiedFields.add(fieldAddres);
        }

        public ArrayList<FieldAddres> getModifiedFields() {
            return modifiedFields;
        }
    }

    public Field[][] getFields() {
        return desk.getFields();
    }
}
