public class Test {

    private static int attempts;
    private static int score;
    private static int sumAttempts;
    private static int sumScore;

    public static void main(String[] args) {

        // # BinaryTreeNode test
        test_ShapeConstructor();
        test_ShapeConstructor();
        test_ShapeConstructor();

        // # BinaryHeap test
        test_GeometryKitConstructor();
        test_ShapeConstructor();
        test_ShapeConstructor();

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
    private static void test_EstimateArea() {
        testingMethod("Shape Area Estimatior");

        try{
            Circle circle = new Circle(0, 0, 1);
            circle.throwMillionDarts(false);
            boolean test = (circle.estimateArea() >= 3.1) && (circle.estimateArea() <= 3.2) ;
            displaySuccessIfTrue(test, "Circle");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        try{
            Circle circle = new Circle(0, 0, 1);
            circle.throwMillionDarts(false);
            boolean test = (circle.estimateArea() >= 3.1) && (circle.estimateArea() <= 3.2) ;
            displaySuccessIfTrue(test, "Square");
        } catch (UnsupportedOperationException NYI){
            displayUnimplementedMethodFailure();
        }

        showResults();
    }

}
