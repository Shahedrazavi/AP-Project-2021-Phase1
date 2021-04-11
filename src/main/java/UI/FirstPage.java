package UI;

import Models.UserLogic;

public class FirstPage extends Page {

    public FirstPage(UserLogic userLogic) {
        super(userLogic);
    }

    @Override
    public void firstView(){
        logger.log("Application started.");

        printer.println("Hi. Welcome");
        printer.println("[1] Sign in");
        printer.println("[2] Sign up");
        printer.println("[3] Exit");

        boolean condition = true;
        while (condition){
            String input = sc.next();
            if (input.equals("1")){
                condition = false;

                printer.makeGap();
                printer.makeLine();
                printer.makeGap();

                SignIn signIn = new SignIn(userLogic);
                signIn.firstView();
            }
            else if (input.equals("2")){
                condition = false;

                SignUp signUp = new SignUp(userLogic);
                signUp.firstView();
            }
            else if (input.equals("3")){
                condition = false;

                System.exit(0);

            }
            else {
                printer.println("Invalid input, please try again");
            }
        }

    }
}
