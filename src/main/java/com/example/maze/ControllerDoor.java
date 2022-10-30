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
import javafx.scene.Node;

public class ControllerDoor implements Initializable{
	@FXML private Pane pane;
	@FXML private GridPane gridpane1;
	@FXML private GridPane gridpane2;
	@FXML private GridPane gridpane3;
	
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
	
	codeArray codeArray;
	private int[] myArray;
	int i =0;
	int j = 0;
	
	private double deltaY;
	
	
	int quest_number = 1234;

	@Override
    public void initialize(URL url, ResourceBundle resourcesBundle) {
    	/*
    	s_top = top.getText();
		s_mid = mid.getText();
		s_bottom = bottom.getText();
		
		nb_top = Integer.parseInt(s_top);
		nb_mid = Integer.parseInt(s_mid);
		nb_bottom = Integer.parseInt(s_bottom);
		*/
		
		myArray = new int[10];
		for (i = 0; i<10; i++) {
			myArray[i] = i;
			System.out.println(myArray[i]);
		}

    }


	@FXML void scroll(ScrollEvent event) {

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
					
					nb_bottom = myArray[j+1];
					s_bottom = Integer.toString(nb_bottom);
					bottom.setText(s_bottom);
					
					j = j+1;
					
				}
				System.out.println(myArray[j]);
			}
		}
		
		
		
	}

	

}
