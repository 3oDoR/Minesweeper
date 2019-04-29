import controller.DescController;
import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;



import javafx.stage.Stage;
import model.Desk;

import java.util.Scanner;

public class Game extends Application {

    public void game (final int columns, final int lines) {
        Desk desk = new Desk(columns, lines);

        DescController descController = new DescController(desk);

        Scanner sc = new Scanner(System.in);

        DescController.GameResult gameResult;
        int action;

        while (true) {
            System.out.println(desk);

            action = sc.nextInt();

            if (action == 1) {
                descController.remarked(sc.nextInt(), sc.nextInt());

                continue;
            }

            gameResult = descController.touch(sc.nextInt(), sc.nextInt());

            if (gameResult.equals(DescController.GameResult.WIN)) {
                System.out.println("Congratulations! You are win!");

                return;
            }

            if (gameResult.equals(DescController.GameResult.LOSE)) {
                System.out.println("Sorry, u r loser");

                return;
            }

        }
    }

    private Pane gamePanel = new Pane();
    GridPane gameField = new GridPane();

    @Override
    public void start(Stage primaryStage) {

    }

    public static void main(String[] args) {
        launch(args);
    }
}


