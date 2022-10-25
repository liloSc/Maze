package com.example.maze;

import javafx.beans.property.SimpleStringProperty;

public class Player {
    private SimpleStringProperty character;
    private int life;

  /*  public static Player example() {
        Player tester = new Player("testervalue",10);
        return tester;
    }*/


    public Player(String character, int life) {
        this.character = new SimpleStringProperty(character);
        this.life = life;
    }


    public void setCharacter(String character) {
        this.character = new SimpleStringProperty(character);

    }

    public String getCharacter() {
        return character.get();
    }

    public void setLife(int life2) {
        this.life = life2;
        System.out.println(life + " is your life of character");

    }

    public int getLife() {
        return life;
    }


}