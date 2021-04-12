package UI;

import Models.User;
import Models.UserLogic;

public class Search extends InnerPage{

    Search(UserLogic userLogic, User user) {
        super(userLogic, user);
    }

    @Override
    public void firstView() {
        userLogic.updateLastSeen(user);

        printer.sectionShower("Search");
        printer.println("Enter the username you want to search: (Enter \"back\" if you want to go back to main menu)");

        boolean condition = true;
        while (condition){
            String input = sc.next();
            if (input.equals("back")){
                condition = false;

                MainMenu mainMenu = new MainMenu(userLogic,user);
                mainMenu.firstView();
            }

            else {
                if (userLogic.isUsernameTaken(input)){
                    condition = false;

                    if (input.equals(user.getUsername())){
                        SelfProfile selfProfile = new SelfProfile(userLogic,user);
                        selfProfile.firstView();
                    }
                    else {
                        User visitedUser = userLogic.findByUsername(input);
                        OthersProfile othersProfile = new OthersProfile(userLogic,user,visitedUser);
                        othersProfile.firstView();
                    }
                }
                else {
                    printer.println("Username not found, please try again");
                }
            }
        }
    }
}
