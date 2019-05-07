import java.io.IOException;
import java.util.Scanner;

public class Start {

    public static String nameOfInputFile = "if.bmp";
    public static String nameOfOutputFile = "of.bmp";

    public static void main(String args[]) throws IOException {
//        Scanner scan = new Scanner(System.in);
//        System.out.println("input text");
//        String text = scan.nextLine();
//       Compression compression = new Compression(text);
       Compression compression1 = new Compression(nameOfInputFile, nameOfOutputFile);
       compression1.compress(nameOfOutputFile);
    }

}
