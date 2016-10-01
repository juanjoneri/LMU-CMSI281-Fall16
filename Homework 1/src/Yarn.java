import javax.naming.OperationNotSupportedException;

/**
 *  A Yarn is an unordered collection of Strings in which duplicates are allowed.
 *  A Yarn maps Strings to the number of occurrences of each String in the Yarn.
 */

public class Yarn implements YarnInterface{

    private final int MAX_SIZE = 100;
    private int size, uniqueSize;
    private Entry[] items;

    public Yarn(){
        size = 0;
        uniqueSize = 0;
        items = new Entry[MAX_SIZE];
    }


    /*Returns a String representation of the items in the Yarn
    * "I think I like this homework" would return:
    * { "I": 2, "think": 1, "like": 1, "this": 1, "homework": 1 } */
    @Override
    public String toString(){
        String toPrint = "{ ";
        for(int i = 0; i < uniqueSize; i ++){
            toPrint += items[i].text;
            toPrint += ": ";
            toPrint += items[i].count;
            toPrint += " ";
        }
        return toPrint + "}";
    }

    /*Returns true if the Yarn has no Strings inside, false otherwise.*/
    @Override
    public boolean isEmpty() {
        return uniqueSize == 0;
    }

    /*Returns the current size of the Yarn (the number of Strings inside, counting duplicates separately).*/
    @Override
    public int getSize() {
        return size;
    }

    /*Returns the number of unique Strings in the Yarn (counting duplicates as 1).*/
    @Override
    public int getUniqueSize() {
        return uniqueSize;
    }

    /*Adds the String toAdd to the Yarn, and returns true if successful.
    * If the Yarn is at capacity (already at 100 unique Strings), then does nothing and returns false.*/
    @Override
    public boolean insert(String toAdd) {
        if(size != 100) {
            if (findIndex(toAdd) == -1) {
                Entry entry = new Entry(toAdd, 1);
                items[uniqueSize] = entry;
                uniqueSize++;
                size++;
                return true;
            } else {
                items[findIndex(toAdd)].count++;
                size++;
                return true;
            }
        } else {
            return false;
        }
    }

    /*Removes 1 occurrence of the String toRemove from the Yarn, and returns the number of occurrences remaining after removal.
    * If toRemove does not exist in the Yarn, simply return 0 and do nothing.*/
    @Override
    public int remove(String toRemove) {
        int index = findIndex(toRemove);

        if(index != -1){
            if(items[index].count == 1){
                removeAll(toRemove);
            } else {
                items[index].count --;
                size -= 1;
                return items[index].count;
            }
        }

        return 0;
    }

    /*Return the number of occurrences of String toCount found in the Yarn.*/
    @Override
    public int count(String toCount) {
        return findIndex(toCount) == -1 ? 0 : items[findIndex(toCount)].count;
    }

    /*Removes ALL occurrences of the String toNuke from the Yarn.
    * If toNuke does not exist in the Yarn, do nothing.*/
    @Override
    public void removeAll(String toNuke) {
        int index = findIndex(toNuke);
        if( index != -1 ){
            size -= items[index].count;
            items[index] = items[uniqueSize - 1];
            uniqueSize --;
        }

    }

    /*Returns true if the String toCheck appears at least once inside of the Yarn.*/
    @Override
    public boolean contains(String toCheck) {
        return findIndex(toCheck) != -1;
    }

    /*Even though Yarns are order-independent, we may at times want a way to iterate through the occurrences stored within.
    * We'll use the getNth method for this purpose.

    * getNth is defined for 0 <= n < size, such that iterating n as many times as there are String occurrences in the Yarn will return each occurrence, but in *any* order.

    * Because of this "any order" constraint, you may assume that any operations that would affect the contents of a Yarn
    * (e.g., insert or remove) may invalidate an existing iteration using getNth.

    * In other words, the requirement of getNth to produce each String occurrence in the Yarn if iterated through from the beginning is removed whenever the Yarn's contents have been modified.

    * In other, other words, if you modify the Yarn in any way, then getNth need not adhere to any prescribed behavior.
    * The user is assumed to know this risk.*/
    @Override
    public String getNth(int n) {
        if(n < size) {
            n = n+1; //easier for this particular implementation
            int index = 0;
            for(int i = 0; i <= n; i ++){
                if (index < n) {
                    index += items[i].count;
                } else {
                    return items[i-1].text;
                }
            }

        }
        throw new IndexOutOfBoundsException();
    }

