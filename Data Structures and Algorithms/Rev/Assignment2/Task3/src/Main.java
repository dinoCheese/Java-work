/**
 * Created by Emily on 21/09/14.
 */


public class Main {
    public static void main(String[] args){


        TreeADT<Integer> theTree = new BinaryTree<Integer>();           //create tree


        System.out.println(theTree.isEmpty());

        theTree.insertRoot(5);                                          //create the root
        theTree.insertLeftChild(4, theTree.getRoot());
        theTree.insertRightChild(3, theTree.getRoot());
        theTree.insertLeftChild(2, theTree.getRoot().leftChild);
        theTree.insertLeftChild(1, theTree.getRoot().rightChild);



        System.out.println("The height is: "+theTree.height(theTree.getRoot()));


        theTree.postOrder(theTree.getRoot());
        theTree.preOrder(theTree.getRoot());










    }
}
