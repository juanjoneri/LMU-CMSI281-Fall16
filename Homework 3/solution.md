# Homework 3
#### *Juan Jose Neri*

## Problem 1
> For each of the following methods, provide the worst case Big-O asymptotic runtime complexities as a function of: **s = the size of the Yarn** (i.e., the number of individual String occurrences), OR **u = the uniqueSize of the Yarn** (i.e., the number of distinct Strings). Show your work.
> 1. removeAll()
> 2. getNth()

### removeAll()

    private int find (String s) {
        for (int i = 0; i < uniqueSize; i++) {                     // ┒
            if (items[i].text.equals(s)) {                         // │ C1  │ #u
                return i;                                          // ┚
            }
        }
        return -1;                                                 // │ C2
    }

*T(s, u) = (C1)u + C2*

    private void replaceFromBack (int index) {
        items[index] = items[uniqueSize-1];                        // ┒ C3
        items[uniqueSize-1] = null;                                // ┚
    }

*T(s, u) = C3*

    private int removeOccurrences (String text, int count) {
        int index = find(text);                                    // │ (C1)u + C2

        if (index == -1) { return 0; }                             // │ C4

        Entry found = items[index];                                // ┒ C5
        int newCount = found.count - count;                        // ┚

        // Case: last instance to remove
        if (newCount <= 0) {                                       // ┒
            replaceFromBack(index);                                // │ C3 + C6
            size -= found.count;                                   // │
            uniqueSize--;                                          // │
            return 0;                                              // ┚

        // Case: more than 1 entry left
        } else {                                                   // ┒
            found.count = newCount;                                // │ C7
            size -= count;                                         // │
            return newCount;                                       // ┚
        }
    }

*T(s, u) = (C1)u + (C2 + C3 + C4 + C5 + C6 + C7)*

    public void removeAll (String toNuke) {
        int index = find(toNuke);                                  // │ (C1)u + C2
        if (index != -1) {                                         // │ C8
            removeOccurrences(toNuke, items[index].count);         // ┒ (C1)u + (C2 + C3 + C4 + C5 + C6 + C7)
        }                                                          // ┚
    }
*T(s, u) = (C1)u + C2 + C8 + [ (C1)u + (C2 + C3 + C4 + C5 + C6 + C7) ]*
#### Answer
 **O(u)**

### getNth()

    public String getNth (int n) {
        if (n >= size ││ n < 0) {                                  // ┒
            throw new IllegalArgumentException();                  // │ C1
        }                                                          // ┚

        int index = 0,                                             // ┒
            i = 0;                                                 // │ C2
        Entry currentEntry;                                        // ┚

        do {
            currentEntry = items[i];                               // ┒
            index += currentEntry.count;                           // │ C3  // ┒
            i++;                                                   // ┚     // │ #s
        } while (index <= n);                                               // ┚

        return currentEntry.text;                                  // │ C4
    }

*T(s, u) = (C3)s + (C1 + C2 + C4)*
#### Answer
 **O(s)**

 ## Problem 2
 >  For each of the following methods, provide the worst case Big-O asymptotic runtime complexities as a function of: **s = the size of the Yarn** (i.e., the number of individual String occurrences), OR **u = the uniqueSize of the LinkedYarn** (i.e., the number of distinct Strings). Show your work.
 > 1. swap()
 > 2. insert()

### swap()

    public void swap (LinkedYarn other) {
        Node tempHead = head;                                     // ┒
        int tempSize = size,                                      // │
            tempUniqueSize = uniqueSize;                          // │
                                                                  // │
        head = other.head;                                        // │
        size = other.size;                                        // │
        uniqueSize = other.uniqueSize;                            // │ C1
                                                                  // │
        other.head = tempHead;                                    // │
        other.size = tempSize;                                    // │
        other.uniqueSize = tempUniqueSize;                        // │
        modCount++;                                               // │
        other.modCount++;                                         // ┚
    }

*T(s, u) = C1*
#### Answer
 **O(1)**

### insert()

    private Node find (String toFind) {
        Node curr = head;                                         // │ C1
        for (; curr != null; curr = curr.next) {                  // │ C2  ┒
            if (curr.text.equals(toFind)) {                       // ┒     │ #u
                break;                                            // │ C3  ┚
            }                                                     // ┚
        }
        return curr;                                              // │ C4
    }

