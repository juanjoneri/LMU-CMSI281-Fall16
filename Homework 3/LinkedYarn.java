package linked_yarn.solution;

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
        return size == 0;
    }
    
    public int getSize () {
        return size;
    }
    
    public int getUniqueSize () {
        return uniqueSize;
    }
    
    public void insert (String toAdd) {
        insertOccurrences(toAdd, 1);
    }
    
    public int remove (String toRemove) {
        return removeOccurrences(toRemove, 1);
    }
    
    public void removeAll (String toNuke) {
        Node toFind = find(toNuke);
        
        // Case: no such String toNuke
        if (toFind == null) {
            return;
        }
        
        removeOccurrences(toNuke, toFind.count);
    }
    
    public int count (String toCount) {
        Node toFind = find(toCount);
        return (toFind == null) ? 0 : toFind.count;
    }
    
    public boolean contains (String toCheck) {
        return find(toCheck) != null;
    }
    
    public String getMostCommon () {
        String mostCommon = null;
        int highestCount = -1;
        for (Node curr = head; curr != null; curr = curr.next) {
            if (curr.count > highestCount) {
                highestCount = curr.count;
                mostCommon = curr.text;
            }
        }
        return mostCommon;
    }
    
    public LinkedYarn clone () {
        LinkedYarn dolly = new LinkedYarn();
        for (Node n = head; n != null; n = n.next) {
            dolly.prependNode(new Node(n.text, n.count));
            dolly.size += n.count;
            dolly.uniqueSize++;
        }
        return dolly;
    }
    
    public void swap (LinkedYarn other) {
        Node tempHead = head;
        int tempSize = size,
            tempUniqueSize = uniqueSize;
        
        head = other.head;
        size = other.size;
        uniqueSize = other.uniqueSize;
        
        other.head = tempHead;
        other.size = tempSize;
        other.uniqueSize = tempUniqueSize;
        modCount++;
        other.modCount++;
    }
    
    public LinkedYarn.Iterator getIterator () {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return new LinkedYarn.Iterator(this);
    }
    
    
    // -----------------------------------------------------------
    // Static methods
    // -----------------------------------------------------------
    
    public static LinkedYarn knit (LinkedYarn y1, LinkedYarn y2) {
        LinkedYarn result = y1.clone();
        for (Node n = y2.head; n != null; n = n.next) {
            result.insertOccurrences(n.text, n.count);
        }
        return result;
    }
    
    public static LinkedYarn tear (LinkedYarn y1, LinkedYarn y2) {
        LinkedYarn result = y1.clone();
        for (Node n = y2.head; n != null; n = n.next) {
            result.removeOccurrences(n.text, n.count);
        }
        return result;
    }
    
    public static boolean sameYarn (LinkedYarn y1, LinkedYarn y2) {
        return tear(y1, y2).isEmpty() && tear(y2, y1).isEmpty();
    }
    
    
    // -----------------------------------------------------------
    // Private helper methods
    // -----------------------------------------------------------

    private Node find (String toFind) {
        Node curr = head;
        for (; curr != null; curr = curr.next) {
            if (curr.text.equals(toFind)) {
                break;
            }
        }
        return curr;
    }
    
    private void prependNode (Node n) {
        Node oldHead = head;
        head = n;
        if (oldHead != null) {
            head.next = oldHead;
            oldHead.prev = head;
        }
    }
    
    private void deleteNode (Node n) {
        if (n == head) {
            head = n.next;
            if (head != null) {
                head.prev = null;
            }
        }
        if (n.prev != null) {
            n.prev.next = n.next;
        }
        if (n.next != null) {
            n.next.prev = n.prev;
        }
    }
    
    private boolean insertOccurrences (String text, int count) {
        Node found = find(text);
        
        // Case: new string, so add new Node
        if (found == null) {
            prependNode(new Node(text, count));
            uniqueSize++;
            
        // Case: existing string, so update count
        } else {
            found.count += count;
        }
        size += count;
        modCount++;
        
        return true;
    }
    
    private int removeOccurrences (String text, int count) {
        Node found = find(text);
        
        // Case: no such string toRemove
        if (found == null) {
            return 0;
        }
        
        int newCount = found.count - count;
        modCount++;
        
        // Case: last instance to remove
        if (newCount <= 0) {
            deleteNode(found);
            size -= found.count;
            uniqueSize--;
            return 0;
        
        // Case: more than 1 entry left
        } else {
            found.count = newCount;
            size -= count;
            return newCount;
        }
    }
    
    
    // -----------------------------------------------------------
    // Inner Classes
    // -----------------------------------------------------------
    
    public class Iterator implements LinkedYarnIteratorInterface {
        LinkedYarn owner;
        Node current;
        int onCount, itModCount;
        
        Iterator (LinkedYarn y) {
            owner = y;
            itModCount = y.modCount;
            current = y.head;
            onCount = 0;
        }
        
        public boolean hasNext () {
            if (current.count > onCount+1) {return true;}
            return isValid() && current.next != null;
        }
        
        public boolean hasPrev () {
            if (onCount-1 >= 0) {return true;}
            return isValid() && current.prev != null;
        }
        
        public boolean isValid () {
            return owner.modCount == itModCount;
        }
        
        public String getString () {
            verifyIntegrity();
            return current.text;
        }

        public void next () {
            verifyIntegrity();
            onCount++;
            if (onCount >= current.count) {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                current = current.next;
                onCount = 0;
            }
        }
        
        public void prev () {
            verifyIntegrity();
            onCount--;
            if (onCount < 0) {
                if (!hasPrev()) {
                    throw new NoSuchElementException();
                }
                current = current.prev;
                onCount = current.count - 1;
            }
        }
        
        public void replaceAll (String toReplaceWith) {
            verifyIntegrity();
            Node existing = find(toReplaceWith);
            
            // Case: toReplaceWith was same as the current
            // Node's text, so do nothing
            if (current == existing) {return;}
            
            // Case: toReplaceWith already exists elsewhere in
            // the LinkedYarn, so merge that Node into this one
            if (existing != null) {
                current.count += existing.count;
                deleteNode(existing);
                uniqueSize--;
            }
            
            // Case: toReplaceWith does not exist elsewhere in
            // the LinkedYarn, so just change the text on the
            // current Node
            current.text = toReplaceWith;
            itModCount++;
            owner.modCount++;
        }
        
        private void verifyIntegrity () {
            if (!isValid()) {
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
    }
    
}