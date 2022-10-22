package com.example.maze;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ControllerLevelSelection {


    private Stage stage;
    private Scene scene;

    private Parent root;
    @FXML
    private Label level1;
    @FXML
    private Label level2;
    @FXML
    private Label level3;
    @FXML
    private Rectangle rectangle_level1;
    @FXML
    private Rectangle rectangle_level2;
    @FXML
    private Rectangle rectangle_level3;

    private void hideLevelSelection() {
        rectangle_level1.setVisible(false);
        rectangle_level2.setVisible(false);
        rectangle_level3.setVisible(false);

    }

    Player player;
    public void setPlayer(Player p) {
        this.player = p;
    }

    public void switchToGame(MouseEvent event) {
        if (isLevelSelected() == true) {
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

    private boolean isLevelSelected() {
        if (rectangle_level1.isVisible() || rectangle_level2.isVisible() || rectangle_level3.isVisible()) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    public void selectLevel(MouseEvent event) {
     //   System.out.println("Char sel: "+ player.character);
       // System.out.println(((Control) event.getSource()).getId());
        if ((event.getSource() == rectangle_level1) || (event.getSource() == level1)) {
            hideLevelSelection();
            rectangle_level1.setVisible(true);
        } else if ((event.getSource() == rectangle_level2) || (event.getSource() == level2)) {
            hideLevelSelection();
            rectangle_level2.setVisible(true);

        } else if ((event.getSource() == rectangle_level3) || (event.getSource() == level3)) {
            hideLevelSelection();
            rectangle_level3.setVisible(true);

        }


    }


}
