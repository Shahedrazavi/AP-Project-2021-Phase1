package UI;

import Models.User;
import Models.UserLogic;

public class NotificationsPage extends InnerPage{


    NotificationsPage(UserLogic userLogic, User user) {
        super(userLogic, user);
    }

    @Override
    public void firstView() {
        printer.sectionShower("Notifications");

    }
}
