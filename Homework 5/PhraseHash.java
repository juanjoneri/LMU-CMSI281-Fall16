import java.util.LinkedList;

public class PhraseHash implements PhraseHashInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------

    private final static int BUCKET_COUNT = 1000;
    private int size, longest;
    private LinkedList<String>[] buckets;


    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------

    @SuppressWarnings("unchecked") // Don't worry about this >_>
    PhraseHash () {
        this.size = 0;
        this.longest = 0;
        this.buckets = new LinkedList[BUCKET_COUNT];
    }


    // -----------------------------------------------------------
    // Public Methods
    // -----------------------------------------------------------

    public int size () {
        return this.size;
    }

    public boolean isEmpty () {
        return this.size == 0;
    }

    public void put (String s) {
        int index = hash(s);
        if (!buckets[index].contains(s)){
            buckets[index].add(s);
            this.size ++;

            int stringLength = length(s);
            if (stringLength > this.longest) this.longest = stringLength;
        }
    }

    public String get (String s) {
        int index = hash(s);
        return buckets[index].contains(s) ? s : null;
    }

    public int longestLength () {
        return this.longest;
    }


    // -----------------------------------------------------------
    // Helper Methods
    // -----------------------------------------------------------

    private int hash (String s) {
        int hash = 0;
        for (char c : s.toCharArray()){
            hash += (int) c;
        }
        return hash % BUCKET_COUNT;
    }

    //Appartently the length of a String is the nubmer of words it contins
    private int length (String s) {
        int spaces = 0;
        for (char c : s.toCharArray()){
            if (c == ' ') spaces ++;
        }
        return spaces + 1;
    }

}
