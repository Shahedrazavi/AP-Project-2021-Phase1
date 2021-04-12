package UI;

import Models.Notifications.Notification;
import Models.User;
import Models.UserLogic;

import java.util.LinkedList;

public class NotificationsPage extends InnerPage{


    NotificationsPage(UserLogic userLogic, User user) {
        super(userLogic, user);
    }

    @Override
    public void firstView() {
        printer.sectionShower("Notifications");
        int unreadNotifs = userLogic.getNotifLogic().unreadNotifs(user);
        printer.showHighlightedMsg("You have " + unreadNotifs + " unread notifications");
        printer.nextLine();

        printer.println("[0] Back ");
        printer.println("[1] Show Notifications");

        boolean condition = true;
        while (condition){
            String input = sc.next();
            if (input.equals("back") || input.equals("0")){
                condition = false;

                MainMenu mainMenu = new MainMenu(userLogic,user);
                mainMenu.firstView();
            }
            else if(input.equals("1")){
                condition = false;

                notifViewer();
            }

            else {
                printer.println("Invalid input, please try again");
            }
        }
    }

    public void notifViewer(){
        LinkedList<Notification> list = user.getNotifications();

        printer.showHighlightedMsg("Notifications:");

        int cursor = 0;
        if (!list.isEmpty()){
            cursor = list.size()-1;
        }

        boolean condition = true;
        while (condition){
            if (list.isEmpty()){
                condition = false;
                printer.println("You don't have any notification.");
                printer.println("Enter any input to go back");
                String input = sc.next();
                firstView();
            }
            else{
                Notification notification = list.get(cursor);
                String fromUserName = userLogic.IDtoUser(notification.getFromUserID()).getUsername();
                Notification.NotifType notifType = notification.getNotifType();

                if (notifType == Notification.NotifType.follow){
                    printer.showHighlightedMsg("@" + fromUserName + " started following you");
                }
                else if (notifType == Notification.NotifType.unfollow){
                    printer.showHighlightedMsg("@" + fromUserName + " unfollowed you");
                }
                else if (notifType == Notification.NotifType.followreq){
                    printer.showHighlightedMsg("@" + fromUserName + " requested to follow you");
                }

                //Mark as read
                if (notification.isNotSeen()){
                    notification.setAsRead();
                }



                printer.println("[0] Go back");
                printer.println("[1] Next");
                printer.println("[2] Previous");
                printer.println("[3] Remove");
                String input = sc.next();
                if (input.equals("back") || input.equals("0")){
                    condition = false;

                    firstView();
                }
                else if(input.equals("1")){
                    if (cursor>0){
                        cursor--;
                    }
                    else{
                        printer.println("You reached the end of the list");
                    }
                }
                else if(input.equals("2")){
                    if (cursor<list.size()-1){
                        cursor++;
                    }
                    else{
                        printer.println("You reached the start of the list");
                    }
                }
                else if (input.equals("3")){


                    //Remove Notification//
                    userLogic.getNotifLogic().removeNotif(user,notification);
                    logger.notifRemove(user.getUsername(),user.getID());

                    if (cursor == (list.size()-1)){
                        cursor--;
                    }

                }
                else {
                    printer.println("Invalid input, please try again");
                }
            }
        }
    }
}
