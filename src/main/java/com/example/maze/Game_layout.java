package com.example.maze;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.Pane;

public class Game_layout {

		
	
	   @FXML

	    private Stage stage;
	    private Scene scene;

	    private Parent root;
	    
	    
	    public void switchToLevelSelection(MouseEvent event) {

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