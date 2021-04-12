package UI;

import Models.*;

import java.util.LinkedList;

public class OthersProfile extends Profile {

    User visitedUser;

    OthersProfile(UserLogic userLogic, User user , User visitedUser) {
        super(userLogic, user);
        this.visitedUser = visitedUser;
    }

    @Override
    public void firstView() {
        // IF YOU ARE BLOCKED //
        if (visitedUser.isInBlockedUsers(user)){
            printer.println(visitedUser.getProfileName());
            printer.println("@"+visitedUser.getUsername());

            printer.showHighlightedMsg("!!This account has blocked you!!");
            printer.println("Enter \"block\" to block this account if you want.(or any other input to go back to main menu)");
            String input = sc.next();
            if (input.equals("block")){
                if (user.isInBlockedUsers(visitedUser)){
                    printer.println("You have already blocked this account");
                }
                else{
                    //Block//
                    userLogic.blockUser(user, visitedUser);
                    logger.block(user.getUsername(),user.getID(), visitedUser.getUsername(), visitedUser.getID());

                    printer.println("Blocked the account");
                }
            }

            MainMenu mainMenu = new MainMenu(userLogic,user);
            mainMenu.firstView();
        }
        // IF YOU BLOCKED THIS ACCOUNT //
        else if (user.isInBlockedUsers(visitedUser)){
            printer.println(visitedUser.getProfileName());
            printer.println("@"+visitedUser.getUsername());

            printer.showHighlightedMsg("!!This account has blocked you!!");
            printer.println("Enter \"unblock\" if you want to unblock this person (If you type anything else you will return to main menu)");
            String input = sc.next();
            if (input.equals("unblock")){
                //Unblock//
                userLogic.unblockUser(user, visitedUser);
                logger.unblock(user.getUsername(),user.getID(), visitedUser.getUsername(), visitedUser.getID());

                printer.println("Unblocked the account");

                firstView();
            }
            else {
                MainMenu mainMenu = new MainMenu(userLogic,user);
                mainMenu.firstView();
            }
        }
        // IF YOU ARE NOT BLOCKED //
        else{
            printer.println(visitedUser.getProfileName());
            printer.println("@"+visitedUser.getUsername());
            printer.nextLine();
            printer.println("Bio :");
            printer.println(visitedUser.getBio());
            printer.nextLine();

            if (userLogic.canSeePrivate(user,visitedUser)){
                mainPublicOptions();
            }
            else {
                mainPrivateOptions();
            }
        }

    }

