package com.example.maze.Model;

public class Position {

    private final double xpos;
    private final double ypos;

    public Position(double xpos, double ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
    }

    public double getXPos(){
        return xpos;
    }
    public double getYPos(){
        return ypos;
    }
}
