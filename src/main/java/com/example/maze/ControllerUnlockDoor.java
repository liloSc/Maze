package com.example.maze;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ControllerUnlockDoor {
    private Stage stage;
    private Scene scene;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;
    @FXML
    private Label label4;

    @FXML
    private Slider slider1;

    @FXML
    private Slider slider2;

    @FXML
    private Slider slider3;
    @FXML
    private Slider slider4;
    @FXML
    private Label label_timer;

    @FXML
    private StackPane pane_timer;

    int quest_number = 1234;

    boolean isNewGame = true;

    public void onSliderChanged(MouseEvent event) {
        if (isNewGame) {
            setTimer();
            isNewGame = false;
        }
        Slider slider = (Slider) event.getSource();
        String id = slider.getId();

        int sliderValue = (int) slider.getValue();

        if (id.equals("slider1")) label1.setText(String.valueOf(sliderValue));
        if (id.equals("slider2")) label2.setText(String.valueOf(sliderValue));
        if (id.equals("slider3")) label3.setText(String.valueOf(sliderValue));
        if (id.equals("slider4")) label4.setText(String.valueOf(sliderValue));
        if (isCodeCorrect()) {
            System.out.println("Code is correct");
        } else {
            //     System.out.println(" NOT correct");
        }
    }

    public boolean isCodeCorrect() {

        try {
            int character1 = Integer.parseInt(label1.getText());
            int character2 = Integer.parseInt(label2.getText());
            int character3 = Integer.parseInt(label3.getText());
            int character4 = Integer.parseInt(label4.getText());
            String quest = String.valueOf(quest_number);
            String userinput = String.valueOf(character1) + String.valueOf(character2) + String.valueOf(character3) + String.valueOf(character4);
            if (quest.equals(userinput)) return true;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    DateFormat timeFormat = new SimpleDateFormat("mm:ss.SSS");
    long startTime = System.currentTimeMillis();
    long intoLong = 10000; //Time that the user has for the quest in milliseconds 30s --> 30 000 ms
    Timeline timeline = new Timeline(
            new KeyFrame(
                    Duration.millis(50),
                    event -> {
                        final long diff = System.currentTimeMillis() - startTime;
                        final long timeReverse = intoLong - diff;
                        if(timeReverse>0) {
                            label_timer.setText(timeFormat.format(timeReverse));
                        }else{
                            label_timer.setText(timeFormat.format(0));
                            try {
                                switchToGameOver();

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    }
            )
    );

    public void setTimer() {

        //  label_timer.setText(timeFormat.format(0));
        timeline.setCycleCount(Animation.INDEFINITE);
        startTime = System.currentTimeMillis();
        timeline.play();

    }

    public void switchToGameOver() throws IOException {
timeline.pause();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("startpage.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);


        //TODO THIS SHOULD SET THE IMAGE FOR THE GAME IN COMBININGGAME CLASS (via Game_layout?
        // Access the controller and call a method
        //CombiningGame controller3 = loader.getController();
        //controller3.initData2(selectedPlayer);

        stage = (Stage) label_timer.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }


}