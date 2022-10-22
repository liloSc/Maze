

package com.example.maze;



import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


public class ControllerGame_layout implements Initializable {
	
	@FXML
	private GridPane gamelayoutgrid;
	//public Main Main;
	@FXML
	private AnchorPane anchorpane;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
System.out.println("teeeeeeeest");
	
	

		Button enemy = new Button();
		GridPane.setRowIndex(enemy, 20);
		GridPane.setColumnIndex(enemy, 20);
		
		
		Label label = new Label("test");
	    GridPane.setConstraints(label, 25, 25); // column=2 row=0

	    GridPane.setConstraints(label, 30,30);
		
	    gamelayoutgrid.getChildren().addAll(label, enemy);
		
	}

	
	
	
	//DONT DELETE
//	public List<Tile> getsurroundingtiles(Tile tile){ // this list calculates the number of bombs 
//		List<Tile> surroundingtiles = new ArrayList<>();	// definition of list
//		
//		int[] points = new int[] {			//x,y values relative of tile
//				-1, -1,
//				-1, 0,
//				-1, 1,
//				0, -1,
//				0, 1,
//				1,-1,
//				1, 0,
//				1, 1,  // there are 8 tiles touching the tile
//		};
//		for (int i=0; i<points.length; i++) {  // every two for new x and y
//			int x = points[i]; //starting from 0 in list for x
//			int y = points[++i];	//increment first by 1 for y
//			
//			int newX = tile.x + x;
//			int newY = tile.y + y;
//			
//			if  (newX >= 0 && newX < X_TILES    // lower bound and upper bound
//					&& newY >= 0 && newY < Y_TILES ) {
//				
//				surroundingtiles.add(canvas[newX][newY]); // place into canvas
//				}	
//		}
//		return surroundingtiles;
//		
		
		
	//}
	
	
	
	
	
	
	
	
}
