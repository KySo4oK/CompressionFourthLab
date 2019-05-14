import java.io.*;
import java.util.ArrayList;

public class Compression {
    Compression(){}

    void compressRlE(String nameOfInputFile, String nameOfOutputFile) throws IOException{
        try(DataInputStream dataInputStream = new DataInputStream(new FileInputStream(nameOfInputFile));
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(nameOfOutputFile))){
            byte[] allBytes = dataInputStream.readAllBytes();
            for(int i = 0; i < allBytes.length; ){
                int start = i;
                byte current = allBytes[i];
                while (i < allBytes.length && allBytes[i]==current){
                    i++;
                }
                dataOutputStream.write((byte)(i - start));
                dataOutputStream.write(current);
            }
        }
    }

    void decompress(String nameOfInputFile){
        try(DataInputStream dataIn = new DataInputStream(new FileInputStream(nameOfInputFile))){

        } catch (Exception e) {
            System.out.println("Error input - output");
        }
    }


    String readFile(String nameOfInputFile){
        String result = "";
        int i = 0;
        try(DataInputStream dataIn = new DataInputStream(new FileInputStream(nameOfInputFile)) ){
            while (i != -1){
                i = dataIn.read();
                result += i;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error input-output");
        }
        return result;
    }

}
