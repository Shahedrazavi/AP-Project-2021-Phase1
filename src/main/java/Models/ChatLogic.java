package Models;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class ChatLogic {
    private String lastID ;

    private LinkedList<ChatRoom> chatRooms;

    public ChatLogic(String lastID, LinkedList<ChatRoom> chatRooms) {
        this.lastID = lastID;
        this.chatRooms = chatRooms;
    }

    public String getLastID() {
        if (lastID.equals("")){
            return "0";
        }
        return lastID;
    }

    public void setLastID(String lastID) {
        this.lastID = lastID;
    }

    public LinkedList<ChatRoom> getAllChatRooms(){
        return chatRooms;
    }

    public void setChatRooms(LinkedList<ChatRoom> chatRooms) {
        this.chatRooms = chatRooms;
    }

    public void addToAllChatRooms(ChatRoom chatRoom){
        getAllChatRooms().add(chatRoom);
    }

    public void removeFromAllChatRooms(ChatRoom chatRoom){
        getAllChatRooms().remove(chatRoom);
    }

    public ChatRoom IDtoChatroom(String ID){
        for (ChatRoom chatRoom : getAllChatRooms()){
            if(chatRoom.getID().equals(ID)){
                return chatRoom;
            }
        }
        return null;
    }

    public ChatRoom newChatRoom(User user1 , User user2){
        int id = Integer.parseInt(getLastID()) + 1;
        String newID = Integer.toString(id);
        setLastID(newID);
        ChatRoom chatRoom = new ChatRoom(newID, user1.getID(), user2.getID());
        user1.addChatRoom(newID);
        user2.addChatRoom(newID);
        addToAllChatRooms(chatRoom);
        return chatRoom;
    }

    public ChatMessage newChatMsg(ChatRoom chatRoom, String text, int userNumber){
        ChatMessage.Owner owner = ChatMessage.Owner.one;
        if (userNumber == 2){
            owner = ChatMessage.Owner.two;
        }
        ChatMessage chatMessage = new ChatMessage(text, LocalDateTime.now(), chatRoom.getID(),owner);
        chatRoom.addMsg(chatMessage);
        return chatMessage;
    }

}
