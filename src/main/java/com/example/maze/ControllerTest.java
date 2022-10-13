package com.example.maze;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerTest {
    @FXML

    private Stage stage;
    private Scene scene;

    private Parent root;

    public void switchToStart(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("startpage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println("Can not load the FXML Startpage");
        }
    }

    public void switchToCharacterSelection(KeyEvent event) {
        if (event.getCode().equals((KeyCode.ENTER))) {
            try {

                Parent root = FXMLLoader.load(getClass().getResource("characterselection.fxml"));

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                System.out.println("Can not load the FXML Character Selection");
            }
        }

    }

    @FXML
    public void switchToLevelSelection(MouseEvent event) {

        try {

            Parent root = FXMLLoader.load(getClass().getResource("levelselection.fxml"));

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println("Can not load the Scene Level Selection");
        }
    }

    //KeyHandler keyHandler= new KeyHandler();
    int playerx = 100;
    int playery = 100;
    int playerspeed = 4;


  /*  public void switchToGame(MouseEvent event) {
        GamePanel gameBoard = new GamePanel();
     /*   GridPane gameBoard = new GridPane();
        gameBoard.setPrefSize(800, 600);
*/
     /* TODO: Grid
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 16; j++) {//Width

                Rectangle tile = new Rectangle(16*3, 16*3);
                tile.setFill(Color.BLACK);
                tile.setStroke(Color.WHITE);
                Text text = new Text();
              //  text.setFont(Font.font(40));
                gameBoard.add(new StackPane(tile, text), j, i);
              //  tile.setOnMouseClicked(e -> drawMove(text));
            }
        }*/
      /*  final Canvas canvas = new Canvas(250,250);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.BLUE);
        gc.fillRect(playerx,playery,50,50);

        gameBoard.getChildren().add(canvas);*/

    // panel_start.setPrefSize(300, 400);
    /*    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(gameBoard);
        scene.addEventHandler(KeyEvent.KEY_PRESSED,keyHandler);

        stage.setScene(scene);
        gameBoard.startGameThread();
    }*/
    public void switchToGame2(MouseEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("game.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println("Can not load the Switch to Game");
        }
    }
}
