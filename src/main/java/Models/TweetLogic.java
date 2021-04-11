package Models;

import Util.IntHolder;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class TweetLogic {
    private String lastID = "";

    private LinkedList<Tweet> tweets;

    public String getLastID() {
        if (lastID.equals("")){
            return "0";
        }
        return lastID;
    }

    public void setLastID(String lastID) {
        this.lastID = lastID;
    }


    public LinkedList<Tweet> getAllTweets(){
        return tweets;
    }

    public void addToAllTweets(Tweet tweet){
        getAllTweets().add(tweet);
    }

    public Tweet IDtoTweet(String ID){
        for (Tweet tweet : getAllTweets()){
            if (tweet.getID().equals(ID)){
                return tweet;
            }
        }
        return null;
    }

    /**
     * Checking if a likedTweet is liked by a certain user
     * @param likedTweet
     * @param user
     * @return boolean
     */
    public boolean checkLike(Tweet likedTweet, User user){
        String userID = user.getID();
        LinkedList<String> likes = IDtoTweet(likedTweet.getSourceTweetID()).getLikes();

        for ( String likedID : likes){
            if(likedID.equals(userID)){
                return true;
            }
        }
        return false;
    }

    public boolean checkRetweet(Tweet sourceTweet, User user){
        String userID = user.getID();
        LinkedList<String> retweets = IDtoTweet(sourceTweet.getSourceTweetID()).getRetweets();

        for ( String retweetedID : retweets){
            if(retweetedID.equals(userID)){
                return true;
            }
        }
        return false;
    }

    public boolean checkSpamReport(Tweet spamTweet, User user){
        String userID = user.getID();
        LinkedList<String> spamReports = IDtoTweet(spamTweet.getSourceTweetID()).getSpamReports();

        for(String spamReportedID : spamReports){
            if(spamReportedID.equals(userID)){
                return true;
            }
        }
        return false;
    }


    /* liking a likedTweet
     */
    public void likeTweet(Tweet likedTweet, User user){
        Tweet source = IDtoTweet(likedTweet.getSourceTweetID());
        source.addLike(user);
    }

    public void unlikeTweet(Tweet unlikedTweet, User user){
        Tweet source = IDtoTweet(unlikedTweet.getSourceTweetID());
        source.removeLike(user);
    }

//    public void newTweet(){
//        Tweet tweet = new Tweet.TweetBuilder().build();
//        getAllTweets().add(tweet);
//    }

    /** Creating rawTweet
     *
     */
    public Tweet.TweetBuilder newRawTweet(String text, User user) {
        LocalDateTime localDateTime = LocalDateTime.now();
        int id = Integer.parseInt(getLastID()) + 1;
        String newID = Integer.toString(id);
        setLastID(newID);
        return new Tweet.TweetBuilder().setID(newID).setText(text).setDate(localDateTime).setUserID(user.getID())
                .setLikes(new LinkedList<>()).setLikeNumber(new IntHolder(0))
                .setRetweets(new LinkedList<>()).setRetweetNumber(new IntHolder(0))
                .setSpamReports(new LinkedList<>()).setSpamReportNumber(new IntHolder(0))
                .setUserID(user.getID())
                .setOwnerID(user.getID())
                .setSubTweets(new LinkedList<>())
                .setSourceTweetID(newID).setRetweetDate(localDateTime).setRetweeted(false);
    }

    /** Creating a new tweet
     *
     */
    public Tweet newTweet(String text, User user){
        return newRawTweet(text, user)
                .setParentTweetID("0")
                .build();
//        addToAllTweets(tweet);
     }

    /** Creating a new subtweet
     *
     */
    public Tweet newSubTweet(String text, User user, Tweet parent){
        return newRawTweet(text, user)
                .setParentTweetID(parent.getID())
                .build();
//        addToAllTweets(tweet);
    }

    /** Creating a retweet
     *
     */
    public Tweet newRetweet(Tweet sourceTweet , User user){
        return newRawTweet(sourceTweet.getText(), user).setDate(sourceTweet.getDate())
                .setLikes(sourceTweet.getLikes()).setLikeNumber(sourceTweet.getLikeNumber())
                .setRetweets(sourceTweet.getRetweets()).setRetweetNumber(sourceTweet.getRetweetNumber())
                .setSpamReports(sourceTweet.getSpamReports()).setSpamReportNumber(sourceTweet.getSpamReportNumber())
                .setOwnerID(sourceTweet.getOwnerID()).setParentTweetID(sourceTweet.getParentTweetID()).setSubTweets(sourceTweet.getSubTweets())
                .setSourceTweetID(sourceTweet.getSourceTweetID()).setRetweeted(true)
                .build();
//        addToAllTweets(tweet);
    }

    public void Tweet(String text, User user){
        Tweet tweet = newTweet(text, user);
        addToAllTweets(tweet);
        user.addToTweets(tweet.getID());
    }

    public void SubTweet(String text, User user, Tweet parent){
        Tweet subTweet = newSubTweet(text, user, parent);
        addToAllTweets(subTweet);
        user.addToTweets(subTweet.getID());
        parent.addToSubTweet(subTweet);
    }

    public void ReTweet(Tweet sourceTweet, User user){
        Tweet retweet = newRetweet(sourceTweet, user);
        addToAllTweets(retweet);
        user.addToTweets(retweet.getID());
        sourceTweet.addRetweet(user);
    }

    public void spamReport(Tweet spamTweet, User user){
        spamTweet.addSpamReport(user);
    }






}
