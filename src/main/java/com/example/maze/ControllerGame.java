package com.example.maze;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ControllerGame implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    ImageView image_door;

    @FXML
    private GridPane gamelayoutgrid;


    @FXML
    private BorderPane instructionPane;

    @FXML
    private Rectangle rectangle_player;

    public Enemy GameClassEnemy1;
    public Enemy GameClassEnemy2;
    public Enemy GameClassEnemy3;
    public Label EnemyLabel1;
    public Label EnemyLabel2;
    public Label EnemyLabel3;

    public Player gamePlayer = new Player(null, 10);
    private int numberOfEnemies;

    double xPosDoor;
    double yPosDoor;
    private Direction direction = Direction.RIGHT;

    //Variable to look if key is pressed
    boolean playerIsActive = false;
    boolean playerIsMoving = false;

    Game_layout gameLayout;
    private int[][] grid;
    private int grid_length = 61;
    private int grid_height = 41;
    int i, j;
    Image image_grass = new Image("file:resources/player/Grass.png", 20, 20, false, false);
    Image image_wall = new Image("file:resources/player/Three.png", 20, 20, false, false);
    ImageView image_background;
    Image image_player;

    public ControllerGame() {

    }

    List<Rectangle> listEnemies = new ArrayList<>();

    public void setRandomEnemiesToGame(int numberOfEnemies) {
        // int numberOfEnemies=2;
        Random randomNumber = new Random();
        int enemyYPosition = randomNumber.nextInt(grid_length - 1);
        int enemyXPosition = randomNumber.nextInt(grid_height - 1);
        for (int i = 0; i < numberOfEnemies; ) {
            Rectangle enemy1 = new Rectangle(20, 20);
            enemy1.setFill(Color.RED);
            // Obtain a number between [0 - 49].

            if (!gameLayout.isWall(enemyYPosition, enemyXPosition)) { //Checks if tile is a wall
                gamelayoutgrid.add(enemy1, enemyYPosition, enemyXPosition);
                listEnemies.add(enemy1);
                enemyYPosition = randomNumber.nextInt(grid_length - 1);
                enemyXPosition = randomNumber.nextInt(grid_height - 1);
                i++;
            } else {
                if (enemyYPosition < grid_length - 1) {
                    enemyYPosition++;
                } else {
                    enemyYPosition = 0;
                }
                if (enemyXPosition < grid_height - 1) {
                    enemyXPosition++;
                } else {
                    enemyXPosition = 0;
                }


            }
        }


    }

    //Method called after the stage is loaded
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        grid = new int[grid_length][grid_height];
        gameLayout = new Game_layout(grid);
        image_player = getCharacterImage();
        rectangle_player.setFill(new ImagePattern(image_player));
        rectangle_player.setVisible(false);
        instructionPane.toFront();
        gamelayoutgrid.setConstraints(rectangle_player, 0, 1);
        image_door.getLayoutBounds();
        xPosDoor = image_door.getLayoutX();
        yPosDoor = image_door.getLayoutY();
        gamelayoutgrid.getChildren().addAll(printGrid());

    }

    private Image getCharacterImage() {
        String path = "resources/player/dog_left_1.png";
        if (playerIsActive) {
            if (gamePlayer.getCharacter().equals("charac1")) {
                path = "resources/player/dog_left_1.png";
                // System.out.println("Get Character Image of " + );
                if (direction == Direction.UP)
                    path = "resources/player/dog_up_1.png";
                if (direction == Direction.DOWN)
                    path = "resources/player/dog_down_1.png";
                if (direction == Direction.LEFT)
                    path = "resources/player/dog_left_1.png";
                if (direction == Direction.RIGHT)
                    path = "resources/player/dog_right_1.png";
                //    if (gamePlayer.getCharacter().equals("charac2")) path = "resources/player/heart.png";

            } else if (gamePlayer.getCharacter().equals("charac2")) {
                path = "resources/player/Luchu/luchu_front.png";

                if (direction == Direction.UP)
                    path = "resources/player/Luchu/luchu_back1.png";
                if (direction == Direction.DOWN)
                    path = "resources/player/Luchu/luchu_front.png";
                if (direction == Direction.LEFT)
                    path = "resources/player/Luchu/luchu_left2.png";
                if (direction == Direction.RIGHT)
                    path = "resources/player/Luchu/luchu_right2.png";

            } else if (gamePlayer.getCharacter().equals("charac3")) {
                path = "resources/player/boba_front.png";

                if (direction == Direction.UP)
                    path = "resources/player/Boba/boba_back.png";
                if (direction == Direction.DOWN)
                    path = "resources/player/Boba/boba_down.png";
                if (direction == Direction.LEFT)
                    path = "resources/player/Boba/boba_left.png";
                if (direction == Direction.RIGHT)
                    path = "resources/player/Boba/boba_right1.png";

            } else if (gamePlayer.getCharacter().equals("charac4")) {
                path = "resources/player/banana.png";
            }
        }

        try {
            image_player = new Image(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return image_player;

    }

    //fill the grid with a simple background
    private Node printGrid() {
        GridPane grid = new GridPane();
        i = 0;
        j = 0;
        for (i = 0; i < grid_length - 1; i++) {
            for (j = 0; j < grid_height - 1; j++) {

                if (gameLayout.isWall(i, j) == true) {
                    image_background = new ImageView();
                    image_background.setImage(image_wall);

                } else {
                    image_background = new ImageView();
                    image_background.setImage(image_grass);
                }

                gamelayoutgrid.add(image_background, i, j);
                image_background.toBack();
            }
        }
        return grid;
    }


    //Change position with key pressed
    @FXML
    void movePlayerKeyPressed(KeyEvent event) throws IOException {
        int i = gamelayoutgrid.getColumnIndex(rectangle_player);
        int j = gamelayoutgrid.getRowIndex(rectangle_player);

        if (KeyEvent.KEY_PRESSED.equals(event.getEventType())) {
            playerIsActive = true;
            if (event.getCode().equals(KeyCode.UP)) {
                direction = Direction.UP;
                if (gameLayout.isWall(i, j - 1) == false) {
                    gamelayoutgrid.setRowIndex(rectangle_player, GridPane.getRowIndex(rectangle_player) - 1);
                }

                playerIsMoving = true;
            } else if (event.getCode().equals(KeyCode.DOWN)) {
                direction = Direction.DOWN;

                if (gameLayout.isWall(i, j + 1) == false) {
                    gamelayoutgrid.setRowIndex(rectangle_player, GridPane.getRowIndex(rectangle_player) + 1);
                }
                playerIsMoving = true;

            } else if (event.getCode().equals(KeyCode.LEFT)) {
                direction = Direction.LEFT;

                if (gameLayout.isWall(i - 1, j) == false) {
                    gamelayoutgrid.setColumnIndex(rectangle_player, GridPane.getColumnIndex(rectangle_player) - 1);
                }
                playerIsMoving = true;
            } else if (event.getCode().equals(KeyCode.RIGHT)) {
                direction = Direction.RIGHT;

                if (gameLayout.isWall(i + 1, j) == false) {
                    gamelayoutgrid.setColumnIndex(rectangle_player, GridPane.getColumnIndex(rectangle_player) + 1);
                }
                playerIsMoving = true;
            }
            image_player = getCharacterImage();
            rectangle_player.setFill(new ImagePattern(image_player));

            rectangle_player.toFront();
            isPlayerOnDoorLilo(event);

            if (isPlayerNextToEnemy()) shootOnEnemy(event);
        }
    }


    @FXML
    void stopMovePlayerKeyReleased(KeyEvent event) {
        if (KeyEvent.KEY_RELEASED.equals(event.getEventType())) {
            playerIsActive = false;
            playerIsMoving = false;
        }
    }


    private void isPlayerOnDoorLilo(KeyEvent event) {
        //get Position of Player
        int playerColumn = gamelayoutgrid.getColumnIndex(rectangle_player);
        int playerRow = gamelayoutgrid.getRowIndex(rectangle_player);

        //getPositionDoor
        int doorColumn = gamelayoutgrid.getColumnIndex(image_door);
        int doorRow = gamelayoutgrid.getRowIndex(image_door);

        if (playerColumn == doorColumn && playerRow == doorRow) {
            switchToTask(event);
        }
    }

    private void shootOnEnemy(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            System.out.println("Shoot on Enemy");
        }
    }

    private boolean isPlayerNextToEnemy() {
        //get Position of Player
        int playerColumn = gamelayoutgrid.getColumnIndex(rectangle_player);
        int playerRow = gamelayoutgrid.getRowIndex(rectangle_player);

        for (Rectangle e : listEnemies
        ) {
            int enemyColumn = gamelayoutgrid.getColumnIndex(e);
            int enemyRow = gamelayoutgrid.getRowIndex(e);

            if (((playerColumn == enemyColumn) || (playerColumn + 1 == enemyColumn) || (playerColumn - 1 == enemyColumn)) && ((playerRow == enemyRow) || (playerRow + 1 == enemyRow) || (playerRow - 1 == enemyRow))) {
                if (playerIsMoving) reduceLife();
                return true;
            }
        }
        return false;

    }

    private void reduceLife() {
        if (gamePlayer.getLife() > 1) {
            gamePlayer.setLife(gamePlayer.getLife() - 1);
            System.out.print("Life " + gamePlayer.getLife());
        } else {
            try {
                switchToGameOver();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Stage stage;
    private Scene scene;

    public void switchToTask(KeyEvent event) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("unlockDoor.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        scene = new Scene(root);


        //TODO THIS SHOULD SET THE IMAGE FOR THE GAME IN COMBININGGAME CLASS (via Game_layout?
        // Access the controller and call a method
        //CombiningGame controller3 = loader.getController();
        //controller3.initData2(selectedPlayer);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }


    public void initData(Player player) { // THIS METHOD accepts a player to initialize the view
        this.gamePlayer = player;
        //  System.out.println("3 Now we have a new char in game " + gamePlayer.getCharacter());
    }


    // 3 off them are not right now needed, maybe later?
    public void initEnemy(Enemy ourEnemy1, Enemy ourEnemy2, Enemy ourEnemy3) {
        this.GameClassEnemy1 = ourEnemy1;
        this.GameClassEnemy2 = ourEnemy2;
        this.GameClassEnemy3 = ourEnemy3;
        //   System.out.println("Level in game is " + GameClassEnemy1.getLevelEnemy());
        //  System.out.println("Level in game is " + GameClassEnemy2.getLevelEnemy());
        //   System.out.println("Level in game is " + GameClassEnemy3.getLevelEnemy());
        this.numberOfEnemies = Integer.parseInt(GameClassEnemy3.getLevelEnemy());
        //	EnemyLabel1.setText(GameClassEnemy1.getPersonTraits());  // ourEnemy can be whatever
        //	EnemyLabel2.setText(GameClassEnemy2.getPersonTraits());
        //	EnemyLabel3.setText(GameClassEnemy3.getPersonTraits());
    }


    @FXML
    public void hideInstructionPane(KeyEvent event) throws IOException {
        if (event.getCode().equals((KeyCode.ENTER))) {
            instructionPane.setVisible(false);
            rectangle_player.setVisible(true);
            rectangle_player.requestFocus();
            movePlayerKeyPressed(event);
            stopMovePlayerKeyReleased(event);
            setRandomEnemiesToGame(numberOfEnemies);

        }
    }

    public void switchToGameOver() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("gameover.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);
        stage = (Stage) rectangle_player.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }
}



