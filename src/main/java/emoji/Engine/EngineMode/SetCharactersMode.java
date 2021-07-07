package emoji.Engine.EngineMode;

import java.sql.SQLException;

import emoji.Engine.Engine;
import emoji.Model.Emoji;
import emoji.Util.Console;
import emoji.Util.ConsoleColor;

public final class SetCharactersMode extends EngineMode {

    protected Emoji emoji;

    public SetCharactersMode(Engine engine, Emoji emoji)
    {
        super(engine);
        this.emoji = emoji;
    }
    
    public void display()
    {

    }

    public void interpret(String userInput)
    {
        emoji.setCharacters(userInput);
        try {
            emoji.save();
            Console.printInColor("New emoji added succesfully!", ConsoleColor.WHITE);
        }
        catch (SQLException exception) {
            Console.error("Error while saving new emoji to database.");
        }
        Console.lineBreak();
        engine.setMode( new SearchMode(engine) );
    }

    public void onEmptyInput()
    {
        engine.setMode( new SearchMode(engine) );
    }

    public String getPrompt()
    {
        return "Emoji characters?";
    }
}
