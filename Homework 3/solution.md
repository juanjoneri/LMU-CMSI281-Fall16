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
 **O(n)**

### getNth()

    public String getNth (int n) {
        if (n >= size || n < 0) {                                  // ┒
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
 **O(n)**

 ## Problem 2
 >  For each of the following methods, provide the worst case Big-O asymptotic runtime complexities as a function of: **s = the size of the Yarn** (i.e., the number of individual String occurrences), OR **u = the uniqueSize of the LinkedYarn** (i.e., the number of distinct Strings). Show your work.
 > 1. swap()
 > 2. insert()

### swap()

    public void swap (LinkedYarn other) {
        Node tempHead = head;                                     // ┒
        int tempSize = size,                                      // |
            tempUniqueSize = uniqueSize;                          // |
                                                                  // |
        head = other.head;                                        // |
        size = other.size;                                        // |
        uniqueSize = other.uniqueSize;                            // | C1
                                                                  // |
        other.head = tempHead;                                    // |
        other.size = tempSize;                                    // |
        other.uniqueSize = tempUniqueSize;                        // |
        modCount++;                                               // |
        other.modCount++;                                         // ┚
    }

*T(s, u) = C1*
#### Answer
 **O(1)**

### insert()

    private Node find (String toFind) {
        Node curr = head;                                         // | C1
        for (; curr != null; curr = curr.next) {                  // | C2  ┒
            if (curr.text.equals(toFind)) {                       // ┒     | #u
                break;                                            // | C3  ┚
            }                                                     // ┚
        }
        return curr;                                              // | C4
    }

*T(s, u) = (C2 + C3)u + C1 + C4*

    private void prependNode (Node n) {
        Node oldHead = head;                                      // | C5
        head = n;                                                 // | C6
        if (oldHead != null) {                                    // ┒
            head.next = oldHead;                                  // | C7
            oldHead.prev = head;                                  // |
        }                                                         // ┚
    }

*T(s, u) = C5 + C6 + C7*

    private boolean insertOccurrences (String text, int count) {
        Node found = find(text);                                  // | (C2 + C3)u + C1 + C4

        // Case: new string, so add new Node
        if (found == null) {                                      // | C8            ┒
            prependNode(new Node(text, count));                   // | C5 + C6 + C7  | C5 + C6 + C7 + C8 + C9
            uniqueSize++;                                         // | C9            ┚

        // Case: existing string, so update count
        } else {
            found.count += count;                                 // | C10
        }
        size += count;                                            // ┒ C11
        modCount++;                                               // ┚

        return true;                                              // | C12
    }

*T(s, u) = (C2 + C3)u + C1 + C4 + C5 + C6 + C7 + C8 + C9 + C10 + C11 + C12*

    public void insert (String toAdd) {
        insertOccurrences(toAdd, 1);           // | (C2 + C3)u + C1 + C4 + C5 + C6 + C7 + C8 + C9 + C10 + C11 + C12
    }

*T(s, u) = (C2 + C3)u + C1 + C4 + C5 + C6 + C7 + C8 + C9 + C10 + C11 + C12*
#### Answer
 **O(n)**
