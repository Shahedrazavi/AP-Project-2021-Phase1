package Util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

public class Logger {
    static Logger logger;
    FileOutputStream fileOutputStream;
    PrintStream printStream;

    static String path = "./log.txt";

    public static Logger getLogger() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    private Logger() {
        try {
            fileOutputStream = new FileOutputStream(path,true);
            printStream = new PrintStream(fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void log(String log){
        printStream.println(LocalDateTime.now()+": "+log);
        printStream.flush();
    }

    public void signIn(String username, String userID){
        log("User with username: \""+username+ "\" and ID: \""+userID + "\" logged in.");
    }

    public void signUp(String username, String userID){
        log("User with username: \""+username+ "\" and ID: \""+userID + "\" created a new account.");
    }
}