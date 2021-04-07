package Models;

import java.util.Date;
import java.util.LinkedList;

public class Tweet {
    private String ID;
    private String text;
    private Date date;

    private LinkedList<String> likes;
    private int likeNumber;
    private LinkedList<String> retweets;
    private int retweetNumber;
    private LinkedList<String> spamReports;
    private int spamReportNumber;

    private String userID;
    private String ownerID;
    private String parentTweetID;
    private LinkedList<String> subTweets;
    private boolean isRetweeted;

    public Tweet(String ID, String text, Date date,
                 LinkedList<String> likes, int likeNumber,
                 LinkedList<String> retweets, int retweetNumber,
                 LinkedList<String> spamReports, int spamReportNumber,
                 String userID,
                 String ownerID, String parentTweetID, LinkedList<String> subTweets, boolean isRetweeted) {
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
        this.isRetweeted = isRetweeted;
    }

    static class TweetBuilder{
        private String ID;
        private String text;
        private Date date;

        private LinkedList<String> likes;
        private int likeNumber;
        private LinkedList<String> retweets;
        private int retweetNumber;
        private LinkedList<String> spamReports;
        private int spamReportNumber;

        private String userID;
        private String ownerID;
        private String parentTweetID;
        private LinkedList<String> subTweets;
        private boolean isRetweeted;

        public TweetBuilder setID(String ID) {
            this.ID = ID;
            return this;
        }

        public TweetBuilder setText(String text) {
            this.text = text;
            return this;
        }

        public TweetBuilder setDate(Date date) {
            this.date = date;
            return this;
        }

        public TweetBuilder setLikes(LinkedList<String> likes) {
            this.likes = likes;
            return this;
        }

        public TweetBuilder setLikeNumber(int likeNumber) {
            this.likeNumber = likeNumber;
            return this;
        }

        public TweetBuilder setRetweets(LinkedList<String> retweets) {
            this.retweets = retweets;
            return this;
        }

        public TweetBuilder setRetweetNumber(int retweetNumber) {
            this.retweetNumber = retweetNumber;
            return this;
        }

        public TweetBuilder setSpamReports(LinkedList<String> spamReports) {
            this.spamReports = spamReports;
            return this;
        }

        public TweetBuilder setSpamReportNumber(int spamReportNumber) {
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

        public TweetBuilder setRetweeted(boolean retweeted) {
            isRetweeted = retweeted;
            return this;
        }

        public boolean isRetweeted() {
            return isRetweeted;
        }

        public Tweet build(){
            return new Tweet(ID, text, date,
                    likes, likeNumber,
                    retweets, retweetNumber,
                    spamReports, spamReportNumber,
                    userID,
                    ownerID, parentTweetID, subTweets, isRetweeted);
//            return new Tweet(ID, text, likes, likeNumber, retweets, retweetNumber, userID, ownerID, parentTweetID, isRetweeted);
        }
    }
}
