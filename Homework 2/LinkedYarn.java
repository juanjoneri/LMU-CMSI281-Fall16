import java.util.NoSuchElementException;

public class LinkedYarn implements LinkedYarnInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
    private Node head;
    private int size, uniqueSize, modCount;


    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------
    LinkedYarn () {
        head = null;
        size = 0;
        uniqueSize = 0;
        modCount = 0;
    }


    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------
    public boolean isEmpty () {
        return head == null;
    }

    public int getSize () {
        return size;
    }

    public int getUniqueSize () {
        return uniqueSize;
    }

    public void insert (String toAdd) {

        if( this.contains(toAdd) ){
            find(toAdd).count ++;
        } else {
            Node toInsert = new Node(toAdd);
            toInsert.next = head;
            head.prev = toInsert;
            this.head = toInsert;
        }
    }

    public int remove (String toRemove) {

        if( this.contains(toRemove) ){
            Node nodeToRemove = find(toRemove);
            if( nodeToRemove.count > 1 ){
                nodeToRemove.count --;
                return nodeToRemove.count;
            } else {
                removeAll(toRemove);
                return 0;
            }

        } else {
            return 0;
        }
    }

    public void removeAll (String toNuke) {
        throw new UnsupportedOperationException();
    }

    public int count (String toCount) {
        throw new UnsupportedOperationException();
    }

    public boolean contains (String toCheck) {
        throw new UnsupportedOperationException();
    }

    public String getMostCommon () {
        throw new UnsupportedOperationException();
    }

    public LinkedYarn clone () {
        throw new UnsupportedOperationException();
    }

    public void swap (LinkedYarn other) {
        throw new UnsupportedOperationException();
    }

    public LinkedYarn.Iterator getIterator () {
        throw new UnsupportedOperationException();
    }

    private Node find(String text){
        //Returns the Node that contains that text
        //Or null if it could not find it
        if( this.contains(text) ){
            Iterator iterator = new Iterator(this);
            while( !iterator.getString().equals(text) && iterator.hasNext() ){
                iterator.next();
            }
            return iterator.getString().equals(text) ? iterator.current : null;
        } else {
            return null;
        }
    }
    // -----------------------------------------------------------
    // Static methods
    // -----------------------------------------------------------

    public static LinkedYarn knit (LinkedYarn y1, LinkedYarn y2) {
        throw new UnsupportedOperationException();
    }

    public static LinkedYarn tear (LinkedYarn y1, LinkedYarn y2) {
        throw new UnsupportedOperationException();
    }

    public static boolean sameYarn (LinkedYarn y1, LinkedYarn y2) {
        throw new UnsupportedOperationException();
    }


    // -----------------------------------------------------------
    // Private helper methods
    // -----------------------------------------------------------

    // You should add some methods here!


    // -----------------------------------------------------------
    // Inner Classes
    // -----------------------------------------------------------

    public class Iterator implements LinkedYarnIteratorInterface {
        LinkedYarn owner;
        Node current;
        int itModCount;

        Iterator (LinkedYarn y) {
            // TODO
        }

        public boolean hasNext () {
            throw new UnsupportedOperationException();
        }

        public boolean hasPrev () {
            throw new UnsupportedOperationException();
        }

        public boolean isValid () {
            throw new UnsupportedOperationException();
        }

        public String getString () {
            throw new UnsupportedOperationException();
        }

        public void next () {
            throw new UnsupportedOperationException();
        }

        public void prev () {
            throw new UnsupportedOperationException();
        }

        public void replaceAll (String toReplaceWith) {
            throw new UnsupportedOperationException();
        }

    }

    class Node {
        Node next, prev;
        String text;
        int count;

        Node (String t, int c) {
            text = t;
            count = c;
        }

        Node (String t) {
            text = t;
            count = 1;
        }
    }

}
