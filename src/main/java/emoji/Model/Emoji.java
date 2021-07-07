package emoji.Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.crypto.Data;

import emoji.Util.Console;
import emoji.Util.DatabaseHandler;

public final class Emoji {
    
    private int id;
    private String code;
    private String characters;

    public Emoji()
    {
        id = 0;
        code = "";
        characters = "";
    }

    public Emoji(int id, String code, String characters)
    {
        this.id = id;
        this.code = code;
        this.characters = characters;
    }

    public static Emoji findByCode(String code)
    {
        try {
            PreparedStatement statement = DatabaseHandler.prepareStatement(
                "SELECT * FROM `emoji` WHERE `code` = ?"
            );
            statement.setString(1, code);
            ResultSet set = statement.executeQuery();
            if (set.first()) {
                return new Emoji(
                    set.getInt("id"),
                    set.getString("code"),
                    set.getString("characters")
                );
            }
            return null;
        }
        catch (SQLException exception) {
            Console.error("Error while fetching emoji '" + code + "'.");
            return null;
        }
    }

    public static Set<Emoji> findByCodeLike(String search)
    {
        try {
            Set<Emoji> result = new HashSet<>();
            PreparedStatement statement = DatabaseHandler.prepareStatement(
                "SELECT * FROM `emoji` WHERE `code` LIKE ?"
            );
            statement.setString(1, "%" + search + "%");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                result.add(
                    new Emoji(
                        set.getInt("id"),
                        set.getString("code"),
                        set.getString("characters")
                    )
                );
            }
            return result;
        }
        catch (SQLException exception) {
            Console.error("Error while fetching emoji containing '" + search + "'.");
            return null;
        }
    }

    public static Set<Emoji> findByTag(Tag tag)
    {
        try {
            Set<Emoji> result = new HashSet<>();
            PreparedStatement statement = DatabaseHandler.prepareStatement(
                "SELECT `emoji`.* FROM `emoji` INNER JOIN `emoji_tags` ON `emoji_tags`.`emoji_id` = `emoji`.`id` WHERE `emoji_tags`.`tag_id` = ?"
            );
            statement.setInt(1, tag.getId());
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                result.add(
                    new Emoji(
                        set.getInt("id"),
                        set.getString("code"),
                        set.getString("characters")
                    )
                );
            }
            return result;
        }
        catch (SQLException exception) {
            Console.error("Error while fetching emoji related to tag '" + tag.getName() + "'.");
            return null;
        }
    }

    public void save() throws SQLException
    {
        if (id == 0) {
            insert();
        } else {
            // update()
        }
    }

    protected void insert() throws SQLException
    {
        PreparedStatement statement = DatabaseHandler.prepareStatement(
            "INSERT INTO `emoji` (`code`, `characters`) VALUES (?, ?)"
        );
        statement.setString(1, code);
        statement.setString(2, characters);
        statement.executeUpdate();
    }

    @Override
    public String toString()
    {
        return characters;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj != null && obj.getClass() == this.getClass()) {
            Emoji otherEmoji = (Emoji)obj;
            return this.getId() == otherEmoji.getId();
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

}
