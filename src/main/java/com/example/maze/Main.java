package com.example.maze;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        try {
            Parent startpage = FXMLLoader.load(getClass().getResource("startpage.fxml"));
            Scene scene = new Scene(startpage);
            stage.setResizable(false); //Window can not be changed
            stage.centerOnScreen();
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}