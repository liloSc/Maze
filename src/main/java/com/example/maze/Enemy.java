package com.example.maze;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringPropertyBase;

public class Enemy {

    private SimpleStringProperty character;
    private SimpleStringProperty PersonTraits;
    private SimpleStringProperty LevelEnemy;

    int health;
    int force;


    public static void enemies(String[] args) {
        Enemy myFirstFigther = new Enemy();  // Object 1
        Enemy mySecondFighter = new Enemy();  // Object 2
        Enemy myThirdFighter = new Enemy(); // NULL IS THE CHARACTER WHICH IS THE


        System.out.println(myFirstFigther.health);
        System.out.println(myFirstFigther.force);
    }


    // Every Enemy gets these properties
    public Enemy() {
        health = 20;
        force = 30;
        // this.character = new SimpleStringProperty(character);
    }

    public void setCharacter(String character) {
        this.character = new SimpleStringProperty(character);
        System.out.println(character + " is your Enemy");
        return;
    }


    public String getCharacter() {
        System.out.println(character + " The Enemy is taken");
        return character.get();
    }

    //public String getPersonTraits() {
    //	System.out.println(PersonTraits +" The Enemy has this info"); // add logic? which enemy?
    //	return PersonTraits.get();
    //}
	

	
	
	
	/*
	// we can do more with knowing the level here, add some logic
	 * EXAMPLE
	 if(LevelEnemy == new SimpleStringProperty("1")) {	
		 // DO Stuff like initialising one enemy 
		 StringPropertyBase oneCharacter;
		//return oneCharacter.get();
	 }
	 else if (LevelEnemy == new SimpleStringProperty("2")) {
		 StringPropertyBase twoCharacter; 
		// return twoCharacter.get();
	 }
	 else if (LevelEnemy == new SimpleStringProperty("3")) {
		 StringPropertyBase threeCharacter;
		// return threeCharacter.get(); 
	 }
	*/


    // Given by the Levelselection Class
    public void setLevelEnemy(String LevelEnemy) {
        this.LevelEnemy = new SimpleStringProperty(LevelEnemy);
        System.out.println(" Enemy class: we are in this level" + LevelEnemy);
        return;
    }


    // This is for the Level information in CombiningGame
    public String getLevelEnemy() {
        return LevelEnemy.get();
    }



}