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
            size ++;
        } else {
            Node toInsert = new Node(toAdd);
            toInsert.next = head;
            toInsert.prev = null;
            head.prev = toInsert;
            this.head = toInsert;
            size ++;
            uniqueSize ++;
        }
    }

    public int remove (String toRemove) {

        if( this.contains(toRemove) ){
            Node nodeToRemove = find(toRemove);
            if( nodeToRemove.count > 1 ){
                nodeToRemove.count --;
                size --;
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

        if( this.contains(toNuke) ){
            Node nodeToNuke = find(toNuke);
            nodeToNuke.prev.next = nodeToNuke.next;
            nodeToNuke.next.prev = nodeToNuke.prev;
            size --;
            uniqueSize --;
        }
    }

    public int count (String toCount) {

        if( this.contains(toCount) ){
            return find(toCount).count;
        }
        return 0;
    }

    public boolean contains (String toCheck) {

        Iterator iterator = new Iterator(this);
        while( !iterator.getString().equals(toCheck) && iterator.hasNext() ){
            iterator.next();
        }

        return iterator.current != null && iterator.getString().equals(toCheck) ? true : false;
    }

    public String getMostCommon () {

        if(size != 0){
            Node mostCommon = head;
            Iterator iterator = new Iterator(this);
            while( iterator.hasNext() ){
                iterator.next();
                mostCommon = iterator.current.count > mostCommon.count ? iterator.current : mostCommon;
            }
            return mostCommon.text;
        } else {
            return null;
        }
    }

    public LinkedYarn clone () {

        LinkedYarn newLinkedYarn = new LinkedYarn();
        newLinkedYarn.head = this.head;

        Iterator iterator = new Iterator(this);
        while( iterator.hasNext() ){
            iterator.next();
            for( int i = 0; i < iterator.current.count; i ++ ){
                newLinkedYarn.insert(iterator.current.text);
            }
        }
        return newLinkedYarn;
    }

    public void swap (LinkedYarn other) {
        LinkedYarn otherClone = other.clone();

        other.size = this.size;
        other.uniqueSize = this.uniqueSize;
        other.head = this.head;
        other.modCount = this.modCount + 1;

        this.size = otherClone.size;
        this.uniqueSize = otherClone.uniqueSize;
        this.head = otherClone.head;
        this.modCount = otherClone.modCount + 1;
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
            return current.next != null;
        }

        public boolean hasPrev () {
            return current.prev != null;
        }

        public boolean isValid () {
            return itModCount == owner.modCount;
        }

        public String getString () {
            return current.text;
        }

        public void next () {

            if( isValid() ) {
                if( hasNext() ){
                    current = current.next;
                } else {
                    throw new NoSuchElementException();
                }
            } else {
                throw new IllegalStateException();
            }
        }

        public void prev () {

            if( isValid() ) {
                if( hasPrev() ){
                    current = current.prev;
                } else {
                    throw new NoSuchElementException();
                }
            } else {
                throw new IllegalStateException();
            }
        }

        public void replaceAll (String toReplaceWith) {

            if( isValid() ) {
                current.text = toReplaceWith;
            } else {
                throw new IllegalStateException();
            }
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
