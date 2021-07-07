package emoji.Util;

import java.util.Scanner;

public final class Console {

    private static Console instance;

    private Scanner scanner;

    private Console()
    {
        scanner = new Scanner(System.in);
    }

    public static Console getInstance()
    {
        if (instance == null) {
            instance = new Console();
        }

        return instance;
    }

    public static void printInColor(String message, ConsoleColor color) {
        System.out.print(color + message + ConsoleColor.RESET);
    }
    
    public static void warn(String message)
    {
        System.out.println(ConsoleColor.YELLOW + message + ConsoleColor.RESET);
    }

    public static void error(String message)
    {
        System.out.println(ConsoleColor.RED + message + ConsoleColor.RESET);
    }

    public static String input(String message)
    {
        if (message != null) {
            System.out.println(ConsoleColor.GREEN + message + ConsoleColor.RESET);
        }
        System.out.print("> ");
        return getInstance().scanner.nextLine().trim();
    }

    public static void lineBreak()
    {
        System.out.print("\n");
    }

}
