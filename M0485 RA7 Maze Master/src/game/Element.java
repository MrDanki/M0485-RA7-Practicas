package game;

/**
 * That class represents any element that can exist in a room of maze
 *
 */
public abstract class Element {

    /**
     * String to represent image of element
     */
    private String image;

    /**
     * room of maze where element is
     */
    private Room room;

    /**
     * Constructor
     *
     * @param image String to show image of element in maze
     */
    public Element(String image) {
        this.image = image;
    }

    /**
     * Method to retrieve image from element
     *
     * @return String with char image of element
     */
    public String getImage() {
        return image;
    }

    /**
     * Method to retrieve room where element is
     *
     * @return Room, room where element is in the maze
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Method to define image of element
     *
     * @param image, image of element in room
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Method to define room for an element
     *
     * @param room, room where element is located
     */
    protected void setRoom(Room room) {
        this.room = room;
    }

    /**
     * Method to print by screen the image of element
     *
     * @return String with image for a ElementSala
     */
    @Override
    public String toString() {
        return getImage();
    }
}
