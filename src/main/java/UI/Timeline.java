package UI;

import Models.Tweet;
import Models.User;
import Models.UserLogic;

import java.util.LinkedList;

public class Timeline extends InnerPage{

    Timeline(UserLogic userLogic, User user) {
        super(userLogic, user);
    }

    @Override
    public void firstView() {
        printer.sectionShower("Timeline");
        printer.println("Type anything to continue (by typing \"back\" you will return to main menu)");
        String dummy = sc.nextLine();
        if (dummy.equals("back")){
            MainMenu mainMenu = new MainMenu(userLogic,user);
            mainMenu.firstView();
        }
        else{
            LinkedList<Tweet> tweets = userLogic.getAllFollowingsTweets(user);
            tweetShower(tweets,true);
        }

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
