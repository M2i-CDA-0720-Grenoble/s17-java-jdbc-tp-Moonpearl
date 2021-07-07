package emoji.Model;

import java.sql.*;
import java.util.Set;

import emoji.Util.Console;
import emoji.Util.DatabaseHandler;

public final class Tag {

    private int id;
    private String name;

    public Tag() {
        id = 0;
        name = "";
    }

    public Tag(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public static Tag findByName(String name)
    {
        try {
            PreparedStatement statement = DatabaseHandler.prepareStatement(
                "SELECT * FROM `tags` WHERE `name` = ?"
            );
            statement.setString(1, name);
            ResultSet set = statement.executeQuery();
            if (set.first()) {
                return new Tag(
                    set.getInt("id"),
                    set.getString("name")
                );
            }
            return null;
        }
        catch (SQLException exception) {
            Console.error("Error while fetching tag '" + name + "'.");
            return null;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Emoji> getEmojis() {
        return Emoji.findByTag(this);
    }

}
