/**
 * Created by Emily on 15/09/14.
 * Assignment 1: Write a method which returns the height of a given binary tree. For this you have to implement the binary tree ADT to build and traverse a binary tree.
 * i.e. the following tree has height 5:
 * Assignment 2: Using the implemented binary tree write two methods which traverse the tree in:
 * a) post-order
 * b) pre-order
 */
public class Tree<E> implements binaryTree<E> {

    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node newRoot) {
        root = newRoot;
    }

    @Override
    public void postOrder() {

    }

    @Override
    public void preOrder() {

    }

    @Override
    public int height(E Node) {
        if (isEmpty()) {
            return 0;
        } else {
            return 1 + Math.max(height(v.leftChild), height(root.rightChild));
            return 0;
        }

    }


    @Override
    public boolean isExternalNode() {
        if ((root.leftChild == null) && (root.rightChild == null)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isEmpty() {
        if (root == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isRoot() {
        if ((root.leftChild && root.rightChild) == null)
        return true;
        else {return false;}
    }

    @Override
    public void insert(E data, E leftChild, E rightChild) {
        //if there is 1+ difference in tree height, throw exception
        //else create binary tree
        //has a root, and left & right

        //must have 0 or 2 children, if LC == null && RC != null then throw exception "This action will make the tree
        //improper.
    }

    @Override
    public void insertNode(E Node) {

    }

    public String toString() {
        String output = "The Tree shows: ";

        if (root.Data == null) {
            output = "The Tree shows: Nothing";
        }
        while (root != null) {
            output += ("Data: "+root.Data.toString()+", "+"left child: " +
                    root.leftChild.toString()+", "+"right child: " + root.rightChild.toString());
            root = root.leftChild;
        }
        return output;
    }
}
