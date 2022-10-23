package com.example.maze;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ControllerLevelSelection implements Initializable {


	 ControllerCharacterSelection controller;


    private Stage stage;
    private Scene scene;

    private Parent root;
    
    @FXML
    private Label level1;
    @FXML
    private Label level2;
    @FXML
    private Label level3;
    @FXML
    private Rectangle rectangle_level1;
    @FXML
    private Rectangle rectangle_level2;
    @FXML
    private Rectangle rectangle_level3;
    @FXML
    private Label yourplayer;
    @FXML
    private ImageView charac1;
    @FXML
    private ImageView charac2;
    @FXML
    private ImageView charac3;
    @FXML
    private ImageView charac4;
    
    
    private void hideLevelSelection() {
        rectangle_level1.setVisible(false);
        rectangle_level2.setVisible(false);
        rectangle_level3.setVisible(false);

    }
    private void hideImageLevel() {
        charac1.setVisible(false);
        charac2.setVisible(false);
        charac3.setVisible(false);
        charac4.setVisible(false);
    }

  
  
  

    
    public void switchToGame(MouseEvent event) throws IOException   {
        if (isLevelSelected() == true) {
        		FXMLLoader loader = new FXMLLoader();
        		loader.setLocation(getClass().getResource("Game_layout.fxml"));
        		Parent root = loader.load();
        		scene = new Scene(root);
        		
        		//TODO THIS SHOULD SET THE IMAGE FOR THE GAME IN COMBININGGAME CLASS (via Game_layout?
        		// Access the controller and call a method 
                //CombiningGame controller3 = loader.getController();
        		//controller3.initData2(selectedPlayer);
        		
        		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
                }
    }
    
   
    
    
    
    
    
    
    
    private boolean isLevelSelected() {
        if (rectangle_level1.isVisible() || rectangle_level2.isVisible() || rectangle_level3.isVisible()) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    public void selectLevel(MouseEvent event) {
        if ((event.getSource() == rectangle_level1) || (event.getSource() == level1)) {
            hideLevelSelection();
            rectangle_level1.setVisible(true);
        } else if ((event.getSource() == rectangle_level2) || (event.getSource() == level2)) {
            hideLevelSelection();
            rectangle_level2.setVisible(true);
        } else if ((event.getSource() == rectangle_level3) || (event.getSource() == level3)) {
            hideLevelSelection();
            rectangle_level3.setVisible(true);
        }
    }

    
    private Player selectedPlayer;
    public void initData(Player player) { // THIS METHOD accepts a player to initialize the view
    	System.out.println("1 Now we have a new char  " + player);
    	selectedPlayer = player;
    	System.out.println("2 Now we have a new char  " + selectedPlayer);
    	yourplayer.setText(selectedPlayer.getCharacter());
    	System.out.println("3 Now we have a new char  " + yourplayer);
    	// SOMETHING WITH SHOW IT IN VIEW?
    	 if (yourplayer.getText() == "charac1") {
    	        hideImageLevel();
    	        charac1.setVisible(true);
    	    	}
    	    else if (yourplayer.getText() == "charac2") {
    	        hideImageLevel();
    	        charac2.setVisible(true);
    	    	}
    	    else if (yourplayer.getText() == "charac3") {
    	        hideImageLevel();
    	        charac3.setVisible(true);
    	    	}
    	    else if (yourplayer.getText() == "charac4") {
    	        hideImageLevel();
    	        charac4.setVisible(true);
    	    	}
    	  
    	    
    	
    }
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}


}
