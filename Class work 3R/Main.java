public class Main{

    public static void main(String[] args) {
        IntList list = new IntList();
        System.out.println(list);

        for(int i = 0; i < 10; i ++){
            list.append(i);
        }
        System.out.println(list);

        for(int i = 0; i < 10; i ++){
            list.prepend(i);
        }
        System.out.println(list);

        list.removeAt(2);
        System.out.println(list);
        list.removeAt(0);
        System.out.println(list);
        list.removeAt(list.getSize()-1);
        System.out.println(list);

        list.insertAt(11, 2);
        System.out.println(list);
        list.insertAt(11, 0);
        System.out.println(list);
        list.insertAt(11, list.getSize()-1);
        System.out.println(list);
        list.insertAt(11, list.getSize());
        System.out.println(list);
        list.insertAt(11, list.getSize()+1);
        System.out.println(list);

    }


}
