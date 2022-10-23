package com.example.maze;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerCharacterSelection implements Initializable {

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
    
    
    
   
    public Player ourPlayer = new Player(null);
    
   
    

    public void switchToLevelSelection(MouseEvent event) throws IOException   // WE HAVE TO ADD PASSING THE PLAYER OBJECT  
    {
        if (isCharacSelected() == true) {
        		FXMLLoader loader = new FXMLLoader();
        		loader.setLocation(getClass().getResource("levelselection.fxml"));
        		Parent root = loader.load();
                Scene root_scene = new Scene(root);
                // Access the controller and call a method 
                ControllerLevelSelection controller2 = loader.getController();
                controller2.initData(ourPlayer);
                
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(root_scene);
                stage.show();
        }
    }

    public Player playerselected;
    
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
            ourPlayer.setCharacter("charac1");
          
        } else if (event.getSource() == charac2) {
            hideImage();
            bigCharac2.setVisible(true);
          
            ourPlayer.setCharacter("charac2");
            String tesssst = ourPlayer.getCharacter();
        	System.out.println("Now we have a new char  " + tesssst);

        } else if (event.getSource() == charac3) {
            hideImage();
            bigCharac3.setVisible(true);
            ourPlayer.setCharacter("charac3");
          
        } else if (event.getSource() == charac4) {
            hideImage();
            bigCharac4.setVisible(true);
            ourPlayer.setCharacter("charac4");
        }
      
    }



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
