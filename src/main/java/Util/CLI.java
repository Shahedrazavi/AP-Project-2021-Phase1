package Util;

public class CLI {
    static CLI printer;

    public static CLI getCLI() {
        if (printer == null) {
            printer = new CLI();
        }
        return printer;
    }

    private CLI() {
    }

    public void print(String string){
        System.out.print(string);
    }

    public void println(String string){
        System.out.println(string);
    }

    private void print(){
        print("\n");
    }

    public void nextLine(){
        print();
    }

    public void makeGap(){
        print("\n\n\n");
    }

    public void makeBigGap(){
        print("\n\n\n\n\n\n\n\n");
    }

    public void makeLine(){
        print("--------------------------------------------------");
    }

    public void makeThickLine(){
        print("==================================================");
    }

    public void makePlusLine(){
        print("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
    }

    public void sectionShower(String string){
        makeThickLine();
        nextLine();
        println(string);
        makeThickLine();
        nextLine();
    }

    public void showHighlightedMsg(String string){
        printer.makeLine();
        printer.nextLine();
        printer.println(string);
        printer.makeLine();
        printer.nextLine();
    }



}
