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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class CombiningGame implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    ImageView doorClose;

    @FXML
    private GridPane gamelayoutgrid;


    @FXML
    private BorderPane instructionPane;


    private final double rectangleSize = 20.0;
    @FXML
    private Rectangle rectangleid;

    // public Player gamePlayer;
    public Enemy GameClassEnemy1;
    public Enemy GameClassEnemy2;
    public Enemy GameClassEnemy3;
    public Label EnemyLabel1;
    public Label EnemyLabel2;
    public Label EnemyLabel3;


    public Player gamePlayer = new Player(null, 10);

    // ControllerCharacterSelection controllerCharacterSelection= new ControllerCharacterSelection();

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
    Image path = new Image("file:resources/player/Grass.png", 20, 20, false, false);
    Image wall = new Image("file:resources/player/Three.png", 20, 20, false, false);

    ImageView backgroundView;


    Image image;

    public CombiningGame() {

    }

    List<Rectangle> listEnemies = new ArrayList<>();

    public void setRandomEnemiesToGame(int numberOfEnemies) {
        // int numberOfEnemies=2;
        Random rand = new Random();
        int enemyYPosition = rand.nextInt(length - 1);
        int enemyXPosition = rand.nextInt(height - 1);
        for (int i = 0; i < numberOfEnemies; ) {
            Rectangle enemy1 = new Rectangle(20, 20);
            enemy1.setFill(Color.RED);
            // Obtain a number between [0 - 49].

            if (!gameLayout.isWall(enemyYPosition, enemyXPosition)) { //Checks if tile is a wall
                gamelayoutgrid.add(enemy1, enemyYPosition, enemyXPosition);
                listEnemies.add(enemy1);
                enemyYPosition = rand.nextInt(length - 1);
                enemyXPosition = rand.nextInt(height - 1);
                i++;
            } else {
                if (enemyYPosition < length - 1) {
                    enemyYPosition++;
                } else {
                    enemyYPosition = 0;
                }
                if (enemyXPosition < height - 1) {
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
        //   gamePlayer = new Player("charac2");
        //    gamePlayer.setCharacter("charac2");

        grid = new int[length][height];
        gameLayout = new Game_layout(grid);

        image = getCharacterImage();
        rectangleid.setFill(new ImagePattern(image));
        rectangleid.setVisible(false);

        instructionPane.toFront();

        gamelayoutgrid.setConstraints(rectangleid, 0, 1);
        setRandomEnemiesToGame(5);
        doorClose.getLayoutBounds();
        xPosDoor = doorClose.getLayoutX();
        yPosDoor = doorClose.getLayoutY();
        gamelayoutgrid.getChildren().addAll(printGrid());

        //   System.out.println("DoorPos : [" + xPosDoor + ", " + yPosDoor + "]");
        //   System.out.println("CharPos : [" + xPos + ", " + yPos + "]");

    }

    private Image getCharacterImage() {
        String path = "resources/player/dog_left_1.png";
        if (isActive) {
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
            image = new Image(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return image;

    }


    //fill the grid with a simple background
    private Node printGrid() {


        GridPane GPane = new GridPane();
        i = 0;
        j = 0;
        for (i = 0; i < length - 1; i++) {
            for (j = 0; j < height - 1; j++) {

                if (gameLayout.isWall(i, j) == true) {

                    backgroundView = new ImageView();
                    backgroundView.setImage(wall);


                    //System.out.println(grid[i][j]);
                } else {
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
    void movePlayerKeyPressed(KeyEvent event) throws IOException {
        int i = gamelayoutgrid.getColumnIndex(rectangleid);
        int j = gamelayoutgrid.getRowIndex(rectangleid);

        //  System.out.println(i + " " + j);

        if (KeyEvent.KEY_PRESSED.equals(event.getEventType())) {

            isActive = true;
            if (event.getCode().equals(KeyCode.UP)) {
                direction = Direction.UP;


                if (gameLayout.isWall(i, j - 1) == false) {
                    gamelayoutgrid.setRowIndex(rectangleid, GridPane.getRowIndex(rectangleid) - 1);
                }
            } else if (event.getCode().equals(KeyCode.DOWN)) {
                direction = Direction.DOWN;

                if (gameLayout.isWall(i, j + 1) == false) {
                    gamelayoutgrid.setRowIndex(rectangleid, GridPane.getRowIndex(rectangleid) + 1);
                }
            } else if (event.getCode().equals(KeyCode.LEFT)) {
                direction = Direction.LEFT;

                if (gameLayout.isWall(i - 1, j) == false) {
                    gamelayoutgrid.setColumnIndex(rectangleid, GridPane.getColumnIndex(rectangleid) - 1);
                }
            } else if (event.getCode().equals(KeyCode.RIGHT)) {
                direction = Direction.RIGHT;

                if (gameLayout.isWall(i + 1, j) == false) {
                    gamelayoutgrid.setColumnIndex(rectangleid, GridPane.getColumnIndex(rectangleid) + 1);
                }
            }
            image = getCharacterImage();
            rectangleid.setFill(new ImagePattern(image));

            rectangleid.toFront();
            isPlayerOnDoorLilo(event);

            if (isPlayerNextToEnemy()) shootOnEnemy(event);
        }

    }


    @FXML
    void moveSquareKeyReleased(KeyEvent event) {
        if (KeyEvent.KEY_RELEASED.equals(event.getEventType())) {
            isActive = false;

        }
    }


    private void isPlayerOnDoorLilo(KeyEvent event) throws IOException {
        //get Position of Player
        int playerColumn = gamelayoutgrid.getColumnIndex(rectangleid);
        int playerRow = gamelayoutgrid.getRowIndex(rectangleid);

        //getPositionDoor
        int doorColumn = gamelayoutgrid.getColumnIndex(doorClose);
        int doorRow = gamelayoutgrid.getRowIndex(doorClose);

        if (playerColumn == doorColumn && playerRow == doorRow) {
            switchToTask(event);
        } else {
            //   System.out.println("Not on Door");
        }


    }

    private void shootOnEnemy(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            System.out.println("Shoot on Enemy");
        }
    }

    private boolean isPlayerNextToEnemy() {
        //get Position of Player
        int playerColumn = gamelayoutgrid.getColumnIndex(rectangleid);
        int playerRow = gamelayoutgrid.getRowIndex(rectangleid);

        for (Rectangle e : listEnemies
        ) {
            int enemyColumn = gamelayoutgrid.getColumnIndex(e);
            int enemyRow = gamelayoutgrid.getRowIndex(e);

            if (((playerColumn == enemyColumn) || (playerColumn + 1 == enemyColumn) || (playerColumn - 1 == enemyColumn)) && ((playerRow == enemyRow) || (playerRow + 1 == enemyRow) || (playerRow - 1 == enemyRow))) {

                //   System.out.println("Next to Enemy");
                reduceLife();
                return true;
            }
        }
        return false;

    }

    private void reduceLife() {
        gamePlayer.setLife(gamePlayer.getLife() - 1);
        //  gamePlayer.setLife(5);
        System.out.print("Life " + gamePlayer.getLife());
    }

    private Stage stage;
    private Scene scene;

    public void switchToTask(KeyEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("unlockDoor.fxml"));
        Parent root = loader.load();
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


    public void initData2(Player player) { // THIS METHOD accepts a player to initialize the view
        this.gamePlayer = player;
        //  System.out.println("3 Now we have a new char in game " + gamePlayer.getCharacter());
    }


    public void initEnemy(Enemy ourEnemy1, Enemy ourEnemy2, Enemy ourEnemy3) {
        this.GameClassEnemy1 = ourEnemy1;
        this.GameClassEnemy2 = ourEnemy2;
        this.GameClassEnemy3 = ourEnemy3;
        //	EnemyLabel1.setText(GameClassEnemy1.getPersonTraits());  // ourEnemy can be whatever
        //	EnemyLabel2.setText(GameClassEnemy2.getPersonTraits());
        //	EnemyLabel3.setText(GameClassEnemy3.getPersonTraits());
    }


    @FXML
    public void hideInstructionPane(KeyEvent event) throws IOException {
        if (event.getCode().equals((KeyCode.ENTER))) {
            instructionPane.setVisible(false);
            rectangleid.setVisible(true);
            rectangleid.requestFocus();
            movePlayerKeyPressed(event);
            moveSquareKeyReleased(event);

        }
    }


}



