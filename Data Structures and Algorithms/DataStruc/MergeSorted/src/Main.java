import java.util.Comparator;

public class Main<E> {

    public static void main(String[] args) {

        Comparator<Integer> compInt = new Compar<Integer>();

        Integer[] theInt = new Integer[]{0, 1, 22, 46, 4, 8, 16, 48};

        System.out.println("Unsorted List");
        for(int n: theInt){System.out.print(n+", ");}

        //MergeSort.sorter(compInt, theInt);

        //System.out.println(" ");
        //System.out.println("Merge Sort");
        //for(int n: theInt){System.out.print(n+", ");}




        System.out.println("Now for Bubble sort");
        BubbleSort.sort(theInt, compInt);
        for(int n: theInt){System.out.print(n+", ");}

    }

}
