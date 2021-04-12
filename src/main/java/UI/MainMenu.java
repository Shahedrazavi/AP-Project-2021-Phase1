package UI;

import Models.User;
import Models.UserLogic;

public class MainMenu extends InnerPage {


    MainMenu(UserLogic userLogic, User user) {
        super(userLogic, user);
    }

    @Override
    public void firstView() {
        userLogic.updateLastSeen(user);

        printer.sectionShower("Welcome to Main Menu");

        if (user.isActive()){
            activatedMainOptions();
        }
        else {
            deactivatedMainOptions();
        }

    }

    public void activatedMainOptions(){

        int unreadNotifs = userLogic.getNotifLogic().unreadNotifs(user);
        printer.showHighlightedMsg("You have " + unreadNotifs + " unread notifications");

        printer.println("[1] Timeline");
        printer.println("[2] Explore");
        printer.println("[3] Search");
        printer.println("[4] Profile");
        printer.println("[5] Notifications");
        printer.println("[6] Messages");
        printer.println("[7] Settings");
        printer.println("[8] Log out / Exit");

        boolean condition = true;
        while (condition){
            String input = sc.next();
            if (input.equals("1")){
                condition = false;

                Timeline timeline = new Timeline(userLogic,user);
                timeline.firstView();

            }
            else if (input.equals("2")){
                condition = false;

                Explore explore = new Explore(userLogic,user);
                explore.firstView();

            }
            else if (input.equals("3")){
                condition = false;

                Search search = new Search(userLogic,user);
                search.firstView();

            }
            else if (input.equals("4")){
                condition = false;

                SelfProfile selfProfile = new SelfProfile(userLogic,user);
                selfProfile.firstView();

            }
            else if (input.equals("5")){
                condition = false;

                NotificationsPage notificationsPage = new NotificationsPage(userLogic,user);
                notificationsPage.firstView();

            }
            else if (input.equals("6")){
                condition = false;

                MessagesPage messagesPage = new MessagesPage(userLogic,user);
                messagesPage.firstView();

            }
            else if (input.equals("7")){
                condition = false;

                Settings settings = new Settings(userLogic,user);
                settings.firstView();

            }
            else if (input.equals("8")){
                condition = false;

                logout();
            }

            else {
                printer.println("Invalid input, please try again");
            }
        }

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
            else if (input.equals("2")){
                condition = false;

                logout();
            }

            else {
                printer.println("Invalid input, please try again");
            }
        }

    }

    public void logout(){
        logger.logOut(user.getUsername(), user.getID());

        FirstPage firstPage = new FirstPage(userLogic);
        firstPage.firstView();
    }
}
