

public class Node<E> {

    public E data;
    public Node<E> leftChild;
    public Node<E> rightChild;
    public Node<E> parent;

    public Node(Node<E> rightChild, Node<E> leftChild, E data, Node<E> parent) {
        this.rightChild = leftChild;
        this.leftChild = rightChild;
        this.data = data;
        this.parent = parent;
    }
}
