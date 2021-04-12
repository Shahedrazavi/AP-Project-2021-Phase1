package UI;

import Models.Tweet;
import Models.User;
import Models.UserLogic;
import Models.UsersList;

import java.util.LinkedList;

public class SelfProfile extends Profile{

    SelfProfile(UserLogic userLogic, User user) {
        super(userLogic, user);
    }

    @Override
    public void firstView() {
        printer.sectionShower("Your Profile");
        printer.println(user.getProfileName());
        printer.println("@"+user.getUsername());
        printer.nextLine();
        printer.println("Bio :");
        printer.println(user.getBio());
        printer.nextLine();

        mainOptions();


    }

    public void mainOptions(){
        printer.println("[0] Show info");
        printer.println("[1] New tweet");
        printer.println("[2] Show tweets");
        printer.println("[3] Edit Profile");
        printer.println("[4] People list (followings, followers, etc)");

        boolean condition = true;
        while (condition){
            String input = sc.next();
            if (input.equals("back")){
                condition = false;

                MainMenu mainMenu = new MainMenu(userLogic,user);
                mainMenu.firstView();
            }
            else if(input.equals("0")){
                condition = false;

                showInfo();
            }
            else if(input.equals("1")){
                condition = false;

                writeTweet();
            }
            else if(input.equals("2")){
                condition = false;

                selfTweetsViewer();
            }
            else if(input.equals("3")){
                condition = false;

                editProfile();
            }
            else if(input.equals("4")){
                condition = false;

                peopleLists();
            }
            else {
                printer.println("Invalid input, please try again");
            }
        }
    }

    public void showInfo(){
        printer.sectionShower("Your Profile");
        printer.println(user.getProfileName());
        printer.println("@"+user.getUsername());
        printer.nextLine();
        printer.println("Bio:");
        printer.println(user.getBio());
        printer.println("birthday: "+ user.getBirthday());
        printer.println("phone number: "+user.getPhoneNumber());
        printer.println("email: "+user.getEmail());

        printer.println("Enter \"back\" to go back");

        boolean condition = true;
        while (condition){
            String input = sc.next();
            if (input.equals("back")){
                condition = false;

                mainOptions();
            }
            else {
                printer.println("Invalid input, please try again");
            }
        }

    }

    public void writeTweet(){
        printer.println("Write your tweet: (If you press Enter it means the end of your tweet)(You can use \"back\" command)");


        boolean condition = true;
        while (condition){
            String input = sc.nextLine();
            if (input.equals("back")){
                condition = false;

                mainOptions();
            }

            else {
                condition = false;
                Tweet tweet =userLogic.getTweetLogic().tweet(input,user);
                logger.newTweet(user.getUsername(),user.getID(),tweet.getID());

                printer.println("New tweet saved");
                logger.changeBio(user.getUsername(),user.getID());


                mainOptions();
            }
        }

    }

    public void selfTweetsViewer(){
        LinkedList<Tweet> tweets = userLogic.getTweetLogic().getUserTweets(user.getID());
        tweetShower(tweets,true);
    }

