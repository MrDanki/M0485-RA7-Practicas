package game;

/**
 * That class represent a specific type of Sortida to allow or deny its access
 * to another room using doors locked by keys
 *
 */
public class Door extends Exit {

    /**
     * Item required to open the door. Key used by player has to be identical to
     * open the door
     */
    private Item key = new Item();

    /**
     * Boolean to indicate how is the door true open door, false closed door
     */
    private boolean open;

    /**
     * Associated door to this door in the side room Each door will have its
     * correspond inverse door to keep both opened or closed in same time
     */
    private Door reverseDoor;

    /**
     * Constructor of door in an exit
     *
     * @param destination Room where character goes using the door
     * @param key Required key to open the door
     * @param status Status of dorr, opened or closed
     */
    public Door(Room destination, Item key, boolean status) {
        super(destination);
        this.key = key;
        open = status;
    }

    /**
     * @return the key
     */
    public Item getKey() {
        return key;
    }

    /**
     * @param key, to define required key in the door
     */
    public void setKey(Item key) {
        this.key = key;
    }

    /**
     * Method to return status of door, open or close
     *
     * @return boolean with true means opened or false means closed
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * Method to define status of door
     *
     * @param estat, true if we want to open close, false if we want to close it
     */
    public void setOpen(boolean open) {
        this.open = open;
    }

    /**
     * Method to control access to the door. only players can be get into the
     * next room using the door if they bring correct key
     *
     * @param character to check who is and which key is using
     *
     */
    @Override
    public Room cross(Character character) {
        Room allowedRoom = null;

        // in case open door, allow go into
        if (isOpen()) {
            allowedRoom = super.getNextRoom();

            // in case closed door
        } else {
            // if it is a player
            if (character instanceof Player) {
                // and if this player brings a correct key, open door and its correspond inverse
                // door
                if (key.equals(((Player) character).getInventory())) {
                    allowedRoom = super.getNextRoom();
                    setOpen(true);
                    reverseDoor.setOpen(true);
                }
            }
        }
        return allowedRoom;
    }

    /**
     * Method to retrieve reverse door associated with this door
     *
     * @return portaInversa, reverse door
     */
    public Door getReverseDoor() {
        return reverseDoor;
    }

    /**
     * Method to define which reverse door is associated with a door
     *
     * @param portaInversa door that is associated
     */
    public void setReverseDoor(Door reverseDoor) {
        this.reverseDoor = reverseDoor;
    }
}
