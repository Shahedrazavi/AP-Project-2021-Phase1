package Models;

import Classes.IntHolder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class UserLogic {

    private LinkedList<User> users;


    public LinkedList<User> getAllUsers(){
        return users;
    }

    public void addToAllUsers(User user){
        getAllUsers().add(user);
    }

    public User IDtoUser(String ID){
        for (User user : getAllUsers()){
            if(user.getID().equals(ID)){
                return user;
            }
        }
        return null;
    }

    public boolean isUsernameTaken(String username){
        for ( User user : getAllUsers()){
            if (user.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    public LinkedList<String> getUserTweets(User user){
        return user.getTweets();
    }

    public void changeUsername(String newUsername , User user){
        user.setUsername(newUsername);
    }

    public User.UserBuilder newRawUser(){
        LocalDateTime localDateTime = LocalDateTime.now();
        int id = Integer.parseInt(User.getLastID()) + 1;
        String newID = Integer.toString(id);
        User.setLastID(newID);
        return new User.UserBuilder().setID(newID)
                .setLastSeen(localDateTime)
                .setTweets(new LinkedList<>()).setTweetNumber(new IntHolder(0))
                .setLikes(new LinkedList<>()).setLikeNumber(new IntHolder(0))
                .setRetweets(new LinkedList<>()).setRetweetNumber(new IntHolder(0))
                .setActive(true).setOnline(true)
                .setFollowing(new LinkedList<>()).setFollowers(new IntHolder(0))
                .setBlockedUsers(new LinkedList<>()).setMutedUsers(new LinkedList<>())
                .setRequested(new LinkedList<>()).setRequesters(new LinkedList<>())
                .setUsersLists(new LinkedList<>())
                .setSavedMessages(new LinkedList<>());
    }

    public void newUser(String username, String password,
                        String profileName,
                        String firstName, String lastName,
                        String email, String phoneNumber,
                        LocalDate birthday,
                        String bio,
                        boolean isPublic){

        User user = newRawUser()
                .setUsername(username).setPassword(password)
                .setProfileName(profileName)
                .setFirstName(firstName).setLastName(lastName)
                .setEmail(email).setPhoneNumber(phoneNumber)
                .setBirthday(birthday)
                .setBio(bio)
                .setPublic(isPublic)
                .build();

        addToAllUsers(user);
    }


}
