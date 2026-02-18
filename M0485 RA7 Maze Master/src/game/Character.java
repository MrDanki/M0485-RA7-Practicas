package game;

/**
 * That class represents features common for any character created in maze
 */
public class Character extends Element {

    private boolean alive = true;

    /**
     * Constructor to create a new character
     *
     * @param image to represent it in maze
     */
    public Character(String image) {
        super(image);
    }

    /**
     * Method to assign a character to a room where is
     *
     * @param room, room where character is found
     */
    public void setRoom(Room room) {
        if (super.getRoom() != null) {
            super.getRoom().setCharacter(null);
        }

        // assign room to character
        super.setRoom(room);
        // assign character to room
        super.getRoom().setCharacter(this);
    }

    /**
     * @return the alive
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * @param alive the alive to set
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Method to move the character in the maze
     *
     * @param direction direction where movement will be done, using 4 main
     * movements defined in Laberint class
     *
     * @return true if character has been moved or false in case it can not move
     * because there were not exit or the door is closed
     *
     */
    public boolean move(int direction) {
        boolean ok = true;
        // retrieve exit based in direction taken
        Exit exit = super.getRoom().getExit(direction);

        // in case there is exit
        if (exit != null) {

            Room availableRoom = exit.cross(this);

            // in case character can get into the exit
            if (availableRoom != null) {
                // assign character to new room where it has moved
                setRoom(availableRoom);

                // in case character is not able to get into the exit
            } else {
                ok = false;
            }

            // in case there is not exit
        } else {
            ok = false;
        }

        return ok;
    }
}
