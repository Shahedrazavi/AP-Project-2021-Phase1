package Models.Notifications;

import java.time.LocalDateTime;

public class Notification {
    protected boolean isSeen;
    protected String userID;
    protected LocalDateTime notifTime;

    protected NotifType notifType;


    public enum NotifType{
        none,
        followreq,
        follow,
        unfollow
    }


    public Notification(String userID , LocalDateTime notifTime){
        isSeen = false;
        this.userID = userID;
        this.notifTime = notifTime;
    }


    public boolean isSeen() {
        return isSeen;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public LocalDateTime getNotifTime() {
        return notifTime;
    }

    public void setNotifTime(LocalDateTime notifTime) {
        this.notifTime = notifTime;
    }

    public NotifType getNotifType() {
        return notifType;
    }

    public void setNotifType(NotifType notifType) {
        this.notifType = notifType;
    }
}