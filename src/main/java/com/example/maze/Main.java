package com.example.maze;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
			
	public ControllerGame_layout gamecontroller;
	
	
    @Override
    public void start(Stage stage) {
        try {
            Parent startpage = FXMLLoader.load(getClass().getResource("Game_layout.fxml"));
            Scene scene = new Scene(startpage);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
       // gamecontroller = new ControllerGame_layout();
       // gamecontroller.layoutcontroller();
    //    gamecontroller.Main = this;
    }



    public static void main(String[] args) {
    	
        launch();
    }
}