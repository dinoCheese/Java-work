/**
 * Created by Emily on 28/09/2014.
 * <p/>
 * Assignment 1: Implement Mergesort for sorting an ArrayList, using both the Comparator ADT and generics.
 * (The comparator interface is provided by the Java SDK, don't write it yourself!)
 * <p/>
 * What is the average runtime for a list of Doubles of length 1,000, 10,000 and 100,000?
 * <p/>
 * Assignment 2: Now implement a second version of Mergesort that uses Bubblesort to sort
 * sublists of less than 20 elements. Compare the running time of this version of the algorithm
 * to the first version.
 */

import java.util.Comparator;


class MergeSort {

    public static <E> void sorter(Comparator<E> comp, E[] array) {
        divide(comp, array, 0, array.length - 1);
    }


    private static <E> void divide(Comparator<E> comp, E[] array, int start, int end) {
        int middle = (int) ((start + end) / 2);

        if (start == end) {
            return;
        } else {
            divide(comp, array, start, middle);
            divide(comp, array, (middle + 1), end);
            merge(comp, array, start, middle, end);
        }
    }

    public static <E> void merge(Comparator<E> comp, E[] array, int start, int middle, int finish) {
        int n = finish - start + 1;
        Object[] values = new Object[n];

        int from = start,      mid = (middle +1),      index = 0;

        while ((from <= middle) && (mid  <= finish)) {
            if (comp.compare(array[from], array[mid]) < 0) {
                values[index] = array[from];
                from++;
            } else {
                values[index] = array[mid];
                mid++;
            }index++;}

        while (from <= middle) {
            values[index] = array[from];
            from++;
            index++;
        }
        while (mid <= finish) {
            values[index] = array[mid];
            mid++;
            index++;
        }
        for (index = 0; index < n; index++)
            array[start + index] = (E) values[index];
    }
}
