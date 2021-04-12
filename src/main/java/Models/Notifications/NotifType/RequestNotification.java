package Models.Notifications.NotifType;

import Models.Notifications.Notification;

import java.time.LocalDateTime;

public class RequestNotification extends Notification {

    public RequestNotification(String userID, String fromUserID, LocalDateTime notifTime) {
        super(userID, fromUserID, notifTime);
        notifType = NotifType.followreq;
    }
}
