package com.example.project;
import java.util.Scanner;

public class Game{
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 

    public Game(int size){ //the constructor should call initialize() and play()
        this.size = size;
        initialize();
        play(); 
    }

    public static void clearScreen() { //do not modify
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-based (Linux, macOS)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(){ //write your game logic here
        Scanner scanner = new Scanner(System.in);
        boolean gameRun = true;

        while (gameRun){
            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clearScreen(); // Clear the screen at the beggining of the while loop
            grid.display();
            // Debugging print statements & info for the player
            System.out.println(player.getCoords() + "\n" + player.getRowCol(size));
            System.out.println("Lives: " + player.getLives());
            System.out.println("Treasures Collected: " + player.getTreasureCount());
            Sprite movedTo = new Sprite(player.getX(), player.getY());
            
            String temp = scanner.nextLine(); // checking for inputs for player movement
            // if the move trying to be made is valid
            if (player.isValid(size, temp)) {
                // changes the position of movedTo based on the input
                if (temp.equals("w")) {
                    movedTo.setY(movedTo.getY() + 1);

                } else if (temp.equals("a")) {
                    movedTo.setX(movedTo.getX() - 1);

                } else if (temp.equals("s")) {
                    movedTo.setY(movedTo.getY() - 1);

                } else if (temp.equals("d")) {
                    movedTo.setX(movedTo.getX() + 1);

                }
                // interacts with the spot player is trying to move to
                player.interact(size, temp, treasures.length, grid.getGrid()[size - 1 - movedTo.getY()][movedTo.getX()]);

                // if the player is not trying to move onto the trophy with an insufficient number of treasures
                if ((player.getTreasureCount() == treasures.length || !(grid.getGrid()[size - 1 - movedTo.getY()][movedTo.getX()] instanceof Trophy)))  {
                    player.move(temp);
                    
                }

                
                if (player.getLives() <= 0 || player.getWin()) {
                    // when the player wins or loses, clear the screen.
                    clearScreen();
                    if (player.getLives() <= 0) {
                        grid.gameover();
                        System.out.println("You lost...");

                    } else {
                        grid.win();
                        System.out.println("You won!!!");

                    }
                    System.out.println("Play again? (y/n)");
                    gameRun = scanner.nextLine().equals("y");
                }
                
            }
            

            grid.placeSprite(player, temp);
            }
            
     
    }

    public void initialize(){ //to test, create a player, trophy, grid, treasure, and enemies. Then call placeSprite() to put them on the grid
        grid = new Grid(size);
        player = new Player(0, 0);

        enemies = new Enemy[2];
        treasures = new Treasure[1];

        grid.placeSprite(new Enemy(1, 0));
        grid.placeSprite(new Enemy(2, 0));
        grid.placeSprite(player);
        grid.placeSprite(new Treasure(3, 2));
        grid.placeSprite(new Trophy(size - 1, size - 1));
   
    }

    public static void main(String[] args) {
        Game jim = new Game(6);
    }
}