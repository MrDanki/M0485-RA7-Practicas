package main;

import java.util.Scanner;

import game.Door;
import game.Escape;
import game.Item;
import game.Maze;
import game.Monster;
import game.Player;
import static assets.ColorManager.*;

/**
 * The Main class is the one that contains the main of the program. It will
 * create a maze, put a player in it and take the commands that the player
 * enters and execute them.
 */
public class Main {

    /**
     * To track the monster on the maze
     */
    private static final String[] direction = {"north", "east", "south", "west"};

    /**
     * Maze where the game is played.
     */
    private Maze maze;

    /**
     * Player who moves in the maze
     */
    private Player player;

    /**
     * Player moving through the maze.
     */
    private Monster monster;

    /**
     * Constructor that receives the created maze and the player inside it.
     *
     * @param maze An already initialized maze.
     * @param player A player that should be inside the maze.
     */
    public Main(Maze laberint, Player jugador, Monster monster) {
        this.maze = laberint;
        this.player = jugador;
        this.monster = monster;
    }

    /**
     * Create a maze and a player and start the program.
     */
    public static void main(String[] args) {
        // defining maze structure
        int[][] map = {
            {0, 0, 0, 2, 0, 0, 0},
            {0, 1, 1, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0},
            {0, 1, 0, 1, 1, 1, 0},
            {0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0},
            {0, 1, 1, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0}};
        // creating the maze using the map
        Maze maze = Maze.create(map);
        // creating the player
        Player player = new Player();
        player.setRoom(maze.getRoom(6, 3));
        // creating items 
        Item diners = new Item("treasure, 1000 bitcoins",BG_YELLOW+" $ "+RESET);
        maze.getRoom(6, 5).setItem(diners);
        Item jvm = new Item("golden JAVAline, to kill the Minotaur",BG_YELLOW+" J "+RESET);
        maze.getRoom(1, 5).setItem(jvm);

        // setting DOOR
        // put a key in maze
        Item masterKey = new Item("master key, to open any door",BG_YELLOW+" ? "+RESET);
        maze.getRoom(4, 3).setItem(masterKey);

        // create two doors for previous key, both closed and joined each other
        Door door1 = new Door(maze.getRoom(3, 1), masterKey, false);
        Door door2 = new Door(maze.getRoom(4, 1), masterKey, false);
        door1.setReverseDoor(door2);
        door2.setReverseDoor(door1);
        // assign door to correspond locations
        // for door 1, in 4,1 to move 3,1 towards nord
        maze.getRoom(4, 1).setExit(door1, Maze.NORTH);
        // for door 2, in 3,1 to move 4,1 towards south
        maze.getRoom(3, 1).setExit(door2, Maze.SOUTH);

        // setting MONSTER 
        // create monster and deploy in map
        Monster mons = new Monster();
        mons.setRoom(maze.getRoom(1, 1));

        // we create a Main object with the maze and the player
        Main ppal = new Main(maze, player, mons);
        // start the game
        ppal.start();
    }

    /**
     * Main loop of the program. At each iteration the user command is taken and
     * executed.
     */
    public void start() {
        // to manage end of program
        boolean endGame = false;
        String action;
        Scanner sc = new Scanner(System.in);

        while (!endGame) {
            System.out.println(maze);
            System.out.println();

            // movement of player in the maze
            System.out.println(
                    "Action (w: north, s: south, d: east, a: west, x: take item, z: drop item, q: quit game): ");
            action = sc.nextLine();
            switch (action) {
                case "w":
                    move(Maze.NORTH);
                    break;
                case "s":
                    move(Maze.SOUTH);
                    break;
                case "d":
                    move(Maze.EAST);
                    break;
                case "a":
                    move(Maze.WEST);
                    break;
                case "x": // take item
                    take();
                    break;
                case "z": // drop item
                    drop();
                    break;
                case "q": // exit program
                    endGame = true;
                    break;
                default:
                    System.out.println("Wrong action");
                    break;
            }

            // movement of monster in the maze
            if (monster.isAlive()) {
                int move = monster.action(player);
                if (move != -1) {
                    System.out.println("Minotaur is moving to " + direction[move]);
                }
            }

            // in case player is not alive, game ends
            if (!player.isAlive()) {
                endGame = true;
                System.out.println("The Minotaur have catched you, GAME OVER!");
            }

            // in case player found the escape from maze, game ends
            if (player.getRoom() instanceof Escape) {
                ((Escape) player.getRoom()).onEnter();
                endGame = true;                
            }
        }
        System.out.println("Bye, GG!");
    }

    /**
     * Attempts to move the player in a certain direction. If it cannot,
     * displays a message indicating this.
     *
     * @param direction The direction in which to move.
     */
    private void move(int direction) {

        // if player cannot move
        if (!player.move(direction)) {
            if (player.getRoom().getExit(direction) instanceof Door) {
                System.out.println("Door is closed, get a key!");
            } else {
                System.out.println("You cannot move to this direction!");
            }
        } else {
            System.out.println("You moved to " + this.direction[direction]);
        }
    }

    /**
     * Try to get the player to grab the object in the room they are in. Show
     * what they grabbed, or if they couldn't grab anything.
     */
    private void take() {
        if (player.takeItem()) {
            System.out.println("You got a " + player.getInventory().getName() + ".");
        } else {
            System.out.println("There is nothing to get in this room.");
        }
    }

    /**
     * Try to get the player to drop the object they are carrying. Show which
     * object they dropped, or if they were unable to drop any objects.
     */
    private void drop() {
        if (player.dropItem()) {
            System.out.println("You drop the " + player.getRoom().getItem().getName() + ".");
        } else {
            System.out.println("You do not anything to drop or there is no space in this room.");
        }
    }
}
