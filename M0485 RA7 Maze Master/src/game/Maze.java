package game;

/**
 * The Labyrinth class represents a complete maze. It contains methods to access
 * the various rooms of the maze, and some useful methods to easily generate
 * standard mazes .
 */
import static assets.ColorManager.*;

public class Maze {

    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    /**
     * Two-dimensional array of the rooms in the maze. At each position there
     * will be a Room, or null if there is no room at that position (there is a
     * wall).
     */
    private Room[][] rooms;

    /**
     * Maze builder based on its dimensions. Creates an empty maze .
     *
     * @param rows Number of rows the maze will have.
     * @param cols Number of columns the maze will have.
     */
    public Maze(int files, int cols) {
        rooms = new Room[files][cols];
    }

    /**
     * Assigns a room to a certain position in the maze.
     *
     * @param room Room to assign, or null to delete an existing room.
     * @param row Row where the room should be placed.
     * @param col Column where the room should be placed.
     */
    public void setRoom(Room room, int row, int col) {
        rooms[row][col] = room;
    }

    /**
     * Gets the number of rows the maze has.
     *
     * @return number of rows the maze has.
     */
    public int getRows() {
        return rooms.length;
    }

    /**
     * Obté la quantitat de columnes que té el laberint.
     *
     * @return quantitat de columnes que té el laberint.
     */
    public int getCols() {
        return rooms[0].length;
    }

    /**
     * Gets the room that occupies a specific position in the maze, or null if
     * there is no room at that position (there is a wall).
     *
     * @param row Row to query.
     * @param col Column to query.
     * @return The room that is at the specified position, or null if there is
     * no room.
     */
    public Room getRoom(int fila, int col) {
        return rooms[fila][col];
    }

    /**
     * Returns a string representation of the maze. The string will be several
     * lines, one for each row of the maze. At each position in the string, the
     * character corresponding to the following will be returned:
     *
     * - "#" if there is a wall. - " " if there is an empty room. - The
     * appearance of the player or the appearance of the object
     *
     * @return a text string with the representation of the maze.
     */
    @Override
    public String toString() {
        String strMaze = "\t\t";
        int row, col;

        for (row = 0; row < getRows(); row++) {
            for (col = 0; col < getCols(); col++) {
                if (rooms[row][col] == null) {
                    strMaze += BG_GREEN+" # "+RESET;
                } else {
                    if (rooms[row][col].getExit(SOUTH) instanceof Door) {
                        Door door = (Door) rooms[row][col].getExit(SOUTH);
                        if (!door.isOpen()) {
                            strMaze += BG_GRAY+" _ "+RESET;
                        } else {
                            strMaze += rooms[row][col].toString();
                        }
                    } else {
                        strMaze += rooms[row][col].toString();
                    }
                }
            }
            strMaze += "\n\t\t";
        }
        return strMaze;
    }

    /**
     * Useful method that allows you to create a typical maze. This method will
     * create all the rooms of the maze and connect them with exits, assuming
     * that neighboring rooms always have bidirectional exits.
     *
     * You can use this method to generate a maze at first, and then modify them
     * with the appropriate features: one-way exits or leading to non-adjacent
     * rooms, objects, the player...
     *
     * @param map a two-dimensional array of zeros and ones. The zeros represent
     * walls and the ones represent rooms.
     * @return a created maze, with all its rooms and exits.
     */
    public static Maze create(int[][] map) {
        int nRows = map.length;
        int nCols = map[0].length;
        Room room;
        Exit exit;
        int row, col;
        int movement, newRow, newCol;

        // this matrix tells us the change in coordinates that comes with moving
        // in each of the possible directions. For example, if we move
        // east (direction 1), the rows are unchanged, and the
        // columns increase by 1.
        int[][] coordinates = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        // creating empty maze
        Maze maze = new Maze(nRows, nCols);

        // creating rooms
        for (row = 0; row < nRows; row++) {
            for (col = 0; col < nCols; col++) {
                // for normal rooms
                if (map[row][col] == 1) {
                    // creating the room and put on maze
                    room = new Room();
                    maze.setRoom(room, row, col);
                    // for escape room
                } else if (map[row][col] == 2) {
                    // creating Escape room and put on maze
                    room = new Escape();
                    maze.setRoom(room, row, col);
                }
            }
        }

        // creating exits for each room
        for (row = 0; row < nRows; row++) {
            for (col = 0; col < nCols; col++) {
                room = maze.getRoom(row, col);
                if (room != null) { // if there is a room
                    // check in every direction
                    for (movement = 0; movement < 4; movement++) {
                        // here we calculate the coordinates if we move towards the
                        // direction we are looking
                        newRow = row + coordinates[movement][0];
                        newCol = col + coordinates[movement][1];
                        // we make sure that with the change of coordinates we have not
                        // left the matrix, and we continue if there is a room in the
                        // new position
                        if (newRow > 0 && newRow < nRows && newCol > 0 && newCol < nCols
                                && maze.getRoom(newRow, newCol) != null) {
                            // we create an exit from the current room to the room
                            // we discovered. The reverse exit will be created
                            // when we get to analyze the other room.
                            exit = new Exit(maze.getRoom(newRow, newCol));
                            // assign the new exit to the room.
                            room.setExit(exit, movement);
                        }
                    }
                }
            }
        }
        // return the maze
        return maze;
    }
}
