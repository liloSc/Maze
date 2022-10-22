

package com.example.maze;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;



public class ControllerGame_layout implements Initializable {
	
	@FXML
	private GridPane gamelayoutgrid;
	//public Main Main;
	@FXML
	private AnchorPane anchorpane;
	@FXML
	private FlowPane flowpane;

	
	private final double cellSize = 20;
	private final Rectangle player = new Rectangle(0, 0, cellSize, cellSize);
    int xPos;
    int yPos;
    private Direction direction = Direction.RIGHT;

    Image image;

  //Variable to look if key is pressed
    boolean isActive = false;
    
    private int gameTicks = 0;

    Game_layout gameLayout;
    private int[][] grid;
	private int length = 60;
	private int height = 40;
	Image path = new Image("file:resources/player/GridB1.png",20,20, false, false);
	Image wall = new Image("file:resources/player/GridW1.png",20,20, false, false);
	
	ImageView backgroundView;

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> {
        movePlayer(player);
        gameTicks++;
        
    }));
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		image = getCharacterImage();
	    player.setFill(new ImagePattern(image));
		GridPane.setConstraints(player, 0, 0);

		xPos = GridPane.getRowIndex(player);
		yPos = GridPane.getColumnIndex(player);
		System.out.println("CharPos : [" + xPos + ", " + yPos + "]");
	    


		
System.out.println("teeeeeeeest");
	

		Button enemy = new Button();
		GridPane.setRowIndex(enemy, 20);
		GridPane.setColumnIndex(enemy, 20);
		
		
		Label label = new Label("test");
	    GridPane.setConstraints(label, 30,30);
	  //  movePlayer(player);
	    
	    gamelayoutgrid.getChildren().addAll(label, enemy, player, printGrid());
	    
	    //flowpane.getChildren().addAll(gamelayoutgrid);
        
	    
	    
       timeline.setCycleCount(Animation.INDEFINITE);
       timeline.play();
	    
	    
		
	}
	
	//fill the grid with a simple background
	private Node printGrid() {
		grid = new int [length][height];
		gameLayout = new Game_layout(grid);
		

		GridPane GPane = new GridPane();
		int i = 0;
		int j = 0;
		for(i = 0; i<length; i++) {
			for (j = 0; j<height; j++) {
				
				if (grid[i][j] == 1) {
	
				backgroundView = new ImageView();
				backgroundView.setImage(wall);
				
				
				//System.out.println(grid[i][j]);
				}
				
				else {
					backgroundView = new ImageView();
					backgroundView.setImage(path);
					
					
					//System.out.println(grid[i][j]);
				}
				System.out.println(grid[i][j]);
				gamelayoutgrid.add(backgroundView, i, j);
			}
			
		}
		
		return GPane;
	}
	
	 ControllerCharacterSelection controller = new ControllerCharacterSelection();
	 
	    private Image getCharacterImage() {

	    	System.out.println("I am in GetImage");
	        // Player player = controller.getPlayerCharacter();
	        // System.out.println(player);
	         String path = "resources/player/dog_left_1.png";
	       /*  if(player==Player.Char2){

	             path = "resources/player/heart.png";

	         }
	         if(player==Player.Char3){

	             path = "resources/player/banana.png";

	         }
	         if(player==Player.Char4){

	             path = "resources/player/pacman.png";

	         }*/
	         if (isActive) {
	        	 System.out.println("hello");
	             //Dog
	         //    if(player==Player.Char1){
	                 if (direction == Direction.UP)
	                     path = "resources/player/dog_up_1.png";
	                 if (direction == Direction.DOWN)
	                     path = "resources/player/dog_down_1.png";
	                 if (direction == Direction.LEFT)
	                     path = "resources/player/dog_left_1.png";
	                 if (direction == Direction.RIGHT)
	                     path = "resources/player/dog_right_1.png";

	           //  }

	             }
	         Image image;
	         try {
	             image = new Image(new FileInputStream(path));
	         } catch (FileNotFoundException e) {
	             throw new RuntimeException(e);
	         }
	         return image;
	     }
	    @FXML
	    void start(MouseEvent event) {
	        //TODO: Restart not implemented yet
	    }

    //Change position with key pressed
    @FXML
    void moveSquareKeyPressed(KeyEvent event) {
    	System.out.println("I am in KeyPressed");
        if (KeyEvent.KEY_PRESSED.equals(event.getEventType())) {
            isActive = true;

            if (event.getCode().equals(KeyCode.UP)) {
                direction = Direction.UP;
                isActive = true;
            } else if (event.getCode().equals(KeyCode.DOWN)) {
                direction = Direction.DOWN;
                isActive = true;
            } else if (event.getCode().equals(KeyCode.LEFT)) {
                direction = Direction.LEFT;
                isActive = true;
            } else if (event.getCode().equals(KeyCode.RIGHT)) {
                direction = Direction.RIGHT;
                isActive = true;
            }
            image = getCharacterImage();
            player.setFill(new ImagePattern(image));
        }
    }
    
    Player player1;
    public void setPlayer(Player p) {
        this.player1 = p;
    }
    
    @FXML
    void moveSquareKeyReleased(KeyEvent event) {
        if (KeyEvent.KEY_RELEASED.equals(event.getEventType())) {
            isActive = false;

        }
    }
    

   
    
	//Player is moved in the direction specified
    private void movePlayer(Rectangle player1) {
    	
    	System.out.println("I am in MovePlayer");
        if (direction.equals(Direction.RIGHT) && isActive) {
            xPos = (int) (xPos + cellSize);
            GridPane.setConstraints(player1, xPos, yPos);
        } else if (direction.equals(Direction.LEFT) && isActive) {
            xPos = (int) (xPos - cellSize);
            GridPane.setConstraints(player1, xPos, yPos);
        } else if (direction.equals(Direction.UP) && isActive) {
            yPos = (int) (yPos - cellSize);
            GridPane.setConstraints(player1, xPos, yPos);
        } else if (direction.equals(Direction.DOWN) && isActive) {
            yPos = (int) (yPos + cellSize);
            GridPane.setConstraints(player1, xPos, yPos);
        }
        System.out.println("[" + xPos + ", " + yPos + "]");

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
