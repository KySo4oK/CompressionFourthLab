import java.io.*;

public class Compression {

    void compressRlE(String[] args) throws IOException{

        try (DataOutputStream dataOutputStream =
                        new DataOutputStream(new FileOutputStream(args[0]))){
            dataOutputStream.writeInt(args.length-1);
            for(int c = 1; c < args.length ; c++){
                try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(args[c]))) {
                    byte[] allBytes = dataInputStream.readAllBytes();
                    dataOutputStream.write(args[c].length());
                    int k;
                    for (k = 0; k < args[c].length(); k++) {
                        dataOutputStream.write(args[c].charAt(k));
                    }
                    byte[] compressBytes = new byte[allBytes.length];
                    int point = 0;
                    for (int i = 0; i < allBytes.length; ) {
                        int start = i;
                        byte current = allBytes[i];
                        while (i < allBytes.length && allBytes[i] == current && i - start < 256) {
                            i++;
                        }
                        compressBytes[point] = (byte)(i - start);
                        point++;
                        compressBytes[point] = current;
                        point++;
                    }
                    dataOutputStream.writeInt(point);
                    compressBytes = byteArray(compressBytes, point);
                    dataOutputStream.write(compressBytes);
                }
            }
        }

    }

    void decompressRLE(String nameOfInputFile) throws IOException{
        try(DataInputStream dataInputStream = new DataInputStream(new FileInputStream(nameOfInputFile))){
            int quantityOfFile = dataInputStream.readInt();
            for(int z = 1; z < quantityOfFile; z++) {
                int lengthNameOfFile = dataInputStream.readInt();
                System.out.println(lengthNameOfFile);
                String nameOfDecompress = "";
                int k;
                for (k = 1; k <= lengthNameOfFile; k++) {
                    char ch = dataInputStream.readChar();
                    nameOfDecompress += Character.toString(ch);
                }
                System.out.println(nameOfDecompress);
                int lengthOfFile = dataInputStream.readInt();
                try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(nameOfDecompress))) {
                    for (int i = k; i < lengthOfFile - 1; i += 2) {
                        int value = dataInputStream.readInt();
                        for (int j = 0; j < value; j++) {
                            dataOutputStream.write(dataInputStream.readByte());
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
