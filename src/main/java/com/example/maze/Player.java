package com.example.maze;

import javafx.beans.property.SimpleStringProperty;

public class Player {
    private SimpleStringProperty character;  
    
    public static Player example() {
    	Player tester = new Player("testervalue");
    	return tester;
    }
    
    
   
    public  Player(String character){
	   this.character = new SimpleStringProperty(character);
    }
	
	public void test() {
		//character = "cool";
		System.out.println(character);	
	}
	
	public void setCharacter(String character) {
		this.character = new SimpleStringProperty(character);
		System.out.println(character +" is your chosen character");
		return;
	}
	
	public String getCharacter() {
		System.out.println(character +" The character is taken");
		return character.get();
	}



}