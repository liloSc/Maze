package com.example.maze;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Controller {
    @FXML
   // private Label gameName;
    private Stage stage;
    private Scene scene;

    private Parent root;
    @FXML
    public void switchToScene2(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("levelselection.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (Exception e) {
            System.out.println("2 Can not load the window");
        }


      //  System.out.println(gameName.getText());
        // gameName.setText("Welcome to JavaFX Application!");
    }

    public void switchToScene1(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("startpage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println("1 Can not load the window");
        }



        // gameName.setText("Welcome to JavaFX Application!");
    }
}


