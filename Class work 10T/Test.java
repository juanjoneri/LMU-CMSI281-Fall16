public class Test{

    public static void main(String[] args){
        BinaryTreeNode root = new BinaryTreeNode("Root");
        System.out.println(root);

        root.add("Left", "L");
        root.add("Right", "R");
        System.out.println(root);

        root.doubleTree();
        System.out.println(root);

    }

}
