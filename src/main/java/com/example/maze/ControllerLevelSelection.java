package com.example.maze;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ControllerLevelSelection implements Initializable {

    private Stage stage;
    private Scene scene;

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
    @FXML
    private Label label_yourPlayer;
    @FXML
    private ImageView image_character1;
    @FXML
    private ImageView image_character2;
    @FXML
    private ImageView image_character3;
    @FXML
    private ImageView image_character4;

    public Enemy ourEnemies = new Enemy(20,30);

    private Player player;

    private void hideLevelSelection() {
        rectangle_level1.setVisible(false);
        rectangle_level2.setVisible(false);
        rectangle_level3.setVisible(false);
    }

    private void hideImageLevel() {
        image_character1.setVisible(false);
        image_character2.setVisible(false);
        image_character3.setVisible(false);
        image_character4.setVisible(false);
    }


    public void switchToGame(MouseEvent event) throws IOException {
        if (isLevelSelected() == true) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("game.fxml"));
            Parent root = loader.load();
            scene = new Scene(root);

            //TODO THIS SHOULD SET THE IMAGE FOR THE GAME IN COMBININGGAME CLASS (via Game_layout?
            // Access the controller and call a method

            ControllerGame controllerGame = loader.getController();
            controllerGame.initEnemy(ourEnemies, ourEnemies, ourEnemies);
            //    ControllerGame controller3 = loader.getController();
            controllerGame.initData(player);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
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
        if ((event.getSource() == rectangle_level1) || (event.getSource() == level1)) {
            hideLevelSelection();
            rectangle_level1.setVisible(true);
            ourEnemies.setLevelEnemy("1");
        } else if ((event.getSource() == rectangle_level2) || (event.getSource() == level2)) {
            hideLevelSelection();
            rectangle_level2.setVisible(true);
            ourEnemies.setLevelEnemy("2");
        } else if ((event.getSource() == rectangle_level3) || (event.getSource() == level3)) {
            hideLevelSelection();
            rectangle_level3.setVisible(true);
            ourEnemies.setLevelEnemy("3");
        }
    }


    public void initData(Player player) { // THIS METHOD accepts a player to initialize the view
        this.player = player;
        label_yourPlayer.setText(this.player.getCharacter());
        if (label_yourPlayer.getText() == "charac1") {
            hideImageLevel();
            image_character1.setVisible(true);
        } else if (label_yourPlayer.getText() == "charac2") {
            hideImageLevel();
            image_character2.setVisible(true);
        } else if (label_yourPlayer.getText() == "charac3") {
            hideImageLevel();
            image_character3.setVisible(true);
        } else if (label_yourPlayer.getText() == "charac4") {
            hideImageLevel();
            image_character4.setVisible(true);
        }


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
