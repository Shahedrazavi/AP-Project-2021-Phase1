package Models.Notifications.NotifType;

import Models.Notifications.Notification;

import java.time.LocalDateTime;

public class RequestNotification extends Notification {

    public RequestNotification(String userID, LocalDateTime notifTime){
        super(userID,notifTime);
        notifType = NotifType.followreq;
    }
}
