package com.example.maze;
/*
public enum Player {
    Char1, Char2, Char3, Char4


}*/
/*
public class Player{
    String character= "Char1";
    public Player
    public void setPlayerCharacter(Player choosedPlayer) {
        player = choosedPlayer;

    }

    public Player getPlayerCharacter() {
        return player;
    }
        }
*/

public class Player {
    private String character;

    private Player player;

   public  Player(String character) {
        this.character=character;
    }
	
	public void test() {
		System.out.println("test");
		character = "cool";
	}
	

	public void setCharacter(String character) {
		this.character = character;
		System.out.println(character +" is your chosen character");
		return;
	}
	
	public String getCharacter() {
		System.out.println(character +" The character is taken");
		return character;
	}
	
	
    
    
    

}