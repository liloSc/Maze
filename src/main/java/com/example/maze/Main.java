package com.example.maze;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxml_startpage = new FXMLLoader(Main.class.getResource("startpage.fxml"));
        Scene scene = new Scene(fxml_startpage.load(), 800, 600);
        stage.setTitle("Name of Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}