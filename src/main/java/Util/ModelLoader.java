package Util;

import Models.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Scanner;

public class ModelLoader {

    public TweetLogic loadTweetLogic(){
        try {
            Gson gson = new GsonBuilder().create();
            File file = new File("src/main/resources/tweetLogic.txt");

            Scanner scanner = new Scanner(file);

            LinkedList<Tweet> tweets = new LinkedList<>();
            String lastID = scanner.nextLine();
            while (scanner.hasNextLine()){
                Tweet tweet = gson.fromJson(scanner.nextLine(), Tweet.class);
                tweets.add(tweet);
            }
            scanner.close();

            return new TweetLogic(lastID,tweets);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveTweetLogic(TweetLogic tweetLogic){
        try {
            Gson gson = new GsonBuilder().create();
            File file = new File("src/main/resources/tweetLogic.txt");
            PrintStream printStream = new PrintStream(file);

            printStream.println(tweetLogic.getLastID());
            for (Tweet tweet : tweetLogic.getAllTweets()){
                String tweetStr = gson.toJson(tweet);
                printStream.println(tweetStr);
            }
            printStream.flush();
            printStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UsersListLogic loadUsersListLogic(){
        try {
            Gson gson = new GsonBuilder().create();
            File file = new File("src/main/resources/usersListLogic.txt");
            Scanner scanner = new Scanner(file);
            LinkedList<UsersList> usersLists = new LinkedList<>();
            String lastID = scanner.nextLine();
            while (scanner.hasNextLine()){
                UsersList usersList = gson.fromJson(scanner.nextLine(), UsersList.class);
                usersLists.add(usersList);
            }
            scanner.close();

            return new UsersListLogic(lastID,usersLists);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveUsersListLogic(UsersListLogic usersListLogic){
        try {
            Gson gson = new GsonBuilder().create();
            File file = new File("src/main/resources/usersListLogic.txt");
            PrintStream printStream = new PrintStream(file);

            printStream.println(usersListLogic.getLastID());
            for (UsersList usersList : usersListLogic.getAllUsersList()){
                String string = gson.toJson(usersList);
                printStream.println(string);
            }
            printStream.flush();
            printStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ChatLogic loadChatLogic(){
        try {
            Gson gson = new GsonBuilder().create();
            File file = new File("src/main/resources/chatLogic.txt");
            Scanner scanner = new Scanner(file);
            LinkedList<ChatRoom> chatRooms = new LinkedList<>();
            String lastID = scanner.nextLine();
            while (scanner.hasNextLine()){
                ChatRoom chatRoom = gson.fromJson(scanner.nextLine(), ChatRoom.class);
                chatRooms.add(chatRoom);
            }
            scanner.close();

            return new ChatLogic(lastID,chatRooms);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveChatLogic(ChatLogic chatLogic){
        try {
            Gson gson = new GsonBuilder().create();
            File file = new File("src/main/resources/chatLogic.txt");
            PrintStream printStream = new PrintStream(file);

            printStream.println(chatLogic.getLastID());
            for (ChatRoom chatRoom : chatLogic.getAllChatRooms()){
                String string = gson.toJson(chatRoom);
                printStream.println(string);
            }
            printStream.flush();
            printStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public String loadUserLogicLastID(){
        try {
            Gson gson = new GsonBuilder().create();
            File file = new File("src/main/resources/userLogic.txt");
            Scanner scanner = new Scanner(file);
            LinkedList<User> users = new LinkedList<>();
            String lastID = scanner.nextLine();
            while (scanner.hasNextLine()){
                User user = gson.fromJson(scanner.nextLine(), User.class);
                users.add(user);
            }
            scanner.close();

            return lastID;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LinkedList<User> loadUserLogicUsers(){
        try {
            Gson gson = new GsonBuilder().create();
            File file = new File("src/main/resources/userLogic.txt");
            Scanner scanner = new Scanner(file);
            LinkedList<User> users = new LinkedList<>();
            String lastID = scanner.nextLine();
            while (scanner.hasNextLine()){
                User user = gson.fromJson(scanner.nextLine(), User.class);
                users.add(user);
            }
            scanner.close();

            return users;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void saveUserLogic(UserLogic userLogic){
        try {
            Gson gson = new GsonBuilder().create();
            File file = new File("src/main/resources/userLogic.txt");
            PrintStream printStream = new PrintStream(file);

            printStream.println(userLogic.getLastID());
            for (User user : userLogic.getAllUsers()){
                String string = gson.toJson(user);
                printStream.println(string);
            }
            printStream.flush();
            printStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
