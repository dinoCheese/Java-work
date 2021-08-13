/**
 * Created by Emily on 14/09/14.
 */

public class List implements Sequence {

    Node Head;
    int Size = 0;


    @Override
    public int size() {
        if (Head == null) {
            return 0;
        } else {
            return Size;
        }
    }

    @Override
    public void insert(Object data) {
        if (isEmpty()) {
            Head = new Node(data, Head);
            Size++;
        } else if (!isEmpty()) {
            Node current = Head;
            Node newNode = new Node(data, current);
            Head = newNode;
            Size++;
        }
    }

    @Override
    public void insertBefore(Object data, Object insert) {
        Node current = Head;
        if (isEmpty()) {
            Head = new Node(insert, Head);
            return;
        } else if(current.nextNode == null){
            Head = new Node(insert, Head);
            return;
        }
        if (!isEmpty()) {
            while (current.nextNode != null) {
                if(current.nextNode.element == data){
                    Node newNode = new Node(insert, current.nextNode);
                    current.nextNode = newNode;
                    return;
                }
                current = current.nextNode;
            }
        }
        Size++;
    }

    @Override
    public void insertAfter(Object data, Object insert) {
        Node current = Head;
        if (isEmpty()){
            Head = new Node(insert, Head);
            return;
        } else if (current.element == null) {
            insert(data);
            return;
        }
        if(current.element == data) {
            Node newNode = new Node(insert, current.nextNode);
            current.nextNode = newNode;
            return;
        }
        Size++;
    }

    public String toString() {
        Node current = Head;
        String output = "The List shows: ";
        if (current == null) {
            output = "The List shows: Nothing";
        }
        while (current != null) {
            output += current.element.toString() + ",";
            current = current.nextNode;
        }
        return output;
    }

    @Override
    public boolean isEmpty() {
        if (Head != null) {
            return false;
        } else {
            return true;
        }
    }
}

