package Models;

import Util.IntHolder;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Tweet implements Memo {

    private String ID;
    private String text;
    private LocalDateTime date;

    private LinkedList<String> likes;
    private IntHolder likeNumber;
    private LinkedList<String> retweets;
    private IntHolder retweetNumber;
    private LinkedList<String> spamReports;
    private IntHolder spamReportNumber;

    private String userID;
    private String ownerID;
    private String parentTweetID;
    private LinkedList<String> subTweets;


    private String sourceTweetID;
    private LocalDateTime retweetDate;
    private boolean isRetweet;

    public MemoType memoType;

    public Tweet(String ID, String text, LocalDateTime date,
                 LinkedList<String> likes, IntHolder likeNumber,
                 LinkedList<String> retweets, IntHolder retweetNumber,
                 LinkedList<String> spamReports, IntHolder spamReportNumber,
                 String userID,
                 String ownerID, String parentTweetID, LinkedList<String> subTweets,
                 String sourceTweetID, LocalDateTime retweetDate, boolean isRetweeted) {
        this.ID = ID;
        this.text = text;
        this.date = date;
        this.likes = likes;
        this.likeNumber = likeNumber;
        this.retweets = retweets;
        this.retweetNumber = retweetNumber;
        this.spamReports = spamReports;
        this.spamReportNumber = spamReportNumber;
        this.userID = userID;
        this.ownerID = ownerID;
        this.parentTweetID = parentTweetID;
        this.subTweets = subTweets;
        this.sourceTweetID = sourceTweetID;
        this.retweetDate = retweetDate;
        this.isRetweet = isRetweeted;
        this.memoType = MemoType.tweet;
    }

    static class TweetBuilder{
        private String ID;
        private String text;
        private LocalDateTime date;

        private LinkedList<String> likes;
        private IntHolder likeNumber;
        private LinkedList<String> retweets;
        private IntHolder retweetNumber;
        private LinkedList<String> spamReports;
        private IntHolder spamReportNumber;

        private String userID;
        private String ownerID;
        private String parentTweetID;
        private LinkedList<String> subTweets;

        private String sourceTweetID;
        private LocalDateTime retweetDate;
        private boolean isRetweet;

        public TweetBuilder setID(String ID) {
            this.ID = ID;
            return this;
        }

        public TweetBuilder setText(String text) {
            this.text = text;
            return this;
        }

        public TweetBuilder setDate(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public TweetBuilder setLikes(LinkedList<String> likes) {
            this.likes = likes;
            return this;
        }

        public TweetBuilder setLikeNumber(IntHolder likeNumber) {
            this.likeNumber = likeNumber;
            return this;
        }

        public TweetBuilder setRetweets(LinkedList<String> retweets) {
            this.retweets = retweets;
            return this;
        }

        public TweetBuilder setRetweetNumber(IntHolder retweetNumber) {
            this.retweetNumber = retweetNumber;
            return this;
        }

        public TweetBuilder setSpamReports(LinkedList<String> spamReports) {
            this.spamReports = spamReports;
            return this;
        }

        public TweetBuilder setSpamReportNumber(IntHolder spamReportNumber) {
            this.spamReportNumber = spamReportNumber;
            return this;
        }

        public TweetBuilder setUserID(String userID) {
            this.userID = userID;
            return this;
        }

        public TweetBuilder setOwnerID(String ownerID) {
            this.ownerID = ownerID;
            return this;
        }

        public TweetBuilder setParentTweetID(String parentTweetID) {
            this.parentTweetID = parentTweetID;
            return this;
        }

        public TweetBuilder setSubTweets(LinkedList<String> subTweets) {
            this.subTweets = subTweets;
            return this;
        }

        public TweetBuilder setRetweetDate(LocalDateTime retweetDate) {
            this.retweetDate = retweetDate;
            return this;
        }

        public TweetBuilder setSourceTweetID(String sourceTweetID) {
            this.sourceTweetID = sourceTweetID;
            return this;
        }

        public TweetBuilder setRetweet(boolean retweet) {
            isRetweet = retweet;
            return this;
        }

        public boolean isRetweet() {
            return isRetweet;
        }

        public Tweet build(){
            return new Tweet(ID, text, date,
                    likes, likeNumber,
                    retweets, retweetNumber,
                    spamReports, spamReportNumber,
                    userID,
                    ownerID, parentTweetID, subTweets,
                    sourceTweetID, retweetDate, isRetweet);
//            return new tweet(ID, text, likes, likeNumber, retweets, retweetNumber, userID, ownerID, parentTweetID, isRetweet);
        }
    }


    @Override
    public MemoType getMemoType() {
        return MemoType.tweet;
    }



    /**
     * Getters and Setters
     */



    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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

    public LinkedList<String> getSpamReports() {
        return spamReports;
    }

    public void setSpamReports(LinkedList<String> spamReports) {
        this.spamReports = spamReports;
    }

    public IntHolder getSpamReportNumber() {
        return spamReportNumber;
    }

    public void setSpamReportNumber(IntHolder spamReportNumber) {
        this.spamReportNumber = spamReportNumber;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public String getParentTweetID() {
        return parentTweetID;
    }

    public void setParentTweetID(String parentTweetID) {
        this.parentTweetID = parentTweetID;
    }

    public LinkedList<String> getSubTweets() {
        return subTweets;
    }

    public void setSubTweets(LinkedList<String> subTweets) {
        this.subTweets = subTweets;
    }

    public String getSourceTweetID() {
        return sourceTweetID;
    }

    public void setSourceTweetID(String sourceTweetID) {
        this.sourceTweetID = sourceTweetID;
    }

    public LocalDateTime getRetweetDate() {
        return retweetDate;
    }

    public void setRetweetDate(LocalDateTime retweetDate) {
        this.retweetDate = retweetDate;
    }

    public boolean isRetweet() {
        return isRetweet;
    }

    public void setRetweet(boolean retweet) {
        isRetweet = retweet;
    }


    /**
     * For Liking and Disliking
     * @param user
     */

    public void addLike(User user){
        getLikeNumber().add(+1);
        addUserToLikes(user);
    }

    public void addUserToLikes(User user){
        getLikes().add(user.getID());
    }

    public void removeLike(User user){
        getLikeNumber().add(-1);
        removeUserFromLikes(user);
    }

    public void removeUserFromLikes(User user) {
        getLikes().remove(user.getID());
    }


    /**
     * For Retweeting and Undo Retweeting
     * @param user
     */

    public void addRetweet(User user){
        getRetweetNumber().add(+1);
        addUserToRetweets(user);
    }

    public void addUserToRetweets(User user){
        getRetweets().add(user.getID());
    }

    public void removeRetweet(User user){
        getRetweetNumber().add(-1);
        addUserToRetweets(user);
    }

    public void removeUserFromRetweets(User user){
        getRetweets().remove(user.getID());
    }

    /**
     * For Adding to spam reports and Removing from spam reports.
     * @param user
     */

    public void addSpamReport(User user){
        getSpamReportNumber().add(+1);
        addUserToSpamReports(user);
    }

    public void addUserToSpamReports(User user){
        getSpamReports().add(user.getID());
    }

    public void removeSpamReport(User user){
        getSpamReportNumber().add(-1);
        removeUserFromSpamReports(user);
    }

    public void removeUserFromSpamReports(User user){
        getSpamReports().remove(user.getID());
    }


    public void addToSubTweet(Tweet tweet){
        getSubTweets().add(tweet.getID());
    }








}
