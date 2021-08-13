/**
 * Created by Emily on 21/09/14.
 */

public interface TreeADT<E> {

    //public Node element(Node<E> v);     //returns the object stored at that position

    public int size(Node<E> v);         //return the number of nodes in the tree.

    public boolean isEmpty();        //Test whether the tree has any nodes or not.

    public void insertLeftChild(E data, Node<E> parentNode);

    public void insertRightChild(E data, Node<E> parentNode);

    public void insertRoot(E data);

    public Node getRoot();


    public int height(Node v);

   // public void traverse(Node<E> v);

    public void preOrder(Node<E> v);

    public void postOrder(Node<E> v);


    public boolean isInternal(Position<E> v) throws InvalidPositionException;

    public boolean isExternal(Position<E> v) throws InvalidPositionException;

    public boolean isRoot(Position<E> v) throws InvalidPositionException;


    public boolean hasLeftChild(Node<E> v) throws InvalidPositionException; //returns if has leftchild

    public boolean hasRightChild(Node<E> v) throws InvalidPositionException; //returns if has rightchild

    //public void insert(int , double );
    //public Iterator<E> iterator();     //return an iterator of all the elements stored at nodes of the tree.
    //public Iterable<Position<E>> positions(); //return an iterable collection of all the nodes of the tree.
    //public Position<E> root() throws EmptyTreeException; //return the tree's root; an error occurs if the tree is empty.
    //public Position<E> parent(Position<E> v);
}
