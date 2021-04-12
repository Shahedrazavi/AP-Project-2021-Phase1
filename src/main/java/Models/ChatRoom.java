package Models;

import java.util.LinkedList;

public class ChatRoom {

    private String ID;
    private String user1ID;
    private String user2ID;
    private int unread1;
    private int unread2;
    private LinkedList<ChatMessage> chatMessages;

    public ChatRoom(String ID, String user1ID, String user2ID) {
        this.ID = ID;
        this.user1ID = user1ID;
        this.user2ID = user2ID;
        this.unread1 = 0;
        this.unread2 = 0;
        this.chatMessages = new LinkedList<>();
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUser1ID() {
        return user1ID;
    }

    public void setUser1ID(String user1ID) {
        this.user1ID = user1ID;
    }

    public String getUser2ID() {
        return user2ID;
    }

    public void setUser2ID(String user2ID) {
        this.user2ID = user2ID;
    }

    public int getUnread1() {
        return unread1;
    }

    public void setUnread1(int unread1) {
        this.unread1 = unread1;
    }

    public int getUnread2() {
        return unread2;
    }

    public void setUnread2(int unread2) {
        this.unread2 = unread2;
    }

    public LinkedList<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(LinkedList<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    public void addMsg(ChatMessage chatMessage){
        getChatMessages().add(chatMessage);
    }

    public String getOtherUserID(String userID){
        if (userID.equals(user1ID)){
            return user2ID;
        }
        return user1ID;
    }
}
