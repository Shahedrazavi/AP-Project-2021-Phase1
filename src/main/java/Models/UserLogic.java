package Models;

import Models.Notifications.*;
import Util.IntHolder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class UserLogic {
    private String lastID = "";

    private LinkedList<User> users;

    NotifLogic notifLogic;

    public UserLogic(LinkedList<User> users , NotifLogic notifLogic) {
        this.users = users;
        this.notifLogic = notifLogic;
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
        if (getAllUsers().size()==0){
            return false;
        }
        for ( User user : getAllUsers()){
            if (user.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    public boolean isEmailTaken(String email){
        if (getAllUsers().size()==0){
            return false;
        }
        for (User user : getAllUsers()){
            if (user.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    public boolean isPhoneNumberTaken(String phoneNumber){
        if (getAllUsers().size()==0){
            return false;
        }
        for (User user : getAllUsers()){
            if (user.getPhoneNumber().equals(phoneNumber)){
                return true;
            }
        }
        return false;
    }

    public boolean passAuthenticator(User user, String password){
        return user.getPassword().equals(password);
    }

    public User findByUsername(String username){
        for (User user: getAllUsers()){
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    public boolean checkBlock(User user, User blockedUser){
        for (String ID : user.getBlockedUsers()){
            if(ID.equals(blockedUser)){
                return true;
            }
        }
        return false;
    }

    public boolean checkMute(User user, User mutedUser){
        for (String ID : user.getMutedUsers()){
            if(ID.equals(mutedUser)){
                return true;
            }
        }
        return false;
    }

    public boolean checkFollowing(User user, User followedUser){
        for (String ID : user.getFollowing()){
            if(ID.equals(followedUser)){
                return true;
            }
        }
        return false;
    }

    public void blockUser(User user, User blockedUser){
        user.addBlock(blockedUser.getID());
    }

    public void unblockUser(User user, User blockedUser){
        user.removeBlock(blockedUser.getID());
    }

    public void muteUser(User user, User mutedUser){
        user.addMute(mutedUser.getID());
    }

    public void unmuteUser(User user, User mutedUser){
        user.removeMute(mutedUser.getID());
    }

    public void followUser(User user, User followedUser){
        user.addFollowing(followedUser.getID());
        followedUser.addFollower(user.getID());
        notifLogic.addNotif(followedUser, Notification.NotifType.follow);
    }

    public void unfollowUser(User user, User followedUser){
        user.removeFollowing(followedUser.getID());
        followedUser.removeFollower(user.getID());
        notifLogic.addNotif(followedUser, Notification.NotifType.unfollow);
    }

    public void request(User user , User requestedUser){
        requestedUser.getRequesters().add(user.getID());
        user.getRequested().add(requestedUser.getID());
        notifLogic.addNotif(requestedUser, Notification.NotifType.followreq);
    }

    public void accept(User user , User acceptedUser){
        user.getRequesters().remove(acceptedUser.getID());
        user.getRequested().remove(acceptedUser.getID());
        followUser(acceptedUser , user);
    }

    public void reject(User user , User rejectedUser){
        user.getRequesters().remove(rejectedUser.getID());
        user.getRequested().remove(rejectedUser.getID());
    }

    public void Activate(User user){
        user.setActive(true);
    }

    public void Deactivate(User user){
        user.setActive(false);
    }

    public LinkedList<String> getUserTweets(User user){
        return user.getTweets();
    }

    public void changeUsername(String newUsername , User user){
        user.setUsername(newUsername);
    }

    public void updateLastSeen(User user){
        LocalDateTime nowTime = LocalDateTime.now();
        user.setLastSeen(nowTime);
    }

    /**
     * Creating a New User
     * @return user
     */

    public User.UserBuilder newRawUser(){
        LocalDateTime localDateTime = LocalDateTime.now();
        int id = Integer.parseInt(getLastID()) + 1;
        String newID = Integer.toString(id);
        setLastID(newID);
        return new User.UserBuilder().setID(newID)
                .setLastSeen(localDateTime)
                .setTweets(new LinkedList<>()).setTweetNumber(new IntHolder(0))
                .setLikes(new LinkedList<>()).setLikeNumber(new IntHolder(0))
                .setRetweets(new LinkedList<>()).setRetweetNumber(new IntHolder(0))
                .setActive(true).setOnline(true)
                .setFollowing(new LinkedList<>()).setFollowers(new LinkedList<>())
                .setBlockedUsers(new LinkedList<>()).setMutedUsers(new LinkedList<>())
                .setRequested(new LinkedList<>()).setRequesters(new LinkedList<>())
                .setUsersLists(new LinkedList<>());
    }

    public User newUser(String username, String password,
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
        return user;
    }


    @Override
    public String toString() {
        if (this == null){
            System.out.println("This is null");
        }
        return super.toString();
    }
}
