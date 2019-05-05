import java.util.ArrayList;
import java.util.Scanner;

public class Start {
    public static ArrayList<ListElement> allChar = new ArrayList<>();

    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        System.out.println("input text");
        String text = scan.nextLine();
       Compression compression = new Compression(text);
    }

}
