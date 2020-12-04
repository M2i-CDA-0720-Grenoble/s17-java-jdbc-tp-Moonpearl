package emoji.Engine;

import java.util.List;
import java.util.Set;

import emoji.Model.Emoji;
import emoji.Model.Tag;
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

        if ("".equals(userInput)) {
            terminate();
            return;
        }

        Set<Emoji> emojis = Emoji.findByCodeLike(userInput);
        Tag tag = Tag.findByName(userInput);
        if (tag != null) {
            Set<Emoji> emojisByTag = tag.getEmojis();

            for (Emoji emoji: emojisByTag) {
                if (!emojis.contains(emoji)) {
                    emojis.add(emoji);
                }
            }
        }

        if (emojis.isEmpty()) {
            Console.warn("No result found. 😢 ");
        } else {
            
            for (Emoji emoji: emojis) {
                System.out.println(emoji);
            }

        }
        return;
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
