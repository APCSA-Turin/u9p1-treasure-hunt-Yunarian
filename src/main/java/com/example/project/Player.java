package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite{
    private int treasureCount;
    private int numLives;
    private boolean win;

    public Player(int x, int y) { //set treasureCount = 0 and numLives = 2 
        super(x, y);
        treasureCount = 0;
        numLives = 2;
    }


    public int getTreasureCount(){return treasureCount;}
    public int getLives(){return numLives;}
    public boolean getWin(){return win;}

    //move method should override parent class, sprite
    public void move(String direction) { //move the (x,y) coordinates of the player
        if (direction.equals("w")) { // w moves the player upwards
            setY(getY() + 1);

        } else if (direction.equals("a")) { // a moves the player to the left
            setX(getX() - 1);

        } else if (direction.equals("s")) { // s moves the player downwards
            setY(getY() - 1);

        } else if (direction.equals("d")) { // d moves the player to the right
            setX(getX() + 1);
        }
    }


    public void interact(int size, String direction, int numTreasures, Object obj) { // interact with an object in the position you are moving to 
        // numTreasures is the number of treasures which the game started with.
        // if the move is valid
        if (isValid(size, direction)) {
            // if the tile it is trying to move to is an enemy,
            // lose a life.
            if (obj instanceof Enemy) {
                numLives--;

            // if the object is jus a treasure, 
            // increase treasureCount by 1
            } else if (obj instanceof Treasure && !(obj instanceof Trophy)) {
                treasureCount++;

            // if the object is a trophy, and the player has collected all the treasures,
            // the player wins the game.
            } else if (obj instanceof Trophy && treasureCount == numTreasures) {
                win = true;

            }
        }
    }

    // if the move is trying to move the player out of bounds, false is returned
    // true is returned otherwise.
    public boolean isValid(int size, String direction){ //check grid boundaries

        if (getY() == size - 1 && direction.equals("w")) {
            return false;

        } else if (getX() == 0 && direction.equals("a")) {
            return false;

        } if (getY() == 0 && direction.equals("s")) {
            return false;

        } else if (getX() == size - 1 && direction.equals("d")) {
            return false;

        }
        return true;
    }


    // Overriden versions of methods in Sprite
    // Returns "Player: " + what the same method in Sprite would return.
    @Override
    public String getRowCol(int size){
        return "Player:" + super.getRowCol(size);
    }
   

    @Override
    public String getCoords() {
        return "Player:" + super.getCoords();
    }
}



