package Models.Notifications.NotifType;

import Models.Notifications.Notification;

import java.time.LocalDateTime;

public class FollowNotification extends Notification {

    public FollowNotification(String userID, String fromUserID, LocalDateTime notifTime) {
        super(userID, fromUserID, notifTime);
        notifType = NotifType.follow;
    }
}
