package UI;

import Models.User;
import Models.UserLogic;

public class Settings extends InnerPage{


    Settings(UserLogic userLogic, User user) {
        super(userLogic, user);
    }

    @Override
    public void firstView() {
        printer.sectionShower("Settings");

        options();

    }

    public void options(){

        if (!user.isActive()){
            printer.showHighlightedMsg("Your account is deactivated.");
        }

        printer.println("[1] Change password");
        printer.println("[2] Privacy");
        printer.println("[3] Last seen & Online");
        printer.println("[4] Activate / Deactivate");
        printer.println("[5] Delete account");
        printer.println("[6] Log out");
        printer.println("[7] Main Menu");

        boolean condition = true;
        while (condition){
            String input = sc.next();
            if (input.equals("back") || input.equals("7")){
                condition = false;

                MainMenu mainMenu = new MainMenu(userLogic,user);
                mainMenu.firstView();

            }
            else if(input.equals("1")){
                condition = false;

                passwordChangeConfirm();
            }
            else if(input.equals("2")){
                condition = false;

                privacy();
            }
            else if(input.equals("3")){
                condition = false;

                lastSeen();
            }
            else if(input.equals("4")){
                condition = false;

                activation();
            }
            else if(input.equals("5")){
                condition = false;

                deleteAccount();
            }
            else if(input.equals("6")){
                condition = false;

                logout();
            }
            else {
                printer.println("Incorrect password, please try again");
            }
        }
    }

    public void passwordChangeConfirm(){
        printer.println("Enter your current password: (Enter \"back\" if you want to go back)");
        boolean condition = true;
        while (condition){
            String input = sc.next();
            if (input.equals("back")){
                condition = false;

                options();
            }
            if (input.equals(user.getPassword())){
                condition = false;

                passwordChange();
            }
            else {
                printer.println("Incorrect password, please try again");
            }
        }
    }

    public void passwordChange(){
        printer.println("Enter your new password (Enter \"back\" if you want to go back)");
        String input = sc.next();
        if (input.equals("back")){

            options();
        }
        else {
            user.setPassword(input);
            logger.passwordChange(user.getUsername(),user.getID());

            printer.showHighlightedMsg("Password changed successfully.");

            options();
        }
    }

    public void privacy(){
        printer.println("Set your account privacy:");
        printer.println("[1] Public");
        printer.println("[2] Private");


        boolean condition = true;
        while (condition){
            String input = sc.next();
            if (input.equals("back")){
                condition = false;

                options();
            }
            else if(input.equals("1")){
                condition = false;

                user.setPublic(true);
                logger.privacy(user.getUsername(),user.getID(),"Public");

                printer.showHighlightedMsg("Changed successfully to public account.");

                options();
            }
            else if(input.equals("2")){
                condition = false;

                user.setPublic(false);
                logger.privacy(user.getUsername(),user.getID(),"Private");

                printer.showHighlightedMsg("Changed successfully to private account.");

                options();
            }
            else {
                printer.println("Invalid input, please try again");
            }

        }
    }

    public void lastSeen(){
        printer.println("Set your last seen and online visibility:");
        printer.println("[1] Everyone can see");
        printer.println("[2] Only following can see");
        printer.println("[3] No one can see");

        boolean condition = true;
        while (condition){
            String input = sc.next();
            if (input.equals("back")){
                condition = false;

                options();
            }
            else if(input.equals("1")){
                condition = false;

                user.setLastSeenType(User.LastSeenType.everyone);
                logger.lastSeenType(user.getUsername(),user.getID(),"everyone");

                printer.showHighlightedMsg("Changed last seen & online type successfully.");

                options();
            }
            else if(input.equals("2")){
                condition = false;

                user.setLastSeenType(User.LastSeenType.followings);
                logger.lastSeenType(user.getUsername(),user.getID(),"followings");

                printer.showHighlightedMsg("Changed last seen & online type successfully.");

                options();
            }
            else if(input.equals("3")){
                condition = false;

                user.setLastSeenType(User.LastSeenType.no_one);
                logger.lastSeenType(user.getUsername(),user.getID(),"no_one");

                printer.showHighlightedMsg("Changed last seen & online type successfully.");

                options();
            }
            else {
                printer.println("Invalid input, please try again");
            }
        }
    }

    public void activation(){
        //Deactivation//
        if (user.isActive()){
            printer.println("Please type \"confirm\" to confirm that you want to deactivate your account: (Type \"back\" if you want to cancel deactivation)");

            boolean condition = true;
            while (condition){
                String input = sc.next();
                if (input.equals("back")){
                    condition = false;
                    options();
                }
                else if (input.equals("confirm")){
                    condition = false;

                    user.setActive(false);
                    logger.activation(user.getUsername(),user.getID(),"deactivated");

                    printer.println("Deactivated Account, returning to first page. bye........");

                    FirstPage firstPage = new FirstPage(userLogic);
                    firstPage.firstView();
                }
                else {
                    condition = false;

                    printer.println("Invalid input, please try again");

                    options();
                }
            }
        }

        //Activation//
        else{
            printer.println("Please type \"confirm\" to confirm that you want to activate your account: (Type \"back\" if you want to cancel activation)");

            boolean condition = true;
            while (condition){
                String input = sc.next();
                if (input.equals("back")){
                    condition = false;
                    options();
                }
                else if (input.equals("confirm")){
                    condition = false;

                    user.setActive(true);
                    logger.activation(user.getUsername(),user.getID(),"activated");

                    printer.println("Activated Account, returning to first page to log in again :)");

                    FirstPage firstPage = new FirstPage(userLogic);
                    firstPage.firstView();
                }
                else {
                    condition = false;

                    printer.println("Invalid input, please try again");

                    options();
                }
            }
        }
    }

    public void logout(){
        logger.logOut(user.getUsername(), user.getID());

        FirstPage firstPage = new FirstPage(userLogic);
        firstPage.firstView();
    }

    public void deleteAccount(){
        printer.println("Please type \"confirm\" to confirm that you want to delete your account: (Type \"back\" if you want to cancel deletion)");

        boolean condition = true;
        while (condition){
            String input = sc.next();
            if (input.equals("back")){
                condition = false;

                options();
            }
            else if (input.equals("confirm")){
                condition = false;

                userLogic.deleteUser(user);
                logger.delete(user.getUsername(),user.getID());

                printer.println("Deleted Account, returning to first page. bye........");

                FirstPage firstPage = new FirstPage(userLogic);
                firstPage.firstView();

            }
            else {
                condition = false;

                printer.println("Invalid input, please try again");

                options();
            }
        }
    }
}
