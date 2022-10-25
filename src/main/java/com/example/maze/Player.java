package com.example.maze;

import javafx.beans.property.SimpleStringProperty;

public class Player {
    private SimpleStringProperty character;
    private int health;



    public Player(String character, int life) {
        this.character = new SimpleStringProperty(character);
        this.health = life;
    }


    public void setCharacter(String character) {
        this.character = new SimpleStringProperty(character);

    }

    public String getCharacter() {
        return character.get();
    }

    public void setHealth(int life2) {
        this.health = life2;
        System.out.println(health + " is your life of character");

    }

    public int getHealth() {
        return health;
    }


}