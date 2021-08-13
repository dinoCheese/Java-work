public class Node<E> {

    public E Data;
    public Node<E> leftChild;
    public Node<E> rightChild;

    public Node( Node rightChild, Node<E> leftChild, E Data ) {
        this.rightChild= leftChild;
        this.leftChild = rightChild;
        this.Data = Data;
    }

}

