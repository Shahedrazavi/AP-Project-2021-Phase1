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


}
