package com.example.maze;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ControllerLevelSelection {
    private Stage stage;
    private Scene scene;

    private Parent root;
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
