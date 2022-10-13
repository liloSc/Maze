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

public class  ControllerGame implements Initializable {

    //A snake body part is 50x50
    private final Double snakeSize = 50.;
    //The head of the snake is created, at position (250,250)
    private final Rectangle snakeHead = new Rectangle(250,250,snakeSize,snakeSize);
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
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.3),e ->{
        positions.add(new Position(snakeHead.getX() +xPos, snakeHead.getY() + yPos));
        moveSnakeHead(snakeHead);
        for (int i = 1; i < snakeBody.size(); i++) {
            moveSnakeTail(snakeBody.get(i),i);
        }
        gameTicks++;
    }));

    //Method called after the stage is loaded
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        snakeBody.add(snakeHead);
        snakeHead.setFill(Color.RED);

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        //  snakeBody.add(snakeTail_1);

        //   anchorPane.getChildren().addAll(snakeHead,snakeTail_1);
        anchorPane.getChildren().addAll(snakeHead);
    }


    @FXML
    void start(MouseEvent event) {
        //Restart not implemented yet
    }

    //Change position with key pressed
    @FXML
    void moveSquareKeyPressed(KeyEvent event) {
        if(event.getCode().equals(KeyCode.UP) && direction != Direction.DOWN){
            direction = Direction.UP;
        } else if(event.getCode().equals(KeyCode.DOWN) && direction != Direction.UP){
            direction = Direction.DOWN;
        }else if(event.getCode().equals(KeyCode.LEFT) && direction != Direction.RIGHT){
            direction = Direction.LEFT;
        }else if(event.getCode().equals(KeyCode.RIGHT) && direction != Direction.LEFT){
            direction = Direction.RIGHT;
        }
    }

    //Create another snake body part
    @FXML
    void addBodyPart(ActionEvent event) {
        addSnakeTail();
    }

    //Snake head is moved in the direction specified
    private void moveSnakeHead(Rectangle snakeHead){
        if(direction.equals(Direction.RIGHT)){
            xPos = xPos + snakeSize;
            snakeHead.setTranslateX(xPos);
        } else if(direction.equals(Direction.LEFT)) {
            xPos = xPos - snakeSize;
            snakeHead.setTranslateX(xPos);
        }else if(direction.equals(Direction.UP)) {
            yPos = yPos - snakeSize;
            snakeHead.setTranslateY(yPos);
        }else if(direction.equals(Direction.DOWN)) {
            yPos = yPos + snakeSize;
            snakeHead.setTranslateY(yPos);
        }
    }

    //A specific tail is moved to the position of the head x game ticks after the head was there
    private void moveSnakeTail(Rectangle snakeTail, int tailNumber){
        double yPos = positions.get(gameTicks - tailNumber + 1).getYPos() - snakeTail.getY();
        double xPos = positions.get(gameTicks - tailNumber + 1).getXPos() - snakeTail.getX();
        snakeTail.setTranslateX(xPos);
        snakeTail.setTranslateY(yPos);
    }

    //New snake tail is created and added to the snake and the anchor pane
    private void addSnakeTail(){
        Rectangle rectangle = snakeBody.get(snakeBody.size() - 1);
        Rectangle snakeTail = new Rectangle(
                snakeBody.get(1).getX() + xPos + snakeSize,
                snakeBody.get(1).getY() + yPos,
                snakeSize,snakeSize);
        snakeBody.add(snakeTail);
        anchorPane.getChildren().add(snakeTail);
    }
}