    public void tweetShower(LinkedList<Tweet> list, boolean isFirst){

        int cursor = 0;
        if (!list.isEmpty()){
            cursor = list.size()-1;
        }

        boolean condition = true;
        while (condition){
            if (list.isEmpty()){
                condition = false;
                printer.println("There are no chats.");
                printer.println("Enter any input to go back");
                String input = sc.next();
                if (isFirst){
                    firstView();
                }

            }
            else{

                //Displaying The Tweet//
                Tweet tweet = list.get(cursor);
                User publisher = userLogic.IDtoUser(tweet.getUserID());
                User owner = userLogic.IDtoUser(tweet.getOwnerID());
                String tweetDate = tweet.getRetweetDate().toString();
                String text = tweet.getText();
                String publisherUsername = publisher.getUsername();
                String publisherProfileName = publisher.getProfileName();

                if (tweet.getTweetType() == Tweet.TweetType.Tweet){
                    printer.makePlusLine();
                    printer.nextLine();
                    printer.println(publisherProfileName+ "   (@"+publisherUsername+")   in"+tweetDate+":");
                    printer.println(text);
                    printer.makePlusLine();
                    printer.nextLine();
                }
                else if (tweet.getTweetType() == Tweet.TweetType.Comment){
                    Tweet parent = userLogic.getTweetLogic().IDtoTweet(tweet.getParentTweetID());
                    User parentOwner = userLogic.IDtoUser(parent.getOwnerID());
                    String parentOwnerUsername = parentOwner.getUsername();

                    printer.makePlusLine();
                    printer.nextLine();
                    printer.println("Replying to  @" + parentOwnerUsername);
                    printer.println(publisherProfileName+ "   (@"+publisherUsername+")   in"+tweetDate+":");
                    printer.println(text);
                    printer.makePlusLine();
                    printer.nextLine();
                }
                else if (tweet.getTweetType() == Tweet.TweetType.Retweet){
                    String ownerUsername = owner.getUsername();
                    String ownerProfileName = owner.getProfileName();

                    printer.makePlusLine();
                    printer.nextLine();
                    printer.println(publisherProfileName + " Retweeted");
                    printer.println(ownerProfileName+ "   (@"+ownerUsername+")   in"+tweetDate+":");
                    printer.println(text);
                    printer.makePlusLine();
                    printer.nextLine();
                }

                printer.println("Press anything to continue");
                String dummy = sc.next();



                //Features//

                printer.println("[0] Go back  [1] Next        [2] Previous");
                printer.println("[3] Like     [4] Retweet     [5] Comment     [6] Show Comments");
                printer.println("[7] Save     [9] View Profile");
                String input = sc.next();
                //Going Back//
                if (input.equals("back") || input.equals("0")){
                    condition = false;

                    if (isFirst){
                        firstView();
                    }

                }
                //Next//
                else if(input.equals("1")){
                    if (cursor>0){
                        cursor--;
                    }
                    else{
                        printer.println("You reached the end of the list");
                    }
                }
                //Previous//
                else if(input.equals("2")){
                    if (cursor<list.size()-1){
                        cursor++;
                    }
                    else{
                        printer.println("You reached the start of the list");
                    }
                }
                //Like//
                else if (input.equals("3")){
                    if (tweet.hasLiked(user.getID())){

                        userLogic.getTweetLogic().likeTweet(tweet,user);
                        logger.like(user.getUsername(),user.getID(),tweet.getID(),true);

                        printer.println("Liked Tweet!");
                    }

                    else {
                        printer.println("You have already liked this tweet.");
                    }
                }
                //Retweet//
                else if (input.equals("4")){
                    if (tweet.hasRetweeted(user.getID())){

                        Tweet retweet = userLogic.getTweetLogic().retweet(tweet,user);
                        logger.retweet(user.getUsername(),user.getID(),tweet.getID(),retweet.getID());

                        printer.println("Retweeted Tweet!");
                    }
                    else {
                        printer.println("You have already retweeted this tweet.");
                    }
                }
                //Comment//
                else if (input.equals("5")){
                    printer.println("Enter your comment: (By pressing enter your comment will end. by typing \"back\" comment will cancel.)");
                    String comment = sc.nextLine();
                    if (!comment.equals("back")){

                        Tweet commentTweet = userLogic.getTweetLogic().subTweet(comment,user,tweet);
                        logger.comment(user.getUsername(),user.getID(),tweet.getID(), commentTweet.getID());

                        printer.println("Comment sent!");
                    }
                }
                //Show Comments//
                else if (input.equals("6")){
                    LinkedList<Tweet> comments = userLogic.getTweetComments(tweet);
                    if (comments.isEmpty()){
                        printer.println("This tweet has no comments.");
                    }
                    else {
                        LinkedList<Tweet> filteredComments = userLogic.tweetFilter(user,comments,false);
                        tweetShower(filteredComments,false);
                    }
                }
                //Save//
                else if (input.equals("7")){
                    user.addMemo(tweet);
                    logger.newMemo(user.getUsername(), user.getID());

                    printer.println("Tweet saved!");
                }
                //View Profile//
                else if (input.equals("9")){
                    if (owner.getID().equals(user.getID())){
                        SelfProfile selfProfile = new SelfProfile(userLogic,user);
                        selfProfile.firstView();
                    }
                    else{
                        OthersProfile othersProfile = new OthersProfile(userLogic,user,owner);
                    }
                }
                //Invalid//
                else {
                    printer.println("Invalid input, please try again");
                }
            }
        }
    }

