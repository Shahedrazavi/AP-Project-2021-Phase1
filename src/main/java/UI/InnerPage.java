package UI;

import Models.User;
import Models.UserLogic;

public abstract class InnerPage extends Page{

    User user;

    InnerPage(UserLogic userLogic , User user) {
        super(userLogic);
        this.user = user;
    }
}
