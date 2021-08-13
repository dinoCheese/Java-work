/**
 * Created by Emily on 01/09/14.
 */

import java.util.EmptyStackException;


public class MyStack<E> implements Stack<E> {

    int n = 0;
    E array[] = (E[]) new Object[1000];


    public int size() {
        return n;
    }

    public boolean isEmpty() {
        if (n == 0) {
            return true;
        } else {
            return false;
        }
    }

    public E top() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            return array[n-1];
        }
    }

    public void push(E element) {
        array[n] = element;
        //inserts the latest value
        n++;
    }

    public E pop() throws EmptyStackException {
        E popVal;
        popVal = array[n-1];
        n--;
        return popVal;
        //Removes the latest value
    }
}

