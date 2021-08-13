/**
 * Created by Emily on 24/09/2014.
 */
public interface Tree<E> {

    public boolean isEmpty();        //Test whether the tree has any nodes or not.

    public void insertLeftChild(E data, Node<E> parentNode);

    public void insertRightChild(E data, Node<E> parentNode);

    public void insertRoot(E data);

    public int size(Node<E> v);

    public int height(Node v);

    public void preOrder(Node<E> v);

    public void postOrder(Node<E> v);


    public boolean isInternal(Node<E> v) throws Exception;

    public boolean isExternal(Node<E> v) throws Exception;

    public boolean hasRightChild(Node<E> v) throws Exception;

    public boolean hasLeftChild(Node<E> v) throws Exception;

}
