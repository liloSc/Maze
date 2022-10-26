package com.example.maze;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerCharacterSelection implements Initializable {

    @FXML

    private Stage stage;


    @FXML
    private ImageView image_charac1;
    @FXML
    private ImageView image_bigCharac1;
    @FXML
    private ImageView image_charac2;
    @FXML
    private ImageView image_bigCharac2;
    @FXML
    private ImageView image_charac3;
    @FXML
    private ImageView image_bigCharac3;
    @FXML
    private ImageView image_charac4;
    @FXML
    private ImageView image_bigCharac4;

    public Player player = new Player(null, 10);

    public void switchToLevelSelection(MouseEvent event) throws IOException   // WE HAVE TO ADD PASSING THE PLAYER OBJECT  
    {
        if (isCharacterSelected() == true) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("levelselection.fxml"));
            Parent root = loader.load();
            Scene root_scene = new Scene(root);

            //THIS SET THE IMAGE FOR THE GAME CONTROLLER GAME CLASS
            // Access the controller and call a method and initialise the selected character in Controller Level Selection class
            ControllerLevelSelection controllerLevelSelection = loader.getController();
            controllerLevelSelection.initData(player);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(root_scene);
            stage.show();

        }
    }
   
    

    private void hideImage() {
        image_bigCharac1.setVisible(false);
        image_bigCharac2.setVisible(false);
        image_bigCharac3.setVisible(false);
        image_bigCharac4.setVisible(false);
    }

 // if a Character is selected return true 
    private boolean isCharacterSelected() {
        if (image_bigCharac1.isVisible() || image_bigCharac2.isVisible() || image_bigCharac3.isVisible() || image_bigCharac4.isVisible()) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * This function checks which character has been clicked and prints the image of the character bigger on the screen
     */
    public void animateCharacter(MouseEvent event) {
        if (event.getSource() == image_charac1) {
            hideImage();
            image_bigCharac1.setVisible(true);
            player.setCharacter("charac1");

        } else if (event.getSource() == image_charac2) {
            hideImage();
            image_bigCharac2.setVisible(true);
            player.setCharacter("charac2");

        } else if (event.getSource() == image_charac3) {
            hideImage();
            image_bigCharac3.setVisible(true);
            player.setCharacter("charac3");

        } else if (event.getSource() == image_charac4) {
            hideImage();
            image_bigCharac4.setVisible(true);
            player.setCharacter("charac4");
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }
}