    public void editProfile(){
        printer.println("[1] Edit profile name");
        printer.println("[2] Edit bio");
        printer.println("[3] back");

        boolean condition = true;
        while (condition){
            String input = sc.next();
            if (input.equals("back")){
                condition = false;

                mainOptions();
            }
            else if(input.equals("1")){
                condition = false;

                editProfileName();
            }
            else if(input.equals("2")){
                condition = false;

                editBio();
            }
            else if(input.equals("3")){
                condition = false;

                mainOptions();
            }

            else {
                printer.println("Invalid input, please try again");
            }
        }

    }

    public void peopleLists(){
        printer.println("[1] Following");
        printer.println("[2] Followers");
        printer.println("[3] Blocked Users");
        printer.println("[4] Muted Users");
        printer.println("[5] Groups");
        printer.println("[6] Requesters");
        printer.println("[7] Accounts that you requested");

        boolean condition = true;
        while (condition){
            String input = sc.next();
            if (input.equals("back")){
                condition = false;

                mainOptions();
            }
            else if(input.equals("1")){
                condition = false;

                followings();
            }
            else if(input.equals("2")){
                condition = false;

                followers();
            }
            else if(input.equals("3")){
                condition = false;

                blockedUsers();
            }
            else if(input.equals("4")){
                condition = false;

                mutedUsers();
            }
            else if(input.equals("5")){
                condition = false;

                groups();
            }
            else if(input.equals("6")){
                condition = false;

                requesters();
            }
            else if(input.equals("7")){
                condition = false;

                requesteds();
            }

            else {
                printer.println("Invalid input, please try again");
            }
        }
    }

    public void followings(){
        LinkedList<String> list = user.getFollowing();

        printer.showHighlightedMsg("Accounts Following:");

        int cursor = 0;
        if (!list.isEmpty()){
            cursor = list.size()-1;
        }

        boolean condition = true;
        while (condition){
            if (list.isEmpty()){
                condition = false;
                printer.println("You are not following any account.");
                printer.println("Enter any input to go back");
                String input = sc.next();
                peopleLists();
            }
            else{
                User followedUser = userLogic.IDtoUser(list.get(cursor));
                String username = followedUser.getUsername();
                printer.showHighlightedMsg("@" + username);


                printer.println("[0] Go back");
                printer.println("[1] Next");
                printer.println("[2] Previous");
                printer.println("[3] Unfollow");
                String input = sc.next();
                if (input.equals("back") || input.equals("0")){
                    condition = false;

                    peopleLists();
                }
                else if(input.equals("1")){
                    if (cursor>0){
                        cursor--;
                    }
                    else{
                        printer.println("You reached the end of the list");
                    }
                }
                else if(input.equals("2")){
                    if (cursor<list.size()-1){
                        cursor++;
                    }
                    else{
                        printer.println("You reached the start of the list");
                    }
                }
                else if (input.equals("3")){


                    //Unfollow//
                    userLogic.unfollowUser(user,followedUser);
                    logger.unfollow(user.getUsername(),user.getID(),followedUser.getUsername(),followedUser.getID());

                    printer.println("Unfollowed the account");

                    if (cursor == (list.size()-1)){
                        cursor--;
                    }

                }
                else {
                    printer.println("Invalid input, please try again");
                }
            }
        }
    }

