package com.example.maze;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
    	
        try {
            music();
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

    MediaPlayer mediaPlayer;

    //plays audioclip when the game starts
    public void music() {
        String fileName = getClass().getResource("audio/musik.mp3").toExternalForm();
        Media media = new Media(fileName);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

    }

    public static void main(String[] args) {
        launch();
    }
    
    
   
}