*T(s, u) = (C2 + C3)u + C1 + C4*

    private void prependNode (Node n) {
        Node oldHead = head;                                      // │ C5
        head = n;                                                 // │ C6
        if (oldHead != null) {                                    // ┒
            head.next = oldHead;                                  // │ C7
            oldHead.prev = head;                                  // │
        }                                                         // ┚
    }

*T(s, u) = C5 + C6 + C7*

    private boolean insertOccurrences (String text, int count) {
        Node found = find(text);                                  // │ (C2 + C3)u + C1 + C4

        // Case: new string, so add new Node
        if (found == null) {                                      // │ C8            ┒
            prependNode(new Node(text, count));                   // │ C5 + C6 + C7  │ C5 + C6 + C7 + C8 + C9
            uniqueSize++;                                         // │ C9            ┚

        // Case: existing string, so update count
        } else {
            found.count += count;                                 // │ C10
        }
        size += count;                                            // ┒ C11
        modCount++;                                               // ┚

        return true;                                              // │ C12
    }

*T(s, u) = (C2 + C3)u + C1 + C4 + C5 + C6 + C7 + C8 + C9 + C10 + C11 + C12*

    public void insert (String toAdd) {
        insertOccurrences(toAdd, 1);                              // │ (C2 + C3)u + C1 + C4 + C5 + C6 + C7 + C8 + C9 + C10 + C11 + C12
    }

*T(s, u) = (C2 + C3)u + C1 + C4 + C5 + C6 + C7 + C8 + C9 + C10 + C11 + C12*
#### Answer
 **O(u)**

 ## Problem 3
 >  For each of the following methods, provide the worst case Big-O asymptotic runtime complexities as a function of: **s1, s2 = the size of the LinkedYarn** (i.e., the number of individual String occurrences in y1 and y2, respectively), OR **u1, u2 = the uniqueSize of the LinkedYarn** (i.e., the number of distinct Strings in y1 and y2, respectively). Show your work.
 > 1. knit()
 > 2. commonThreads()
 > 3. betterCommonThreads()

### knit()

    private void prependNode (Node n) { }

*See problem 2 for solution*
*T(s, u)  = C5 + C6 + C7*

    private boolean insertOccurrences (String text, int count) { }

*See problem 2 for solution, including dependencies*
*T(s, u)  = (C2 + C3)u + C1 + C4 + C5 + C6 + C7 + C8 + C9 + C10 + C11 + C12*

    public LinkedYarn clone () {
        LinkedYarn dolly = new LinkedYarn();                      // │ C13
        for (Node n = head; n != null; n = n.next) {              // │ C14           ┒
            dolly.prependNode(new Node(n.text, n.count));         // │ C5 + C6 + C7  │ #u
            dolly.size += n.count;                                // ┒ C15           ┚
            dolly.uniqueSize++;                                   // ┚
        }
        return dolly;
    }

*T(s, u) = (C5 + C6 + C7 + C14 + C15)u + C13*

    public static LinkedYarn knit (LinkedYarn y1, LinkedYarn y2) {
        LinkedYarn result = y1.clone();                           // │ (C5 + C6 + C7 + C14 + C15)u1 + C13
        for (Node n = y2.head; n != null; n = n.next) {           // │ C16                                                               ┒ #u2
            result.insertOccurrences(n.text, n.count);            // │ (C2 + C3)u1 + C1 + C4 + C5 + C6 + C7 + C8 + C9 + C10 + C11 + C12  ┚
        }
        return result;                                            // │ C17
    }

*T(s1, s2, u1, u2) = (C5 + C6 + C7 + C14 + C15)u1 + C13 + [ (C2 + C3)u1 + C1 + C4 + C5 + C6 + C7 + C8 + C9 + C10 + C11 + C12 + C16 ]u2 + C17*<br/>
*T(s1, s2, u1, u2) = (C2 + C3)u1u2 + (C5 + C6 + C7 + C14 + C15)u1 + (C1 + C4 + C5 + C6 + C7 + C8 + C9 + C10 + C11 + C12 + C16)u + C13 + C17*
#### Answer
 **O(u1*u2)**
