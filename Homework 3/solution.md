# Homework 3
#### *Juan Jose Neri*

## Problem 1
> For each of the following methods, provide the worst case Big-O asymptotic runtime complexities as a function of: **s = the size of the Yarn** (i.e., the number of individual String occurrences), OR **u = the uniqueSize of the Yarn** (i.e., the number of distinct Strings). Show your work.
> 1. removeAll()
> 2. getNth()

### removeAll()

    private int find (String s) {
        for (int i = 0; i < uniqueSize; i++) {                     // ┒
            if (items[i].text.equals(s)) {                         // | C1  | #u
                return i;                                          // ┚
            }
        }
        return -1;                                                 // | C2  ┚
    }

*T(s, u) = (C1)u + C2*

    private void replaceFromBack (int index) {
        items[index] = items[uniqueSize-1];                        // ┒ C3
        items[uniqueSize-1] = null;                                // ┚
    }

*T(s, u) = C3*

    private int removeOccurrences (String text, int count) {
        int index = find(text);                                    // | (C1)u + C2

        if (index == -1) { return 0; }                             // | C4

        Entry found = items[index];                                // ┒ C5
        int newCount = found.count - count;                        // ┚

        // Case: last instance to remove
        if (newCount <= 0) {                                       // ┒
            replaceFromBack(index);                                // | C3 + C6
            size -= found.count;                                   // |
            uniqueSize--;                                          // |
            return 0;                                              // ┚

        // Case: more than 1 entry left
        } else {                                                   // ┒
            found.count = newCount;                                // | C7
            size -= count;                                         // |
            return newCount;                                       // ┚
        }
    }

*T(s, u) = (C1)u + (C2 + C3 + C4 + C5 + C6 + C7)*

    public void removeAll (String toNuke) {
        int index = find(toNuke);                                  // | (C1)u + C2
        if (index != -1) {                                         // | C8
            removeOccurrences(toNuke, items[index].count);         // ┒ (C1)u + (C2 + C3 + C4 + C5 + C6 + C7)
        }                                                          // ┚
    }
*T(s, u) = (C1)u + C2 + C8 + [ (C1)u + (C2 + C3 + C4 + C5 + C6 + C7) ]*
#### Answer
 **O(n)**

### getNth()

    public String getNth (int n) {
        if (n >= size || n < 0) {                                  // ┒
            throw new IllegalArgumentException();                  // | C1
        }                                                          // ┚

        int index = 0,                                             // ┒
            i = 0;                                                 // | C2
        Entry currentEntry;                                        // ┚

        do {
            currentEntry = items[i];                               // ┒
            index += currentEntry.count;                           // | C3  // ┒
            i++;                                                   // ┚     // | #s
        } while (index <= n);                                               // ┚

        return currentEntry.text;                                  // | C4
    }

*T(s, u) = (C3)u + (C1 + C2 + C4
#### Answer
 **O(n)**
