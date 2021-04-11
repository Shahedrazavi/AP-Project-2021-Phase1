package Models.Notifications.NotifType;

import Models.Notifications.Notification;

import java.time.LocalDateTime;

public class UnfollowNotification extends Notification {

    public UnfollowNotification(String userID, LocalDateTime notifTime){
        super(userID,notifTime);
        notifType = NotifType.unfollow;
    }
}
