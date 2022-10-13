package com.example.maze.Model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class GamePanel extends Pane implements Runnable {
    final int originalTileSize = 16; //16 x 16 Tile --> Default Size Characters (for the most games)
    //16x 16 may look tiny on a big screen --> Scale it!
    final int scale = 3; //common scale
    public final int tileSize = originalTileSize * scale; //48 x 48 tilesize
    //How many tiles can be displayed
    public final int maxScreenColumn = 16; //Horizontal
    public final int maxScreenRow = 12; //Vertical
    public final int screenWidth = tileSize * maxScreenColumn;
    public final int screenHeight = tileSize * maxScreenRow;

    Thread gameThread;

    KeyHandler keyHandler = new KeyHandler();
    int playerx = 100;
    int playery = 100;
    int playerspeed = 4;

    public GamePanel() {
        this.setPrefSize(screenWidth, screenHeight);
        this.setBackground(Background.fill(Color.BLACK));
        //  this.addEventHandler();
        //    this.setDoubleBuffered(true); //Enabling this: Improve Games Rendering performance
        // this.addKeyListener(keyH);
        //  this.setFocusable(true);

    }

    public void run() {
        while (gameThread != null) {
            // System.out.println("Game loop");
            //Update information such as character position
            //    update();
            //2 Draw: Draw the screen
            paintComponent();
            // System.out.println("Run");
        }
    }


    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {

    }

    public void paintComponent() {
      /*  Group root = new Group();
        final Canvas canvas = new Canvas(250, 250);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLUE);
        gc.fillRect(75, 75, 100, 100);
      //  gc.dispose();
        root.getChildren().add(canvas);

*/

        GridPane gameBoard = new GridPane();
        gameBoard.setPrefSize(800, 600);
        final Canvas canvas = new Canvas(250, 250);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.BLUE);
        gc.fillRect(playerx, playery, 50, 50);

        gameBoard.getChildren().add(canvas);
        this.getChildren().add(gameBoard);

    }

}
