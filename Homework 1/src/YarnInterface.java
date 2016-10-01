/**
 * Created by juanj on 9/23/2016.
 */

public interface YarnInterface {

    boolean isEmpty ();
    int getSize ();
    int getUniqueSize ();
    boolean insert (String toAdd);
    int remove (String toRemove);
    int count (String toCount);
    void removeAll (String toNuke);
    boolean contains (String toCheck);
    String getNth (int n);
    String getMostCommon ();
    Yarn clone();
    void swap (Yarn other);

}
