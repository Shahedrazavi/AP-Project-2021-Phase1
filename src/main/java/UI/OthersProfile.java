package UI;

import Models.ChatMessage;
import Models.ChatRoom;
import Models.User;
import Models.UserLogic;

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
            else {
                printer.println("Invalid input, please try again");
            }
        }
    }

    public void showInfo(boolean isOpen){
        printer.sectionShower("Your Profile");
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

    }
}