package emoji.Engine;

import java.util.Scanner;

import emoji.Model.Emoji;
import emoji.Util.Console;

public final class Engine {

    private boolean isRunning;

    public Engine()
    {
        isRunning = true;
    }
    
    public void update() {
        Console.lineBreak();
        String userInput = Console.input("Search for an emoji");
        Emoji userEmoji = Emoji.findByCode(userInput);

        if ("".equals(userInput)) {
            terminate();
            return;
        }

        if (userEmoji == null) {
            Console.warn("No result found. 😢 ");
        } else {
            System.out.println(userEmoji);
        }
    }

    private void terminate()
    {
        isRunning = false;
    }

    public boolean isRunning()
    {
        return isRunning;
    }

}
