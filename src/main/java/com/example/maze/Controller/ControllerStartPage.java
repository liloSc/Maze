package com.example.maze.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ControllerStartPage {

    @FXML

    private Stage stage;
    private Scene scene;

    private Parent root;
	
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
	
}
