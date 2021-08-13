import java.util.Iterator;

/**
 * Created by Emily on 21/09/14.
 */
public class BinaryTree<E> implements TreeADT<E> {

    private Node<E> root;

    public void insertRoot(E data) {
        root = new Node<E>(null, null, data, null);
    }

    public Node<E> getRoot() {
        return root;
    }

    public void insertLeftChild(E data, Node<E> parentNode) {
        Node<E> newNode = new Node<E>(null, null, data, parentNode);
        parentNode.leftChild = newNode;
    }

    public void insertRightChild(E data, Node<E> parentNode) {
        Node<E> newNode = new Node<E>(null, null, data, parentNode);
        parentNode.rightChild = newNode;
    }
/*
    public void traverse(Node<E> v) {

        if (root == null) {
            //System.out.println("Preorder traversal: ");
            preOrder(v);
        } else {
            // System.out.println("Postorder traversal: ");
            postOrder(v);
        }
    }
    */

        /*
    Algorithm preOrder(v)
        visit(v)
        for each child w of v
	        preOrder (w)

     */

    public void preOrder(Node<E> v) {
        System.out.println(v.data.toString());
        if (v.leftChild != null) {
            preOrder(v.leftChild);
        }
        if (v.rightChild != null) {
            preOrder(v.rightChild);
        }
    }


    /*
    Algorithm postOrder(v)
        for each child w of v
	    postOrder (w)
        visit(v)

     */

    public void postOrder(Node<E> v) {
        if (v.leftChild != null) {
            postOrder(v.leftChild);
        }
        if (v.rightChild != null) {
            postOrder(v.rightChild);
        }

        System.out.println(v.data.toString());
    }

    public int height(Node v) {
        if (v == null) {
            return 0;
        } else {
            return (1 + Math.max(height(v.leftChild), height(v.rightChild)));
        }
    }


    @Override
    public int size(Node<E> v) {
        return 0;
    }               //do this bit

    @Override
    public boolean isEmpty() {
        return (root == null);
        //(size(root) == 0);
    }


    @Override
    public boolean isInternal(Position<E> v) throws InvalidPositionException {
        return false;
    }           //DO

    @Override
    public boolean isExternal(Position<E> v) throws InvalidPositionException {
        return false;
    }           //DO

    @Override
    public boolean isRoot(Position<E> v) throws InvalidPositionException {
        return false;
    }               //DO


    @Override
    public boolean hasLeftChild(Node<E> v) throws InvalidPositionException {
        return (v.leftChild != null);
    }

    @Override
    public boolean hasRightChild(Node<E> v) throws InvalidPositionException {
        return (v.rightChild != null);
    }
}
