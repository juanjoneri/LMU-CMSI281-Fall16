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
            if(head != null) { head.prev = toInsert; }
            this.head = toInsert;
            size ++;
            uniqueSize ++;
        }

        modCount ++;
    }

    public int remove (String toRemove) {

        if( this.contains(toRemove) ){
            modCount ++;
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
            size -= nodeToNuke.count;
            uniqueSize --;
            modCount ++;
            if(nodeToNuke.prev == null) {
                if(nodeToNuke.next == null) {
                    head = null;
                } else {
                    head = nodeToNuke.next;
                    nodeToNuke.next.prev = null;
                }
            } else {
                if(nodeToNuke.next == null) {
                    nodeToNuke.prev.next = null;
                } else {
                    nodeToNuke.prev.next = nodeToNuke.next;
                    nodeToNuke.next.prev = nodeToNuke.prev;
                }
            }

        }
    }

    public int count (String toCount) {

        if( this.contains(toCount) ){
            return find(toCount).count;
        }
        return 0;
    }

    public boolean contains (String toCheck) {

        if (head != null) {
            Iterator iterator = getIterator();
            while( !iterator.getString().equals(toCheck) && iterator.hasNext() ){
                iterator.next();
            }
            return iterator.current != null && iterator.getString().equals(toCheck);
        }
        return false;
    }

    public String getMostCommon () {

        if(size != 0){
            Node mostCommon = head;
            Iterator iterator = getIterator();
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
        if( this.head != null){
            newLinkedYarn.insertNode(this.head);
        }

        Iterator iterator = getIterator();
        while( iterator.hasNext() ){
            iterator.next();
            newLinkedYarn.insertNode(iterator.current);
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
        return new Iterator(this);
    }

    // -----------------------------------------------------------
    // Static methods
    // -----------------------------------------------------------

    public static LinkedYarn knit (LinkedYarn y1, LinkedYarn y2) {

        LinkedYarn knittedYarn = y1.clone();
        Iterator iterator = y2.getIterator();

        knittedYarn.insertNode(y2.head);
        while ( iterator.hasNext() ) {
            iterator.next();
            knittedYarn.insertNode(iterator.current);
        }
        return knittedYarn;
    }

    public static LinkedYarn tear (LinkedYarn y1, LinkedYarn y2) {

        LinkedYarn tearedYarn = y1.clone();
        Iterator iterator = y2.getIterator();

        tearedYarn.removeNode(y2.head);
        while ( iterator.hasNext() ) {
            iterator.next();
            tearedYarn.removeNode(iterator.current);
        }
        return tearedYarn;
    }

    public static boolean sameYarn (LinkedYarn y1, LinkedYarn y2) {

        if(y1.head != null && y2.head != null){
            Iterator iterator = y2.getIterator();
            boolean isSameYarn = y1.count(y2.head.text) == y2.head.count;
            while ( iterator.hasNext() ) {
                iterator.next();
                isSameYarn = !( !isSameYarn || y1.count(iterator.current.text) != iterator.current.count );
            }
            return isSameYarn;
        }
        return true;
    }


    // -----------------------------------------------------------
    // Private helper methods
    // -----------------------------------------------------------

    // You should add some methods here!
    private Node find(String text){
        //Returns the Node that contains that text
        //Or null if it could not find it
        if( this.contains(text) ){
            Iterator iterator = getIterator();
            while( !iterator.getString().equals(text) && iterator.hasNext() ){
                iterator.next();
            }
            return iterator.getString().equals(text) ? iterator.current : null;
        } else {
            return null;
        }
    }

    private void insertNode(Node toAdd){
        //Will add all ocurences of the string of that node to the LinkedYarn
        for( int i = 0; i < toAdd.count; i ++ ){
            this.insert(toAdd.text);
        }
    }

    private void removeNode(Node toRemove){
        //Will remove all ocurences of the string of that node from the LinkedYarn
        for( int i = 0; i < toRemove.count; i ++ ){
            this.remove(toRemove.text);
        }
    }

    // -----------------------------------------------------------
    // Inner Classes
    // -----------------------------------------------------------

    public class Iterator implements LinkedYarnIteratorInterface {
        LinkedYarn owner;
        Node current;
        //index designates the position inside the node (0 being 1st occurrence)
        int itModCount, index;

        Iterator (LinkedYarn y) {
            owner = y;
            current = y.head;
            itModCount = y.modCount;
        }

        public boolean hasNext () {
            return index < current.count || current.next != null;
        }

        public boolean hasPrev () {
            return index > 0  || current.prev != null;
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
                    if(index < current.count) {
                        index ++;
                    } else {
                        current = current.next;
                        index = 0;
                    }
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
                    if (index > 0) {
                        index --;
                    }
                    else{
                        current = current.prev;
                        index = 0;
                    }
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
