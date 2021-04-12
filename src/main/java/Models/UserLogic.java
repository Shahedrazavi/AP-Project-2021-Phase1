package Models;

import Models.Notifications.*;
import Util.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class UserLogic {
    private String lastID ;

    private LinkedList<User> users;

    private ModelLoader modelLoader;
    private NotifLogic notifLogic;
    private TweetLogic tweetLogic;
    private UsersListLogic usersListLogic;
    private ChatLogic chatLogic;

    private Logger logger;

    public UserLogic(String lastID, LinkedList<User> users , ModelLoader modelLoader, NotifLogic notifLogic , TweetLogic tweetLogic , UsersListLogic usersListLogic , ChatLogic chatLogic) {
        this.lastID = lastID;
        this.users = users;
        this.modelLoader = modelLoader;
        this.notifLogic = notifLogic;
        this.tweetLogic = tweetLogic;
        this.usersListLogic = usersListLogic;
        this.chatLogic = chatLogic;
        this.logger = Logger.getLogger();
    }

    public void initialize(){
        loadUsersListLogic();
        loadChatLogic();
        loadTweetLogic();
        loadUserLogic();
    }

    public void save(){
        saveUsersListLogic();
        saveChatLogic();
        saveTweetLogic();
        saveUserLogic();
    }

    private void loadTweetLogic(){
        tweetLogic = modelLoader.loadTweetLogic();
        if (tweetLogic.getAllTweets()==null){
            tweetLogic.setTweets(new LinkedList<>());
        }
        logger.loadTweetLogic();
    }

    private void saveTweetLogic(){
        modelLoader.saveTweetLogic(tweetLogic);
        logger.saveTweetLogic();
    }

    private void loadUsersListLogic(){
        usersListLogic = modelLoader.loadUsersListLogic();
        if (usersListLogic.getAllUsersList()==null){
            usersListLogic.setUsersLists(new LinkedList<>());
        }
        logger.loadUsersListLogic();
    }

    private void saveUsersListLogic(){
        modelLoader.saveUsersListLogic(usersListLogic);
        logger.saveUsersListLogic();
    }

    private void loadChatLogic(){
        chatLogic = modelLoader.loadChatLogic();
        if (chatLogic.getAllChatRooms()==null){
            chatLogic.setChatRooms(new LinkedList<>());
        }
        logger.loadChatLogic();
    }

    private void saveChatLogic(){
        modelLoader.saveChatLogic(chatLogic);
        logger.saveChatLogic();
    }

    private void loadUserLogic(){
        String lastID = modelLoader.loadUserLogicLastID();
        LinkedList<User> users = modelLoader.loadUserLogicUsers();

        setLastID(lastID);
        setUsers(users);

        if (getAllUsers()==null){
            setUsers(new LinkedList<>());
        }

        for (User user : this.users){
            String userID = user.getID();

            for ( UsersList usersList : getUsersListLogic().getAllUsersList()){
                if (usersList.getOwnerID().equals(userID)){
                    if (!user.getUsersLists().contains(usersList)){
                        user.addUserList(usersList);
                    }
                }
            }
            for ( Tweet tweet : getTweetLogic().getAllTweets()){
                if (tweet.getUserID().equals(userID)){
                    if(!user.getTweets().contains(tweet.getID())){
                        user.getTweets().add(tweet.getID());
                    }
                }
            }
            for ( ChatRoom chatRoom : getChatLogic().getAllChatRooms()){
                if (chatRoom.getUser1ID().equals(userID) || chatRoom.getUser2ID().equals(userID)){
                    if(!user.getChatRooms().contains(chatRoom.getID())){
                        user.getChatRooms().add(chatRoom.getID());
                    }
                }
            }
        }

        logger.loadUserLogic();
    }

    private void saveUserLogic(){
        modelLoader.saveUserLogic(this);
        logger.saveUserLogic();
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

    public NotifLogic getNotifLogic() {
        return notifLogic;
    }

    public TweetLogic getTweetLogic() {
        return tweetLogic;
    }

    public UsersListLogic getUsersListLogic() {
        return usersListLogic;
    }

    public ChatLogic getChatLogic() {
        return chatLogic;
    }



    public LinkedList<User> getAllUsers(){
        return users;
    }

    public void addToAllUsers(User user){
        getAllUsers().add(user);
    }

    public void removeFromAllUsers(User user){
        getAllUsers().remove(user);
    }

    public void setUsers(LinkedList<User> users) {
        this.users = users;
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
        String blockedUserID = blockedUser.getID();
        // if the blocked user is in the followers , program will remove it from the followers too //
        if (user.isInFollowers(blockedUser)){
            unfollowUser(blockedUser,user);
        }
        // if you were following this user , you will be removed from this account followers //
        if (blockedUser.isInFollowers(user)){
            unfollowUser(user,blockedUser);
        }
        user.addBlock(blockedUserID);
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
        notifLogic.addNotif(followedUser, user, Notification.NotifType.follow);
    }

    public void unfollowUser(User user, User followedUser){
        user.removeFollowing(followedUser.getID());
        followedUser.removeFollower(user.getID());
        notifLogic.addNotif(followedUser, user, Notification.NotifType.unfollow);
    }

    public void request(User requester, User target){
        target.addRequestedBy(requester.getID());
        requester.addRequestingFrom(target.getID());
        notifLogic.addNotif(target, requester, Notification.NotifType.followreq);
    }

    public void accept(User user , User acceptor){
        acceptor.removeRequestedBy(user.getID());
        user.removeRequestingFrom(acceptor.getID());
        followUser(user, acceptor);
    }

    public void reject(User user , User rejecter){
        rejecter.removeRequestedBy(user.getID());
        user.removeRequestingFrom(rejecter.getID());
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


    /** !!!Deleting A User!!!
     * Delete all contents of this user from all parts of the models
     */
    public void deleteUser(User deletedUser){
        String deletedUserID = deletedUser.getID();
        /* Removing User's tweets , likes, retweets and spam reports.

         */
        if (!tweetLogic.getAllTweets().isEmpty()){
            for ( Tweet tweet :tweetLogic.getAllTweets()){
                if (tweet.getLikes().contains(deletedUserID)){
                    tweet.removeLike(deletedUser);
                }
                if (tweet.getRetweets().contains(deletedUserID)){
                    tweet.removeRetweet(deletedUser);
                }
                if (tweet.getSpamReports().contains(deletedUserID)){
                    tweet.removeSpamReport(deletedUser);
                }



                if (tweet.getUserID().equals(deletedUserID)){

                    /* Retweets get deleted too */
                    if (tweet.getRetweets().size()!=0){
                        for (String ID : tweet.getRetweets()){
                            Tweet retweet = tweetLogic.IDtoTweet(ID);
                            User user = IDtoUser(retweet.getUserID());
                            user.removeFromTweets(ID);
                            tweetLogic.removeFromAllTweets(retweet);
                        }
                    }
                    /* Subtweets wont longer be a subtweet */
                    if (tweet.getSubTweets().size()!=0){
                        for (String ID : tweet.getSubTweets()){
                            Tweet subtweet = tweetLogic.IDtoTweet(ID);
                            subtweet.setParentTweetID("0");
                        }
                    }
                    /* deleting the tweet */
                    tweetLogic.removeFromAllTweets(tweet);
                }
            }
        }

        /* Removing chatrooms of this user */
        if (!chatLogic.getAllChatRooms().isEmpty()){
            for (ChatRoom chatRoom : chatLogic.getAllChatRooms()){
                String chatroomID = chatRoom.getID();
                if (chatRoom.getUser1ID().equals(deletedUserID)){
                    User user2 = IDtoUser(chatRoom.getUser2ID());
                    user2.removeChatRoom(chatroomID);
                }
                else if (chatRoom.getUser2ID().equals(deletedUserID)){
                    User user1 = IDtoUser(chatRoom.getUser1ID());
                    user1.removeChatRoom(chatroomID);
                }
                chatLogic.removeFromAllChatRooms(chatRoom);
            }
        }


        /* Removing userslist of this user and user from others userslist */
        if (!usersListLogic.getAllUsersList().isEmpty()){
            for (UsersList usersList : usersListLogic.getAllUsersList()){
                String userListID = usersList.getID();
                if (usersList.getUsers().contains(deletedUserID)){
                    usersList.removeUserFromList(deletedUserID);
                }
                if (usersList.getOwnerID().equals(deletedUserID)){
                    usersListLogic.removeFromAllUsersLists(usersList);
                }
            }
        }

        /* Deleting the user itself */
        removeFromAllUsers(deletedUser);


    }

    public LinkedList<String> newChatroomFilter(User user , LinkedList<String> users){
        LinkedList<String> filteredList = new LinkedList<>();

        for (String selectedUserID : users){
            User selectedUser = IDtoUser(selectedUserID);
            if (!hasChatroom(user, selectedUserID)){
                if (!user.isInBlockedUsers(selectedUser)){
                    if (!selectedUser.isInBlockedUsers(user)){
                        filteredList.add(selectedUserID);
                    }
                }
            }
        }
        return filteredList;
    }

    public LinkedList<String> oldChatroomFilter(User user , LinkedList<String> users){
        LinkedList<String> filteredList = new LinkedList<>();

        for (String selectedUserID : users){
            User selectedUser = IDtoUser(selectedUserID);
            if (hasChatroom(user, selectedUserID)){
                if (!user.isInBlockedUsers(selectedUser)){
                    if (!selectedUser.isInBlockedUsers(user)){
                        filteredList.add(selectedUserID);
                    }
                }
            }
        }
        return filteredList;
    }

    public boolean hasChatroom(User user , String checkingUserID){
        for ( String chatRoomID : user.getChatRooms()){
            ChatRoom chatRoom = getChatLogic().IDtoChatroom(chatRoomID);
            if (chatRoom.getOtherUserID(user.getID()).equals(checkingUserID)){
                return true;
            }
        }
        return false;
    }

    public ChatRoom findChatroomByTwoUsers( User user1 , User user2){
        for (String chatRoomID :user1.getChatRooms()){
            ChatRoom chatRoom = getChatLogic().IDtoChatroom(chatRoomID);
            if (chatRoom.getOtherUserID(user1.getID()).equals(user2.getID())){
                return chatRoom;
            }
        }
        return null;
    }

    public LinkedList<Tweet> tweetFilter(User user , LinkedList<Tweet> inputTweets , boolean mutedTweetsSwitch){
        LinkedList<Tweet> filteredTweets = new LinkedList<>();

        for (Tweet tweet : inputTweets){
            User tweetSourceUser = IDtoUser(tweet.getOwnerID());
            User tweetPublisherUser = IDtoUser(tweet.getUserID());

            // Checking for private accounts //
            boolean isSourcePrivate = canSeePrivate(user,tweetSourceUser);
            boolean isPublisherPrivate = canSeePrivate(user,tweetPublisherUser);

            // Checking for Blocks //
            boolean isSourceBlocked = user.isInBlockedUsers(tweetSourceUser);
            boolean isPublisherBlocked = user.isInBlockedUsers(tweetPublisherUser);
            boolean isUserBlockedBySource = tweetSourceUser.isInBlockedUsers(user);
            boolean isUserBlockedByPublisher = tweetPublisherUser.isInBlockedUsers(user);

            //Checking for mute if it's necessary //
            boolean isSourceMuted = true;
            boolean isPublisherMuted = true;

            if(mutedTweetsSwitch){
                isSourceMuted = user.isInMutedUsers(tweetSourceUser);
                isPublisherMuted = user.isInMutedUsers(tweetPublisherUser);
            }

            if (isSourcePrivate && isPublisherPrivate &&
            isSourceBlocked && isPublisherBlocked && isUserBlockedBySource && isUserBlockedByPublisher &&
            isSourceMuted && isPublisherMuted){
                filteredTweets.add(tweet);
            }
        }

        return filteredTweets;
    }

    public boolean canSeePrivate(User user , User target){
        if (target.isPublic()){
            return true;
        }
        else {
            return target.isInFollowers(user);
        }
    }

    public LinkedList<Tweet> getTweetComments(Tweet tweet){
        LinkedList<Tweet> comments = new LinkedList<>();
        for (String commentID : tweet.getSubTweets()){
            Tweet comment = getTweetLogic().IDtoTweet(commentID);
            comments.add(comment);
        }

        return comments;
    }

    public LinkedList<Tweet> getAllFollowingsTweets(User user){
        LinkedList<Tweet> tweets = new LinkedList<>();
        for (String followingID : user.getFollowing()){

            LinkedList<Tweet> followingTweets = getTweetLogic().getUserTweets(followingID);

            for (Tweet tweet : followingTweets){
                if (!tweets.contains(tweet)){
                    tweets.add(tweet);
                }
            }
        }
        return tweets;
    }

    @Override
    public String toString() {
        if (this == null){
            System.out.println("This is null");
        }
        return super.toString();
    }
}
