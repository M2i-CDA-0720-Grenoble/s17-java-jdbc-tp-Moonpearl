package emoji;

import emoji.Engine.Engine;
import emoji.Util.DatabaseHandler;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) throws Exception {
        
        DatabaseHandler.getInstance();

        Engine engine = new Engine();

        while (engine.isRunning()) {
            engine.update();
        }

    }
}
