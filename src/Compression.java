import java.io.*;
import java.util.ArrayList;

public class Compression {
    private String text;
    private List table;
    private HuffmanTree tree;
    public static ArrayList<ListElement> allChar = new ArrayList<>();
    public String nameOfInputFile;
    public String nameOfOutputFile;
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

    Compression(String nameOfInputFile, String nameOfOutputFile) {
        this.nameOfInputFile = nameOfInputFile;
        this.nameOfOutputFile = nameOfOutputFile;
        this.text = readFile(nameOfInputFile);
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
            tree.printTableToFile(nameOfInputFile);
        }
    }

    void compress(String nameOfOutputFile){
        tree.printTableToFile(nameOfOutputFile);
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
