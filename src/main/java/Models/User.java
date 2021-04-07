package Models;

import java.util.Date;
import java.util.LinkedList;

public class User {
    private String ID;
    private String username;
    private String password;
    private String profileName;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date birthday;
    private String bio;
    private Date lastSeen;

    // for future features //
    private LinkedList<String> likes;
    private int likeNumber;
    private LinkedList<String> retweets;
    private int retweetNumber;

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
                String firstName, String lastName, String email, String phoneNumber, Date birthday,
                String bio,
                Date lastSeen,
                LinkedList<String> likes, int likeNumber,
                LinkedList<String> retweets, int retweetNumber,
                boolean isActive, boolean isPublic, boolean isOnline,
                LinkedList<String> following, LinkedList<String> followers,
                LinkedList<String> blockedUsers, LinkedList<String> mutedUsers,
                LinkedList<String> requested, LinkedList<String> requesters,
                LinkedList<UsersList> usersLists) {
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
        private Date birthday;
        private String bio;
        private Date lastSeen;

        // for future features //
        private LinkedList<String> likes;
        private int likeNumber;
        private LinkedList<String> retweets;
        private int retweetNumber;

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

        public UserBuilder setBirthday(Date birthday) {
            this.birthday = birthday;
            return this;
        }

        public UserBuilder setBio(String bio) {
            this.bio = bio;
            return this;
        }

        public UserBuilder setLastSeen(Date lastSeen) {
            this.lastSeen = lastSeen;
            return this;
        }

        public UserBuilder setLikes(LinkedList<String> likes) {
            this.likes = likes;
            return this;
        }

        public UserBuilder setLikeNumber(int likeNumber) {
            this.likeNumber = likeNumber;
            return this;
        }

        public UserBuilder setRetweets(LinkedList<String> retweets) {
            this.retweets = retweets;
            return this;
        }

        public UserBuilder setRetweetNumber(int retweetNumber) {
            this.retweetNumber = retweetNumber;
            return this;
        }

        public UserBuilder setActive(boolean active) {
            isActive = active;
            return this;
        }

        public UserBuilder setPublic(boolean aPublic) {
            isPublic = aPublic;
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

        public User build(){
            return new User(ID,
                    username, password,
                    profileName,
                    firstName, lastName, email, phoneNumber, birthday,
                    bio,
                    lastSeen,
                    likes, likeNumber,
                    retweets, retweetNumber,
                    isActive , isPublic , isOnline,
                    following, followers,
                    blockedUsers, mutedUsers,
                    requested, requesters,
                    usersLists);
        }
    }
}
