package yarn.solution;

public class Yarn implements YarnInterface {

    // Fields
    // ----------------------------------------------------------
    private Entry[] items;
    private int size;
    private int uniqueSize;
    private final int MAX_SIZE = 100;


    // Constructor
    // ----------------------------------------------------------
    Yarn () {
        items = new Entry[MAX_SIZE];
        size = 0;
        uniqueSize = 0;
    }


    // Methods
    // ----------------------------------------------------------
    public boolean isEmpty () {
        return size == 0;
    }

    public int getSize () {
        return size;
    }

    public int getUniqueSize () {
        return uniqueSize;
    }

    public boolean insert (String toAdd) {
        return insertOccurrences(toAdd, 1);
    }

    public int remove (String toRemove) {
        return removeOccurrences(toRemove, 1);
    }

    public void removeAll (String toNuke) {
        int index = find(toNuke);
        if (index != -1) {
            removeOccurrences(toNuke, items[index].count);
        }
    }

    public int count (String toCount) {
        int index = find(toCount);

        // Case: no such string toCount
        if (index == -1) {
            return 0;
        }

        // Case: return the number of instances
        return items[index].count;
    }

    public boolean contains (String toCheck) {
        return find(toCheck) != -1;
    }

    public String getNth (int n) {
        if (n >= size || n < 0) {
            throw new IllegalArgumentException();
        }

        int index = 0,
            i = 0;
        Entry currentEntry;
        do {
            currentEntry = items[i];
            index += currentEntry.count;
            i++;
        } while (index <= n);

        return currentEntry.text;
    }

    public String getMostCommon () {
        String mostCommon = null;
        int highestCount = -1;
        for (int i = 0; i < uniqueSize; i++) {
            Entry currentEntry = items[i];
            if (currentEntry.count > highestCount) {
                highestCount = currentEntry.count;
                mostCommon = currentEntry.text;
            }
        }
        return mostCommon;
    }

    public Yarn clone () {
        Yarn dolly = new Yarn();
        for (int i = 0; i < uniqueSize; i++) {
            dolly.items[i] = new Entry(items[i].text, items[i].count);
        }
        dolly.size = size;
        dolly.uniqueSize = uniqueSize;
        return dolly;
    }

    public void swap (Yarn other) {
        Entry[] tempItems = items;
        int tempSize = size,
            tempUniqueSize = uniqueSize;

        items = other.items;
        size = other.size;
        uniqueSize = other.uniqueSize;

        other.items = tempItems;
        other.size = tempSize;
        other.uniqueSize = tempUniqueSize;
    }


    // Static methods
    // ----------------------------------------------------------
    public static Yarn knit (Yarn y1, Yarn y2) {
        Yarn result = y1.clone();
        for (int i = 0; i < y2.uniqueSize; i++) {
            result.insertOccurrences(y2.items[i].text, y2.items[i].count);
        }
        return result;
    }

    public static Yarn tear (Yarn y1, Yarn y2) {
        Yarn result = y1.clone();
        for (int i = 0; i < y2.uniqueSize; i++) {
            result.removeOccurrences(y2.items[i].text, y2.items[i].count);
        }
        return result;
    }

    public static boolean sameYarn (Yarn y1, Yarn y2) {
        return tear(y1, y2).isEmpty() && tear(y2, y1).isEmpty();
    }


    // Private helper methods
    // ----------------------------------------------------------
    private int find (String s) {
        for (int i = 0; i < uniqueSize; i++) {
            if (items[i].text.equals(s)) {
                return i;
            }
        }
        return -1;
    }

    private void replaceFromBack (int index) {
        items[index] = items[uniqueSize-1];
        items[uniqueSize-1] = null;
    }

    private boolean insertOccurrences (String text, int count) {
        int index = find(text);
        // Case: new string, so add new Entry
        if (index == -1) {
            // Case: at capacity, so do not insert
            if (uniqueSize == MAX_SIZE) {
                return false;
            }
            items[uniqueSize] = new Entry(text, count);
            size += count;
            uniqueSize++;

        // Case: existing string, so update count
        } else {
            items[index].count += count;
            size += count;
        }

        return true;
    }

    private int removeOccurrences (String text, int count) {
        int index = find(text);

        // Case: no such string toRemove
        if (index == -1) {
            return 0;
        }

        Entry found = items[index];
        int newCount = found.count - count;

        // Case: last instance to remove
        if (newCount <= 0) {
            replaceFromBack(index);
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
}

class Entry {
    String text;
    int count;

    Entry (String s, int c) {
        text = s;
        count = c;
    }
}
