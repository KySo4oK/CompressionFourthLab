import java.io.IOException;
import java.util.Scanner;

public class Start {
    public static void main(String args[]) throws IOException {
        Compression compression1 = new Compression();
        if(args.length>1){
            compression1.decompressRLE(args[0]);
        } else {
            compression1.compressRlE(args);
        }
    }
}
