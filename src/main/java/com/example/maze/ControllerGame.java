package com.example.maze;

import com.example.maze.Direction;
import com.example.maze.Position;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerGame implements Initializable {

    //A snake body part is 50x50
    private final Double snakeSize = 50.;
    //The head of the snake is created, at position (250,250)
    private final Rectangle snakeHead = new Rectangle(250, 250, snakeSize, snakeSize);
    //First snake tail created behind the head of the snake
    //  Rectangle snakeTail_1 = new Rectangle(snakeHead.getX() - snakeSize,snakeHead.getY(),snakeSize,snakeSize);

    //x and y position of the snake head different from starting position
    double xPos = snakeHead.getLayoutX();
    double yPos = snakeHead.getLayoutY();

    //Direction snake is moving at start
    private Direction direction = Direction.RIGHT;

    //List of all position of thew snake head
    private final List<Position> positions = new ArrayList<>();

    //List of all snake body parts
    private final ArrayList<Rectangle> snakeBody = new ArrayList<>();

    //Game ticks is how many times the snake have moved
    private int gameTicks = 0;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button startButton;

    //Timeline that is running the game every time the KeyFrame is called (0.3 s)
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.3), e -> {
        positions.add(new Position(snakeHead.getX() + xPos, snakeHead.getY() + yPos));
        moveSnakeHead(snakeHead);

        gameTicks++;
    }));

    //Method called after the stage is loaded
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        snakeBody.add(snakeHead);
        snakeHead.setFill(Color.RED);

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        anchorPane.getChildren().addAll(snakeHead);
    }


    @FXML
    void start(MouseEvent event) {
        //Restart not implemented yet
    }
boolean isActive=false;
    //Change position with key pressed
    @FXML
    void moveSquareKeyPressed(KeyEvent event) {
        if (KeyEvent.KEY_PRESSED.equals(event.getEventType())) {


            if (event.getCode().equals(KeyCode.UP)) {
                direction = Direction.UP;
                isActive=true;
            } else if (event.getCode().equals(KeyCode.DOWN)) {
                direction = Direction.DOWN;
                isActive=true;
            } else if (event.getCode().equals(KeyCode.LEFT)) {
                direction = Direction.LEFT;
                isActive=true;
            } else if (event.getCode().equals(KeyCode.RIGHT)) {
                direction = Direction.RIGHT;
                isActive=true;
            }
        }
    }
    @FXML
    void moveSquareKeyReleased(KeyEvent event) {
        if (KeyEvent.KEY_RELEASED.equals(event.getEventType())) {
            isActive=false;

        }
    }


    //Snake head is moved in the direction specified
    private void moveSnakeHead(Rectangle snakeHead) {
        if (direction.equals(Direction.RIGHT)&&   isActive) {
            xPos = xPos + snakeSize;
            snakeHead.setTranslateX(xPos);
        } else if (direction.equals(Direction.LEFT)&&   isActive) {
            xPos = xPos - snakeSize;
            snakeHead.setTranslateX(xPos);
        } else if (direction.equals(Direction.UP)&&   isActive) {
            yPos = yPos - snakeSize;
            snakeHead.setTranslateY(yPos);
        } else if (direction.equals(Direction.DOWN)&&   isActive) {
            yPos = yPos + snakeSize;
            snakeHead.setTranslateY(yPos);
        }
    }
}

