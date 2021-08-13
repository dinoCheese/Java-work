/**
 * Created by Emily on 11/03/14.
 */

public class Fibbo {
    static void fibonacci() {
        int ptr1 = 1, ptr2 = 1;
        int temp = 0;
        System.out.print(ptr1 + " " + ptr2 + " ");
        for (int i = 0; i < 10; i++) {
            System.out.print(ptr1 + ptr2 + " ");
            temp = ptr1;
            ptr1 = ptr2;
            ptr2 = temp + ptr2;
        }
    }
}
