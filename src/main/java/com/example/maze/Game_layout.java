package com.example.maze;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Game_layout {

		private int length = 61;
		private int height = 41;
		
		int i, j;
		
		private int[][] grid;
	
	   @FXML

	    private Stage stage;
	    private Scene scene;

	    private Parent root;
	    
	    
	    public Game_layout(int[][] grid) {
	    	this.grid = grid;
	    	
	    	
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
	    public void createGrid() {
	    	
	    	
	    	
	    	i = 0;
	    	j = 0;
	    	
	    	grid = new int [length][height];
	    	
	    	for (i = 0; i< length-1; i++) {
	    		for(j = 0; j< height-1; j++) {

	    			if (	(j == 0) || 
	    					(j == (height-2)) ||
	    					((i == 0) && (j>2)) ||
	    					((i == length - 2) && (j < height - 4))
	    					) 
	    			{
	    				grid[i][j]=8;
	    			}
	    			
	    			else {
	    				grid[i][j]=0;
	    			}

	    		}
	    	}
	    	for (i = 43; i < 45; i++ ) {
	    		for (j=1; j<3; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	
	    	for (i = 1; i < 33; i++ ) {
	    		for (j=3; j<5; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	
	    	for (i = 35; i < 41; i++ ) {
	    		for (j=3; j<5; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	
	    	for (i = 43; i < 45; i++ ) {
	    		for (j=3; j<5; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	
	    	for (i = 47; i < 49; i++ ) {
	    		for (j=3; j<5; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	
	    	for (i = 51; i < length - 2; i++ ) {
	    		for (j=3; j<5; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	
	    	for (i = 35; i < 37; i++ ) {
	    		for (j=5; j<7; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	
	    	for (i = 43; i < 45; i++ ) {
	    		for (j=5; j<7; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	
	    	for (i = 47; i < 49; i++ ) {
	    		for (j=5; j<7; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	
	    	for (i = 3; i < 37; i++ ) {
	    		for (j=7; j<9; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	
	    	for (i = 39; i < 57; i++ ) {
	    		for (j=7; j<9; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	
	    	for (i = 23; i < 25; i++ ) {
	    		for (j=9; j<11; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	
	    	for (i = 47; i < 49; i++ ) {
	    		for (j=9; j<17; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	
	    	for (i = 3; i < 21; i++ ) {
	    		for (j=11; j<13; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	
	    	for (i = 23; i < 45; i++ ) {
	    		for (j=11; j<13; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	
	    	for (i = 51; i < length - 2; i++ ) {
	    		for (j=11; j<13; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	
	    	for (i = 19; i < 21; i++ ) {
	    		for (j=13; j<25; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	
	    	for (i = 27; i < 29; i++ ) {
	    		for (j=13; j<25; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	
	    	for (i = 0; i < 17; i++ ) {
	    		for (j=15; j<17; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 21; i < 25; i++ ) {
	    		for (j=15; j<17; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	
	    	for (i = 29; i < 45; i++ ) {
	    		for (j=15; j<17; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 47; i < 57; i++ ) {
	    		for (j=15; j<17; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 15; i < 17; i++ ) {
	    		for (j=17; j<19; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 39; i < 41; i++ ) {
	    		for (j=17; j<19; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 3; i < 17; i++ ) {
	    		for (j=19; j<21; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 3; i < 17; i++ ) {
	    		for (j=19; j<21; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 23; i < 25; i++ ) {
	    		for (j=19; j<29; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 39; i < 59; i++ ) {
	    		for (j=19; j<21; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 31; i < 37; i++ ) {
	    		for (j=19; j<21; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 35; i < 37; i++ ) {
	    		for (j=21; j<23; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 3; i < 19; i++ ) {
	    		for (j=23; j<25; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 25; i < 33; i++ ) {
	    		for (j=23; j<25; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 35; i < 57; i++ ) {
	    		for (j=23; j<25; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 3; i < 5; i++ ) {
	    		for (j=25; j<27; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 15; i < 17; i++ ) {
	    		for (j=25; j<37; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 47; i < 49; i++ ) {
	    		for (j=25; j<27; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 3; i < 11; i++ ) {
	    		for (j=27; j<29; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 19; i < 23; i++ ) {
	    		for (j=27; j<29; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 27; i < 49; i++ ) {
	    		for (j=27; j<29; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 51; i < 57; i++ ) {
	    		for (j=27; j<29; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 27; i < 29; i++ ) {
	    		for (j=29; j<31; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 39; i < 41; i++ ) {
	    		for (j=29; j<31; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 51; i < 53; i++ ) {
	    		for (j=29; j<31; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 1; i < 13; i++ ) {
	    		for (j=31; j<33; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 17; i < 29; i++ ) {
	    		for (j=31; j<33; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 31; i < 41; i++ ) {
	    		for (j=31; j<33; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 43; i < 53; i++ ) {
	    		for (j=31; j<33; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 55; i < 59; i++ ) {
	    		for (j=31; j<33; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 11; i < 13; i++ ) {
	    		for (j=33; j<35; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 31; i < 33; i++ ) {
	    		for (j=33; j<39; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 43; i < 45; i++ ) {
	    		for (j=33; j<39; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 55; i < 57; i++ ) {
	    		for (j=33; j<35; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 3; i < 13; i++ ) {
	    		for (j=35; j<37; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	    	for (i = 17; i < 21; i++ ) {
	    		for (j=35; j<37; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	       	for (i = 23; i < 29; i++ ) {
	    		for (j=35; j<37; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	       	for (i = 35; i < 43; i++ ) {
	    		for (j=35; j<37; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	       	for (i = 47; i < 57; i++ ) {
	    		for (j=35; j<37; j++) {
	    			grid[i][j]=8;
	    		}
	    	}
	      	for (i = 23; i < 25; i++ ) {
		    	for (j=37; j<39; j++) {
		    		grid[i][j]=8;
		    	}
		    }
	
	    	System.out.println(grid[i][j]);
		
	    	
	    
		
	    	
	    	
	    	
	    	
	    }
	    

	    public boolean isWall(int x, int y) {
	    	if (grid[x][y] == 8) {
	    		return true;
	    	}
	    	else {
	    		return false;
	    	}
	    }
	    

}