package emoji.Engine.EngineMode;

import java.util.ArrayList;
import java.util.Set;

import emoji.Engine.Engine;
import emoji.Model.Emoji;
import emoji.Model.Tag;
import emoji.Util.Console;

public final class SearchMode extends EngineMode {
    
    public SearchMode(Engine engine)
    {
        super(engine);
    }

    public void display() { }

    public void interpret(String userInput)
    {
        if ("+".equals(userInput)) {
            engine.setMode( new SetCodeMode(engine, new Emoji()) );
            return;
        }

        // Récupère tous les emojis dont le nom contient la saisie de l'utilisateur
        Set<Emoji> emojis = Emoji.findByCodeLike(userInput);
        // Si la saisie de l'utilisateur correspondant à un nom de catégorie
        Tag tag = Tag.findByName(userInput);
        if (tag != null) {
            // Récupère tous les emojis correspondant à la catégorie, et les ajoute à ceux récupérés précédemment
            // en vérifiant qu'il n'y a pas de doublon
            Set<Emoji> emojisByTag = tag.getEmojis();

            for (Emoji emoji: emojisByTag) {
                if (!emojis.contains(emoji)) {
                    emojis.add(emoji);
                }
            }
        }

        // Si la recherche n'a renvoyé aucun résultat, affiche un message
        if (emojis.isEmpty()) {
            Console.warn("No result found. 😢 ");
        // Sinon, passe en mode "sélection"
        } else {
            engine.setMode( new SelectMode(engine, new ArrayList<Emoji>(emojis) ) );
        }
    }

    public void onEmptyInput()
    {
        engine.terminate();
    }

    public String getPrompt()
    {
        return "Search for an emoji";
    }

}
