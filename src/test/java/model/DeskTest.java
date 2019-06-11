package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeskTest {

    Desk desk = new Desk(3,3);



    @Test
    void getNeighbords() {
        assertEquals(desk.getNeighbords(0,0),2);
        assertEquals(desk.getNeighbords(1,1).length,6);
    }

    @Test
    void init() {
        assertEquals(desk.getCountOfElements(),9);
    }


}
