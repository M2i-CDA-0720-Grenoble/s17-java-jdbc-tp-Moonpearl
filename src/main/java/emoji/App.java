package emoji;

import java.sql.*;
import java.util.Scanner;

import emoji.Model.Emoji;
import emoji.Util.DatabaseHandler;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().trim().toLowerCase();
        Emoji userEmoji = Emoji.findByCode(userInput);

        System.out.println(userEmoji);
    }
}
