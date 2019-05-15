import java.io.*;

public class Compression {

    void compressRlE(String[] args) throws IOException{

        try (DataOutputStream dataOutputStream =
                        new DataOutputStream(new FileOutputStream(args[0]))){
            dataOutputStream.writeInt(args.length-1); //quantity of files
            for(int c = 1; c < args.length ; c++){
                try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(args[c]))) {
                    byte[] allBytes = dataInputStream.readAllBytes();
                    dataOutputStream.writeInt(args[c].length()); //length of file name
                    int k;
                    for (k = 0; k < args[c].length(); k++) {
                        dataOutputStream.writeChar(args[c].charAt(k)); // write name of file in loop
                    }
                    byte[] compressBytes = new byte[2*allBytes.length];
                    int point = 0;
                    for (int i = 0; i < allBytes.length; ) {
                        int start = i;
                        byte current = allBytes[i];
                        while (i < allBytes.length && allBytes[i] == current && i - start < 255) {
                            i++;
                        }
                        compressBytes[point] = (byte)(i - start);
                        point++;
                        compressBytes[point] = current;
                        point++;
                    }
                    dataOutputStream.writeInt(point); // length of file in byte
                    for(int i = 0; i<point; i++) {
                        dataOutputStream.write(compressBytes[i]);
                    }
                }
            }
        }

    }

    void decompressRLE(String nameOfInputFile) throws IOException{
        int quantityOfFile;
        byte[] allBytes;
        try(DataInputStream dataInputStream = new DataInputStream(new FileInputStream(nameOfInputFile))) {
            quantityOfFile = dataInputStream.readInt(); // read quantity of files
            allBytes = dataInputStream.readAllBytes();

        }
        int endOfPreviousFile = 0;
        try(DataInputStream dataInputStream = new DataInputStream(new FileInputStream(nameOfInputFile))) {
            for(int z = 0; z < quantityOfFile; z++) {
                int lengthNameOfFile = dataInputStream.readInt(); //length of file name
                System.out.println(lengthNameOfFile);
                String nameOfDecompress = "";
                int k;
                for (k = 1; k <= lengthNameOfFile; k++) { // search file name
                    char ch = dataInputStream.readChar();
                    nameOfDecompress += Character.toString(ch);
                }
                System.out.println(nameOfDecompress);
                int lengthOfFile = dataInputStream.readInt();
                System.out.println(lengthOfFile);
                int start = 16 + lengthNameOfFile + endOfPreviousFile; // used 16 like 4 int in bytes
                endOfPreviousFile = start + lengthOfFile;
                try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(nameOfDecompress))) {
                    for (int i = start; i < endOfPreviousFile - 1; i += 2) {
                        int value = allBytes[i];
                        for (int j = 0; j < value; j++) {
                            dataOutputStream.write(allBytes[i++]);
                        }
                    }
                }
            }
        }
    }


    byte[] byteArray(byte[] compress, int point){
        byte[] bytes = new byte[point+1];
        for(int i = 0; i <= point; i++){
            bytes[i] = compress[i];
        }
        return compress;
    }
}
