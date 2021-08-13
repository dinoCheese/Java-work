import java.lang.*;
import java.util.Comparator;

public class Compar<E extends Comparable<E>> implements Comparator<E>{

    public int compare(E e, E e2) {
            return e.compareTo(e2);

    }
}