    public void followers(){
        LinkedList<String> list = user.getFollowers();

        printer.showHighlightedMsg("Accounts that Follow you:");

        int cursor = 0;
        if (!list.isEmpty()){
            cursor = list.size()-1;
        }

        boolean condition = true;
        while (condition){
            if (list.isEmpty()){
                condition = false;
                printer.println("You don't have any followers. Poor you :(   (Yeah I can cyberbully too!)");
                printer.println("Enter any input to go back");
                String input = sc.next();
                peopleLists();
            }
            else{
                User followingUser = userLogic.IDtoUser(list.get(cursor));
                String username = followingUser.getUsername();
                printer.showHighlightedMsg("@" + username);


                printer.println("[0] Go back");
                printer.println("[1] Next");
                printer.println("[2] Previous");
                printer.println("[3] Block");
                String input = sc.next();
                if (input.equals("back") || input.equals("0")){
                    condition = false;

                    peopleLists();
                }
                else if(input.equals("1")){
                    if (cursor>0){
                        cursor--;
                    }
                    else{
                        printer.println("You reached the end of the list");
                    }
                }
                else if(input.equals("2")){
                    if (cursor<list.size()-1){
                        cursor++;
                    }
                    else{
                        printer.println("You reached the start of the list");
                    }
                }
                else if (input.equals("3")){


                    //Block//
                    userLogic.blockUser(user, followingUser);
                    logger.block(user.getUsername(),user.getID(), followingUser.getUsername(), followingUser.getID());

                    printer.println("Blocked the account");

                    if (cursor == (list.size()-1)){
                        cursor--;
                    }

                }
                else {
                    printer.println("Invalid input, please try again");
                }
            }


        }
    }

    public void blockedUsers(){
        LinkedList<String> list = user.getBlockedUsers();

        printer.showHighlightedMsg("Blocked Accounts:");

        int cursor = 0;
        if (!list.isEmpty()){
            cursor = list.size()-1;
        }

        boolean condition = true;
        while (condition){
            if (list.isEmpty()){
                condition = false;
                printer.println("You didn't block anyone.");
                printer.println("Enter any input to go back");
                String input = sc.next();
                peopleLists();
            }
            else{
                User blockedUser = userLogic.IDtoUser(list.get(cursor));
                String username = blockedUser.getUsername();
                printer.showHighlightedMsg("@" + username);


                printer.println("[0] Go back");
                printer.println("[1] Next");
                printer.println("[2] Previous");
                printer.println("[3] unblock");
                String input = sc.next();
                if (input.equals("back") || input.equals("0")){
                    condition = false;

                    peopleLists();
                }
                else if(input.equals("1")){
                    if (cursor>0){
                        cursor--;
                    }
                    else{
                        printer.println("You reached the end of the list");
                    }
                }
                else if(input.equals("2")){
                    if (cursor<list.size()-1){
                        cursor++;
                    }
                    else{
                        printer.println("You reached the start of the list");
                    }
                }
                else if (input.equals("3")){


                    //Unblock//
                    userLogic.unblockUser(user, blockedUser);
                    logger.unblock(user.getUsername(),user.getID(), blockedUser.getUsername(), blockedUser.getID());

                    printer.println("Unblocked the account");

                    if (cursor == (list.size()-1)){
                        cursor--;
                    }

                }
                else {
                    printer.println("Invalid input, please try again");
                }
            }


        }
    }

