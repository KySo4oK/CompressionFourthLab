public class ListElement {
    char data;
    ListElement next;
    int priority;
    ListElement right;
    ListElement left;
    ListElement parent;

    ListElement(int priority, char data) {
        this.data = data;
        this.priority = priority;
    }
    ListElement() {}

    ListElement(int priority){
        this.priority = priority;
    }

    ListElement(ListElement left, ListElement right){
        priority = left.priority + right.priority;
        this.left = left;
        this.right = right;
    }

    public boolean isLetter(){ return data != '\u0000';}

    boolean hasRight() {return right != null;}

    boolean hasLeft() {return  left != null;}

    int height() {
        if(hasRight() || hasLeft()){
            if(hasLeft() && hasRight()) {
                if(left.height() > right.height()){
                    return 1 + left.height();
                } else {
                    return 1 + right.height();
                }
            } else {
                if(hasRight()) {
                    return 1 + right.height();
                } else  {
                    return 1 + left.height();
                }
            }
        } else return 0;
    }
}
