package Classes;

import java.util.LinkedList;

public class EXPCLASS {
    private int value;
    private LinkedList<String> objects;
    public static String A;
    private String string1;
    private String string2;
    class A{
    }

//    public EXPCLASS(int value, LinkedList<String> objects) {
//        this.value = value;
//        this.objects = objects;
//    }
    public EXPCLASS(String string1, String string2){
        this.string1 = string1;
        this.string2 = string2;
    }

    public String getString1() {
        return string1;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public String getString2() {
        return string2;
    }

    public void setString2(String string2) {
        this.string2 = string2;
    }

    @Override
    public String toString() {
        return "EXPCLASS{" +
                "string1='" + string1 + '\'' +
                ", string2='" + string2 + '\'' +
                '}';
    }
}
