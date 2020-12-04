package emoji.Engine;

import emoji.Engine.EngineMode.EngineMode;
import emoji.Engine.EngineMode.SearchMode;
import emoji.Util.Console;

public final class Engine {

    private boolean isRunning;
    private EngineMode mode;

    public Engine()
    {
        isRunning = true;
        mode = new SearchMode(this);
    }
    
    public void update() {
        Console.lineBreak();
        String userInput = Console.input( mode.getPrompt() );

        if ("".equals(userInput)) {
            mode.onEmptyInput();
            return;
        }

        mode.interpret(userInput);
    }

    public void setMode(EngineMode mode)
    {
        this.mode = mode;

        mode.display();
    }

    public void terminate()
    {
        isRunning = false;
    }

    public boolean isRunning()
    {
        return isRunning;
    }

}
