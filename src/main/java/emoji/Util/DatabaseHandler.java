package emoji.Util;

import java.sql.*;

public final class DatabaseHandler {
    
    static private DatabaseHandler instance;

    private Connection connection;
    
    private DatabaseHandler()
    {
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/java_emoji?user=root&password=root");
        }
        catch (SQLException exception) {
            Console.error("Error while establishing connection to database.");
            System.exit(1);
        }
    }

    public static DatabaseHandler getInstance()
    {
        if (instance == null) {
            instance = new DatabaseHandler();
        }

        return instance;
    }

    public static Connection getConnection()
    {
        return getInstance().connection;
    }

    public static Statement createStatement() throws SQLException
    {
        return getConnection().createStatement();
    }

    public static PreparedStatement prepareStatement(String sql) throws SQLException
    {
        return getConnection().prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
    }

}
