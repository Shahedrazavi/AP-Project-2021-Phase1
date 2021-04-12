package Util;

import Models.TweetLogic;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

public class ModelLoader {

//    private TweetLogic loadTweetLogic(){
//
//    }

    private void saveTweetLogic(){
        try {
            Gson gson = new GsonBuilder().create();
            PrintStream printStream = new PrintStream("src/main/resources/tweetlogic.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
