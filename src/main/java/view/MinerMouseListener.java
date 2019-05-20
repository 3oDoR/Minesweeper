package view;

import controller.DescController;
import controller.Game;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

public class MinerMouseListener implements MouseListener{
    private int x;
    private int y;
    private final DescController controller;
    private final JFrame frame;

    private static final String[] OPTIONS = new String[]{"Yes", "No"};
    private static final String LOSE_MESSAGE = "Sorry, you are looser =(";
    private static final String WIN_MESSAGE = "Congratulations! You are winner!";
    private static final String CONFIRM_NEW_GAME_MESSAGE = "Do you want a new game?";

    public MinerMouseListener (
            int x,
            int y,
            DescController controller,
            JFrame frame) {
        this.x = x;
        this.y = y;
        this.controller = controller;
        this.frame = frame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        DescController.GameResult result = DescController.GameResult.NONE;

        if (e.getButton() == MouseEvent.BUTTON3) {
            result = controller.remarked(x, y);
        } else if (e.getButton() == MouseEvent.BUTTON1) {
            result = controller.touch(x, y);
        }


        int option = -1;

        if (result.equals(DescController.GameResult.LOSE)) {
            option = showOptionDialog(false);

        } else if (result.equals(DescController.GameResult.WIN)) {
            option = showOptionDialog(true);
        }

        if (option == 0) {
            frame.setVisible(false);
            frame.setEnabled(false);

            new Game(Game.getSizeOfLine()).run();

        } else if (option == 1) {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }

        Game.repaint(controller.getFields());
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

    private int showOptionDialog (boolean win) {
        String message;

        if (win) {
            message = WIN_MESSAGE;
        } else {
            message = LOSE_MESSAGE;
        }

        return JOptionPane.showOptionDialog(
                frame,
                CONFIRM_NEW_GAME_MESSAGE,
                message,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                OPTIONS,
                OPTIONS[0]);
    }
}

