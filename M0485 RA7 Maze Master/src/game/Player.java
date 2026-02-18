package game;
/**
 * The Player class represents a player moving around the interior of the maze.
 */
import static assets.ColorManager.*;
public class Player extends Character {

    /**
     * Item that the player is carrying. In this version, a player can only
     * carry one item. It will be null if the player is not carrying any items.
     */
    private Item inventory;

    /**
     * Create a player with the default appearance, i.e. an @.
     */
    public Player() {
        super(BG_RED+" @ "+RESET);
    }

    /**
     * Creates a player with a specific appearance.
     *
     * @param appearance How the player will appear when drawing the maze.
     */
    public Player(String aspecte) {
        super(aspecte);
    }

    /**
     * Grabs the item in the player's current room. If the player already has an
     * item in their hand, they will drop it on the ground instead of the item
     * they are grabbing.
     *
     * @return true if the item was grabbable or false otherwise. The item
     * cannot be grabbed if there is no item in the room.
     */
    public boolean takeItem() {
        boolean ok = true;
        Item oldItem = inventory;
        Item item = super.getRoom().takeItem();
        if (item != null) {
            inventory = item;
            if (oldItem != null) {
                super.getRoom().setItem(oldItem);
            }
        } else {
            ok = false;
        }

        return ok;
    }

    /**
     * Drop the item in his hand on the floor of the room he is in.
     *
     * @return true if an item could be dropped, false otherwise. An item cannot
     * be dropped if the player is not carrying anything in his hand, or if
     * there is already an item in the room.
     */
    public boolean dropItem() {
        boolean ok = false;

        if (inventory != null && super.getRoom().setItem(inventory)) {
            inventory = null;
            ok = true;
        }
        return ok;
    }

    /**
     * Gets the object the player is carrying in their hand.
     *
     * @return The item the player is carrying or null if none is carried.
     */
    public Item getInventory() {
        return inventory;
    }
}
