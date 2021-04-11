package Models.Notifications.NotifType;

import Models.Notifications.Notification;

import java.time.LocalDateTime;

public class FollowNotification extends Notification {

    public FollowNotification(String userID, LocalDateTime notifTime) {
        super(userID, notifTime);
        notifType = NotifType.follow;
    }
}
