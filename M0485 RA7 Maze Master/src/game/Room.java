package game;

/**
 * This class represents each of the rooms in the maze. It will allow a player
 * and an object, and will have information about their exits.
 */
import static assets.ColorManager.*;
public class Room {

    /**
     * The possible exits from the room. An array of four exits. Each position
     * in the array will contain an Exit if there is an exit in that direction,
     * or null if there is no exit in that direction.
     */
    private Exit[] exits = new Exit[4];
    /**
     * An object that can be in the room, or null if there is no object. In this
     * version, there can only be one object per room.
     */
    private Item item = null;
    /**
     * The player in the room, or null if there are no players. There can only
     * be one player per room.
     */
    private Character character = null;

    /**
     * Gets the Exit that is in a given direction, or null if there is no exit
     * in that direction.
     *
     * @param direction The direction for which you want to find the exit. See
     * the Labyrinth class for the definition of the constants with the possible
     * directions.
     * @return the exit in the specified direction or null if there is none.
     */
    public Exit getExit(int direction) {
        return exits[direction];
    }

    /**
     * Assigns an Output in a specific direction.
     *
     * @param output The output to assign, or null to delete an existing output.
     * @param direction The direction where the output is to be added. See the
     * class Labyrinth for defining constants.
     */
    public void setExit(Exit sortida, int direction) {
        exits[direction] = sortida;
    }

    /**
     * Returns the player currently in this room or null if there are no players
     * in the room.
     *
     * @return the Player currently in, or null.
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * Assigns a player to this room. Note: To maintain consistency between the
     * player and the room, this method should only be called from the Player
     * class.
     *
     * @param character The player who has arrived at this room.
     */
    protected void setCharacter(Character character) {
        this.character = character;
    }

    /**
     * Assigns a player to this room. Note: To maintain consistency between the
     * player and the room, this method should only be called from the Player
     * class.
     *
     * @param jug The player who has arrived at this room.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Returns and removes the item from the room. Does nothing if there is no
     * item.
     *
     * @return the item removed from the room, or null if there was none.
     */
    protected Item takeItem() {
        Item it = item;
        item = null;

        return it;
    }

    /**
     * Put an item in the room, if there was none. In this version, there can
     * only be one item per room.
     *
     * @param newItem The item to put in the room.
     * @return false if the item was not put in the room because there was
     * already one, true if it could be put.
     */
    public boolean setItem(Item newItem) {
        boolean ok = true;
        if (item != null) {
            ok = false;
        } else {
            item = newItem;
        }
        newItem.setRoom(this);
        return ok;
    }

    /**
     * Returns a string representing the contents of the room. If there is a
     * player, the player symbol will be returned. If not, and there is an
     * object, the object symbol will be returned. If there is neither a player
     * nor an object, a blank space will be returned.
     *
     * @return the string representing this room.
     */
    @Override
    public String toString() {
        String strRoom = BG_GRAY+"   "+RESET;

        if (character != null) {
            strRoom = character.toString();
        } else if (item != null) {
            strRoom = item.toString();
        }

        return strRoom;
    }
}
