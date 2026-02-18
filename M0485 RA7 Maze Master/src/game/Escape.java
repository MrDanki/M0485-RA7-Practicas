package game;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Scanner;
import static assets.ColorManager.*;
/**
 * That Escape class represent a final room with final exit from maze if player
 * comes here, it is the final of game
 */
public class Escape extends Room {

    private boolean triggered = false;

    /**
     * Called when player enters the final room
     */
    public void onEnter() {
        if (triggered) {
            return;
        }
        triggered = true;
        showFinalInput();
    }

    /**
     * Method to retrieve image of this final room
     *
     * @return String with image of this final room
     */
    @Override
    public String toString() {
        String stringRoom = super.toString();

        // in case the room is empty, set its image as "E"
        if ((BG_GRAY+"   "+RESET).equals(stringRoom)) {
            stringRoom =  BG_BLUE+" E "+RESET;
        }

        return stringRoom;
    }

    /*
     * Handles the final interaction when the player reaches the escape room.
     * 
     * The entered number is combined with a predefined key to generate the
     * decoding key used to decrypt a hidden message stored in an external file.
     * If the decoding process is successful, a congratulatory message and the
     * decoded secret are displayed in the terminal.
     * 
     * If an error occurs (invalid input, missing file, or incorrect decoding key),
     * an error message is shown and the stack trace is printed to assist in
     * debugging during development.
     */
    private void showFinalInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n=== FINAL EXIT ===");
        System.out.println("Enter the final number");
        System.out.println("(Hint: sum of the 3 previous lockers)");
        System.out.print("> ");

        try {
            int number = scanner.nextInt();
            String key = "roomescape-key-" + number;
            String message = decodeSecret("secret.txt", key);

            System.out.println("You have escaped from the maze, CONGRATULATIONS!");
            System.out.println(message);

        } catch (Exception e) {
            System.err.println("Error decoding final lock");
            e.printStackTrace();
        }
    }

    /*
     * Decodes a hidden secret message stored in an external file using a key
     * derived from the player's final input.
     * 
     * The file content is expected to be a hexadecimal representation of data
     * previously encrypted using an XOR operation combined with a SHA-256 based
     * key. During execution, the method reverses this process to recover the
     * original message.
     * 
     * This mechanism is used to prevent the secret message from being readable
     * in plain text, requiring successful completion of the final puzzle in
     * order to reveal the information.
     * 
     * @param filePath path to the file containing the encrypted secret
     * @param key      decoding key generated from the player's input
     * @return the decoded secret message as a UTF-8 string
     * @throws Exception if the file cannot be read or the decoding process fails
     */
    private String decodeSecret(String filePath, String key) throws Exception {
        String hexData = new String(Files.readAllBytes(Paths.get(filePath))).trim();
        byte[] encrypted = hexToBytes(hexData);
        byte[] keyHash = sha256(key);

        byte[] decrypted = new byte[encrypted.length];
        for (int i = 0; i < encrypted.length; i++) {
            decrypted[i] = (byte) (encrypted[i] ^ keyHash[i % keyHash.length]);
        }

        return new String(decrypted, "UTF-8");
    }

    private byte[] sha256(String input) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(input.getBytes("UTF-8"));
    }

    private byte[] hexToBytes(String hex) {
        byte[] data = new byte[hex.length() / 2];
        for (int i = 0; i < data.length; i++) {
            int index = i * 2;
            data[i] = (byte) Integer.parseInt(hex.substring(index, index + 2), 16);
        }
        return data;
    }
}
