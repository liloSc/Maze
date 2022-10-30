package com.example.maze;

public class codeArray {

	private int[] myArray;
	
	void createArray(int[] myArray) {
		this.myArray = myArray;
		initializeMyArray();
	}
	
	private void initializeMyArray() {
		myArray = new int[10];
		
		for(int i = 0; i<10; i++) {
			myArray[i]=i;
			System.out.println(myArray[i]);
		}
		
	}
}
