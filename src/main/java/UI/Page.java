package UI;

import Models.UserLogic;
import Util.CLI;
import Util.Logger;

import java.util.Scanner;

public class Page {
    protected CLI printer = CLI.getCLI();

    protected Logger logger = Logger.getLogger();

    Scanner sc = new Scanner(System.in);

    protected UserLogic userLogic;

    Page(UserLogic userLogic){
        this.userLogic = userLogic;
    }

    public void firstView(){

    }

    public UserLogic getUserLogic() {
        return userLogic;
    }
}
