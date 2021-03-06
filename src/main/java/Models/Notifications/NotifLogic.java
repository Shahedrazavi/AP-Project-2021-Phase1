package Models.Notifications;

import Models.Notifications.NotifType.*;
import Models.User;

import java.time.LocalDateTime;

public class NotifLogic {

    public int unreadNotifs(User user){
        int count = 0;
        for (Notification notif :user.getNotifications()){
            if (notif.isNotSeen()){
                count++;
            }
        }
        return count;
    }

    public void addNotif(User user , User fromUser , Notification.NotifType notifType){
        Notification newNotif = null;
        if (notifType == Notification.NotifType.follow){
            newNotif = new FollowNotification(user.getID(), fromUser.getID(), LocalDateTime.now());
        }
        if (notifType == Notification.NotifType.followreq){
            newNotif = new RequestNotification(user.getID(), fromUser.getID(), LocalDateTime.now());
        }
        if (notifType == Notification.NotifType.unfollow){
            newNotif = new UnfollowNotification(user.getID(), fromUser.getID(), LocalDateTime.now());
        }

        user.getNotifications().add(newNotif);
    }

    public void removeNotif(User user , Notification removedNotif){
        user.getNotifications().remove(removedNotif);
    }
}
