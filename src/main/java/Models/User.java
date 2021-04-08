package Models;

import Classes.IntHolder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;

public class User {
    public static String lastID;

    private String ID;
    private String username;
    private String password;
    private String profileName;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate birthday;
    private String bio;
    private LocalDateTime lastSeen;


    private LinkedList<String> tweets;
    private IntHolder tweetNumber;

    // for future features //
    private LinkedList<String> likes;
    private IntHolder likeNumber;
    private LinkedList<String> retweets;
    private IntHolder retweetNumber;

    private boolean isActive;
    private boolean isPublic;
    private boolean isOnline;

    private LinkedList<String> following;
    private LinkedList<String> followers;
    private LinkedList<String> blockedUsers;
    private LinkedList<String> mutedUsers;
    private LinkedList<String> requested;
    private LinkedList<String> requesters;

    private LinkedList<UsersList> usersLists;

    private LinkedList<SavedMessage> savedMessages;

    class UsersList{
        private String listName;
        private LinkedList<String> users;

        UsersList(String listName) {
            this.listName = listName;
        }

        public void addUser(String ID){
            users.add(ID);
        }
    }

    public User(String ID,
                String username, String password,
                String profileName,
                String firstName, String lastName, String email, String phoneNumber, LocalDate birthday,
                String bio,
                LocalDateTime lastSeen,
                LinkedList<String> tweets, IntHolder tweetNumber,
                LinkedList<String> likes, IntHolder likeNumber,
                LinkedList<String> retweets, IntHolder retweetNumber,
                boolean isActive, boolean isPublic, boolean isOnline,
                LinkedList<String> following, LinkedList<String> followers,
                LinkedList<String> blockedUsers, LinkedList<String> mutedUsers,
                LinkedList<String> requested, LinkedList<String> requesters,
                LinkedList<UsersList> usersLists,
                LinkedList<SavedMessage> savedMessages) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.profileName = profileName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.bio = bio;
        this.lastSeen = lastSeen;
        this.tweets = tweets;
        this.tweetNumber = tweetNumber;
        this.likes = likes;
        this.likeNumber = likeNumber;
        this.retweets = retweets;
        this.retweetNumber = retweetNumber;
        this.isActive = isActive;
        this.isPublic = isPublic;
        this.isOnline = isOnline;
        this.following = following;
        this.followers = followers;
        this.blockedUsers = blockedUsers;
        this.mutedUsers = mutedUsers;
        this.requested = requested;
        this.requesters = requesters;
        this.usersLists = usersLists;
        this.savedMessages = savedMessages;
    }

    static class UserBuilder{
        private String ID;
        private String username;
        private String password;
        private String profileName;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private LocalDate birthday;
        private String bio;
        private LocalDateTime lastSeen;

        private LinkedList<String> tweets;
        private IntHolder tweetNumber;

        // for future features //
        private LinkedList<String> likes;
        private IntHolder likeNumber;
        private LinkedList<String> retweets;
        private IntHolder retweetNumber;

        private boolean isActive;
        private boolean isPublic;
        private boolean isOnline;

        private LinkedList<String> following;
        private LinkedList<String> followers;
        private LinkedList<String> blockedUsers;
        private LinkedList<String> mutedUsers;
        private LinkedList<String> requested;
        private LinkedList<String> requesters;

        private LinkedList<UsersList> usersLists;

        private LinkedList<SavedMessage> savedMessages;

        public UserBuilder setID(String ID) {
            this.ID = ID;
            return this;
        }

        public UserBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setProfileName(String profileName) {
            this.profileName = profileName;
            return this;
        }

        public UserBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserBuilder setBirthday(LocalDate birthday) {
            this.birthday = birthday;
            return this;
        }

        public UserBuilder setBio(String bio) {
            this.bio = bio;
            return this;
        }

        public UserBuilder setLastSeen(LocalDateTime lastSeen) {
            this.lastSeen = lastSeen;
            return this;
        }

        public UserBuilder setTweets(LinkedList<String> tweets) {
            this.tweets = tweets;
            return this;
        }

        public UserBuilder setTweetNumber(IntHolder tweetNumber) {
            this.tweetNumber = tweetNumber;
            return this;
        }

        public UserBuilder setLikes(LinkedList<String> likes) {
            this.likes = likes;
            return this;
        }

        public UserBuilder setLikeNumber(IntHolder likeNumber) {
            this.likeNumber = likeNumber;
            return this;
        }

        public UserBuilder setRetweets(LinkedList<String> retweets) {
            this.retweets = retweets;
            return this;
        }

        public UserBuilder setRetweetNumber(IntHolder retweetNumber) {
            this.retweetNumber = retweetNumber;
            return this;
        }

        public UserBuilder setActive(boolean active) {
            isActive = active;
            return this;
        }

        public UserBuilder setPublic(boolean Public) {
            isPublic = Public;
            return this;
        }

        public UserBuilder setOnline(boolean online) {
            isOnline = online;
            return this;
        }

        public UserBuilder setFollowing(LinkedList<String> following) {
            this.following = following;
            return this;
        }

        public UserBuilder setFollowers(LinkedList<String> followers) {
            this.followers = followers;
            return this;
        }

        public UserBuilder setBlockedUsers(LinkedList<String> blockedUsers) {
            this.blockedUsers = blockedUsers;
            return this;
        }

        public UserBuilder setMutedUsers(LinkedList<String> mutedUsers) {
            this.mutedUsers = mutedUsers;
            return this;
        }

        public UserBuilder setRequested(LinkedList<String> requested) {
            this.requested = requested;
            return this;
        }

        public UserBuilder setRequesters(LinkedList<String> requesters) {
            this.requesters = requesters;
            return this;
        }

        public UserBuilder setUsersLists(LinkedList<UsersList> usersLists) {
            this.usersLists = usersLists;
            return this;
        }

        public UserBuilder setSavedMessages(LinkedList<SavedMessage> savedMessages) {
            this.savedMessages = savedMessages;
            return this;
        }

        public User build(){
            return new User(ID,
                    username, password,
                    profileName,
                    firstName, lastName, email, phoneNumber, birthday,
                    bio,
                    lastSeen,
                    tweets , tweetNumber,
                    likes, likeNumber,
                    retweets, retweetNumber,
                    isActive , isPublic , isOnline,
                    following, followers,
                    blockedUsers, mutedUsers,
                    requested, requesters,
                    usersLists,
                    savedMessages);
        }
    }

    /** Setters and Getters
     *
     */

    public static String getLastID() {
        return lastID;
    }

    public static void setLastID(String lastID) {
        User.lastID = lastID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public LocalDateTime getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(LocalDateTime lastSeen) {
        this.lastSeen = lastSeen;
    }

    public LinkedList<String> getTweets() {
        return tweets;
    }

    public void setTweets(LinkedList<String> tweets) {
        this.tweets = tweets;
    }

    public IntHolder getTweetNumber() {
        return tweetNumber;
    }

    public void setTweetNumber(IntHolder tweetNumber) {
        this.tweetNumber = tweetNumber;
    }

    public LinkedList<String> getLikes() {
        return likes;
    }

    public void setLikes(LinkedList<String> likes) {
        this.likes = likes;
    }

    public IntHolder getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(IntHolder likeNumber) {
        this.likeNumber = likeNumber;
    }

    public LinkedList<String> getRetweets() {
        return retweets;
    }

    public void setRetweets(LinkedList<String> retweets) {
        this.retweets = retweets;
    }

    public IntHolder getRetweetNumber() {
        return retweetNumber;
    }

    public void setRetweetNumber(IntHolder retweetNumber) {
        this.retweetNumber = retweetNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public LinkedList<String> getFollowing() {
        return following;
    }

    public void setFollowing(LinkedList<String> following) {
        this.following = following;
    }

    public LinkedList<String> getFollowers() {
        return followers;
    }

    public void setFollowers(LinkedList<String> followers) {
        this.followers = followers;
    }

    public LinkedList<String> getBlockedUsers() {
        return blockedUsers;
    }

    public void setBlockedUsers(LinkedList<String> blockedUsers) {
        this.blockedUsers = blockedUsers;
    }

    public LinkedList<String> getMutedUsers() {
        return mutedUsers;
    }

    public void setMutedUsers(LinkedList<String> mutedUsers) {
        this.mutedUsers = mutedUsers;
    }

    public LinkedList<String> getRequested() {
        return requested;
    }

    public void setRequested(LinkedList<String> requested) {
        this.requested = requested;
    }

    public LinkedList<String> getRequesters() {
        return requesters;
    }

    public void setRequesters(LinkedList<String> requesters) {
        this.requesters = requesters;
    }

    public LinkedList<UsersList> getUsersLists() {
        return usersLists;
    }

    public void setUsersLists(LinkedList<UsersList> usersLists) {
        this.usersLists = usersLists;
    }

    public LinkedList<SavedMessage> getSavedMessages() {
        return savedMessages;
    }

    public void setSavedMessages(LinkedList<SavedMessage> savedMessages) {
        this.savedMessages = savedMessages;
    }




    public void addToTweets(String ID){
        getTweets().add(ID);
    }
}
