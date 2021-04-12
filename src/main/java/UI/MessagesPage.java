package UI;

import Models.*;

import java.util.LinkedList;

public class MessagesPage extends InnerPage{


    MessagesPage(UserLogic userLogic, User user) {
        super(userLogic, user);
    }


    @Override
    public void firstView() {
        userLogic.updateLastSeen(user);

        printer.sectionShower("Messages");
        printer.println("Tip : If you press enter your message ends");

        mainOptions();

    }

    public void mainOptions(){
        printer.println("[1] Saved Messages (Memos)");
        printer.println("[2] Chats");
        boolean condition = true;
        while (condition){
            String input = sc.next();
            if (input.equals("back")){
                condition = false;

                MainMenu mainMenu = new MainMenu(userLogic,user);
                mainMenu.firstView();
            }
            else if(input.equals("1")){
                condition = false;

                memos();
            }
            else if(input.equals("2")){
                condition = false;

                chats();
            }
            else {
                printer.println("Invalid input, please try again");
            }
        }
    }

    public void memos(){
        printer.println("[1] Show Memos");
        printer.println("[2] New Memo");

        boolean condition = true;
        while (condition){
            String input = sc.next();
            if (input.equals("back")){
                condition = false;

                mainOptions();
            }
            else if(input.equals("1")){
                condition = false;

                showMemos();
            }
            else if(input.equals("2")){
                condition = false;

                newMemo();
            }
            else {
                printer.println("Invalid input, please try again");
            }
        }

    }

    public void chats(){
        printer.println("[1] Show Chats");
        printer.println("[2] New message to group(s)");

        boolean condition = true;
        while (condition){
            String input = sc.next();
            if (input.equals("back")){
                condition = false;

                mainOptions();
            }
            else if(input.equals("1")){
                condition = false;

                showChats();
            }
            else if(input.equals("2")){
                condition = false;

                groupMessage();
            }
            else {
                printer.println("Invalid input, please try again");

            }
        }
    }

