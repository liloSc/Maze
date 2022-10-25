package com.example.maze;

import javafx.beans.property.SimpleStringProperty;

public class Player {
    private SimpleStringProperty character;
    private int life;

    public static Player example() {
        Player tester = new Player("testervalue",10);
        return tester;
    }


    public Player(String character, int life) {
        this.character = new SimpleStringProperty(character);
        this.life= life;
    }

    public void test() {
        //character = "cool";
        //	System.out.println(character);
    }

    public void setCharacter(String character) {
        this.character = new SimpleStringProperty(character);
        System.out.println(character + " is your chosen character");
        return;
    }

    public String getCharacter() {
        //	System.out.println(character +" The character is taken");
        return character.get();
    }

    public void setLife(int life2) {
        this.life = life2;
        System.out.println(life + " is your life of character");
        return;
    }

    public int getLife() {
        //	System.out.println(character +" The character is taken");
        return life;
    }


}