package emoji.Engine;

import java.util.List;

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
        List<Emoji> userEmoji = Emoji.findByCodeLike(userInput);

        if ("".equals(userInput)) {
            terminate();
            return;
        }

        if (userEmoji.isEmpty()) {
            Console.warn("No result found. 😢 ");
        } else {
            
            for (Emoji emoji: userEmoji) {
                System.out.println(emoji);
            }

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
