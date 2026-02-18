package game;

import java.util.Random;
import static assets.ColorManager.*;

/**
 * That class represent a specific character as enemy of players. It will move
 * for each player movement and will kill player if they are in same room.
 */
public class Monster extends Character {

    /**
     * Random movement. Used to calculate random moves of monsters
     */
    private Random movement;

    /**
     * Constructor of monsters
     */
    public Monster() {
        super(BG_RED+" M "+RESET);
        this.movement = new Random();
    }

    /**
     * Method to move the monster randomly in the maze.
     *
     * @param player
     * @return int direction that monster moved
     */
    public int action(Character player) {
        int direction = 0;

        // in case monster and player are in same room
        if (this.getRoom().equals(player.getRoom())) {
            if (((Player)player).getInventory().equals(YELLOW+" J "+RESET)) {
                this.setAlive(false);
                this.setImage(BG_GRAY+"   "+RESET);
                System.out.println("Well done, you killed the monster");
            } else {
                player.setAlive(false);
                direction = -1;
            }
            // in case they are in different rooms, move in random direction
        } else {
            direction = movement.nextInt(Maze.WEST + 1);
            move(direction);
        }
        return direction;
    }
}