    public void mainPrivateOptions(){
        printer.println("[0] Show info");
        printer.println("[1] Request to follow");
        printer.println("[5] Block");
        printer.println("[6] Report User");

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

                showInfo(false);
            }
            else if(input.equals("1")){
                condition = false;

                //request//
                if (visitedUser.hasRequested(user)){
                    printer.println("You have already requested. Please wait.");
                }
                else{
                    userLogic.request(user,visitedUser);
                    logger.request(user.getUsername(), user.getID(), visitedUser.getUsername(), visitedUser.getID());
                    printer.println("Request sent");
                }


                mainPrivateOptions();

            }
            else if(input.equals("5")){
                condition = false;

                if(user.isInBlockedUsers(visitedUser)){
                    //Unblock//
                    userLogic.unblockUser(user, visitedUser);
                    logger.unblock(user.getUsername(),user.getID(), visitedUser.getUsername(), visitedUser.getID());

                    printer.println("Unblocked the account");

                }
                else{
                    //Block//
                    userLogic.blockUser(user, visitedUser);
                    logger.block(user.getUsername(),user.getID(), visitedUser.getUsername(), visitedUser.getID());

                    printer.println("Blocked the account");

                }
                firstView();
            }
            else if (input.equals("6")){
                condition = false;
                printer.println("!!!Reported the account!!!");

                firstView();
            }
            else {
                printer.println("Invalid input, please try again");
            }
        }
    }

    public void mainPublicOptions(){
        printer.println("[0] Show info");
        printer.println("[1] follow/unfollow");
        printer.println("[2] Show tweets");
        printer.println("[3] Message");
        printer.println("[4] mute/unmute");
        printer.println("[5] Block");
        printer.println("[6] Report User");

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

                showInfo(true);

            }
            else if(input.equals("1")){
                condition = false;

                //Unfollow//
                if (visitedUser.isInFollowers(user)){

                    userLogic.unfollowUser(user,visitedUser);
                    logger.unfollow(user.getUsername(),user.getID(),visitedUser.getUsername(),visitedUser.getID());

                    printer.println("Unfollowed the account");
                }
                //Follow//
                else{

                    userLogic.followUser(user,visitedUser);
                    logger.follow(user.getUsername(),user.getID(),visitedUser.getUsername(),visitedUser.getID());

                    printer.println("Followed the account");
                }

                MainMenu mainMenu = new MainMenu(userLogic,user);
                mainMenu.firstView();
            }
            else if(input.equals("2")){
                condition = false;

                showVisitedUserTweets();
            }
            else if(input.equals("3")){
                condition = false;

                messageVisitedUser();

            }
            else if(input.equals("4")){
                condition = false;

                //Unmute//
                if (user.isInMutedUsers(visitedUser)){
                    userLogic.unmuteUser(user, visitedUser);
                    logger.unmute(user.getUsername(),user.getID(), visitedUser.getUsername(), visitedUser.getID());

                    printer.println("Unmuted the account");

                    mainPublicOptions();
                }
                //Mute//
                else{
                    userLogic.muteUser(user, visitedUser);
                    logger.mute(user.getUsername(),user.getID(), visitedUser.getUsername(), visitedUser.getID());

                    printer.println("Muted the account");

                    mainPublicOptions();
                }
            }
            else if(input.equals("5")){
                condition = false;

                if(user.isInBlockedUsers(visitedUser)){
                    //Unblock//
                    userLogic.unblockUser(user, visitedUser);
                    logger.unblock(user.getUsername(),user.getID(), visitedUser.getUsername(), visitedUser.getID());

                    printer.println("Unblocked the account");
                }
                else{
                    //Block//
                    userLogic.blockUser(user, visitedUser);
                    logger.block(user.getUsername(),user.getID(), visitedUser.getUsername(), visitedUser.getID());

                    printer.println("Blocked the account");
                }

                firstView();
            }
            else if (input.equals("6")){
                condition = false;
                printer.println("!!!Reported the account!!!");

                firstView();
            }
            else {
                printer.println("Invalid input, please try again");
            }
        }
    }

    public void showInfo(boolean isOpen){
        printer.sectionShower("Information:");
        printer.println(visitedUser.getProfileName());
        printer.println("@"+visitedUser.getUsername());
        printer.nextLine();
        printer.println("Bio:");
        printer.println(visitedUser.getBio());
        if (isOpen){
            printer.println("birthday: "+ visitedUser.getBirthday());
            printer.println("phone number: "+visitedUser.getPhoneNumber());
            printer.println("email: "+visitedUser.getEmail());
        }

        // Last seen //
        if (visitedUser.getLastSeenType() == User.LastSeenType.no_one){
            printer.println("Last seen recently");
        }
        else if (visitedUser.getLastSeenType() == User.LastSeenType.followings) {
            if(visitedUser.isInFollowings(user)){
                printer.println("Last seen on :"+ visitedUser.getLastSeen());
            }
            else{
                printer.println("Last seen recently");
            }
        }
        else if (visitedUser.getLastSeenType() == User.LastSeenType.everyone){
            printer.println("Last seen on :"+ visitedUser.getLastSeen());
        }


        printer.println("Enter \"back\" to go back");

        boolean condition = true;
        while (condition){
            String input = sc.next();
            if (input.equals("back")){
                condition = false;

                if(isOpen){
                    mainPublicOptions();
                }
                else {
                    mainPrivateOptions();
                }
            }
            else {
                printer.println("Invalid input, please try again");
            }
        }

    }

    public void messageVisitedUser(){
        //Blocked restriction //
        if (visitedUser.isInBlockedUsers(user) || user.isInBlockedUsers(visitedUser)){
            printer.println("Sorry, one of you is in the black list of the other, so you can't chat...");

            firstView();
        }
        // Not following restriction //
        else if (!visitedUser.isInFollowings(user) && !user.isInFollowings(visitedUser)){
            printer.println("Sorry, no one of you followed the other, so you can't chat...");

            firstView();
        }

        else{
            ChatRoom chatRoom = userLogic.findChatroomByTwoUsers(user,visitedUser);
            if (chatRoom==null){
                //MAKE NEW CHATROOM
                chatRoom = userLogic.getChatLogic().newChatRoom(user,visitedUser);
                logger.newChatroom(chatRoom.getID(),user.getID(),visitedUser.getID());
            }

            showChatroom(chatRoom);
        }

    }

    public void showChatroom(ChatRoom chatRoom){
        LinkedList<ChatMessage> chatMsgs = chatRoom.getChatMessages();

        printer.showHighlightedMsg("Messages:");

        int cursor = 0;
        if (!chatMsgs.isEmpty()){
            cursor = chatMsgs.size()-1;
        }

        boolean condition = true;
        while (condition){
            if (chatMsgs.isEmpty()){
                condition = false;
                printer.println("No chats.");
                printer.println("[1] new message (or enter anything else to go back to chat selection)");
                String input = sc.next();
                if (input.equals("1")) {
                    newChatMsg(chatRoom,1);
                }
                else {
                    firstView();
                }
            }
            else{
                ChatMessage msg = chatMsgs.get(cursor);
                ChatMessage.Owner owner = msg.getOwner();
                String username = userLogic.IDtoUser(chatRoom.getUser1ID()).getUsername();
                if (owner == ChatMessage.Owner.two){
                    username = userLogic.IDtoUser(chatRoom.getUser2ID()).getUsername();
                }

                printer.showHighlightedMsg(msg.getTimeString()+"\n" +username+ ": " +msg.getText());

                printer.println("[0] Cancel");
                printer.println("[1] Next");
                printer.println("[2] Previous");
                printer.println("[3] New message");
                String input = sc.next();
                if (input.equals("back") || input.equals("0")){
                    condition = false;

                    firstView();
                }
                else if(input.equals("1")){
                    if (cursor>0){
                        cursor--;
                    }
                    else{
                        printer.println("You reached the end of the chat");
                    }
                }
                else if(input.equals("2")){
                    if (cursor< chatMsgs.size()-1){
                        cursor++;
                    }
                    else{
                        printer.println("You reached the start of the chat");
                    }
                }
                else if (input.equals("3")){
                    condition = false;

                    newChatMsg(chatRoom,1);

                }
                else {
                    printer.println("Invalid input, please try again");
                }
            }
        }

    }

    public void newChatMsg(ChatRoom chatRoom , int key){
        printer.println("Enter the message: (By pressing enter your message ends)");
        String text = sc.nextLine();
        if (text.equals("back")){
            if (key==1){
                showChatroom(chatRoom);
            }
        }
        else{
            if (chatRoom.getUser1ID().equals(user.getID())){
                userLogic.getChatLogic().newChatMsg(chatRoom,text,1);
                logger.newChatMsg(chatRoom.getID(),1);
            }
            if (chatRoom.getUser2ID().equals(user.getID())){
                userLogic.getChatLogic().newChatMsg(chatRoom,text,2);
                logger.newChatMsg(chatRoom.getID(),2);
            }

            printer.println("Message sent");

            if(key==1){
                showChatroom(chatRoom);
            }

        }
    }

    public void showVisitedUserTweets(){
        LinkedList<Tweet> tweets = userLogic.getTweetLogic().getUserTweets(visitedUser.getID());
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
                printer.println("[7] Save     [8] Report      [9] View Profile");
                printer.println("[10] Mute    [11] Block");
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
                //Report//
                else if (input.equals("8")){

                    printer.println("Reported Tweet!");
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
                //Mute//
                else if (input.equals("10")){
                    //Unmute//
                    if (user.isInMutedUsers(owner)){
                        userLogic.unmuteUser(user, owner);
                        logger.unmute(user.getUsername(),user.getID(), owner.getUsername(), owner.getID());

                        printer.println("Unmuted the account");
                    }
                    //Mute//
                    else{
                        userLogic.muteUser(user, owner);
                        logger.mute(user.getUsername(),user.getID(), owner.getUsername(), owner.getID());

                        printer.println("Muted the account");
                    }
                }
                //Block//
                else if (input.equals("11")){
                    if(user.isInBlockedUsers(owner)){
                        //Unblock//
                        userLogic.unblockUser(user, owner);
                        logger.unblock(user.getUsername(),user.getID(), owner.getUsername(), owner.getID());

                        printer.println("Unblocked the account");
                    }
                    else{
                        //Block//
                        userLogic.blockUser(user, owner);
                        logger.block(user.getUsername(),user.getID(), owner.getUsername(), owner.getID());

                        printer.println("Blocked the account");
                    }
                }
                //Invalid//
                else {
                    printer.println("Invalid input, please try again");
                }
            }
        }
    }
}