package UI;

import Models.User;
import Models.UserLogic;

public class Settings extends Page{

    User user;

    Settings(UserLogic userLogic , User user) {
        super(userLogic);
        this.user = user;
    }

    @Override
    public void firstView() {
        super.firstView();
    }
}
