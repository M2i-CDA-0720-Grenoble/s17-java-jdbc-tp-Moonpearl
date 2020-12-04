package emoji;

import java.sql.*;

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
        Statement statement = DatabaseHandler.createStatement();
        ResultSet set = statement.executeQuery("SELECT * FROM `emoji`");
        while (set.next()) {
            System.out.println(set.getString("characters") + "  " + set.getString("code"));
        }

    }
}
