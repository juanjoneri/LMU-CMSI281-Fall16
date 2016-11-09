public class BinaryTreeNode {

     private String data;
     private BinaryTreeNode left, right;

     BinaryTreeNode (String s) {
         data = s;
         left = null;
         right = null;
     }

     // Value to insert 's' and position relative to Node 'L' or 'R'
     public void add (String s, String child) {
         if (child.equals("L")) {
             left = new BinaryTreeNode(s);
         } else if (child.equals("R")) {
             right = new BinaryTreeNode(s);
         } else {
             throw new IllegalArgumentException();
         }
     }

     //Get L or R child of Node
     public BinaryTreeNode getChild (String child) {
         return (child.equals("L")) ? left : right;
     }

     public String getString () {
         return data;
     }

     public void doubleTree () {
        if( this.hasChild("L")){
            this.left.doubleTree();
        }
        if( this.hasChild("R")){
            this.right.doubleTree();
        }
        this.doubleNode();
     }

    public boolean sameTree (BinaryTreeNode n1, BinaryTreeNode n2) {
        if(n1 == null && n2 == null){
            return true;
        } else if(n1 == null || n2 == null){
            return false;
        }
        // Very readable code!
        return n1.getString().equals( n2.getString() ) ?  sameTree( n1.getChild("L"), n2.getChild("L") ) && sameTree( n1.getChild("R"), n2.getChild("R") ) : false;

    }

    public String toString(){

        String name = this.getString();
        name += " ";
        if( this.hasChild("L") ){
            name += this.getChild("L").toString();
        }
        if( this.hasChild("R") ){
            name += this.getChild("R").toString();
        }
        return name;
    }

    private boolean hasChild(String child){
        return this.getChild( child ) != null;
    }

     //similar to add but with an actual node
     private void add( BinaryTreeNode node, String child ){
         if (child.equals("L")) {
             left = node;
         } else if (child.equals("R")) {
             right = node;
         } else {
             throw new IllegalArgumentException();
         }
     }

     private void doubleNode(){
        BinaryTreeNode toAdd = new BinaryTreeNode(this.getString());
        toAdd.add(this.getChild("L"), "L");
        this.left = toAdd;
     }

 }
