import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Compression {
    private String text;
    private List table;
    private HuffmanTree tree;
    public static ArrayList<ListElement> allChar = new ArrayList<>();
    Compression(String text){
        this.text = text;
        for(int i = 0; i < text.length() ; i++){
                if (!isAlreadyReViewed(text.charAt(i))) {
                    ListElement temp = new ListElement(1, text.charAt(i));
                    allChar.add(temp);
                } else {
                    findAndSetFrequency(text.charAt(i));
                }
        }
        table = new List();
        for(int i = 0; i < allChar.size(); i++){
            table.push(allChar.get(i));
        }
        tree = new HuffmanTree(table);
        result();
    }

    public void result(){
        for (int i = 0; i < text.length(); i++) {
            System.out.print(tree.codeOfChar(text.charAt(i)));
            System.out.print(" ");
        }
    }

    Compression(String nameOfInputFile, int n) throws IOException {
        this.text = readBytes(nameOfInputFile);
        if(this.text != null) {
            for (int i = 0; i < text.length(); i++) {
                if (!isAlreadyReViewed(text.charAt(i))) {
                    ListElement temp = new ListElement(1, text.charAt(i));
                    allChar.add(temp);
                } else {
                    findAndSetFrequency(text.charAt(i));
                }
            }
            table = new List();
            for (int i = 0; i < allChar.size(); i++) {
                table.push(allChar.get(i));
            }
            tree = new HuffmanTree(table);
            tree.printTable();
            result();
        }
    }

    void compress(String nameOfOtputFile) throws IOException{
        tree.printTableToFile(nameOfOtputFile);
    }

    String readBytes(String nameOfInputFile) throws IOException {
        FileInputStream fin = null;
        String result = "";
        int i = 0;
        try{
            fin =new FileInputStream(nameOfInputFile);
            while (i != -1){
                i = fin.read();
                result += i;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error input-output");
        }
        return result;
    }



    public  boolean isAlreadyReViewed(char ch) {
        for(int i = 0; i < allChar.size(); i++){
            if(allChar.get(i).data == ch){
                return true;
            }
        }
        return false;
    }

    public  void findAndSetFrequency(char ch) {
        for(int i = 0; i < allChar.size(); i++){
            if(allChar.get(i).data == ch) {
                newFrequency(allChar.get(i));
            }
        }
    }

    public  void newFrequency(ListElement a){
        a.priority++;
    }
}