    public void showMemos(){
        LinkedList<Memo> list = user.getMemos();

        printer.showHighlightedMsg("Memos:");

        int cursor = 0;
        if (!list.isEmpty()){
            cursor = list.size()-1;
        }

        boolean condition = true;
        while (condition){
            if (list.isEmpty()){
                condition = false;
                printer.println("You don't have any memos.");
                printer.println("Enter any input to go back");
                String input = sc.next();
                memos();
            }
            else{
                Memo memo = list.get(cursor);
                String text = memo.getText();

                printer.showHighlightedMsg(text);


                printer.println("[0] Go back");
                printer.println("[1] Next");
                printer.println("[2] Previous");

                String input = sc.next();
                if (input.equals("back") || input.equals("0")){
                    condition = false;

                    memos();
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

    public void newMemo(){
        printer.println("Enter your new Memo: (if you press enter it means that you ended your memo)");

        boolean condition = true;
        while (condition){
            String input = sc.nextLine();
            System.out.println(input);
            if (input.equals("back")) {
                condition = false;

                memos();
            }
            else {
                condition = false;

                Message message = new Message(input);
                user.addMemo(message);
                logger.newMemo(user.getUsername(), user.getID());
                userLogic.save();


                printer.println("Memo saved");

                memos();
            }
        }
    }

    public void showChats(){
        LinkedList<String> chatRooms = user.getChatRooms();

        printer.showHighlightedMsg("Chats:");

        int cursor = 0;
        if (!chatRooms.isEmpty()){
            cursor = chatRooms.size()-1;
        }

        boolean condition = true;
        while (condition){
            if (chatRooms.isEmpty()){
                condition = false;
                printer.println("You don't have any chats.");
                printer.println("[1] new chat (or enter anything else to go back to chat selection)");
                String input = sc.next();
                if (input.equals("1")) {
                    newChatRoom();
                }
                else {
                    chats();
                }
            }
            else{
                ChatRoom chatRoom = userLogic.getChatLogic().IDtoChatroom(chatRooms.get(cursor));
                String otherUserID = chatRoom.getOtherUserID(user.getID());
                User otherUser = userLogic.IDtoUser(otherUserID);
                String username = otherUser.getUsername();
                printer.showHighlightedMsg(username);

                printer.println("[0] Cancel");
                printer.println("[1] Next");
                printer.println("[2] Previous");
                printer.println("[3] Enter");
                printer.println("[4] New Chat");
                String input = sc.next();
                if (input.equals("back") || input.equals("0")){
                    condition = false;

                    chats();
                }
                else if(input.equals("1")){
                    if (cursor>0){
                        cursor--;
                    }
                    else{
                        printer.println("You reached the end of the chatRooms");
                    }
                }
                else if(input.equals("2")){
                    if (cursor< chatRooms.size()-1){
                        cursor++;
                    }
                    else{
                        printer.println("You reached the start of the chatRooms");
                    }
                }
                else if (input.equals("3")){
                    //Blocked restriction //
                    if (otherUser.isInBlockedUsers(user) || user.isInBlockedUsers(otherUser)){
                        printer.println("Sorry, one of you is in the black list of the other, so you can't chat...");
                    }
                    // Not following restriction //
                    else if (!otherUser.isInFollowings(user) && !user.isInFollowings(otherUser)){
                        printer.println("Sorry, no one of you followed the other, so you can't chat...");
                    }
                    else{
                        condition = false;

                        showChatroom(chatRoom);
                    }
                }
                else if(input.equals("4")){
                    condition = false;

                    newChatRoom();
                }
                else {
                    printer.println("Invalid input, please try again");
                }
            }
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
                    showChats();
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
                printer.println("[4] save this message as Memo");
                String input = sc.next();
                if (input.equals("back") || input.equals("0")){
                    condition = false;

                    chats();
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
                else if (input.equals("4")){

                    user.addMemo(msg);
                    logger.newMemo(user.getUsername(), user.getID());
                    userLogic.save();

                    printer.println("Memo saved");

                }
                else {
                    printer.println("Invalid input, please try again");
                }
            }
        }

    }

    public void groupMessage(){
        printer.println("Enter the message you want to send");

        boolean condition = true;
        String text = sc.nextLine();
        if(text.equals("back")){
            chats();
        }
        else {
            printer.println("Now pick the group(s) you want to send");
            pickGroups(text);
        }
    }

    public void pickGroups(String text){
        LinkedList<UsersList> pickedGroups = new LinkedList<>();

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
                chats();
            }
            else{
                UsersList group = list.get(cursor);
                String groupName = group.getListName();
                printer.showHighlightedMsg("Group: " + groupName);


                printer.println("[0] Cancel");
                printer.println("[1] Next");
                printer.println("[2] Previous");
                printer.println("[3] Pick group");
                printer.println("[4] Send");
                String input = sc.next();
                if (input.equals("back") || input.equals("0")){
                    condition = false;

                    chats();
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

                    //Picking group//
                    pickedGroups.add(group);

                    printer.println("Group picked");
                }
                else if (input.equals("4")){
                    condition = false;

                    groupSend(text, pickedGroups);
                }
                else {
                    printer.println("Invalid input, please try again");
                }
            }
        }
    }

    public void newChatRoom(){
        LinkedList<String> list = userLogic.newChatroomFilter(user,user.getFollowing());

        printer.showHighlightedMsg("Select an account:");

        int cursor = 0;
        if (!list.isEmpty()){
            cursor = list.size()-1;
        }

        boolean condition = true;
        while (condition){
            if (list.isEmpty()){
                condition = false;
                printer.println("There is no account.");
                printer.println("Enter any input to go back");
                String input = sc.next();
                showChats();
            }
            else{
                User selectedUser = userLogic.IDtoUser(list.get(cursor));
                String username = selectedUser.getUsername();
                printer.showHighlightedMsg("@" + username);


                printer.println("[0] Go back");
                printer.println("[1] Next");
                printer.println("[2] Previous");
                printer.println("[3] Select");
                String input = sc.next();
                if (input.equals("back") || input.equals("0")){
                    condition = false;

                    showChats();
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

                    //MAKE NEW CHATROOM
                    ChatRoom chatRoom = userLogic.getChatLogic().newChatRoom(user,selectedUser);
                    logger.newChatroom(chatRoom.getID(),user.getID(),selectedUser.getID());
                    userLogic.save();

                    printer.println("New Chatroom created");

                    showChats();

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
            userLogic.save();

            printer.println("Message sent");

            if(key==1){
                showChatroom(chatRoom);
            }

        }
    }

    public void groupSend(String text, LinkedList<UsersList> pickedGroups){
        LinkedList<String> userIDs = new LinkedList<>();
        for (UsersList group : pickedGroups){
            for (String userID : group.getUsers()){
                if (!userIDs.contains(userID)){
                    userIDs.add(userID);
                }
            }
        }

        LinkedList<String> validUsersToStartNewChats = userLogic.newChatroomFilter(user,userIDs);
        LinkedList<String> validUsersToChat = userLogic.oldChatroomFilter(user,userIDs);

        // Creating new chatrooms and sending messages for validUsersToStartNewChats //

        for (String selectedUserID : validUsersToStartNewChats){
            User selectedUser = userLogic.IDtoUser(selectedUserID);
            //MAKE NEW CHATROOM
            ChatRoom chatRoom = userLogic.getChatLogic().newChatRoom(user,selectedUser);
            logger.newChatroom(chatRoom.getID(),user.getID(),selectedUser.getID());

            //SENDING MESSAGES//
            userLogic.getChatLogic().newChatMsg(chatRoom,text,1);
            logger.newChatMsg(chatRoom.getID(),1);

        }

        //Sending message for validUsersToChat who user already has chatrooms with them.  //

        for (String selectedUserID :validUsersToChat){

            ChatRoom chatRoom = userLogic.findChatroomByTwoUsers(user,userLogic.IDtoUser(selectedUserID));
            //SENDING MESSAGES//
            if (chatRoom.getUser1ID().equals(user.getID())){
                userLogic.getChatLogic().newChatMsg(chatRoom,text,1);
                logger.newChatMsg(chatRoom.getID(),1);
            }
            if (chatRoom.getUser2ID().equals(user.getID())){
                userLogic.getChatLogic().newChatMsg(chatRoom,text,2);
                logger.newChatMsg(chatRoom.getID(),2);
            }
        }

        userLogic.save();

        printer.println("Messages sent successfully");

        chats();
    }
}
