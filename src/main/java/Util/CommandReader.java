package Util;

import java.util.Scanner;

public class CommandReader {
    static CommandReader commandReader;
    Scanner scanner;

    private CommandReader() {
        this.scanner = new Scanner(System.in);
    }

    public static CommandReader get() {
        if (commandReader == null) {
            commandReader = new CommandReader();
        }
        return commandReader;
    }

    public int nextInt() {
        return scanner.nextInt();
    }

    public boolean nextBoolean() {
        return scanner.nextBoolean();
    }

    public String nextLine() {
        return scanner.nextLine();
    }

    public String next() {
        String ans = scanner.next();
        scanner.nextLine();
        return ans;
    }
}