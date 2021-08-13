/**
 * Created by Emily on 14/09/14.
 */
public class Node<E> {

    public E element;
    public Node<E> nextNode;

    public Node(E element, Node<E> nextNode) {
        this.element=element;
        this.nextNode=nextNode;
    }
}
