/**
 * Created by Emily on 01/09/14.
 */
public class Otherfile {
    public static void main (String[] args) throws EmptyStackException {
        //Stack name = new MyStack<String>();
        //Stack myStack = new MyStack();
        Que myQue = new Que<Integer>();
        myQue.enQueue(12);
        myQue.enQueue(13);
        myQue.enQueue(14);
        myQue.enQueue(15);
        myQue.enQueue(16);

        try {
            System.out.println( myQue.deQueue());
            System.out.println( myQue.deQueue());
            System.out.println( myQue.deQueue());
            System.out.println( myQue.deQueue());
        } catch (EmptyQueException e) {
            e.printStackTrace();
        }

        //System.out.println(myQue);
        //System.out.print(myStack.top());
    }

}
