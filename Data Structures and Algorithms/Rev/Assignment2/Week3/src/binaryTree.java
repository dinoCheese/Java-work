
public interface binaryTree<E> {

    public void postOrder();
    public void preOrder();
    public int height(E something);
    public boolean isExternalNode();
    public boolean isEmpty();
    public boolean isRoot();
    public void insert (E data, E leftChild, E rightChild);
}
