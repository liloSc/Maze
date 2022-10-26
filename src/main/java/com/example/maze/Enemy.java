package com.example.maze;

import javafx.beans.property.SimpleStringProperty;


public class Enemy {

    private SimpleStringProperty LevelEnemy;
   
 //ENEMY SHARE INFOMRMATION 
   // Future implementation can include things such as different kind of items which have effect on enemy
    int health;
    int health1 =20;
    int health2 =20;
    int health3 =20;
   // int forceAgainstPlayer;

    // Every Enemy gets these properties
    public Enemy(int health, int forceAgainstPlayer) {
        this.health = health;
     //   this.forceAgainstPlayer = forceAgainstPlayer;
    }



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

   
    
    
    
    //SET and GET enemies health
    
	public int getHealth(int k) {
		if(k==1) {
			return health1;
		}
		else if (k==2) {
			return health2;
		}
		else {
			return health3;
		}
	}
	void setHealth1(int life2 ) {
	      this.health1 = life2;
	   
}
	void setHealth2( int fighter2) {
	      this.health2 = fighter2;
}
	void setHealth3( int fighter3) {
	    
	      this.health3 = fighter3;
}
  
}



// Each player has a health an is a subclass of Enemy. T
// These Enemies are used in Controller Game. However there are not further used
// i.e. enemy specific functions

class Fighter1 extends Enemy {
  public Fighter1(int health, int forceAgainstPlayer) {
		super(health, forceAgainstPlayer);
		// TODO Auto-generated constructor stub
  }
}



class Fighter2 extends Enemy {
  Fighter2(int health, int forceAgainstPlayer) {
		super(health, forceAgainstPlayer);
		// TODO Auto-generated constructor stub
	}
 }



class Fighter3 extends Enemy {
	  Fighter3(int health, int forceAgainstPlayer) {
		super(health, forceAgainstPlayer);
		// TODO Auto-generated constructor stub
	}
	//void setHealth(int life2) {
	//      this.health = life2;
	//}
	//int getHealth() {
	 //     return health;
}





    
    