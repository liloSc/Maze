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


    public void switchToLevelSelection(MouseEvent event) {
        if (isCharacSelected() == true) {

            try {

                Parent root = FXMLLoader.load(getClass().getResource("levelselection.fxml"));

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

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
        } else if (event.getSource() == charac2) {
            hideImage();
            bigCharac2.setVisible(true);

        } else if (event.getSource() == charac3) {
            hideImage();
            bigCharac3.setVisible(true);

        } else if (event.getSource() == charac4) {
            hideImage();
            bigCharac4.setVisible(true);
        }

    }
}
