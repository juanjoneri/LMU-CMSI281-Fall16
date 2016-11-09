public class Test{

    public static void main(String[] args){
        BinaryHeap root = new BinaryHeap();
        for (int i = 0; i < 10; i ++){
            root.insert(i);
        }
        root.print();
        System.out.println(root.getSortedElements());

    }

}
