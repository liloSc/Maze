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

		private int length = 60;
		private int height = 40;
		
		private int[][] grid;
	
	   @FXML

	    private Stage stage;
	    private Scene scene;

	    private Parent root;
	    
	    
	    public Game_layout(int[][] gridc) {
	    	this.grid = gridc;
	    	
	    	
	    	createGrid();
	    	System.out.println(grid);
	    }
	    
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
	    
	    
	    //This is the model for the grid. At each position [i,j] the value is 0
	    private int[][] createGrid() {
	    	
	    	
	    	
	    	int i = 0;
	    	int j = 0;
	    	
	    	grid = new int [length][height];
	    	
	    	for (i = 0; i< length; i++) {
	    		for(j = 0; j< height; j++) {
	    			grid[i][j]=0;
	    			//System.out.println(grid[i][j]);
	    		}
	    	}
			return grid;
	    	
	    	}
	
	
	
	
}