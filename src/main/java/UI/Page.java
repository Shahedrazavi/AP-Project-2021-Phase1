package UI;

import Models.UserLogic;
import Util.CLI;
import Util.CommandReader;
import Util.Logger;

import java.util.Scanner;

public abstract class Page {
    protected CLI printer = CLI.getCLI();

    protected Logger logger = Logger.getLogger();

//    Scanner sc = new Scanner(System.in);
    CommandReader sc = CommandReader.get();

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
