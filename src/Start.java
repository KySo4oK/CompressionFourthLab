import java.util.ArrayList;
import java.util.Scanner;

public class Start {
    public static ArrayList<ListElement> allChar = new ArrayList<>();

    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        System.out.println("input text");
        String text = scan.nextLine();
        for(int i = 0; i < text.length() ; i++){
            if(text.charAt(i) != ' ') {
                if (!isAlreadyReViewed(text.charAt(i))) {
                    ListElement temp = new ListElement(1, text.charAt(i));
                    allChar.add(temp);
                } else {
                    findAndSetFrequency(text.charAt(i));
                }
            }
        }
        List table = new List();
        for(int i = 0; i < allChar.size(); i++){
            table.push(allChar.get(i));
        }
        table.print();
        HuffmanTree tree = new HuffmanTree(table);
        tree.printTable();
        tree.printDetours();



        System.out.println("Weight : " + tree.compWeight());
        System.out.println("Height : " + tree.compHeight());
        System.out.println("Middle Height : " + tree.compMiddleHeight());
        System.out.println("input code");
        tree.decode(scan.nextLine());
    }

    public static boolean isAlreadyReViewed(char ch) {
        for(int i = 0; i < allChar.size(); i++){
            if(allChar.get(i).data == ch){
                return true;
            }
        }
        return false;
    }

    public static void findAndSetFrequency(char ch) {
        for(int i = 0; i < allChar.size(); i++){
            if(allChar.get(i).data == ch) {
                newFrequency(allChar.get(i));
            }
        }
    }

    public static void newFrequency(ListElement a){
        a.priority++;
    }
}