    public void mutedUsers(){
        LinkedList<String> list = user.getMutedUsers();

        printer.showHighlightedMsg("Muted Accounts:");

        int cursor = 0;
        if (!list.isEmpty()){
            cursor = list.size()-1;
        }

        boolean condition = true;
        while (condition){
            if (list.isEmpty()){
                condition = false;
                printer.println("You didn't mute anyone.");
                printer.println("Enter any input to go back");
                String input = sc.next();
                peopleLists();
            }
            else{
                User mutedUser = userLogic.IDtoUser(list.get(cursor));
                String username = mutedUser.getUsername();
                printer.showHighlightedMsg("@" + username);


                printer.println("[0] Go back");
                printer.println("[1] Next");
                printer.println("[2] Previous");
                printer.println("[3] unmute");
                String input = sc.next();
                if (input.equals("back") || input.equals("0")){
                    condition = false;

                    peopleLists();
                }
                else if(input.equals("1")){
                    if (cursor>0){
                        cursor--;
                    }
                    else{
                        printer.println("You reached the end of the list");
                    }
                }
                else if(input.equals("2")){
                    if (cursor<list.size()-1){
                        cursor++;
                    }
                    else{
                        printer.println("You reached the start of the list");
                    }
                }
                else if (input.equals("3")){


                    //Unmute//
                    userLogic.unmuteUser(user, mutedUser);
                    logger.unmute(user.getUsername(),user.getID(), mutedUser.getUsername(), mutedUser.getID());

                    printer.println("Unmuted the account");

                    if (cursor == (list.size()-1)){
                        cursor--;
                    }

                }
                else {
                    printer.println("Invalid input, please try again");
                }
            }


        }
    }

    public void groups(){
        printer.println("[1] Show Groups");
        printer.println("[2] Create new group");

        boolean condition = true;
        while (condition){
            String input = sc.next();
            if (input.equals("back")){
                condition = false;

                peopleLists();
            }
            else if(input.equals("1")){
                condition = false;

                showGroups();
            }
            else if(input.equals("2")){
                condition = false;

                createGroup();
            }
            else {
                printer.println("Invalid input, please try again");
            }
        }
    }

    public void showGroups(){
        LinkedList<UsersList> list = user.getUsersLists();

        printer.showHighlightedMsg("Groups:");

        int cursor = 0;
        if (!list.isEmpty()){
            cursor = list.size()-1;
        }

        boolean condition = true;
        while (condition){
            if (list.isEmpty()){
                condition = false;
                printer.println("You don't have any groups.");
                printer.println("Enter any input to go back");
                String input = sc.next();
                groups();
            }
            else{
                UsersList group = list.get(cursor);
                String groupName = group.getListName();
                printer.showHighlightedMsg("Group: " + groupName);


                printer.println("[0] Go back");
                printer.println("[1] Next");
                printer.println("[2] Previous");
                printer.println("[3] Edit Group");
                String input = sc.next();
                if (input.equals("back") || input.equals("0")){
                    condition = false;

                    groups();
                }
                else if(input.equals("1")){
                    if (cursor>0){
                        cursor--;
                    }
                    else{
                        printer.println("You reached the end of the list");
                    }
                }
                else if(input.equals("2")){
                    if (cursor<list.size()-1){
                        cursor++;
                    }
                    else{
                        printer.println("You reached the start of the list");
                    }
                }
                else if (input.equals("3")){
                    condition = false;

                    //Editing group//

                    editGroup(group);

                }
                else {
                    printer.println("Invalid input, please try again");
                }
            }
        }
    }

    public void editGroup(UsersList group){
        printer.println("[1] Remove from Group");
        printer.println("[2] Add to group");

        boolean condition = true;
        while (condition){
            String input = sc.next();
            if (input.equals("back")){
                condition = false;

                showGroups();
            }
            else if(input.equals("1")){
                condition = false;

                removeInGroup(group);
            }
            else if(input.equals("2")){
                condition = false;

                addToGroup(group);
            }

            else {
                printer.println("Invalid input, please try again");
            }
        }
    }

