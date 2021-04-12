package UI;

import Models.User;
import Models.UserLogic;

public abstract class Profile extends InnerPage{


    Profile(UserLogic userLogic, User user) {
        super(userLogic, user);
    }
}
