package com.example.maze;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public ControllerGame_layout gamecontroller;
    public Player player; // Creating an instance of player
    public ControllerLevelSelection controllergame;
    public ControllerCharacterSelection controller;

    //Player player;
    public void setPlayer(Player p) {
        this.player = p;
    }


    @Override
    public void start(Stage stage) {
        //player = new Player("test");
        //player.Main = this;
        // player.test();


        controller = new ControllerCharacterSelection(); // Here we initialise the Controller from the ControllerCharacterSelection class

        //controller.saveOurPlayer(player);


        try {
            Parent startpage = FXMLLoader.load(getClass().getResource("startpage.fxml"));
            Scene scene = new Scene(startpage);
            stage.setResizable(false);
            stage.centerOnScreen();
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