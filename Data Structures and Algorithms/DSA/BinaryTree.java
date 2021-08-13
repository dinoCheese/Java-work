public class BinaryTree<E> implements Tree<E> {

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

    public void preOrder(Node<E> v) {
        System.out.println(v.data.toString());
        if (v.leftChild != null) {
            preOrder(v.leftChild);
        }
        if (v.rightChild != null) {
            preOrder(v.rightChild);
        }
    }


    public void postOrder(Node<E> v) {
        if (v.leftChild != null) {
            postOrder(v.leftChild);
        }
        if (v.rightChild != null) {
            postOrder(v.rightChild);
        }

        System.out.println(v.data.toString());
    }

    @Override
    public boolean isInternal(Node<E> v) throws Exception {
        return false;
    }

    @Override
    public boolean isExternal(Node<E> v) throws Exception {
        return false;
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
    public boolean hasLeftChild(Node<E> v) throws Exception {
        return (v.leftChild != null);
    }

    @Override
    public boolean hasRightChild(Node<E> v) throws Exception {
        return (v.rightChild != null);
    }
}
