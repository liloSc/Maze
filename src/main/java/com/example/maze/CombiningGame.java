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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class CombiningGame implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    ImageView doorClose;

    @FXML
    private GridPane gamelayoutgrid;
    @FXML
    private FlowPane flowpane;
    @FXML
    private Rectangle rectangleid;

    //Rectangle is 50x50
    private final double rectangleSize = 20.0;
    //The Rectangle is created, at position (250,250)
    private final Rectangle rectangle_player = new Rectangle(250, 250, rectangleSize, rectangleSize);


    //x and y position of the rectangle different from starting position
    double xPos;
    double yPos;

    double xPosDoor;
    double yPosDoor;
    private Direction direction = Direction.RIGHT;



    //Variable to look if key is pressed
    boolean isActive = false;


    //Timeline that is running the game every time the KeyFrame is called (0.1s)
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> {
    //    movePlayer(rectangle_player);
        moveCircle(rectangleid);
        //   gameTicks++;
    }));

    Image image;

    public CombiningGame() {

    }

    //Method called after the stage is loaded
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        image = getCharacterImage();
        rectangle_player.setFill(new ImagePattern(image));
        anchorPane.getChildren().addAll(rectangle_player);
        rectangle_player.getLayoutBounds();
        xPos = rectangle_player.getX();
        yPos = rectangle_player.getY();
        doorClose.getLayoutBounds();
        xPosDoor = doorClose.getLayoutX();
        yPosDoor = doorClose.getLayoutY();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        //  playerOnDoor();
        System.out.println("DoorPos : [" + xPosDoor + ", " + yPosDoor + "]");
        System.out.println("CharPos : [" + xPos + ", " + yPos + "]");
    }

    public void Play() {
        movePlayer(rectangle_player);
        // gameTicks++;
    }


    private Image getCharacterImage() {
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
        Image characterImage;
        try {
            characterImage = new Image(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return characterImage;
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
            rectangle_player.setFill(new ImagePattern(image));
        }
    }

    @FXML
    void moveSquareKeyPressedBall(KeyEvent event) {
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
            rectangle_player.setFill(new ImagePattern(image));
        }
    }
    @FXML
    void moveSquareKeyReleased(KeyEvent event) {
        if (KeyEvent.KEY_RELEASED.equals(event.getEventType())) {
            isActive = false;

        }
    }

    //Player is moved in the direction specified
    private void movePlayer(Rectangle player) {
        if (direction.equals(Direction.RIGHT) && isActive) {
            xPos = xPos + rectangleSize;
            player.setTranslateX(xPos);
        } else if (direction.equals(Direction.LEFT) && isActive) {
            xPos = xPos - rectangleSize;
            player.setTranslateX(xPos);
        } else if (direction.equals(Direction.UP) && isActive) {
            yPos = yPos - rectangleSize;
            player.setTranslateY(yPos);
        } else if (direction.equals(Direction.DOWN) && isActive) {
            yPos = yPos + rectangleSize;
            player.setTranslateY(yPos);
        }
        System.out.println("[" + xPos + ", " + yPos + "]");
    }
    private void moveCircle(Rectangle c) {
        if (direction.equals(Direction.RIGHT) && isActive) {
            xPos = xPos + rectangleSize;
            c.setTranslateX(xPos);
        } else if (direction.equals(Direction.LEFT) && isActive) {
            xPos = xPos - rectangleSize;
            c.setTranslateX(xPos);
        } else if (direction.equals(Direction.UP) && isActive) {
            yPos = yPos - rectangleSize;
            c.setTranslateY(yPos);
        } else if (direction.equals(Direction.DOWN) && isActive) {
            yPos = yPos + rectangleSize;
            c.setTranslateY(yPos);
        }
        System.out.println("[" + xPos + ", " + yPos + "]");
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
}


