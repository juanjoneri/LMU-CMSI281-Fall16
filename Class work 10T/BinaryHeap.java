import java.util.ArrayList;

class BinaryHeap {

    ArrayList<Integer> heap;

    BinaryHeap () {
        heap = new ArrayList<Integer>();
    }

    /*
     * Continues to bubble values up the tree until we
     * find a node that is greater than it
     */
    public void bubbleUp (int index) {
        if (index == 0) {return;}

        int parent = getParent(index);

        if (heap.get(parent) < heap.get(index)) {
            Integer temp = heap.get(index);
            heap.set(index, heap.get(parent));
            heap.set(parent, temp);
            bubbleUp(parent);
        }

    }

    public void insert (Integer toInsert) {
        heap.add(toInsert);
        bubbleUp(heap.size() - 1);
    }

    // Traversal helpers
    public int getParent (int index) {
        return (index - 1) / 2;
    }

    public int getChild (int index, char child) {
        int result = (index * 2) + 1;
        if (child == 'R') {
            result++;
        }
        return result;
    }

    public void print () {
        for (int i = 0; i < heap.size(); i++) {
            System.out.println(heap.get(i));
        }
    }

    public ArrayList<Integer> getSortedElements () {
        //to avoid "unsafe operations" i could override the behaviour of clone (i.e. write a helper method for cloning) and thus I would not have to cast the Object to ArrayList<> however time is precious for this assigment.

        ArrayList<Integer> sortedHeap = (ArrayList<Integer>) this.heap.clone();
        if(sortedHeap.size() == 1) { return sortedHeap; }

        for(int i = sortedHeap.size() - 1; i > 1; i --){
            swap(sortedHeap, 0, i);
            sortedHeap = heapify(sortedHeap, 0, i-1);
        }
        //with this structure we miss one swap at the end
        swap(sortedHeap, 0, 1);
        return sortedHeap;
    }

    private void swap(ArrayList<Integer> aList, int i, int j){
        Integer temp = aList.get(i);
        aList.set( i, aList.get(j) );
        aList.set( j, temp);
    }

    private boolean inHeap(ArrayList<Integer> heap, int i){
        return i >= 0 && i < heap.size();
    }

    private ArrayList<Integer> heapify(ArrayList<Integer> heap, int i){

        int l = getChild(i, 'L');
        int r = getChild(i, 'R');

        if( inHeap(heap, r) ){
            if( heap.get(i) > heap.get(l) && heap.get(i) > heap.get(r)){ return heap; }
            if ( heap.get(l) > heap.get(r) ){
                swap(heap, l, i);
                heapify(heap, l);
            } else {
                swap(heap, r, i);
                heapify(heap, r);
            }
        } else if( inHeap(heap, l) ){
            if( heap.get(i) < heap.get(l) ){
                swap(heap, l, i);
            }
        }
        return heap;
    }

    private ArrayList<Integer> heapify( ArrayList<Integer> heap, int i, int lock ){
        //heapifies up to the lock
        ArrayList<Integer> lockedHeap = new ArrayList<>();
        for( int j = 0; j <= lock; j++ ){
            lockedHeap.add(heap.get(j));
        }
        lockedHeap = heapify(lockedHeap, i);
        for( int j = lock + 1; j < heap.size(); j++ ){
            lockedHeap.add(heap.get(j));
        }
        return lockedHeap;
    }

}
