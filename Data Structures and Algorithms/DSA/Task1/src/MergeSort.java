/**
 * Created by Emily on 24/09/2014.
 */

import java.util.*;
import java.util.List;

public class MergeSort<E> {

    public class MergeList(List<E> list1, list2, finalList){

        List<E> newList = new List<E>() {

            public int size() {
                return 0;
            }

            public boolean isEmpty() {
                return false;
            }

            public Object[] toArray() {
                return new Object[0];
            }

            public <T> T[] toArray(T[] a) {
                return null;
            }

            public boolean equals(Object o) {
                return false;
            }

            public void add(int index, E element) {

            }
        };

    }





/*

    MergeList(List<E> L1, L2, L, Comparator<E> C)
    input: two sorted lists L1, L2 and comparator C
    output: merged list L

    while L1.size()>0 and L2.size()>0
            if  C.smallerEqual(L1.first.element, L2.first.element)
            L.addEnd(L1.first.element); L1.remove(L1.first)
            else
            L.addEnd(L2.first.element); L2.remove(L2.first)
            if L1.size()>0 L.addEnd(L1) else L.addEnd(L2)

*/


    public static void main(String[] args) {

        System.out.println("State the number of items you would like to sort: ");
        Scanner scanner = new Scanner(System.in);

        int i = scanner.nextInt();          //take input
        int[] theArray = new int[i];        //create array, user defines size
        System.out.println("Thank you, now enter your " + i + "element: ");

        System.out.println();
        System.out.println();


    }
}


