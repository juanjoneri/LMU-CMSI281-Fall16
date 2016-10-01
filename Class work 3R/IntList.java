public class IntList {

    // Fields
    private int[] items;
    private int   size;
    private static final int START_SIZE = 8;

    // Constructor
    IntList () {
        items = new int[START_SIZE];
        size  = 0;
    }

    public int getAt(int index) {
        return items[index];
    }

    public void append(int toAdd) {
        insertAt(toAdd, size);
    }

    public void prepend (int toAdd) {
        insertAt(toAdd, 0);
    }

    public void insertAt(int toAdd, int index) {
        if(index <= size){
            checkAndGrow();
            shiftRight(index);
            items[index] = toAdd;
            size ++;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void removeAt(int index) {
        if(index < size){
            shiftLeft(index);
            size--;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public String toString(){
        String toOutput = "{ ";
        for(int i = 0; i < size; i ++){
            toOutput += items[i] + " ";
        }
        toOutput += "}";
        return toOutput;
    }

    public int getSize(){
        return size;
    }

    private void checkAndGrow () {

        if (size < items.length) {
            return;
        }

        int[] newItems = new int[items.length * 2];

        for (int i = 0; i < items.length; i++) {
            newItems[i] = items[i];
        }

        items = newItems;
    }

    private void shiftLeft (int index) {
        for (int i = index; i < size-1; i++) {
            items[i] = items[i+1];
        }
    }

    private void shiftRight (int index) {
        for (int i = size; i > index; i--) {
            items[i] = items[i-1];
        }
    }

}
