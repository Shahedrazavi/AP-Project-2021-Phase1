package Models;

import java.util.LinkedList;

public class UsersList {

    private String ID;
    private String listName;
    private LinkedList<String> users;
    private String ownerID;

    UsersList(String ID, String listName , String ownerID) {
        this.ID = ID;
        this.listName = listName;
        this.users = new LinkedList<>();
        this.ownerID = ownerID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public LinkedList<String> getUsers() {
        return users;
    }

    public void setUsers(LinkedList<String> users) {
        this.users = users;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public void addUserToList(String userID){
        users.add(userID);
    }

    public void removeUserFromList(String ID){
        getUsers().remove(ID);
    }
}
