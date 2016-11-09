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
         this.left.add(this.clone(), "L");
     }

     public boolean sameTree (BinaryTreeNode n1, BinaryTreeNode n2) {
         throw new UnsupportedOperationException();
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

     // Returns a clone of the current node with same children
     @Override
     public BinaryTreeNode clone(){

        BinaryTreeNode cloneNode = new BinaryTreeNode( this.getString() );
        if( this.hasChild("L") ){
            cloneNode.add( this.getChild("L").clone(), "L");
        }
        if( this.hasChild("R") ){
            cloneNode.add( this.getChild("R").clone(), "R");
        }

        return cloneNode;
     }

 }
