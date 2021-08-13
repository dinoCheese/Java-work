/**
 * Created by Emily on 14/09/14.
 */
public class Main {
    public static void main(String[] args) throws Exception {

        List SomeList = new List();
        System.out.println("The list is empty: " + SomeList.isEmpty());
        System.out.println("The size of the list is: " + SomeList.size());
        System.out.println(SomeList.toString());


        for (int i = 0; i < 10; i++) {
            int k = (int) (Math.random() * 10);
            Node current = SomeList.Head;

            if (SomeList.isEmpty()) {
                SomeList.insert(k);
            }
            while (current != null) {
                if (k <= (Integer) current.element) {
                    SomeList.insertBefore((Integer) current.element, k);
                    break;
                }
                if (current.nextNode == null) {
                    SomeList.insertAfter((Integer) current.element, k);
                    break;
                }
                current = current.nextNode;
            }
        }
        System.out.println(SomeList.toString());
    }
}




/*

                while (curr != null) {
                    if (rand <= (Integer) curr.data) {
                        myLL.insertBefore((Integer) curr.data, rand);
                        break;
                    }
                    if (curr.next == null) {
                        myLL.insertAfter((Integer) curr.data, rand);
                        break;
                    }
                    curr = curr.next;
                }
                //
        }
 */



