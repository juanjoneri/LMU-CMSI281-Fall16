public class Test{

    public static void main(String[] args){
        BinaryHeap root = new BinaryHeap();
        for (int i = 1; i < 20; i ++){
            root.insert(i);
        }
        System.out.println(root.getSortedElements());

    }

}
