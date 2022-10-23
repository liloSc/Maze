package com.example.maze;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class CombiningGame implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    ImageView doorClose;

    @FXML
    private GridPane gamelayoutgrid;
  

    // private FlowPane flowpane;

    private final double rectangleSize = 20.0;
    @FXML
    private Rectangle rectangleid;
    public Player gamePlayer;

    //x and y position of the rectangle different from starting position
    double xPos;
    double yPos;

    double xPosDoor;
    double yPosDoor;
    private Direction direction = Direction.RIGHT;


    //Variable to look if key is pressed
    boolean isActive = false;
    
    
    Game_layout gameLayout;
    private int[][] grid;
	private int length = 61;
	private int height = 41;
	int i, j;
	Image path = new Image("file:resources/player/GridB1.png",20,20, false, false);
	Image wall = new Image("file:resources/player/GridW1.png",20,20, false, false);
	
	ImageView backgroundView;


    //Timeline that is running the game every time the KeyFrame is called (0.1s)
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> {

        moveRectangle(rectangleid);

    }));

    Image image;

    public CombiningGame() {

    }

    //Method called after the stage is loaded
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	
        image = getCharacterImage();
        rectangleid.setFill(new ImagePattern(image));
        
	    
        doorClose.getLayoutBounds();
        xPosDoor = doorClose.getLayoutX();
        yPosDoor = doorClose.getLayoutY();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        //  playerOnDoor();
        System.out.println("DoorPos : [" + xPosDoor + ", " + yPosDoor + "]");
        System.out.println("CharPos : [" + xPos + ", " + yPos + "]");
        gamelayoutgrid.getChildren().addAll(printGrid());
    }


    private Image getCharacterImage() {

        String path = "resources/player/dog_left_1.png";

        if (isActive) {
       	 
                if (direction == Direction.UP)
                    path = "resources/player/dog_up_1.png";
                if (direction == Direction.DOWN)
                    path = "resources/player/dog_down_1.png";
                if (direction == Direction.LEFT)
                    path = "resources/player/dog_left_1.png";
                if (direction == Direction.RIGHT)
                    path = "resources/player/dog_right_1.png";

        }
        
 
        //   Image image;
        try {
            image = new Image(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return image;
    }

    
	//fill the grid with a simple background
	private Node printGrid() {
		grid = new int [length][height];
		gameLayout = new Game_layout(grid);

		

		GridPane GPane = new GridPane();
		i = 0;
		j = 0;
		for(i = 0; i<length-1; i++) {
			for (j = 0; j<height-1; j++) {
				
				if (gameLayout.isWall(i, j) == true) {
	
				backgroundView = new ImageView();
				backgroundView.setImage(wall);
				
				
				//System.out.println(grid[i][j]);
				}
				
				else {
					backgroundView = new ImageView();
					backgroundView.setImage(path);
					
					
					//System.out.println(grid[i][j]);
				}
				
				gamelayoutgrid.add(backgroundView, i, j);
                backgroundView.toBack();
			}
			
		}
		
		return GPane;
	}
	
    @FXML
    void start(MouseEvent event) {
        //TODO: Restart not implemented yet
    }


    //Change position with key pressed
    @FXML
    void moveSquareKeyPressed(KeyEvent event) {

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
            rectangleid.setFill(new ImagePattern(image));
        }
    }


    @FXML
    void moveSquareKeyReleased(KeyEvent event) {
        if (KeyEvent.KEY_RELEASED.equals(event.getEventType())) {
            isActive = false;

        }
    }

   
    private void moveRectangle(Rectangle rectangle) {
        if (direction.equals(Direction.RIGHT) && isActive) {
            xPos = xPos + rectangleSize;
            rectangle.setTranslateX(xPos);
        } else if (direction.equals(Direction.LEFT) && isActive) {
            xPos = xPos - rectangleSize;
            rectangle.setTranslateX(xPos);
        } else if (direction.equals(Direction.UP) && isActive) {
            yPos = yPos - rectangleSize;
            rectangle.setTranslateY(yPos);
        } else if (direction.equals(Direction.DOWN) && isActive) {
            yPos = yPos + rectangleSize;
            rectangle.setTranslateY(yPos);
        }
        //   System.out.println("[" + xPos + ", " + yPos + "]");
    }

    private void playerOnDoor() {
        if (isPlayerOnDoor()) {
            System.out.println("I'm on door");
            doorClose.setVisible(false);
        }
    }

    private boolean isPlayerOnDoor() {
        if (
                ((xPosDoor - rectangleSize <= xPos) &&
                        (xPosDoor + rectangleSize >= xPos)) &&
                        ((yPosDoor - rectangleSize <= yPos) &&
                                (yPosDoor + rectangleSize >= yPos))

        ) {
            return true;
        } else {
            return false;
        }
    }
    public void initData2(Player player) { // THIS METHOD accepts a player to initialize the view
    	gamePlayer = player;
    	System.out.println("3 Now we have a new char in game " + gamePlayer);
    }
   
}


