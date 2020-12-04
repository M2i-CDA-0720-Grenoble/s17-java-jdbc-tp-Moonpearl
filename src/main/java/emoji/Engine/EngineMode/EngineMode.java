package emoji.Engine.EngineMode;

import emoji.Engine.Engine;

abstract public class EngineMode {
    
    protected Engine engine;

    public EngineMode(Engine engine)
    {
        this.engine = engine;
    }

    abstract public void display();

    abstract public void interpret(String userInput);

    abstract public void onEmptyInput();

    abstract public String getPrompt();

}
