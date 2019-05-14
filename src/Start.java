import java.io.IOException;
import java.util.Scanner;

public class Start {

    public static String nameOfInputFile = "if.bmp";
    public static String nameOfOutputFile = "of.bmp";

    public static void main(String args[]) throws IOException {
       Compression compression1 = new Compression();
       compression1.compressRlE(nameOfInputFile,nameOfOutputFile);
    }

}
