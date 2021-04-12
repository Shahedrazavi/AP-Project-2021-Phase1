package UI;

import Models.User;
import Models.UserLogic;

import java.time.DateTimeException;
import java.time.LocalDate;

public class SignUp extends Page {

    String firstName;
    String lastName;
    String phoneNumber;
    String email;
    String username;
    String password;
    String profileName;
    String bio;
    LocalDate birthday;
    boolean isPublic;

    public SignUp(UserLogic userLogic){
        super(userLogic);
    }

    @Override
    public void firstView() {
        printer.sectionShower("Sign Up");
        enterInfo();
    }

    public void enterInfo(){
        printer.println("For continuing, you should agree to our terms of service" );
        printer.println("Ok I don't care, we think you agreed");
        printer.println("We need your name, phone number, email, birthday date for stealin..... for creating a new account for you");
        enterFirstName();

    }

    public void enterFirstName(){
        printer.println("Enter your first name");

        boolean condition = true;

        while (condition){
            String input = sc.next();
            if (input.equals("back")){
                condition = false;

                FirstPage firstPage = new FirstPage(userLogic);
                firstPage.firstView();
            }
            else {
                condition = false;

                this.firstName = input;
                enterLastName();
            }
        }
    }

    public void enterLastName(){
        printer.println("Enter you last name");

        boolean condition = true;

        while (condition){
            String input = sc.next();
            if (input.equals("back")){
                condition = false;

                enterFirstName();
            }

            else {
                condition = false;

                this.lastName = input;
                enterPhoneNumber();
            }
        }
    }

    public void enterPhoneNumber(){
        printer.println("Enter your phone number");

        boolean condition = true;
        while (condition){
            String input = sc.next();
            if (input.equals("back")){
                condition = false;

                enterLastName();
            }
            else{
                if(!userLogic.isPhoneNumberTaken(input)){
                    condition = false;

                    this.phoneNumber = input;
                    enterEmail();
                }
                else {
                    printer.println("Sorry, this phone number is taken. Try another one.");
                }
            }
        }



    }

    public void enterEmail(){
        printer.println("Enter your email");

        boolean condition = true;
        while (condition){
            String input = sc.next();

            if(input.equals("back")){
                condition = false;

                enterPhoneNumber();
            }
            else {
                if (!userLogic.isEmailTaken(input)){
                    condition = false;

                    this.email = input;
                    enterUsername();
                }
                else {
                    printer.println("Sorry, this email is taken. Try another one.");
                }
            }
        }


    }

    public void enterUsername(){
        printer.println("Enter your desired username");
        boolean condition = true;
        while (condition){
            String input = sc.next();
            if(input.equals("back")){
                condition = false;

                enterEmail();
            }
            else {
                if(!userLogic.isUsernameTaken(input)){
                    condition = false;


                    printer.println("Now we need to just go through a few more steps to completely setup your account");

                    this.username = input;
                    enterPassword();
                }
                else {
                    printer.println("Sorry, this username is taken. Try another one.");
                }
            }
        }
    }

    public void enterPassword(){
        printer.println("Enter your password (Don't use a weak one!)");

        boolean condition = true;
        while (condition){
            String input = sc.next();
            if (input.equals("back")){
                condition = false;

                enterUsername();
            }

            else {
                condition = false;

                this.password = input;
                enterBirthday();
            }
        }
    }

    public void enterBirthday(){
        boolean condition = true;

        int yearNumber = 0;
        int monthNumber = 0;
        int dayNumber = 0;

        if (condition){
            printer.println("Enter your birthday year number");
            String year = sc.next();
            if (year.equals("back")){
                condition = false;

                enterPassword();
            }
            else {
                yearNumber = Integer.parseInt(year);
                if (yearNumber <1){
                    yearNumber = 1;
                }
            }
        }

        if (condition){
            printer.println("Enter your birthday month number");
            String month = sc.next();
            if (month.equals("back")){
                condition = false;

                enterPassword();
            }
            else {
                monthNumber = Integer.parseInt(month);
                if (monthNumber >12){
                    monthNumber=12;
                }
                if (monthNumber <1){
                    monthNumber=1;
                }
            }
        }

        if (condition){
            printer.println("Enter your birthday day number (in the month)");
            String day = sc.next();
            if (day.equals("back")){
                condition = false;

                enterPassword();
            }
            else {
                dayNumber = Integer.parseInt(day);
                if (dayNumber > 31){
                    dayNumber = 31;
                }
                if (dayNumber < 1){
                    dayNumber = 1;
                }
            }
        }

        if(condition){
            try {
                this.birthday = LocalDate.of(yearNumber,monthNumber,dayNumber);
                enterProfileName();
            }
            catch (DateTimeException e){

                printer.println("Invalid Date. Try again.");
                enterBirthday();
            }

        }

    }

    public void enterProfileName(){
        printer.println("Enter your desired profile name");

        boolean condition = true;
        while (condition){
            String input = sc.next();
            if (input.equals("back")){
                condition = false;

                enterBirthday();
            }
            else {
                condition = false;

                setPrivacy();
            }
        }
    }

    public void setPrivacy(){
        printer.println("Set your account privacy:");
        printer.println("[1] Public");
        printer.println("[2] Private");


        boolean condition = true;
        while (condition){
            String input = sc.next();
            if (input.equals("back")){
                condition = false;

                enterProfileName();
            }
            else if(input.equals("1")){
                condition = false;

                this.isPublic = true;

                setBio();
            }
            else if(input.equals("2")){
                condition = false;

                this.isPublic = false;

                setBio();
            }
            else {
                printer.println("Invalid input, please try again");
            }

        }
    }

    public void setBio(){
        printer.println("Do you want to write a bio for your account? (You can skip it now change it later)");
        printer.println("[1] Yes");
        printer.println("[2] No");

        boolean condition = true;
        while (condition){
            String input = sc.next();
            if (input.equals("back")){
                condition = false;

                setPrivacy();
            }

            else if (input.equals("1")){
                condition = false;

                printer.println("Enter your bio: (Press Enter when you finished)");

                bio = sc.nextLine();

                createAccount();

            }

            else if (input.equals("2")){
                condition = false;

                printer.println("OK! :)");

                bio = "";

                createAccount();

            }
            else {
                printer.println("Invalid input, please try again");
            }

        }
    }

    public void createAccount(){
        printer.println("Creating a new account.....");
        User user = userLogic.newUser(username,password,profileName,firstName,lastName,email,phoneNumber,birthday,bio,isPublic);
        String username = user.getUsername();
        String userID = user.getID();
        logger.signUp(username,userID);


        String password = user.getPassword();
        System.out.println("username = " + username);
        System.out.println("password = " + password);

        printer.println("Successfully created your account :)");
        printer.println("Now you will return to first page. Go to sign in section to continue.");

        FirstPage firstPage = new FirstPage(userLogic);
        firstPage.firstView();
    }

}
