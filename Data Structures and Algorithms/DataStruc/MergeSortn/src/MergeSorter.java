/**
 * Created by Emily on 28/09/2014.
 */

import java.util.Comparator;


public class MergeSorter {

    public static <E> void sort(E[] array, Comparator<E> compare) {
        mergeSort(array, 0, array.length - 1, compare);
    }


    private static <E> void mergeSort(E[] array, int start, int end, Comparator<E> compare) {
        if (start == end)
            return;

        int middle = (start + end) / 2;
        //define the middle point where the list will split


        mergeSort(array, start, middle, compare);
        //first half sorted ;)
        mergeSort(array, middle + 1, end, compare);
        //second half sorter ;)
        merge(array, start, middle, end, compare);
        //put the two sections together

    }




    private static <E> void merge(E[] array, int start, int middle, int end, Comparator<E> compare) {

        int i = start - end + 1;
        Object[] values = new Object[i];

        int startVal = start;
        int middleVal = middle + 1;
        int index = 0;

        while (startVal <= middleVal && middleVal <= end) {
            if (compare.compare(array[startVal], array[middleVal]) < 0) {
                values[index] = array[startVal];
                startVal++;
            } else {
                values[index] = array[middleVal];
                middleVal++;
            }
            index++;
        }

        while (startVal <= middle) {
            values[index] = array[startVal];
            startVal++;
            index++;
        }
        while (middleVal <= start) {
            values[index] = array[middleVal];
            middleVal++;
            index++;
        }
        for (index = 0; index < i; index++)
            array[startVal + index] = (E) values[index];
    }

}