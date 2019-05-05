import java.util.ArrayList;


public class HuffmanTree {
    ListElement root;
    ArrayList<ListElement> allLetters = new ArrayList<>();


    HuffmanTree(List table) {
        while(table.size() >= 0){
            ListElement left = table.pop();
            ListElement right = table.pop();
            ListElement parent = new ListElement(left,right);
            table.push(parent);
            parent.left = left;
            parent.right = right;
            left.parent = parent;
            right.parent = parent;
            if(left.isLetter()) {
                allLetters.add(left);
            }
            if(right.isLetter()) {
                allLetters.add(right);
            }
            if(table.size() == 1) {
                root = table.pop();
                break;
            }
        }
    }
    String code (ListElement a){
           String g = new StringBuffer(invertCode(a)).reverse().toString();
           return g;
    }

    String invertCode (ListElement a) {
        if (a != root) {
            if(a.parent.left == a) {
                return "0" + invertCode(a.parent);
            }
            else {
                return "1" + invertCode(a.parent);
            }
        }
        return "";
    }

    public void printDetours() {
        System.out.println("Infix :");
        inOrder(root);
        System.out.println();
        System.out.println("Postfix :");
        postOrder(root);
        System.out.println();
        System.out.println("Prefix :");
        preOrder(root);
        System.out.println();
    }

    void decode(String code){
        ListElement temp = root;
        for(int i = 0; i < code.length(); i++){
            if(code.charAt(i) == '0') {
                temp = temp.left;
            } else {
                temp = temp.right;
            }
        }
        System.out.println("char : " + temp.data);
    }

    void printTable() {
        for(int i = 0; i < allLetters.size(); i++) {
            System.out.println(allLetters.get(i).data + " "  + code(allLetters.get(i)));
        }
    }

    private void postOrder(ListElement root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        if(root.isLetter()){
        System.out.print(root.data + " ");
        }
    }

    private void inOrder(ListElement root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        if(root.isLetter()) {
            System.out.print(root.data + " ");
        }
        inOrder(root.right);
    }

    private void preOrder(ListElement root) {
        if (root == null) {
            return;
        }
        if(root.isLetter()) {
            System.out.print(root.data + " ");
        }
        preOrder(root.left);
        preOrder(root.right);
    }

    public int compWeight(){
        int sum = 0;
        for(int i=0; i < allLetters.size(); i++) {
            sum+=invertCode(allLetters.get(i)).length()*allLetters.get(i).priority;
        }
        return sum;
    }

    public int compHeight(){ return root.height();}

    public int compMiddleHeight() {
        int sum =0;
        for (int i = 0; i < allLetters.size(); i++){
            sum += invertCode(allLetters.get(i)).length();
        }
        return sum/allLetters.size();
    }


}