    public void removeInGroup(UsersList group){
        LinkedList<String> list = group.getUsers();

        printer.showHighlightedMsg("People in the group:");

        int cursor = 0;
        if (!list.isEmpty()){
            cursor = list.size()-1;
        }

        boolean condition = true;
        while (condition){
            if (list.isEmpty()){
                condition = false;
                printer.println("This group is empty.");
                printer.println("Enter any input to go back");
                String input = sc.next();
                editGroup(group);
            }
            else{
                User userInGroup = userLogic.IDtoUser(list.get(cursor));
                String username = userInGroup.getUsername();
                printer.showHighlightedMsg("@" + username);


                printer.println("[0] Go back");
                printer.println("[1] Next");
                printer.println("[2] Previous");
                printer.println("[3] remove");
                String input = sc.next();
                if (input.equals("back") || input.equals("0")){
                    condition = false;

                    editGroup(group);
                }
                else if(input.equals("1")){
                    if (cursor>0){
                        cursor--;
                    }
                    else{
                        printer.println("You reached the end of the list");
                    }
                }
                else if(input.equals("2")){
                    if (cursor<list.size()-1){
                        cursor++;
                    }
                    else{
                        printer.println("You reached the start of the list");
                    }
                }
                else if (input.equals("3")){


                    //Remove//
                    group.removeUserFromList(userInGroup.getID());
                    logger.removeFromGroup(userInGroup.getUsername(),userInGroup.getID(),group.getListName(),group.getID());

                    printer.println("Removed from the group");

                    if (cursor == (list.size()-1)){
                        cursor--;
                    }

                }
                else {
                    printer.println("Invalid input, please try again");
                }
            }


        }
    }

    public void addToGroup(UsersList group){
        LinkedList<String> list = user.getFollowing();

        printer.showHighlightedMsg("Select from the accounts that you follow:");

        int cursor = 0;
        if (!list.isEmpty()){
            cursor = list.size()-1;
        }

        boolean condition = true;
        while (condition){
            if (list.isEmpty()){
                condition = false;
                printer.println("You are not following any account.");
                printer.println("Enter any input to go back");
                String input = sc.next();
                editGroup(group);
            }
            else{
                User followedUser = userLogic.IDtoUser(list.get(cursor));
                String username = followedUser.getUsername();
                printer.showHighlightedMsg("@" + username);


                printer.println("[0] Go back");
                printer.println("[1] Next");
                printer.println("[2] Previous");
                printer.println("[3] Add to Group");
                String input = sc.next();
                if (input.equals("back") || input.equals("0")){
                    condition = false;

                    editGroup(group);
                }
                else if(input.equals("1")){
                    if (cursor>0){
                        cursor--;
                    }
                    else{
                        printer.println("You reached the end of the list");
                    }
                }
                else if(input.equals("2")){
                    if (cursor<list.size()-1){
                        cursor++;
                    }
                    else{
                        printer.println("You reached the start of the list");
                    }
                }
                else if (input.equals("3")){
                    //Add//
                    if (group.getUsers().contains(followedUser.getID())){
                        printer.println("This account is already in the group");
                    }
                    else{
                        group.addUserToList(followedUser.getID());
                        logger.addToGroup(followedUser.getUsername(),followedUser.getID(),group.getListName(),group.getID());

                        printer.println("Added to Group");
                    }
                }
                else {
                    printer.println("Invalid input, please try again");
                }
            }
        }
    }

    public void createGroup(){
        printer.println("Enter the name of your new group:");

        boolean condition = true;
        while (condition){
            String input = sc.next();
            if (input.equals("back")){
                condition = false;
                groups();
            }

            else {
                condition = false;

                UsersList group = userLogic.getUsersListLogic().newUsersList(input,user);
                logger.newGroup(user.getUsername(), user.getID(), group.getListName() , group.getID());

                printer.println("Created a new group");

                groups();
            }
        }
    }

