package com.example.project;

//Enemy only need constructor and getCoords() getRowCol()
public class Enemy extends Sprite{ //child  of Sprite
    
    public Enemy(int x, int y) {
        super(x, y);
    }


    // Overriden versions of methods in Sprite
    // Returns "Enemy: " + what the same method in Sprite would return.
    @Override
    public String getCoords(){ //returns "Enemy:"+coordinates
        return "Enemy:" + super.getCoords();
    }


    @Override
    public String getRowCol(int size){ //return "Enemy:"+row col
        return "Enemy:" + super.getRowCol(size);
    }
}