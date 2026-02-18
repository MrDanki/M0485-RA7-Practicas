package game;

/**
 * This class represents the objects that can be found in the rooms of the maze.
 */
public class Item extends Element {

    /**
     * Name of the item
     */
    private String name;

    /**
     * Creates a new object.
     *
     * @param name Name of the object.
     * @param image String to represent the object in the maze.
     */
    public Item(String name, String image) {
        super(image);
        this.name = name;
    }

    public Item() {
        super("");
    }

    /**
     * Gets the name of the object.
     *
     * @return Name of the item, for example, "a treasure chest".
     */
    public String getName() {
        return name;
    }
}
