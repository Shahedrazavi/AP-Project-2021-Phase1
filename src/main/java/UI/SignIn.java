package UI;

import Models.User;
import Models.UserLogic;

public class SignIn extends Page{

    User user;


    public SignIn(UserLogic userLogic){
        super(userLogic);
    }


    @Override
    public void firstView() {
        printer.sectionShower("Sign In");
        enterUsername();
    }

    public void enterUsername(){
        printer.println("Enter username:   (Enter \"back\" if you want to go back)");

        boolean condition = true;

        while (condition){
            String input = sc.next();
            if (input.equals("back")){
                condition = false;

                printer.makeGap();
                printer.makeLine();
                printer.makeGap();

                FirstPage firstPage = new FirstPage(userLogic);
                firstPage.firstView();
            }
            else{
                printer.println("Checking username....");
                if(userLogic.isUsernameTaken(input)){
                    condition = false;

                    user = userLogic.findByUsername(input);
//                    User user = null;
                    enterPassword();
                }
                else {
                    printer.println("Username not found, please try again");
                }
            }
        }
    }

    public void enterPassword(){
        String username = user.getUsername();
        String password = user.getPassword();
        System.out.println("username = " + username);
        System.out.println("password = " + password);
//        String username = "yes";
//        String password = "no";

        printer.println("Enter password:   (Enter \"back\" if you want to go back)");
        boolean condition = true;
        while (condition){
            String input = sc.next();
            if (input.equals("back")){
                condition = false;

                enterUsername();

            }
            else{
                printer.println("Checking password....");
                if(userLogic.passAuthenticator(user, input)){
                    condition = false;

                    printer.println("Correct password, signing in .....");


                    /* update last seen */
                    userLogic.updateLastSeen(user);

                    /* log */
                    String userID = user.getID();
                    logger.signIn(username,userID);

                    /* goes to main menu
                     */
                    MainMenu mainMenu = new MainMenu(userLogic, user);
                    mainMenu.firstView();


                }
                else {
                    printer.println("Incorrect password, please try again");
                }
            }
        }
    }
}
