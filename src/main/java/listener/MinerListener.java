package listener;

import controller.DescController;
import controller.Game;
import hexagon.Hexagon;
import hexagon.HexagonPanel;
import model.Desk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

public class MinerListener implements MouseListener {
    private final DescController controller;
    private final HexagonPanel hexagonPanel;
    private final JFrame jFrame;
    Desk desk;

    private static final String[] OPTIONS = new String[]{"Yes", "No"};
    private static final String LOSE_MESSAGE = "Sorry, you are looser =(";
    private static final String WIN_MESSAGE = "Congratulations! You are winner!";
    private static final String CONFIRM_NEW_GAME_MESSAGE = "Do you want a new game?";

    public MinerListener(DescController controller, JFrame jFrame, HexagonPanel hexagonPanel, Desk desk) {
        this.controller = controller;
        this.hexagonPanel = hexagonPanel;
        this.jFrame = jFrame;
        this.desk = desk;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        DescController.GameResult result = DescController.GameResult.NONE;

        int x = 0;
        int y = 0;

        PointerInfo coordinate = MouseInfo.getPointerInfo();
        Point point = new Point(coordinate.getLocation());
        SwingUtilities.convertPointFromScreen(point, e.getComponent());

        x = (int) point.getX();
        y = (int) point.getY();

        for (Hexagon hexagon : hexagonPanel.getHexagon()) {
            if (hexagon.createHexagon().contains(x, y)) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                   for (int i = 0; i < desk.getColumns(); i++) {
                       for (int j = 0; j < desk.getLines(); j++) {
                           if (desk.getField(i,j).getHexagon() == hexagon) {
                               System.out.println("b3");
                               System.out.println(j + " " + i);
                               System.out.println(desk.getField(i,j).getCountOfBombs());
                               System.out.println(desk.getField(i,j).isBomb());
                               result = controller.remarked(i,j);
                           }
                       }
                   }
                }
                else if (e.getButton() == MouseEvent.BUTTON1) {
                    for (int i = 0; i < desk.getColumns(); i++) {
                        for (int j = 0; j < desk.getLines(); j++) {
                            if (desk.getField(i,j).getHexagon() == hexagon) {
                                System.out.println("b1");
                                System.out.println(j + " " + i);
                                System.out.println(desk.getField(i,j).getCountOfBombs());
                                System.out.println(desk.getField(i,j).isBomb());
                                result = controller.touch(i,j);
                            }
                        }
                    }
                }
            }
        }

        int option = -1;

        if (result.equals(DescController.GameResult.LOSE)) {
            option = showOptionDialog(false);

        } else if (result.equals(DescController.GameResult.WIN)) {
            option = showOptionDialog(true);
        }

        if (option == 0) {
            jFrame.setVisible(false);
            jFrame.setEnabled(false);


            new Game(Game.getSizeOfLine()).run();

        } else if (option == 1) {
            jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));
        }

      Game.repaint(hexagonPanel);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private int showOptionDialog(boolean win) {
        String message;

        if (win) {
            message = WIN_MESSAGE;
        } else {
            message = LOSE_MESSAGE;
        }

        return JOptionPane.showOptionDialog(
                jFrame,
                CONFIRM_NEW_GAME_MESSAGE,
                message,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                OPTIONS,
                OPTIONS[0]);
    }
}
