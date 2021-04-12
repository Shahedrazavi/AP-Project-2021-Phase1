package Models;

import java.util.LinkedList;

public class UsersListLogic {
    private String lastID ;

    private LinkedList<UsersList> usersLists;

    public UsersListLogic(String lastID, LinkedList<UsersList> usersLists) {
        this.lastID = lastID;
        this.usersLists = usersLists;
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

    public LinkedList<UsersList> getAllUsersList(){
        return usersLists;
    }

    public void addToAllUsersLists(UsersList usersList){
        getAllUsersList().add(usersList);
    }

    public void removeFromAllUsersLists(UsersList usersList){
        getAllUsersList().remove(usersList);
    }

    public UsersList IDtoUsersList(String ID){
        for (UsersList usersList : getAllUsersList()){
            if(usersList.getID().equals(ID)){
                return usersList;
            }
        }
        return null;
    }

    public UsersList newUsersList(String listName , User owner){
        int id = Integer.parseInt(getLastID()) + 1;
        String newID = Integer.toString(id);
        setLastID(newID);
        UsersList usersList = new UsersList(newID,listName,owner.getID());
        addToAllUsersLists(usersList);
        owner.getUsersLists().add(usersList);
        return usersList;
    }

}
