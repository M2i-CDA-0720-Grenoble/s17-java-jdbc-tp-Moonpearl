package emoji.Engine.EngineMode;

import emoji.Engine.Engine;
import emoji.Model.Emoji;
import emoji.Util.Console;
import emoji.Util.ConsoleColor;

public final class SetCodeMode extends EngineMode {

    protected Emoji emoji;

    public SetCodeMode(Engine engine, Emoji emoji)
    {
        super(engine);
        this.emoji = emoji;
    }
    
    public void display()
    {
        Console.lineBreak();
        Console.printInColor("New emoji mode", ConsoleColor.MAGENTA);
    }

    public void interpret(String userInput)
    {
        emoji.setCode(userInput);
        engine.setMode( new SetCharactersMode(engine, emoji) );
    }

    public void onEmptyInput()
    {
        engine.setMode( new SearchMode(engine) );
    }

    public String getPrompt()
    {
        return "Emoji code?";
    }

}
