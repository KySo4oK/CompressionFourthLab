public class List {
    private ListElement back = null;
    private ListElement front = null;
    private int num = 0;

    List(){}

    void push(ListElement a) {
        if(isEmpty()) {
            back = a;
            front = a;
            a.next = null;
        } else {
            ListElement temp = back;
            while (temp!=null) {
                if(back.priority<=a.priority){
                    a.next = back;
                    back = a;
                    break;
                }
                if(a.priority<front.priority){
                    front.next = a;
                    front = a;
                    a.next = null;
                    break;
                }
                if(a.priority<temp.priority && a.priority>=temp.next.priority) {
                    a.next = temp.next;
                    temp.next = a;
                    break;
                }
                temp = temp.next;
            }
        }
        num++;

    }

    boolean isEmpty() {
        return num==0;
    }

    void clear(){
        back = null;
        front = null;
        num = 0;
    }

    ListElement pop() {
        ListElement temp = front;
        ListElement t = back;
        while (t.next!=null){
            if(t.next==front) {
                front = t;
                front.next = null;
                break;
            }
            t = t.next;
        }
        num--;
        return temp;
    }

    void print() {
        ListElement temp = back;
        while (temp != null) {
            System.out.println(temp.data + "(" + temp.priority + ") ");
            temp = temp.next;
        }
        System.out.println("");
    }

    int size() { return num; }




}
