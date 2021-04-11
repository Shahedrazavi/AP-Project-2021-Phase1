package UI;

import Models.User;
import Models.UserLogic;

public class MainMenu extends Page {

    User user;

    MainMenu(UserLogic userLogic,User user) {
        super(userLogic);
        this.user = user;
    }

    @Override
    public void firstView() {
        printer.makeThickLine();
        printer.makeLine();
        printer.println("Welcome to Main Menu");
        printer.makeThickLine();
        printer.makeGap();

        if (user.isActive()){
            activatedMainOptions();
        }
        else {
            deactivatedMainOptions();
        }

    }

    public void activatedMainOptions(){

    }

    public void deactivatedMainOptions(){
        printer.println("[1] Settings");
        printer.println("[2] Log out / Exit");

        boolean condition = true;
        while (condition){
            String input = sc.next();
            if (input.equals("1")){
                condition = false;

                Settings settings = new Settings(userLogic,user);
                settings.firstView();

            }

            else {
                printer.println("Invalid input, please try again");
            }
        }

    }


    public void Logout(){
        FirstPage firstPage = new FirstPage(userLogic);
        firstPage.firstView();
    }
}
