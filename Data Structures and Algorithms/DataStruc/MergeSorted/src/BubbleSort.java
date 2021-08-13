import java.util.Comparator;

/**
 * Created by Emily on 17/10/2014.
 */
public class BubbleSort<E> {
    public static <E> void sort(E[] array, Comparator<E> comp) {

        int n = array.length;

        for (int c = 0; c < ( n - 1 ); c++) {
            for (int d = 0; d < n - c - 1; d++) {
                if (comp.compare(array[d], array[d+1]) > 0){      //compare if l > r
                    E tmp = array[d];
                    array[d] = array[d + 1];
                    array[d + 1] = tmp;
                }
            }
        }
    }
}


/*
 */