    /*Returns the String that occurs most frequently in the Yarn. In the event of a tie, you may return *either* of the most frequent. If the Yarn is empty, return null.*/
    @Override
    public String getMostCommon() {
        if( !this.isEmpty() ) {
            Entry mmostCommon = items[0];

            for (Entry entry : items) {
                if(entry != null) {
                    if (entry.count > mmostCommon.count) {
                        mmostCommon = entry;
                    }
                } else {
                    break;
                }
            }

            return mmostCommon.text;
        }
        return null;
    }

    /*Produces a *new* copy of the calling Yarn and returns a reference to the new copy.
    * The copy should contain all Strings in the original, but modifications to the copy
    * (e.g., through insert or remove) should not be manifest in the original (and vice versa).*/
    @Override
    public Yarn clone() {
        Yarn clone = new Yarn();

        for( Entry entry: items ){
            if(entry != null) {
                for (int i = 0; i < entry.count; i++) {
                    clone.insert(entry.text);
                }
            } else {
                break;
            }
        }
        return clone;
    }

    /*Swaps the contents of the calling Yarn and the other specified.
    * you may NOT use iteration/recursion to solve this problem! Hint: use fields that have references!*/
    @Override
    public void swap(Yarn other) {
        Yarn otherClone = other.clone();
        other.items = this.items;
        other.size = this.size;
        other.uniqueSize = this.uniqueSize;
        this.items = otherClone.items;
        this.size = otherClone.size;
        this.uniqueSize = otherClone.uniqueSize;

    }

    /*finds the current index of the Entry with text "toFind"
    * returns -1 if not found*/
    private int findIndex(String toFind){
        for( int i = 0; i < uniqueSize; i ++ ){
            if(items[i].text.equals(toFind)){
                return i;
            }
        }
        return -1;
    }

    /*Returns a *new* Yarn object consisting of a combination of all String occurrences from y1 and y2.*/
    static Yarn knit (Yarn y1, Yarn y2){
        Yarn sum = y1.clone();
        for( int i = 0; i < y2.getSize(); i ++){
            sum.insert(y2.getNth(i));
        }
        return sum;
    }

    /*Returns a *new* Yarn object consisting of all String occurrences from y1 that do NOT appear in y2.*/
    static Yarn tear (Yarn y1, Yarn y2){
        Yarn diff = y1.clone();
        for( int i = 0; i < y2.getSize(); i ++){
            diff.remove(y2.getNth(i));
        }
        return diff;
    }

    /*Returns true if y1 and y2 contain the exact same unique Strings and String occurrences (i.e., the same Strings and the same counts of each String).

    * Note: because order does not matter for Yarns, the Strings in y1 and y2 can be found in different orders but still be considered equivalent.

    * Refer to the notes in the above Description section for examples of equivalent Yarns.*/
    static boolean sameYarn (Yarn y1, Yarn y2){
        if(y1.getSize() == y2.getSize() && y1.getUniqueSize() == y2.getUniqueSize()) {

            for (int i = 0; i < y2.size; i++) {
                //There is certainly a lot of unnecessary repetition in this algorithm
                //However provided the restrictions in the public interface this is what I came up with
                String toMatch = y2.getNth(i);
                if(!y1.contains(toMatch) || y1.count(toMatch) != y2.count(toMatch)){
                    return false;
                }
            }
            return true;

        }
        return false;
    }


    /**
     *   Each Entry (see class definition below) holds a unique String in the Yarn,
     *   as well as the number of occurrences of that String.
     */
    private class Entry{
        String text;
        int count;

        Entry (String s, int c) {
            text = s;
            count = c;
        }
    }

}