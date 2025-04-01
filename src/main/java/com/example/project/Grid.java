package com.example.project;


//DO NOT DELETE ANY METHODS BELOW
public class Grid{
    private Sprite[][] grid;
    private int size;

    public Grid(int size) { //initialize and create a grid with all DOT objects
        grid = new Sprite[size][size];
        this.size = size;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = new Dot(row, col);
            }
        }

    }
 
    public Sprite[][] getGrid(){return grid;}

    public void placeSprite(Sprite s){ //place sprite in new spot
        grid[size - 1 - s.getY()][s.getX()] = s;
    }

    // PRECONDITION: The spot where the sprite is trying to move is valid.
    public void placeSprite(Sprite s, String direction) { //place sprite in a new spot based on direction
        Dot josh = new Dot(s.getX(), s.getY()); // makes the dot object which will be placed where the object lat was
        boolean validMove = false; // for when the dot will be placed

        if (direction.equals("w")) { // moves the dot's position to the left of s
            josh.setY(josh.getY() - 1);
            validMove = true;

        } else if (direction.equals("a")) { // moves the dot's position to the left of s
            josh.setX(josh.getX() + 1);
            validMove = true;

        } else if (direction.equals("s")) { // moves the dot's position to the left of s
            josh.setY(josh.getY() + 1);
            validMove = true;

        } else if (direction.equals("d")) { // moves the dot's position to the left of s
            josh.setX(josh.getX() - 1);
            validMove = true;
        }

        grid[size - 1 - s.getY()][s.getX()] = s;
        if (validMove == true) {
           placeSprite(josh); 
        }
    }


    public void display() { //print out the current grid to the screen 
        // runs through all items in grid in row col order.
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Sprite curItem = grid[row][col]; // here so I don't have to write grid[row]col] four times.
                
                // Runs through what class each sprite could be,
                // then prints out the emoji coresponding to that class
                if (curItem instanceof Dot) {
                    System.out.print(" ⬜");

                } else if (curItem instanceof Enemy) {
                    System.out.print(" 🔥");

                } else if (curItem instanceof Trophy) {
                    System.out.print(" ©️ ");

                } else if (curItem instanceof Treasure) {
                    System.out.print(" 💎");
                    
                } else if (curItem instanceof Player) {
                    System.out.print(" 🧿");
                }
            }
            System.out.println(); // line break for new row
        }
    }
    
    public void gameover(){ //use this method to display a loss
        // when the player loses, display every non player object as ‼️
        // and the player object as 🔰
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (!(grid[row][col] instanceof Player)) {
                    System.out.print("‼️  ");

                } else if (grid[row][col] instanceof Player) {
                    System.out.print("🔰 ");
                }
            }
            System.out.println();
        }
    }

    public void win(){ //use this method to display a win 
        // when the player wins, display every non player object as 🌟
        // and the player object as 🐲
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (!(grid[row][col] instanceof Player)) {
                    System.out.print("🌟 ");

                } else if (grid[row][col] instanceof Player) {
                    System.out.print("🐲 ");
                }
            }
            System.out.println();
        }
    }


}