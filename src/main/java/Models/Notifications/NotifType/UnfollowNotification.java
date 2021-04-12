package Models.Notifications.NotifType;

import Models.Notifications.Notification;

import java.time.LocalDateTime;

public class UnfollowNotification extends Notification {

    public UnfollowNotification(String userID, String fromUserID, LocalDateTime notifTime) {
        super(userID, fromUserID, notifTime);
        notifType = NotifType.unfollow;
    }
}
