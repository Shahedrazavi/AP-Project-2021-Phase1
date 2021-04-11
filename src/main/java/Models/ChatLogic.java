package Models;

import java.util.LinkedList;

public class ChatLogic {

    private String lastID = "";

    private LinkedList<ChatRoom> chatRooms;

    public String getLastID() {
        if (lastID.equals("")){
            return "0";
        }
        return lastID;
    }

    public void setLastID(String lastID) {
        this.lastID = lastID;
    }



}
