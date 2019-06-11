package model;

import controller.DeskController;
import hexagon.Hexagon;
import hexagon.HexagonPanel;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeskControllerTest {

    List<Hexagon> hexagons = new ArrayList<>();
    Desk desk = new Desk(3,3);
    HexagonPanel hexagonPanel = new HexagonPanel(hexagons,desk);
    DeskController deskController = new DeskController(desk,hexagonPanel);


     @Test
     void remarked() {
         assertEquals(deskController.remarked(0,0), DeskController.GameResult.NONE);
    }

    @Test
    void touch() {
            if (deskController.touch(0, 0) == DeskController.GameResult.NONE) {
                assertEquals(deskController.touch(0, 0), DeskController.GameResult.NONE);
            } else if ((deskController.touch(0, 0) == DeskController.GameResult.WIN)) {
                assertEquals(deskController.touch(0, 0), DeskController.GameResult.NONE);
            }
        if (desk.getField(0,0).isBomb()) {
            assertEquals(deskController.touch(0, 0), DeskController.GameResult.LOSE);
        }
    }
}



