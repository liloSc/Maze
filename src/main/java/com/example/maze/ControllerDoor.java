package com.example.maze;

import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ControllerDoor implements Initializable{
	@FXML private Pane pane;
	@FXML private GridPane gridpane;
	@FXML private GridPane gridpane1;
	@FXML private GridPane gridpane2;
	
	@FXML 
	private Text top;
	
	@FXML 
	private Text mid;
	
	@FXML 
	private Text bottom;
	
	String s_top;
	String s_mid;
	String s_bottom;
	
	int nb_top;
	int nb_mid;
	int nb_bottom;
	
	String idtop;
	String idmid;
	String idbottom;
	
	private double deltaY;
	
	private int code=5;
	private int code1=3;
	private int code2=12;
	

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public void initialize(URL location, ResourceBundle resources) {
    	s_top = top.getText();
		s_mid = mid.getText();
		s_bottom = bottom.getText();
		
		nb_top = Integer.parseInt(s_top);
		nb_mid = Integer.parseInt(s_mid);
		nb_bottom = Integer.parseInt(s_bottom);
    	gridpane.setOnScroll(new EventHandler() {

			@Override
			public void handle(Event event) {
			 // gridpane.setTranslateY(gridpane.getTranslateY() + ((ScrollEvent) event).getDeltaY());
				deltaY = ((ScrollEvent) event).getDeltaY();

				if(deltaY < 0) {

					nb_top = nb_top + 1;
					nb_mid = nb_mid + 1;
					nb_bottom = nb_bottom + 1;
				}

				else {						
					nb_top = nb_top - 1;
					nb_mid = nb_mid - 1;
					nb_bottom = nb_bottom - 1;
	

				}
				
				s_top = Integer.toString(nb_top);
				s_mid = Integer.toString(nb_mid);
				s_bottom = Integer.toString(nb_bottom);
				
				top.setText(s_top);
				mid.setText(s_mid);
				bottom.setText(s_bottom);
			}
			
			

        });
    	
		
	
		
		
    }


	@FXML void scroll(ScrollEvent event) {
		
		if(ScrollEvent.SCROLL.equals(event)) {
			System.out.println("Event Detected");
			deltaY = event.getDeltaY();
			if(deltaY > 0 ) {
				nb_top = nb_top + 1;
				nb_mid = nb_mid + 1;
				nb_bottom = nb_bottom + 1;
				
				s_top = Integer.toString(nb_top);
				
				top.setText(s_top);
				
			} else {
				
			}
		}
		
	}
	

}
