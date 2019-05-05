public class Node {
    ListElement core;
    Node parent;
    Node left;
    Node right;
    public boolean isLetter(){ return core.data != '\u0000';}

    Node(){}

    Node(ListElement a) {
        core = a;
    }


}
