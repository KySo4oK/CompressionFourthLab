import java.io.IOException;
import java.util.Scanner;

public class Start {

    public static String nameOfInputFile = "if.csv";
    public static String nameOfOutputFile = "of.csv";

    public static void main(String args[]) throws IOException {
//        Scanner scan = new Scanner(System.in);
//        System.out.println("input text");
//        String text = scan.nextLine();
//       Compression compression = new Compression(text);
       Compression compression1 = new Compression(nameOfInputFile, 1);
    }

}
