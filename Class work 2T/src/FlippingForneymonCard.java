/**
 * Design a class called FlippingForneymonCard that adheres to the following specifications.
 *
 * FlippingForneymonCards are ForneymonCards (share properties and methods) except that they may be placed face-up or face-down. You shall implement this property as a boolean field that is false when face-up and true when face-down.
 * FlippingForneymonCards can be constructed as either face-up or face-down as long as a name and type of Forneymon are specified.
 * FlippingForneymonCards can be default constructed (no arguments given), at which point its name should be "MissingNu", its type "Burnymon", and face-down.
 * Each FlippingForneymonCard can flip(); which makes it face-up if it was previously face-down, and vice versa. Return true if the card is face-down, and false otherwise.
 * Define a method int match (FlippingForneymonCard other); that returns:
 * 2 if either it or the other FlippingForneymonCard are face-down.
 * 1 if both are face-up and share the same name *and* Forneymon type.
 * 0 if both are face-up and disagree on either the name *or* the Forneymon type.
 * A FlippingForneymonCard that is currently face-down will instead return the string "?: ?" when toString() is called on it.
 * A FlippingForneymonCard that is currently face-up will use the default toString() behavior.
 */
public class FlippingForneymonCard extends ForneymonCard{

    //Fields

    private boolean isFaceDown;

    //Constructors

    FlippingForneymonCard(String name, String type, boolean isFaceDown){
        super(name, type);
        this.isFaceDown = isFaceDown;
    }

    FlippingForneymonCard(){
        super();
        this.isFaceDown = true;
    }

    //Methods

    @Override
    public String toString() {
        return isFaceDown ? "?: ?" : super.toString();
    }

    public void flip(){
        isFaceDown = !isFaceDown;
    }

    public int match(FlippingForneymonCard other){
        if(isFaceDown || other.isFaceDown()){
            return 2;
        } else if(super.getName().equals(other.getName()) && super.getType().equals(other.getType())){
            return 1;
        } else {
            return 0;
        }
    }

    //Getters and Setters

    public boolean isFaceDown(){ return isFaceDown; }

}
