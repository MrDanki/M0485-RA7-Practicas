package game;

/**
 * The Exit class represents an exit from one room to another (in one direction,
 * if the exit is bidirectional you must create two Exit objects).
 *
 */
public class Exit {

    /**
     * The room where you will end up if you go through this exit.
     */
    private Room nextRoom;

    /**
     * Constructor that receives the destination exit.
     *
     * @param destination Room where it will end if it goes through this exit.
     */
    public Exit(Room nextRoom) {
        this.nextRoom = nextRoom;
    }

    /**
     * Assign a new destination to this exit.
     *
     * @param destination Room where it will end if it goes through this exit.
     */
    public void setNextRoom(Room nextRoom) {
        this.nextRoom = nextRoom;
    }

    /**
     * Gets the destination Room of this Exit.
     *
     * @return Room where it will end up if it goes through this exit.
     */
    public Room getNextRoom() {
        return nextRoom;
    }

    /**
     * Mehot to get into another room through a exit
     *
     * @param personatje character that is using the exit
     * @return Sala, destin room once the exit has been crossed
     */
    public Room cross(Character personatje) {
        return nextRoom;
    }
}
