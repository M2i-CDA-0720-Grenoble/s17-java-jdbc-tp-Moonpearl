package emoji.Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            System.out.println("Error while fetching emoji '" + code + "'.");
            return null;
        }
    }

    @Override
    public String toString()
    {
        return characters;
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