    public void requesters(){
        LinkedList<String> list = user.getRequesters();

        printer.showHighlightedMsg("Accounts Requested from you to accept:");

        int cursor = 0;
        if (!list.isEmpty()){
            cursor = list.size()-1;
        }

        boolean condition = true;
        while (condition){
            if (list.isEmpty()){
                condition = false;
                printer.println("You don't have any requester.");
                printer.println("Enter any input to go back");
                String input = sc.next();
                peopleLists();
            }
            else{
                User requestedUser = userLogic.IDtoUser(list.get(cursor));
                String username = requestedUser.getUsername();
                printer.showHighlightedMsg("@" + username);


                printer.println("[0] Go back");
                printer.println("[1] Next");
                printer.println("[2] Previous");
                printer.println("[3] Accept");
                printer.println("[4] Reject");
                String input = sc.next();
                if (input.equals("back") || input.equals("0")){
                    condition = false;

                    peopleLists();
                }
                else if(input.equals("1")){
                    if (cursor>0){
                        cursor--;
                    }
                    else{
                        printer.println("You reached the end of the list");
                    }
                }
                else if(input.equals("2")){
                    if (cursor<list.size()-1){
                        cursor++;
                    }
                    else{
                        printer.println("You reached the start of the list");
                    }
                }
                else if (input.equals("3")){


                    //Accept//
                    userLogic.accept(requestedUser,user);
                    logger.accept(user.getUsername(),user.getID(),requestedUser.getUsername(),requestedUser.getID());
                    logger.follow(requestedUser.getUsername(), requestedUser.getID(), user.getUsername(),user.getID());

                    printer.println("Accepted the account");

                    if (cursor == (list.size()-1)){
                        cursor--;
                    }

                }
                else if (input.equals("4")){


                    //Reject//
                    userLogic.reject(requestedUser,user);
                    logger.reject(user.getUsername(),user.getID(),requestedUser.getUsername(),requestedUser.getID());

                    printer.println("Rejected the account");

                    if (cursor == (list.size()-1)){
                        cursor--;
                    }

                }
                else {
                    printer.println("Invalid input, please try again");
                }
            }
        }
    }

    public void requesteds(){
        LinkedList<String> list = user.getRequested();

        printer.showHighlightedMsg("Accounts waiting to accept you:");

        int cursor = 0;
        if (!list.isEmpty()){
            cursor = list.size()-1;
        }

        boolean condition = true;
        while (condition){
            if (list.isEmpty()){
                condition = false;
                printer.println("You didn't send request to anyone.");
                printer.println("Enter any input to go back");
                String input = sc.next();
                peopleLists();
            }
            else{
                User userRequestedFrom = userLogic.IDtoUser(list.get(cursor));
                String username = userRequestedFrom.getUsername();
                printer.showHighlightedMsg("@" + username);


                printer.println("[0] Go back");
                printer.println("[1] Next");
                printer.println("[2] Previous");
                String input = sc.next();
                if (input.equals("back") || input.equals("0")){
                    condition = false;

                    peopleLists();
                }
                else if(input.equals("1")){
                    if (cursor>0){
                        cursor--;
                    }
                    else{
                        printer.println("You reached the end of the list");
                    }
                }
                else if(input.equals("2")){
                    if (cursor<list.size()-1){
                        cursor++;
                    }
                    else{
                        printer.println("You reached the start of the list");
                    }
                }

                else {
                    printer.println("Invalid input, please try again");
                }
            }
        }
    }

    public void editProfileName(){
        printer.println("Enter your new profile name: ");

        boolean condition = true;
        while (condition){
            String input = sc.next();
            if (input.equals("back")){
                condition = false;

                editProfile();
            }

            else {
                condition = false;
                user.setProfileName(input);

                printer.println("New profile name saved");
                logger.changeProfileName(user.getUsername(),user.getID());

                editProfile();
            }
        }

    }

    public void editBio(){
        printer.println("Enter your new bio:");

        boolean condition = true;
        while (condition){
            String input = sc.nextLine();
            if (input.equals("back")){
                condition = false;

                editProfile();
            }

            else {
                condition = false;
                user.setBio(input);

                printer.println("New bio saved");
                logger.changeBio(user.getUsername(),user.getID());


                editProfile();
            }
        }




    }
}
