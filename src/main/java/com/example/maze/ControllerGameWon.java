package com.example.maze;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ControllerGameWon {

    private Stage stage;
    private Scene scene;

    public void switchToHomeScreen(KeyEvent event) {
        if (event.getCode().equals((KeyCode.ENTER))) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("startpage.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                System.out.println("Can not load the FXML Start ");
            }
        }

    }

    public void quit() {
        System.exit(-1);
    }
}
