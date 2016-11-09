public class Test {

    private static int attempts;
    private static int score;
    private static int sumAttempts;
    private static int sumScore;

    public static void main(String[] args) {

        // # BinaryTreeNode test
        test_SameTree();
        test_DoubleTree();

        // # BinaryHeap test
        //test_GetSortedElements();

        //show results
        sumUp();

    }

    //Required functions for the testing ---------------------------------------------
    private static void displaySuccessIfTrue(boolean test, String label) {
        attempts ++;
        score += test ? 1 : 0;

        label = (test ? "   [+] " : " =>[-] ") + label;
        System.out.println(label);
    }

    private static void displayUnimplementedMethodFailure() {
        System.out.println("    => " + ": (NYI)");
    }

    public static void testingMethod(String testName){
        attempts = 0;
        score = 0;
        System.out.println("\n ## Testing " + testName + ": \n");
    }

    public static void showResults(){
        sumAttempts += attempts;
        sumScore += score;

        String label = "-------------------------------------(" + String.valueOf(score) + "/" + String.valueOf(attempts) + ")";
        System.out.println(label);
    }

    public static void sumUp(){
        int percent =  (int) ( ( (float)sumScore / (float)sumAttempts ) * 100F);
        String grade = "F ";
        if(percent > 95){ grade = "A+";}
        else if(percent > 93){grade = "A ";}
        else if(percent > 90){grade = "A-";}
        else if(percent > 87){grade = "B+";}
        else if(percent > 83){grade = "B ";}
        else if(percent > 80){grade = "B-";}
        else if(percent > 77){grade = "C+";}
        else if(percent > 73){grade = "C ";}
        else if(percent > 70){grade = "C-";}
        else if(percent > 67){grade = "D+";}
        else if(percent > 63){grade = "D ";}
        else if(percent > 60){grade = "D-";}

        String label =" __________________\n";
        label += "/\\                 \\\n";
        label += "\\_| Test Restults: |          /)\n";
        label += "  |                |         //\n  |";
        label += " -->(" + String.valueOf(sumScore) + "/" + String.valueOf(sumAttempts) + ")<- |        (/\n";
        label += "  | ----- -------  |       _/\n";
        label += "  | ------.     "+ grade +" |      ) (\n";
        label += "  |  ______________|_    /INK\\\n";
        label += "  \\_/_______________/    \\___/";

        System.out.println(label);
    }

    //Actual Tests ----------------------------------------------------------------
    private static void test_SameTree() {
        testingMethod("SameTree method");

        try{
            BinaryTreeNode tree = new BinaryTreeNode("1");
            BinaryTreeNode tree2 = new BinaryTreeNode("1");
            boolean test = tree.sameTree(tree, tree2);
            displaySuccessIfTrue(test, "1 element Tree");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BinaryTreeNode tree = new BinaryTreeNode("1");
            tree.add("2", "L");
            tree.add("3", "R");
            BinaryTreeNode tree2 = new BinaryTreeNode("1");
            tree2.add("2", "L");
            tree2.add("3", "R");
            boolean test = tree.sameTree(tree, tree2);
            displaySuccessIfTrue(test, "3 element Tree");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BinaryTreeNode tree = new BinaryTreeNode("1");
            BinaryTreeNode tree2 = new BinaryTreeNode("2");
            boolean test = !tree.sameTree(tree, tree2);
            displaySuccessIfTrue(test, "1 element != Tree");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BinaryTreeNode tree = new BinaryTreeNode("1");
            tree.add("2", "L");
            tree.add("3", "R");
            BinaryTreeNode tree2 = new BinaryTreeNode("1");
            tree2.add("2", "L");
            tree2.add("Jamon", "R");
            boolean test = !tree.sameTree(tree, tree2);
            displaySuccessIfTrue(test, "3 element != Tree");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BinaryTreeNode tree = new BinaryTreeNode("1");
            tree.add("2", "L");
            tree.add("3", "R");
            tree.getChild("L").add("4", "L");
            tree.getChild("L").add("5", "R");
            BinaryTreeNode tree2 = new BinaryTreeNode("1");
            tree2.add("2", "L");
            tree2.add("3", "R");
            tree2.getChild("L").add("4", "L");
            tree2.getChild("L").add("5", "R");
            boolean test = tree.sameTree(tree, tree2);
            displaySuccessIfTrue(test, "5 element Tree");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BinaryTreeNode tree = new BinaryTreeNode("1");
            tree.add("2", "L");
            tree.add("3", "R");
            tree.getChild("L").add("4", "L");
            tree.getChild("L").add("5", "R");
            BinaryTreeNode tree2 = new BinaryTreeNode("1");
            tree2.add("2", "L");
            tree2.add("3", "R");
            tree2.getChild("R").add("4", "L");
            tree2.getChild("R").add("5", "R");
            boolean test = !tree.sameTree(tree, tree2);
            displaySuccessIfTrue(test, "= elements != structure");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        showResults();
    }

    private static void test_DoubleTree() {
        testingMethod("DoubleTree method");

        try{
            BinaryTreeNode tree = new BinaryTreeNode("1");
            tree.doubleTree();
            BinaryTreeNode tree2 = new BinaryTreeNode("1");
            tree2.add("1", "L");
            boolean test = tree.sameTree(tree, tree2);
            displaySuccessIfTrue(test, "1 element Tree");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BinaryTreeNode tree = new BinaryTreeNode("1");
            tree.add("2", "L");
            tree.add("3", "R");
            tree.doubleTree();
            BinaryTreeNode tree2 = new BinaryTreeNode("1");
            tree2.add("1", "L");
            tree2.getChild("L").add("2", "L");
            tree2.getChild("L").getChild("L").add("2", "L");
            tree2.add("3", "R");
            tree2.getChild("R").add("3", "L");

            boolean test = tree.sameTree(tree, tree2);
            displaySuccessIfTrue(test, "3 element Tree");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BinaryTreeNode tree = new BinaryTreeNode("1");
            tree.add("2", "L");
            tree.add("3", "R");
            tree.getChild("R").add("4", "R");
            tree.doubleTree();
            BinaryTreeNode tree2 = new BinaryTreeNode("1");
            tree2.add("1", "L");
            tree2.getChild("L").add("2", "L");
            tree2.getChild("L").getChild("L").add("2", "L");
            tree2.add("3", "R");
            tree2.getChild("R").add("3", "L");
            tree2.getChild("R").add("4", "R");
            tree2.getChild("R").getChild("R").add("4", "L");

            boolean test = tree.sameTree(tree, tree2);
            displaySuccessIfTrue(test, "5 element Tree");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BinaryTreeNode tree = new BinaryTreeNode("1");
            tree.doubleTree();
            BinaryTreeNode tree2 = new BinaryTreeNode("1");
            tree2.add("1", "R");
            boolean test = !tree.sameTree(tree, tree2);
            displaySuccessIfTrue(test, "1 element != Tree");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            BinaryTreeNode tree = new BinaryTreeNode("1");
            tree.add("2", "L");
            tree.add("3", "R");
            tree.doubleTree();
            BinaryTreeNode tree2 = new BinaryTreeNode("1");
            tree2.add("1", "L");
            tree2.getChild("L").add("3", "L");
            tree2.getChild("L").getChild("L").add("2", "L");
            tree2.add("3", "R");
            tree2.getChild("R").add("3", "L");

            boolean test = !tree.sameTree(tree, tree2);
            displaySuccessIfTrue(test, "3 element != Tree");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

            showResults();
    }

}
