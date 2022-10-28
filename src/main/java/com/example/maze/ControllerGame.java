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
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javax.swing.text.Position;

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
    @FXML
    private Label label_lifePlayer;
    @FXML
    private Label label_healthEnemy1;
    @FXML
    private Label label_healthEnemy2;
    @FXML
    private Label label_healthEnemy3;
    @FXML
    private Label label_foundKeys;


    @FXML
    private ProgressBar bar_healthPlayer;
    Image image_enemy;

    public Enemy gameClassEnemy1;
    public Enemy gameClassEnemy2;
    public Enemy gameClassEnemy3;


    public Player gamePlayer = new Player(null, 10);
    public Enemy myfighter1 = new Fighter1(20, 30);
    public Enemy myfighter2 = new Fighter2(20, 30);
    public Enemy myfighter3 = new Fighter3(20, 30);

    private int numberOfEnemies;
    private int numberOfKeys = 4;

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
    DraggableMaker draggableMaker = new DraggableMaker();


    public ControllerGame() {

    }

    List<Rectangle> listEnemies = new ArrayList<>();

    /**
     * Method to set the enemies randomly to the game
     * Depending on the selected level
     *
     * @param numberOfEnemies
     */
    public void setRandomEnemiesToGame(int numberOfEnemies) {
        // int numberOfEnemies=2;
        Random randomNumber = new Random();
        int enemyYPosition = randomNumber.nextInt(grid_length - 1);
        int enemyXPosition = randomNumber.nextInt(grid_height - 1);
        for (int i = 0; i < numberOfEnemies; ) {
            Rectangle enemy1 = new Rectangle(20, 20);
            image_enemy = getEnemyImage();
            enemy1.setFill(new ImagePattern(image_enemy));

            //   enemy1.setFill(Color.RED);
            // Obtain a number between [0 - 49].

            if (!gameLayout.isWall(enemyYPosition, enemyXPosition)) { //Checks if tile is a wall
                gamelayoutgrid.add(enemy1, enemyYPosition, enemyXPosition);
                //  gamelayoutgrid.add(enemy1, 5, 1);
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

    List<Rectangle> listHealth = new ArrayList<>();
    // int[][] healthPosition= {4,5,"c","a1","b1","c1","a2","b2","c2"};
    //  = new int[4][5];
    //Horizontal, Vertikal
    int[][] healthPosition = {{14, 2}, {15, 22}, {2, 10}, {30, 10}, {28, 25}, {30, 30}, {45, 30}, {45, 15}};

    /**
     * Method to set the Hearts in the game at defined position
     *
     * @param numberOfEnemies
     */
    public void setHealthCharger(int numberOfEnemies) {
        if (numberOfEnemies == 1)//Level 1
            healthPosition = new int[][]{{14, 2}, {33, 3}, {49, 4}, {40, 5}, {2, 10}, {2, 10}, {20, 10}, {35, 10}, {49, 10}, {14, 2}, {15, 22}, {2, 10}, {30, 10}, {28, 25}, {30, 30}, {45, 30}, {45, 15}, {2, 20}, {2, 30}, {9, 38}, {25, 38}, {35, 38}, {50, 38}, {56, 30}, {45, 30},};
        //{15, 22}, {2, 10}, {30, 10}, {28, 25}, {30, 30}, {45, 30}, {45, 15}

        if (numberOfEnemies == 2)//Level 2
            healthPosition = new int[][]{{14, 2}, {15, 22}, {2, 10}, {30, 10}, {28, 25}, {30, 30}, {45, 30}, {45, 15}};


        if (numberOfEnemies == 3)//Level 2
            healthPosition = new int[][]{{14, 2}, {15, 22}, {2, 10}, {30, 10}, {28, 25}, {30, 30}, {45, 30}, {45, 15}};


        //= new int[][]{{14, 2}, {15, 22}, {2, 10}, {30, 10}, {28, 25}, {30, 30}, {45, 30}, {45, 15}};

        for (int i = 0; i < healthPosition.length; i++) {
            Rectangle heart = new Rectangle(20, 20);
            Image image_heart = null;
            String path = "resources/objects/heart.png";
            try {
                image_heart = new Image(new FileInputStream(path));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            heart.setFill(new ImagePattern(image_heart));
            //  for(int j = 0 ; j < 2; j++){
            //    System.out.print(healthPosition[i][j] + " ");
            gamelayoutgrid.add(heart, healthPosition[i][0], healthPosition[i][1]);
            listHealth.add(heart);
            //  }

        }


    }

    List<Rectangle> listOfKeys = new ArrayList<>();

    /**
     * Method to set the Keys Randomly in the grid
     *
     * @param numberOfKeysToSet
     */
    public void setRandomlyKeys(int numberOfKeysToSet) {
        //  int numberOfKeys = 1;
        // Obtain a number between [0 - 49].
        Random randomNumber = new Random();
        int keyPositionY = randomNumber.nextInt(grid_length - 1);
        int keyPositionX = randomNumber.nextInt(grid_height - 1);
        //    int keyPositionY = 1;
        //     int keyPositionX = 5;
        for (int i = 0; i < numberOfKeysToSet; ) {
            Rectangle key = new Rectangle(20, 20);
            Image image_key = null;
            String path = "resources/objects/key.png";
            try {
                image_key = new Image(new FileInputStream(path));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            key.setFill(new ImagePattern(image_key));


            if (!gameLayout.isWall(keyPositionY, keyPositionX)) { //Checks if tile is a wall
                gamelayoutgrid.add(key, keyPositionY, keyPositionX);
                //  gamelayoutgrid.add(enemy1, 5, 1);
                listOfKeys.add(key);
                keyPositionY = randomNumber.nextInt(grid_length - 2);
                keyPositionX = randomNumber.nextInt(grid_height - 2);
                i++;
            } else {
                if (keyPositionY < grid_length - 2) {
                    keyPositionY++;
                } else {
                    keyPositionY = 0;
                }
                if (keyPositionX < grid_height - 2) {
                    keyPositionX++;
                } else {
                    keyPositionX = 0;
                }


            }
        }

    }

    //Method called after the stage is loaded
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        grid = new int[grid_length][grid_height];
        image_door.setVisible(false);
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
        label_lifePlayer.setText(String.valueOf(gamePlayer.getHealth()));


    }

    /**
     * Method to get the image of the enemy
     *
     * @return
     */
    private Image getEnemyImage() {
        String path = "resources/enemy/ghost.png";
        try {
            image_enemy = new Image(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return image_enemy;
    }

    /**
     * Method to get the image of character
     * This function changes the image of the character depending on it's direction
     */
    private Image getCharacterImage() {
        String path = "resources/player/Rob/rob_front.png";
        if (playerIsActive) {
            if (gamePlayer.getCharacter().equals("charac1")) {
                path = "resources/player/Rob/rob_left.png";
                // System.out.println("Get Character Image of " + );
                if (direction == Direction.UP)
                    path = "resources/player/Rob/rob_back.png";
                if (direction == Direction.DOWN)
                    path = "resources/player/Rob/rob_down.png";
                if (direction == Direction.LEFT)
                    path = "resources/player/Rob/rob_left.png";
                if (direction == Direction.RIGHT)
                    path = "resources/player/Rob/rob_right.png";
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
                    path = "resources/player/Boba/bob_back.png";
                if (direction == Direction.DOWN)
                    path = "resources/player/Boba/bob_down.png";
                if (direction == Direction.LEFT)
                    path = "resources/player/Boba/bob_left.png";
                if (direction == Direction.RIGHT)
                    path = "resources/player/Boba/bob_right.png";

            } else if (gamePlayer.getCharacter().equals("charac4")) {
                path = "resources/player/Cube/cube_front.png";
                if (direction == Direction.UP)
                    path = "resources/player/Cube/cube_back.png";
                if (direction == Direction.DOWN)
                    path = "resources/player/Cube/cube_down.png";
                if (direction == Direction.LEFT)
                    path = "resources/player/Cube/cube_left.png";
                if (direction == Direction.RIGHT)
                    path = "resources/player/Cube/cube_right.png";
            }
        }

        try {
            image_player = new Image(new FileInputStream(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return image_player;

    }

    /**
     * In this function the model Game_layout.java is called and for each value of the grid, a GridPane is filled with
     * either an image of the path or of the wall (here represented with threes)
     */
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


    /**
     * Change position with key pressed
     *
     * @param event
     * @throws IOException
     */
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
            if (playerIsMoving) reduceLife(false);
            //bar_healthPlayer.setProgress(bar_healthPlayer.getProgress() - 0.01);
            image_player = getCharacterImage();
            rectangle_player.setFill(new ImagePattern(image_player));

            rectangle_player.toFront();
            isPlayerOnDoor(event);
            isPlayerOnKey();
            isPlayerOnHeart();

            if (isPlayerNextToEnemy()) shootOnEnemy(event);
            //   if (isPlayerNextToEnemy()) System.out.print(nextenemy  + " test ");
            //   if (isPlayerNextToEnemy()) System.out.print(whichcharacterisclose  + " whichcharacterisclose ");
        }
    }

    /**
     * @param event
     */
    @FXML
    void stopMovePlayerKeyReleased(KeyEvent event) {
        if (KeyEvent.KEY_RELEASED.equals(event.getEventType())) {
            playerIsActive = false;
            playerIsMoving = false;
        }
    }

    /**
     * Method to check if the player is on door
     *
     * @param event
     */
    private void isPlayerOnDoor(KeyEvent event) {
        //get Position of Player
        int playerColumn = gamelayoutgrid.getColumnIndex(rectangle_player);
        int playerRow = gamelayoutgrid.getRowIndex(rectangle_player);

        //getPositionDoor
        int doorColumn = gamelayoutgrid.getColumnIndex(image_door);
        int doorRow = gamelayoutgrid.getRowIndex(image_door);

        if (playerColumn == doorColumn && playerRow == doorRow) {
            if (foundKeys == numberOfKeys) switchToTask(event);
        }
    }

    /**
     * Check if Player is on Heart
     */
    private void isPlayerOnHeart() {
        //get Position of Player
        int playerColumn = gamelayoutgrid.getColumnIndex(rectangle_player);
        int playerRow = gamelayoutgrid.getRowIndex(rectangle_player);

        for (Rectangle health : listHealth) {
            //getPositionDoor
            int healthColumn = gamelayoutgrid.getColumnIndex(health);
            int healthRow = gamelayoutgrid.getRowIndex(health);
            if (playerColumn == healthColumn && playerRow == healthRow) {
                augmentLife();
                //    key.setVisible(false);
                //   foundKeys++;
                if (numberOfEnemies == 3) {
                    gamelayoutgrid.getChildren().remove(health); //Remove Key when Player is on it
                    listHealth.remove(health);
                    return;
                }


            }
        }

    }

    /**
     * Method to check if Player is on KeyObject
     */

    private void isPlayerOnKey() {
        //get Position of Player
        int playerColumn = gamelayoutgrid.getColumnIndex(rectangle_player);
        int playerRow = gamelayoutgrid.getRowIndex(rectangle_player);

        for (Rectangle key : listOfKeys) {
            //getPositionDoor
            int keyColumn = gamelayoutgrid.getColumnIndex(key);
            int keyRow = gamelayoutgrid.getRowIndex(key);
            if (playerColumn == keyColumn && playerRow == keyRow) {
                key.setVisible(false);

                gamelayoutgrid.getChildren().remove(key); //Remove Key when Player is on it
                label_foundKeys.setText(String.valueOf(++foundKeys));
                revealDoor();
                listOfKeys.remove(key);
                return;
            }

        }
    }

    int foundKeys = 0;

    /**
     * Method to reveal the door if all keys were found
     */
    private void revealDoor() {
        if (foundKeys == numberOfKeys) {
            image_door.setVisible(true);

        }

    }

    /**
     * Shoot on Enemy if you are next to it
     *
     * @param event
     */
    private void shootOnEnemy(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            //   System.out.println("Shoot on Enemy");
            if (!(nextenemy1 == null)) {
                if (myfighter1.getHealth(1) > 1) {
                    myfighter1.setHealth1(myfighter1.getHealth(1) - 1);
                    label_healthEnemy1.setText(String.valueOf(myfighter1.getHealth(1)));
                } else {
                    removeEnemy(label_healthEnemy1, nextEnemyRectangle);


                }
            } else if (!(nextenemy2 == null)) {
                if (myfighter2.getHealth(2) > 1) {
                    myfighter2.setHealth2(myfighter2.getHealth(2) - 1);
                    label_healthEnemy2.setText(String.valueOf(myfighter2.getHealth(2)));
                } else {
                    removeEnemy(label_healthEnemy2, nextEnemyRectangle);

                }
            } else {
                if (myfighter3.getHealth(3) > 1) {
                    myfighter3.setHealth3(myfighter3.getHealth(3) - 1);
                    label_healthEnemy3.setText(String.valueOf(myfighter3.getHealth(3)));
                } else {
                    removeEnemy(label_healthEnemy3, nextEnemyRectangle);

                }
            }
        }
    }

    /**
     * Method to remove the enemy after he dies
     *
     * @param label_healthEnemy1
     * @param rectangle
     */
    private void removeEnemy(Label label_healthEnemy1, Rectangle rectangle) {
        gamelayoutgrid.getChildren().remove(rectangle);
        anchorPane.getChildren().remove(label_healthEnemy1);
        listEnemies.remove(rectangle);
        augmentLife();
    }


    Enemy nextenemy1;
    Enemy nextenemy2;
    Enemy nextenemy3;
    Rectangle nextEnemyRectangle;


    /**
     * Method to check if player is next to enemy
     *
     * @return
     */
    private boolean isPlayerNextToEnemy() {
        //get Position of Player
        int playerColumn = gamelayoutgrid.getColumnIndex(rectangle_player);
        int playerRow = gamelayoutgrid.getRowIndex(rectangle_player);
        int i = 1;
        for (Rectangle e : listEnemies
        ) {
            int enemyColumn = gamelayoutgrid.getColumnIndex(e);
            int enemyRow = gamelayoutgrid.getRowIndex(e);

            if (((playerColumn == enemyColumn) || (playerColumn + 1 == enemyColumn) || (playerColumn - 1 == enemyColumn)) && ((playerRow == enemyRow) || (playerRow + 1 == enemyRow) || (playerRow - 1 == enemyRow))) {
                if (playerIsMoving) reduceLife(true);
                if (i == 1) {
                    nextenemy1 = myfighter1;
                    nextenemy2 = null;
                    nextenemy3 = null;
                }
                //   System.out.print(nextenemy1 + " test ");

                if (i == 2) {
                    nextenemy2 = myfighter1;
                    nextenemy1 = null;
                    nextenemy3 = null;
                }
                //   System.out.print(nextenemy2 + " tester ");
                if (i == 3) {
                    nextenemy3 = myfighter1;
                    nextenemy1 = null;
                    nextenemy2 = null;
                }
                //  System.out.print(nextenemy3 + " tester3 ");

                nextEnemyRectangle = e;
                return true;
            }
            i++;
        }
        return false;

    }

    /**
     * Method to reduce life of the player
     * when he moves or when he is next to Enemy
     *
     * @param isNextToEnemy
     */
    private void reduceLife(boolean isNextToEnemy) {
        double loosesHealth = 0.01;
        // double loosesHealth = 0;
        if (isNextToEnemy) loosesHealth = 0.1;
        if (/*gamePlayer.getHealth() > 1 && */bar_healthPlayer.getProgress() > 0.05) {
            //  gamePlayer.setHealth(gamePlayer.getHealth() - 1);
            // System.out.print("Life " + gamePlayer.getLife());
            //  label_lifePlayer.setText(String.valueOf(gamePlayer.getHealth()));
            //   bar_healthPlayer.setProgress(bar_healthPlayer.getProgress() - (gamePlayer.getHealth() / 100));
            bar_healthPlayer.setProgress(bar_healthPlayer.getProgress() - loosesHealth);
        } else {
            try {
                switchToGameOver();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Method to Augment the Life of the player
     */
    private void augmentLife() {
        //double gainsHealth = 0.2;
        bar_healthPlayer.setProgress(1);
        /*if (bar_healthPlayer.getProgress() < 0.51) {
            bar_healthPlayer.setProgress(bar_healthPlayer.getProgress() + gainsHealth);
        } else {
            bar_healthPlayer.setProgress(1);
        }*/
    }

    private Stage stage;
    private Scene scene;

    /**
     * Method to switch Scene
     *
     * @param event
     */
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
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }


    public void initData(Player player) { // THIS METHOD accepts a player to initialize the view
        this.gamePlayer = player;

    }


    /**
     * Method to initialize the enemy
     *
     * @param ourEnemy1
     * @param ourEnemy2
     * @param ourEnemy3
     */
    public void initEnemy(Enemy ourEnemy1, Enemy ourEnemy2, Enemy ourEnemy3) {
        this.myfighter1 = ourEnemy1;
        this.myfighter2 = ourEnemy2;
        this.myfighter3 = ourEnemy3;
        this.numberOfEnemies = Integer.parseInt(myfighter3.getLevelEnemy());
        //	EnemyLabel1.setText(GameClassEnemy1.getPersonTraits()); 

    }


    /*
     * This function is called when the user press enter.
     * The instruction pane, visible at the beginning is set non visible.
     * The objects present in the maze are initialize as well as their event handlers
     *
     */
    @FXML
    public void hideInstructionPane(KeyEvent event) throws IOException {
        if (event.getCode().equals((KeyCode.ENTER))) {
            instructionPane.setVisible(false);
            rectangle_player.setVisible(true);
            draggableMaker.makeDraggable(rectangle_player);
            rectangle_player.requestFocus();
            movePlayerKeyPressed(event);
            stopMovePlayerKeyReleased(event);
            setRandomEnemiesToGame(numberOfEnemies);
            setRandomlyKeys(numberOfKeys);
            setHealthCharger(numberOfEnemies);
            label_healthEnemy1.setText(String.valueOf(myfighter1.getHealth(1)));  // translate enemy to label
            label_healthEnemy2.setText(String.valueOf(myfighter2.getHealth(2)));
            label_healthEnemy3.setText(String.valueOf(myfighter3.getHealth(3)));
            if (numberOfEnemies >= 1) label_healthEnemy1.setVisible(true);
            if (numberOfEnemies >= 2) label_healthEnemy2.setVisible(true);
            if (numberOfEnemies >= 3) label_healthEnemy3.setVisible(true);


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

    public class DraggableMaker {

        private double mouseAnchorX;
        private double mouseAnchorY;
        private double startPositionX;
        private double startPositionY;

        public void makeDraggable(Node node) {
            node.setOnMousePressed(mouseEvent -> {
                //    startPositionX = node.getTranslateX();
                //  startPositionY = node.getTranslateY();
                //  startPositionX = gamelayoutgrid.getRowIndex(rectangle_player);
                //  startPositionY = gamelayoutgrid.getColumnIndex(rectangle_player);
                //  System.out.println(" " + startPositionX + " " + startPositionY);

                mouseAnchorX = mouseEvent.getSceneX() - node.getTranslateX();
                mouseAnchorY = mouseEvent.getSceneY() - node.getTranslateY();

            });

            node.setOnMouseDragged(mouseEvent -> {
                int newXPosition = (int) Math.round((mouseEvent.getSceneX() - mouseAnchorX) / 20);
                int newYPosition = (int) Math.round((mouseEvent.getSceneY() - mouseAnchorY) / 20);

             //   node.setTranslateX(mouseEvent.getSceneX() - mouseAnchorX);
             //   node.setTranslateY(mouseEvent.getSceneY() - mouseAnchorY);
            });
            node.setOnMouseReleased(mouseEvent -> {
                        //  System.out.print(" X " + (mouseEvent.getSceneX() - mouseAnchorX) / 20);
                        //  System.out.print(" Y " + (mouseEvent.getSceneY() - mouseAnchorY) / 20);
                        int newXPosition = (int) Math.round((mouseEvent.getSceneX() /*- mouseAnchorX*/) / 20);
                        int newYPosition = (int) Math.round((mouseEvent.getSceneY()/* - mouseAnchorY*/) / 20);
                        // System.out.print("New X " + newXPosition);
                        //  System.out.print(" New Y " + newYPosition);
                        // if (newXPosition < 0) newXPosition = newXPosition * (-1);
                        //  if (newYPosition < 0) newYPosition = newYPosition * (-1);
                        //   System.out.print("New X " + newXPosition);
                        //   System.out.print(" New Y " + newYPosition);
                       // node.setTranslateX(newXPosition);
                     //   node.setTranslateY(newYPosition);
                        gamelayoutgrid.setRowIndex(rectangle_player,newYPosition );
                        gamelayoutgrid.setColumnIndex(rectangle_player, newXPosition);
                        //    gamelayoutgrid.setRowIndex(node, 3);
                        //    gamelayoutgrid.setColumnIndex(node, 3);

                      //  startPositionX = gamelayoutgrid.getRowIndex(rectangle_player);
                       // startPositionY = gamelayoutgrid.getColumnIndex(rectangle_player);
                        System.out.println("Position " + newXPosition + " " + newYPosition);
                    }
            );


        }
    }
}



