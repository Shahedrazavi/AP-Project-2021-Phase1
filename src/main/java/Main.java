import Models.Notifications.NotifLogic;
import Models.*;
import UI.FirstPage;

import java.time.LocalDate;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        NotifLogic notifLogic = new NotifLogic();
        UserLogic userLogic = new UserLogic(new LinkedList<>(), notifLogic);


//        String firstName = "sh";
//        String lastName = "raz";
//        String phoneNumber = "0930";
//        String email = "aa@b.com";
//        String username = "jelighe";
//        String password = "1234";
//        String profileName = "mrblue";
//        String bio = "";
//        boolean isPublic = true;
//        LocalDate birthday = LocalDate.of(2001,12,6);
//        User user = userLogic.newUser(username,password,profileName,firstName,lastName,email,phoneNumber,birthday,bio,isPublic);
//        System.out.println("ID: "+ user.getID());


        FirstPage firstPage = new FirstPage(userLogic);
        firstPage.firstView();
        System.out.println("The end");

//        try {
//            PrintStream printStream = new PrintStream("./log.txt");
//            printStream.print("Hellloooo");
//            printStream.flush();
////            printStream.close();
//            printStream.print("Hi");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

//        Scanner sc = new Scanner(System.in);
//        IntHolder a = new IntHolder(5);
//        LinkedList<Object> list= new LinkedList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add("Hame bikhiale ghosse");
//        list.add(new IntHolder(5));
//        int num = 6;
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        System.out.print(gson.toJson(a));
//        System.out.print(gson.toJson(list));
//        System.out.print(gson.toJson(num));


//        FileWriter writer = null;
//        try {
//            writer = new FileWriter("src/main/resources/newfile.txt",true);
//            writer.write("Hello\n");
//            writer.flush();
//            writer.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        FileReader reader;
//        try{
//            return;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }



//        boolean condition = true;
//        while (condition){
//            String input = sc.next();
//            if (input.equals("back")){
//                condition = false;
//            }
//
//            else {
//
//            }
//        }


    }
}
