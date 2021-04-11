package Models.Notifications;

import Models.Notifications.NotifType.*;
import Models.User;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class NotifLogic {

    public int unreadNotifs(User user){
        int count = 0;
        for (Notification notif :user.getNotifications()){
            if (!notif.isSeen()){
                count++;
            }
        }
        return count;
    }

    public void setAsRead(Notification notification){
        notification.setSeen(true);
    }

    public void setAsUnread(Notification notification){
        notification.setSeen(false);
    }

    public void addNotif(User user , Notification.NotifType notifType){
        Notification newNotif = null;
        if (notifType == Notification.NotifType.follow){
            newNotif = new FollowNotification(user.getID(), LocalDateTime.now());
        }
        if (notifType == Notification.NotifType.followreq){
            newNotif = new RequestNotification(user.getID(), LocalDateTime.now());
        }
        if (notifType == Notification.NotifType.unfollow){
            newNotif = new UnfollowNotification(user.getID(), LocalDateTime.now());
        }

        user.getNotifications().add(newNotif);
    }

    public void removeNotif(User user , Notification removedNotif){
        user.getNotifications().remove(removedNotif);
    }
}
