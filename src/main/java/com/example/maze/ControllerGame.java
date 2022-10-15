package com.example.maze;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerGame implements Initializable {

    //Rectangle is 50x50
    private final Double rectangleSize = 50.;
    //The Rectangle is created, at position (250,250)
    private final Rectangle rectangle_player = new Rectangle(250, 250, rectangleSize, rectangleSize);
    //First snake tail created behind the head of the snake
    //  Rectangle snakeTail_1 = new Rectangle(snakeHead.getX() - snakeSize,snakeHead.getY(),snakeSize,snakeSize);

    //x and y position of the snake head different from starting position
    double xPos = rectangle_player.getLayoutX();
    double yPos = rectangle_player.getLayoutY();

    //Direction snake is moving at start
    private Direction direction = Direction.RIGHT;

    //List of all position of thew snake head
    private final List<Position> positions = new ArrayList<>();


    //Game ticks is how many times the snake have moved
    private int gameTicks = 0;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button startButton;

    //Timeline that is running the game every time the KeyFrame is called (0.3 s)
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.3), e -> {
        positions.add(new Position(rectangle_player.getX() + xPos, rectangle_player.getY() + yPos));
        moveSnakeHead(rectangle_player);

        gameTicks++;
    }));
    Image image;
    //Method called after the stage is loaded
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //  snakeBody.add(player);
        // Image img = new Image("resources/dog_left_1.png");
        // rectangle_player.setFill(new ImagePattern(img));

      image= getImage();

        //  rectangle_player.setFill(Color.RED);
        rectangle_player.setFill(new ImagePattern(image));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        anchorPane.getChildren().addAll(rectangle_player);
    }

    private Image getImage() {
        String path = "resources/player/dog_left_1.png";

        if (direction == Direction.UP && isActive)
            path = "resources/player/dog_up_1.png";
        if (direction == Direction.DOWN && isActive)
            path = "resources/player/dog_down_1.png";
        if (direction == Direction.LEFT && isActive)
            path = "resources/player/dog_left_1.png";
        if (direction == Direction.RIGHT && isActive)
            path = "resources/player/dog_right_1.png";
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
        //Restart not implemented yet
    }

    boolean isActive = false;

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
            image= getImage();
            rectangle_player.setFill(new ImagePattern(image));
        }
    }

    @FXML
    void moveSquareKeyReleased(KeyEvent event) {
        if (KeyEvent.KEY_RELEASED.equals(event.getEventType())) {
            isActive = false;

        }
    }


    //Snake head is moved in the direction specified
    private void moveSnakeHead(Rectangle snakeHead) {
        if (direction.equals(Direction.RIGHT) && isActive) {
            xPos = xPos + rectangleSize;
            snakeHead.setTranslateX(xPos);
        } else if (direction.equals(Direction.LEFT) && isActive) {
            xPos = xPos - rectangleSize;
            snakeHead.setTranslateX(xPos);
        } else if (direction.equals(Direction.UP) && isActive) {
            yPos = yPos - rectangleSize;
            snakeHead.setTranslateY(yPos);
        } else if (direction.equals(Direction.DOWN) && isActive) {
            yPos = yPos + rectangleSize;
            snakeHead.setTranslateY(yPos);
        }
    }
}

