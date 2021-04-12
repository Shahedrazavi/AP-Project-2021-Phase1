package Util;

import java.awt.*;
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
            fileOutputStream = new FileOutputStream(path, true);
            printStream = new PrintStream(fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void log(String log) {
        printStream.println(LocalDateTime.now() + ": " + log);
        printStream.flush();
    }

    public void signIn(String username, String userID) {
        log("User with username: \"" + username + "\" and ID: \"" + userID + "\" logged in.");
    }

    public void signUp(String username, String userID) {
        log("User with username: \"" + username + "\" and ID: \"" + userID + "\" created a new account.");
    }

    public void logOut(String username, String userID) {
        log("User with username: \"" + username + "\" and ID: \"" + userID + "\" logged out.");
    }

    public void delete(String username, String userID) {
        log("User with username: \"" + username + "\" and ID: \"" + userID + "\" deleted account.");
    }

    public void passwordChange(String username, String userID) {
        log("User with username: \"" + username + "\" and ID: \"" + userID + "\" changed password.");
    }

    public void privacy(String username, String userID, String privacy ){
        log("User with username: \"" + username + "\" and ID: \"" + userID + "\" changed the account privacy to " + privacy + ".");
    }

    public void lastSeenType(String username, String userID, String lastSeen){
        log("User with username: \"" + username + "\" and ID: \"" + userID + "\" changed lastSeen & online type to " + lastSeen  + ".");
    }

    public void activation(String username, String userID, String activate){
        log("User with username: \"" + username + "\" and ID: \"" + userID + "\" " + activate +" account.");
    }

    public void changeProfileName(String username, String userID){
        log("User with username: \"" + username + "\" and ID: \"" + userID + "\" changed profile name.");
    }

    public void changeBio(String username, String userID){
        log("User with username: \"" + username + "\" and ID: \"" + userID + "\" changed bio.");
    }

    public void newTweet(String username, String userID, String tweetID){
        log("User with username: \"" + username + "\" and ID: \"" + userID + "\" made a new tweet with ID: \"" + tweetID + "\".");

    }

    public void follow(String username1, String userID1 , String username2, String userID2){
        log("User with username: \"" + username1 + "\" and ID: \"" + userID1 + "\" followed user with username: \"" + username2 + "\" and ID: \"" + userID2 +"\".");
    }

    public void unfollow(String username1, String userID1 , String username2, String userID2){
        log("User with username: \"" + username1 + "\" and ID: \"" + userID1 + "\" unfollowed user with username: \"" + username2 + "\" and ID: \"" + userID2 +"\".");
    }

    public void block(String username1, String userID1 , String username2, String userID2){
        log("User with username: \"" + username1 + "\" and ID: \"" + userID1 + "\" blocked user with username: \"" + username2 + "\" and ID: \"" + userID2 +"\".");
    }

    public void unblock(String username1, String userID1 , String username2, String userID2){
        log("User with username: \"" + username1 + "\" and ID: \"" + userID1 + "\" unblocked user with username: \"" + username2 + "\" and ID: \"" + userID2 +"\".");
    }

    public void mute(String username1, String userID1 , String username2, String userID2){
        log("User with username: \"" + username1 + "\" and ID: \"" + userID1 + "\" muted user with username: \"" + username2 + "\" and ID: \"" + userID2 +"\".");
    }

    public void unmute(String username1, String userID1 , String username2, String userID2){
        log("User with username: \"" + username1 + "\" and ID: \"" + userID1 + "\" unmuted user with username: \"" + username2 + "\" and ID: \"" + userID2 +"\".");
    }

    public void addToGroup(String username1, String userID1 , String groupName, String groupID){
        log("User with username: \"" + username1 + "\" and ID: \"" + userID1 + "\" is added to the group with name: \"" + groupName + "\" and ID: \"" + groupID +"\".");
    }

    public void removeFromGroup(String username1, String userID1 , String groupName, String groupID){
        log("User with username: \"" + username1 + "\" and ID: \"" + userID1 + "\" is removed from the group with name: \"" + groupName + "\" and ID: \"" + groupID +"\".");
    }

    public void newGroup(String username1, String userID1 , String groupName, String groupID){
        log("User with username: \"" + username1 + "\" and ID: \"" + userID1 + "\" created a new group with name: \"" + groupName + "\" and ID: \"" + groupID +"\".");
    }

    public void request(String username1, String userID1 , String username2, String userID2){
        log("User with username: \"" + username1 + "\" and ID: \"" + userID1 + "\" requested from user with username: \"" + username2 + "\" and ID: \"" + userID2 +"\".");
    }

    public void accept(String username1, String userID1 , String username2, String userID2){
        log("User with username: \"" + username1 + "\" and ID: \"" + userID1 + "\" accepted user with username: \"" + username2 + "\" and ID: \"" + userID2 +"\".");
    }

    public void reject(String username1, String userID1 , String username2, String userID2){
        log("User with username: \"" + username1 + "\" and ID: \"" + userID1 + "\" rejected user with username: \"" + username2 + "\" and ID: \"" + userID2 +"\".");
    }

    public void newMemo(String username, String userID){
        log("User with username: \"" + username + "\" and ID: \"" + userID + "\" made a new Memo.");
    }

    public void newChatroom(String chatRoomID ,String user1ID, String user2ID){
        log("New Chatroom with ID \""+chatRoomID+"\" created for users with ID: \"" + user1ID + "\" and ID: \"" + user2ID + "\".");
    }

    public void newChatMsg(String chatRoomID , int userNumber){
        log("New Message sent by user number  \""+userNumber+"\" in the chatroom with ID: \"" + chatRoomID + "\".");
    }

    public void notifRemove(String username , String userID){
        log("User with username: \"" + username + "\" and ID: \"" + userID + "\" deleted a notification.");
    }

    public void like(String username , String userID , String tweetID , boolean liked){
        if (liked){
            log("User with username: \"" + username + "\" and ID: \"" + userID + "\" liked tweet with ID: \"" + tweetID +"\".");
        }
        else {
            log("User with username: \"" + username + "\" and ID: \"" + userID + "\" removed the like for tweet with ID: \"" + tweetID +"\".");
        }
    }

    public void retweet(String username , String userID , String tweetID , String retweetID){
        log("User with username: \"" + username + "\" and ID: \"" + userID + "\" retweeted tweet with ID: \"" + tweetID +"\".(Retweet ID is: \""+ retweetID + "\")");
    }

    public void reportTweet(String username , String userID , String tweetID){
        log("User with username: \"" + username + "\" and ID: \"" + userID + "\" reported tweet with ID: \"" + tweetID +"\".");
    }

    public void comment(String username , String userID , String tweetID , String commentID){
        log("User with username: \"" + username + "\" and ID: \"" + userID + "\" retweeted tweet with ID: \"" + tweetID +"\".(Comment ID is: \""+ commentID + "\")");
    }
}