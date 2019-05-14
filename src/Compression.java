import java.io.*;

public class Compression {

    void compressRlE(String[] args) throws IOException{

        try (DataOutputStream dataOutputStream =
                        new DataOutputStream(new FileOutputStream(args[0]))){
            dataOutputStream.write(args.length-1);
            for(int c = 1; c < args.length ; c++){
                try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(args[c]))) {
                    byte[] allBytes = dataInputStream.readAllBytes();
                    dataOutputStream.write(args[c].length());
                    int k;
                    for (k = 0; k < args[c].length(); k++) {
                        dataOutputStream.write(args[c].charAt(k));
                    }
                    for (int i = 0; i < allBytes.length; ) {
                        int start = i;
                        byte current = allBytes[i];
                        while (i < allBytes.length && allBytes[i] == current && i - start < 256) {
                            i++;
                        }
                        dataOutputStream.write((byte) (i - start));
                        dataOutputStream.write(current);
                    }
                }
                for(int r = 0; r<3; r++){
                dataOutputStream.write(0);
                }
            }
        }

    }

    void decompressRLE(String nameOfInputFile) throws IOException{
        try(DataInputStream dataInputStream = new DataInputStream(new FileInputStream(nameOfInputFile))){
            byte[] allBytes = dataInputStream.readAllBytes();
            int quantityOfFile = allBytes[0];
            for(int z = 1; z < quantityOfFile; z++) {
                int lengthNameOfFile = allBytes[z];
                System.out.println(lengthNameOfFile);
                String nameOfDecompress = "";
                int k;
                for (k = 1; k <= lengthNameOfFile; k++) {
                    char ch = (char) allBytes[k];
                    nameOfDecompress += Character.toString(ch);
                }
                System.out.println(nameOfDecompress);
                try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(nameOfDecompress))) {
                    for (int i = k; i < allBytes.length - 1; i += 2) {
                        if(k+2<allBytes.length && allBytes[k] == 0 && allBytes[k+1]==0 && allBytes[k+2]==0){
                            z = k+2;
                            break;
                        }
                        int value = allBytes[i];
                        for (int j = 0; j < value; j++) {
                            dataOutputStream.write(allBytes[i + 1]);
                        }
                    }
                }
            }
        }
    }
}
