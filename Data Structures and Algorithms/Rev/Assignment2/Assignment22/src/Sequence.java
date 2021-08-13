/**
 * Created by Emily on 14/09/14.
 */
// void insert( x, p )    --> Insert x after current iterator position p

public interface Sequence<E> {

    public int size();      //Done
    public void insert (E data);     //Done
    public boolean isEmpty();        //Done
    public void insertBefore(E data, E pointer);
    public void insertAfter(E data, E pointer);
}
