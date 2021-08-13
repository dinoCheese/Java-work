/**
 * Created by Emily on 01/09/14.
*/

public class Que<E> {

    public Stack<E> s1;
    public Stack<E> s2;

    public Que() {
        s1 = new MyStack<E>();
        s2 = new MyStack<E>();

    }

    public void enQueue(E input) {
        s1.push(input);
    }

    public E deQueue() throws EmptyQueException, EmptyStackException {
        if ((s1.isEmpty()) && s2.isEmpty()) {
            throw new EmptyQueException();
        }
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }
}