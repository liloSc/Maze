package com.example.maze;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerGame implements Initializable {

    //Rectangle is 50x50
    private final Double rectangleSize = 50.;
    //The Rectangle is created, at position (250,250)
    private final Rectangle rectangle_player = new Rectangle(250, 250, rectangleSize, rectangleSize);

    //x and y position of the rectangle different from starting position
    double xPos = rectangle_player.getLayoutX();
    double yPos = rectangle_player.getLayoutY();
    private Direction direction = Direction.RIGHT;


    //Game ticks is how many times the rectangle have moved
    private int gameTicks = 0;

    @FXML
    private AnchorPane anchorPane;
    //Variable to look if key is pressed
    boolean isActive = false;


    //Timeline that is running the game every time the KeyFrame is called (0.1s)
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), e -> {
        movePlayer(rectangle_player);
        gameTicks++;
    }));
    Image image;

    //Method called after the stage is loaded
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        image = getCharacterImage();
        rectangle_player.setFill(new ImagePattern(image));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        anchorPane.getChildren().addAll(rectangle_player);
    }

    ControllerCharacterSelection controller = new ControllerCharacterSelection();

    private Image getCharacterImage() {

       // Player player = controller.getPlayerCharacter();
        System.out.println(player);
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
    Player player;
    public void setPlayer(Player p) {
        this.player = p;
    }
/*
    public Player getPlayer() {
        return this.player;
    }*/
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
    }
}

