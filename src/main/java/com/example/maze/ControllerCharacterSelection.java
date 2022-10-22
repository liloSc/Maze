package com.example.maze;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerCharacterSelection {

    @FXML

    private Stage stage;
    private Scene scene;

    private Parent root;

    @FXML
    private ImageView charac1;
    @FXML
    private ImageView bigCharac1;
    @FXML
    private ImageView charac2;
    @FXML
    private ImageView bigCharac2;
    @FXML
    private ImageView charac3;
    @FXML
    private ImageView bigCharac3;
    @FXML
    private ImageView charac4;
    @FXML
    private ImageView bigCharac4;

    private Player player = new Player("charac1");
   /*

    public void setPlayerCharacter(Player choosedPlayer) {
        this.player = choosedPlayer;

    }

    public Player getPlayerCharacter() {
        return player;
    }*/


    public void switchToLevelSelection(MouseEvent event) {
        if (isCharacSelected() == true) {
            // ControllerGame controllergame = new ControllerGame();

            try {
                Parent root = FXMLLoader.load(getClass().getResource("levelselection.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                ControllerLevelSelection controllergame = new ControllerLevelSelection();
                controllergame.setPlayer(player);
                stage.setScene(scene);
                stage.show();
                //  controllergame.setPlayer(player);

            } catch (Exception e) {
                System.out.println("Can not load the Scene Level Selection");
            }

        }

    }

    private void hideImage() {
        bigCharac1.setVisible(false);
        bigCharac2.setVisible(false);
        bigCharac3.setVisible(false);
        bigCharac4.setVisible(false);
    }

    private boolean isCharacSelected() {
        if (bigCharac1.isVisible() || bigCharac2.isVisible() || bigCharac3.isVisible() || bigCharac4.isVisible()) {
            return true;
        } else {
            return false;
        }
    }

    public void animateCharacter(MouseEvent event) {


        if (event.getSource() == charac1) {
            hideImage();
            bigCharac1.setVisible(true);
            //  player.setPlayer(player);
            											//player.character = "charac1";
            player.setCharacter("charac1");
            //   setPlayerCharacter(Player.Char1);

        } else if (event.getSource() == charac2) {
            hideImage();
            bigCharac2.setVisible(true);
            //   player.setPlayer(player);
           // System.out.println("open Charc2");
            										// player.character = "charac2";
            player.setCharacter("charac2");
            // setPlayerCharacter(Player.Char2);

        } else if (event.getSource() == charac3) {
            hideImage();
            bigCharac3.setVisible(true);
            											//player.character = "charac3";
            player.setCharacter("charac3");
            //   setPlayerCharacter(Player.Char3);

        } else if (event.getSource() == charac4) {
            hideImage();
            bigCharac4.setVisible(true);
            										//  player.character = "charac4";
            player.setCharacter("charac4");
            //   setPlayerCharacter(Player.Char4);
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
            Parent root = (Parent) loader.load();

            System.out.println("Char Selection: " + player.getCharacter());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
