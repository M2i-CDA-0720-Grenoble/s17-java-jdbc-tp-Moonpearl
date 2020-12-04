package emoji.Engine.EngineMode;

import java.awt.*;
import java.awt.datatransfer.*;
import java.util.ArrayList;
import java.util.List;

import emoji.Engine.Engine;
import emoji.Model.Emoji;
import emoji.Util.Console;
import emoji.Util.ConsoleColor;

public final class SelectMode extends EngineMode {

    private List<Emoji> searchResults;

    public SelectMode(Engine engine, ArrayList<Emoji> searchResults)
    {
        super(engine);

        this.searchResults = searchResults;
    }
    
    public void display()
    {
        Console.lineBreak();
        // Affiche tous les emojis trouvés par l'utilisateur
        for (int i = 0; i < searchResults.size(); i++) {
            Emoji emoji = searchResults.get(i);

            Console.printInColor("[", ConsoleColor.CYAN);
            Console.printInColor( Integer.toString(i), ConsoleColor.GREEN);
            Console.printInColor("] ", ConsoleColor.CYAN);
            Console.printInColor( emoji.getCharacters() + " " + emoji.getCode() , ConsoleColor.CYAN);
            Console.lineBreak();
        }
    }

    public void interpret(String userInput)
    {
        // Gère la sélection de l'utilisateur

        int choice = 0;
        try {
            choice = Integer.parseInt(userInput);
        }
        catch (NumberFormatException exception) {
            Console.warn("You must input a number! 😱 ");
            return;
        }

        if (choice >= 0 && choice < searchResults.size()) {
            Emoji emoji = searchResults.get(choice);

            StringSelection stringSelection = new StringSelection( emoji.getCharacters() );
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);

            Console.printInColor("Copied " + emoji.getCharacters() + " " + emoji.getCode() + " to clipboard!", ConsoleColor.WHITE_BRIGHT);
            Console.lineBreak();
        } else {
            Console.warn("Invalid choice! 🤐 ");
        }
    } 

    public void onEmptyInput()
    {
        engine.setMode( new SearchMode(engine) );
    }

    public String getPrompt()
    {
        return "Select an emoji from the list";
    }

}
