

package com.example.maze;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class ControllerUnlockDoor implements Initializable {
    private Stage stage;
    private Scene scene;

    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private Label label3;
    @FXML private Label label4;

    @FXML
    private Label label_codeToUnlock;

    
    @FXML private GridPane gridpane1;
    @FXML private GridPane gridpane2;
    @FXML private GridPane gridpane3;
    @FXML private GridPane gridpane4;

    @FXML
    private Label label_timer;

	@FXML private Text top;
	@FXML private Text top2;
	@FXML private Text top3;
	@FXML private Text top4;
	
	@FXML private Text mid;
	@FXML private Text mid2;
	@FXML private Text mid3;
	@FXML private Text mid4;
	
	
	@FXML private Text bottom;
	@FXML private Text bottom2;
	@FXML private Text bottom3;
	@FXML private Text bottom4;
	
	String s_top;
	String s_mid;
	String s_bottom;
	
	int nb_top;
	int nb_mid;
	int nb_bottom;
	
	String idtop;
	String idmid;
	String idbottom;
	
	codeArray codeArray;
	private int[] myArray;
	int i =0;
	int j = 0;
	
	private double deltaY;
	

    @FXML
    private StackPane pane_timer;

    int quest_number = 1234;

    boolean isNewGame = true;

    /**Method to check if the slider was changed
     *
     * @param event
     */
    /*
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
          //  System.out.println("Code is correct");
            try {
                switchToGameWon();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            //     System.out.println(" NOT correct");
        }
    }

*/
    /**Check if Code is correct
     *
     * @return
     */
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
                        if (timeReverse > 0) {
                            label_timer.setText(timeFormat.format(timeReverse));
                        } else {
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

    /**Method to set the timer
     *
     */
    public void setTimer() {
        timeline.setCycleCount(Animation.INDEFINITE);
        startTime = System.currentTimeMillis();
        timeline.play();

    }

    /**Method to go to Game Over Screen
     *
     * @throws IOException
     */
    public void switchToGameOver() throws IOException {
        timeline.pause();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("gameover.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);
        stage = (Stage) label_timer.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }

    /**
     * Method to Game Won
     * @throws IOException
     */
    public void switchToGameWon() throws IOException {
        timeline.pause();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("gamewon.fxml"));
        Parent root = loader.load();
        scene = new Scene(root);
        stage = (Stage) label_timer.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }

    
    @FXML void scroll(ScrollEvent event) {
        if (isNewGame) {
            setTimer();
            isNewGame = false;
        }

		deltaY = ((ScrollEvent) event).getDeltaY();
		System.out.println("scroll");
		if(event.getSource() == gridpane1) {
			
			if(deltaY > 0) {
				
				if(j == 0) {
					nb_top = myArray[9];
					s_top = Integer.toString(nb_top);
					top.setText(s_top);
			
					nb_mid = myArray[0];
					s_mid = Integer.toString(nb_mid);
					mid.setText(s_mid);
					label1.setText(s_mid);
					
					nb_bottom = myArray[1];
					s_bottom = Integer.toString(nb_bottom);
					bottom.setText(s_bottom);
					
					j = 9;
					
				} else if (j == 9) {
					nb_top = myArray[8];
					s_top = Integer.toString(nb_top);
					top.setText(s_top);
					
					nb_mid = myArray[9];
					s_mid = Integer.toString(nb_mid);
					mid.setText(s_mid);
					label1.setText(s_mid);
					
					nb_bottom = myArray[0];
					s_bottom = Integer.toString(nb_bottom);
					bottom.setText(s_bottom);
					
					j = j-1;
					
				} else {
					nb_top = myArray[j-1];
					s_top = Integer.toString(nb_top);
					top.setText(s_top);
					
					nb_mid = myArray[j];
					s_mid = Integer.toString(nb_mid);
					mid.setText(s_mid);
					label1.setText(s_mid);
					
					nb_bottom = myArray[j+1];
					s_bottom = Integer.toString(nb_bottom);
					bottom.setText(s_bottom);
					
					j = j - 1;
					
				}
				System.out.println(myArray[j]);
			}
			
			if(deltaY<0) {
				if(j==9) {
					nb_top = myArray[8];
					s_top = Integer.toString(nb_top);
					top.setText(s_top);
					
					nb_mid = myArray[9];
					s_mid = Integer.toString(nb_mid);
					mid.setText(s_mid);
					label1.setText(s_mid);
					
					nb_bottom = myArray[0];
					s_bottom = Integer.toString(nb_bottom);
					bottom.setText(s_bottom);
					
					j = 0;
				} else if (j == 0) {
					nb_top = myArray[9];
					s_top = Integer.toString(nb_top);
					top.setText(s_top);
					
					nb_mid = myArray[0];
					s_mid = Integer.toString(nb_mid);
					mid.setText(s_mid);
					label1.setText(s_mid);
					
					nb_bottom = myArray[1];
					s_bottom = Integer.toString(nb_bottom);
					bottom.setText(s_bottom);
					
					j = j+1;
					
				} else {
					nb_top = myArray[j-1];
					s_top = Integer.toString(nb_top);
					top.setText(s_top);
					
					nb_mid = myArray[j];
					s_mid = Integer.toString(nb_mid);
					mid.setText(s_mid);
					label1.setText(s_mid);
					
					nb_bottom = myArray[j+1];
					s_bottom = Integer.toString(nb_bottom);
					bottom.setText(s_bottom);
					
					j = j+1;
					
				}
				System.out.println(myArray[j]);
			}
		}
		
		if(event.getSource() == gridpane2) {
			
			if(deltaY > 0) {
				
				if(j == 0) {
					nb_top = myArray[9];
					s_top = Integer.toString(nb_top);
					top2.setText(s_top);
			
					nb_mid = myArray[0];
					s_mid = Integer.toString(nb_mid);
					mid2.setText(s_mid);
					label2.setText(s_mid);
					
					nb_bottom = myArray[1];
					s_bottom = Integer.toString(nb_bottom);
					bottom2.setText(s_bottom);
					
					j = 9;
					
				} else if (j == 9) {
					nb_top = myArray[8];
					s_top = Integer.toString(nb_top);
					top2.setText(s_top);
					
					nb_mid = myArray[9];
					s_mid = Integer.toString(nb_mid);
					mid2.setText(s_mid);
					label2.setText(s_mid);
					
					nb_bottom = myArray[0];
					s_bottom = Integer.toString(nb_bottom);
					bottom2.setText(s_bottom);
					
					j = j-1;
					
				} else {
					nb_top = myArray[j-1];
					s_top = Integer.toString(nb_top);
					top2.setText(s_top);
					
					nb_mid = myArray[j];
					s_mid = Integer.toString(nb_mid);
					mid2.setText(s_mid);
					label2.setText(s_mid);
					
					nb_bottom = myArray[j+1];
					s_bottom = Integer.toString(nb_bottom);
					bottom2.setText(s_bottom);
					
					j = j - 1;
					
				}
				System.out.println(myArray[j]);
			}
			
			if(deltaY<0) {
				if(j==9) {
					nb_top = myArray[8];
					s_top = Integer.toString(nb_top);
					top2.setText(s_top);
					
					nb_mid = myArray[9];
					s_mid = Integer.toString(nb_mid);
					mid2.setText(s_mid);
					label2.setText(s_mid);
					
					nb_bottom = myArray[0];
					s_bottom = Integer.toString(nb_bottom);
					bottom2.setText(s_bottom);
					
					j = 0;
				} else if (j == 0) {
					nb_top = myArray[9];
					s_top = Integer.toString(nb_top);
					top2.setText(s_top);
					
					nb_mid = myArray[0];
					s_mid = Integer.toString(nb_mid);
					mid2.setText(s_mid);
					label2.setText(s_mid);
					
					nb_bottom = myArray[1];
					s_bottom = Integer.toString(nb_bottom);
					bottom2.setText(s_bottom);
					
					j = j+1;
					
				} else {
					nb_top = myArray[j-1];
					s_top = Integer.toString(nb_top);
					top2.setText(s_top);
					
					nb_mid = myArray[j];
					s_mid = Integer.toString(nb_mid);
					mid2.setText(s_mid);
					label2.setText(s_mid);
					
					nb_bottom = myArray[j+1];
					s_bottom = Integer.toString(nb_bottom);
					bottom2.setText(s_bottom);
					
					j = j+1;
					
				}
				System.out.println(myArray[j]);
			}
		}
		
		if(event.getSource() == gridpane3) {
			
			if(deltaY > 0) {
				
				if(j == 0) {
					nb_top = myArray[9];
					s_top = Integer.toString(nb_top);
					top3.setText(s_top);
			
					nb_mid = myArray[0];
					s_mid = Integer.toString(nb_mid);
					mid3.setText(s_mid);
					label3.setText(s_mid);
					
					nb_bottom = myArray[1];
					s_bottom = Integer.toString(nb_bottom);
					bottom3.setText(s_bottom);
					
					j = 9;
					
				} else if (j == 9) {
					nb_top = myArray[8];
					s_top = Integer.toString(nb_top);
					top3.setText(s_top);
					
					nb_mid = myArray[9];
					s_mid = Integer.toString(nb_mid);
					mid3.setText(s_mid);
					label3.setText(s_mid);
					
					nb_bottom = myArray[0];
					s_bottom = Integer.toString(nb_bottom);
					bottom3.setText(s_bottom);
					
					j = j-1;
					
				} else {
					nb_top = myArray[j-1];
					s_top = Integer.toString(nb_top);
					top3.setText(s_top);
					
					nb_mid = myArray[j];
					s_mid = Integer.toString(nb_mid);
					mid3.setText(s_mid);
					label3.setText(s_mid);
					
					nb_bottom = myArray[j+1];
					s_bottom = Integer.toString(nb_bottom);
					bottom3.setText(s_bottom);
					
					j = j - 1;
					
				}
				System.out.println(myArray[j]);
			}
			
			if(deltaY<0) {
				if(j==9) {
					nb_top = myArray[8];
					s_top = Integer.toString(nb_top);
					top3.setText(s_top);
					
					nb_mid = myArray[9];
					s_mid = Integer.toString(nb_mid);
					mid3.setText(s_mid);
					label3.setText(s_mid);
					
					nb_bottom = myArray[0];
					s_bottom = Integer.toString(nb_bottom);
					bottom3.setText(s_bottom);
					
					j = 0;
				} else if (j == 0) {
					nb_top = myArray[9];
					s_top = Integer.toString(nb_top);
					top3.setText(s_top);
					
					nb_mid = myArray[0];
					s_mid = Integer.toString(nb_mid);
					mid3.setText(s_mid);
					label3.setText(s_mid);
					
					nb_bottom = myArray[1];
					s_bottom = Integer.toString(nb_bottom);
					bottom3.setText(s_bottom);
					
					j = j+1;
					
				} else {
					nb_top = myArray[j-1];
					s_top = Integer.toString(nb_top);
					top3.setText(s_top);
					
					nb_mid = myArray[j];
					s_mid = Integer.toString(nb_mid);
					mid3.setText(s_mid);
					label3.setText(s_mid);
					
					nb_bottom = myArray[j+1];
					s_bottom = Integer.toString(nb_bottom);
					bottom3.setText(s_bottom);
					
					j = j+1;
					
				}
				System.out.println(myArray[j]);
			}
		}
		
		if(event.getSource() == gridpane4) {
			
			if(deltaY > 0) {
				
				if(j == 0) {
					nb_top = myArray[9];
					s_top = Integer.toString(nb_top);
					top4.setText(s_top);
			
					nb_mid = myArray[0];
					s_mid = Integer.toString(nb_mid);
					mid4.setText(s_mid);
					label4.setText(s_mid);
					
					nb_bottom = myArray[1];
					s_bottom = Integer.toString(nb_bottom);
					bottom4.setText(s_bottom);
					
					j = 9;
					
				} else if (j == 9) {
					nb_top = myArray[8];
					s_top = Integer.toString(nb_top);
					top4.setText(s_top);
					
					nb_mid = myArray[9];
					s_mid = Integer.toString(nb_mid);
					mid4.setText(s_mid);
					label4.setText(s_mid);
					
					nb_bottom = myArray[0];
					s_bottom = Integer.toString(nb_bottom);
					bottom4.setText(s_bottom);
					
					j = j-1;
					
				} else {
					nb_top = myArray[j-1];
					s_top = Integer.toString(nb_top);
					top4.setText(s_top);
					
					nb_mid = myArray[j];
					s_mid = Integer.toString(nb_mid);
					mid4.setText(s_mid);
					label4.setText(s_mid);
					
					nb_bottom = myArray[j+1];
					s_bottom = Integer.toString(nb_bottom);
					bottom4.setText(s_bottom);
					
					j = j - 1;
					
				}
				System.out.println(myArray[j]);
			}
			
			if(deltaY<0) {
				if(j==9) {
					nb_top = myArray[8];
					s_top = Integer.toString(nb_top);
					top4.setText(s_top);
					
					nb_mid = myArray[9];
					s_mid = Integer.toString(nb_mid);
					mid4.setText(s_mid);
					label4.setText(s_mid);
					
					nb_bottom = myArray[0];
					s_bottom = Integer.toString(nb_bottom);
					bottom4.setText(s_bottom);
					
					j = 0;
				} else if (j == 0) {
					nb_top = myArray[9];
					s_top = Integer.toString(nb_top);
					top4.setText(s_top);
					
					nb_mid = myArray[0];
					s_mid = Integer.toString(nb_mid);
					mid4.setText(s_mid);
					label4.setText(s_mid);
					
					nb_bottom = myArray[1];
					s_bottom = Integer.toString(nb_bottom);
					bottom4.setText(s_bottom);
					
					j = j+1;
					
				} else {
					nb_top = myArray[j-1];
					s_top = Integer.toString(nb_top);
					top4.setText(s_top);
					
					nb_mid = myArray[j];
					s_mid = Integer.toString(nb_mid);
					mid4.setText(s_mid);
					label4.setText(s_mid);
				
					nb_bottom = myArray[j+1];
					s_bottom = Integer.toString(nb_bottom);
					bottom4.setText(s_bottom);
					
					j = j+1;
					
				}
				System.out.println(myArray[j]);
			}
		}
		

        if (isCodeCorrect()) {
            //  System.out.println("Code is correct");
              try {
                  switchToGameWon();
              } catch (IOException e) {
                  throw new RuntimeException(e);
              }
          } else {
              //     System.out.println(" NOT correct");
          }
      
	}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
		myArray = new int[10];
		for (i = 0; i<10; i++) {
			myArray[i] = i;
			System.out.println(myArray[i]);
		}

        label_codeToUnlock.setText(String.valueOf(quest_number));
    }
    
    
    
}