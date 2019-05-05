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
