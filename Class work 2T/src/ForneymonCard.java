/**
 * Design a class called ForneymonCard that adheres to the following specifications.
 *
 * Every ForneymonCard has a name and a type of Forneymon (one of three options: "Burnymon", "Dampymon", "Leafymon").
 * When creating a new ForneymonCard object, we will populate the fields with its given name and its type of Forneymon.
 * If the card was constructed with a blank name, throw new IllegalArgumentException();
 * If the card was constructed with a type that is anything other than the three allowed, throw new IllegalArgumentException();
 * It's perfectly fine to define multiple constructors for a class, as long as those constructors do not share the same parameterizations.
 * ForneymonCards can *also* be default constructed (no arguments given), at which point its name should be "MissingNu" and its type "Burnymon".
 * When printed out (i.e., for toString()), a ForneymonCard shall return a String of the format: "Type: NameOfForneymon". E.g., a Burnymon named Burny would return: "Burnymon: Burny"
 */
public abstract class ForneymonCard {

    //Fields

    private String name;
    private String type;

    //Constructors

    ForneymonCard(String name, String type){

        if( name.isEmpty() ){
            throw new IllegalArgumentException();
        } else {
            this.name = name;
        }

        if( type.equals("Burnymon") || type.equals("Dampymon") || type.equals("Leafymon") ) {
            this.type = type;
        } else {
            throw new IllegalArgumentException();
        }

    }

    ForneymonCard(){
        this( "MissingNu", "Burnymon" );
    }

    //Methods

    @Override
    public String toString() {
        return type + ": " + name;
    }

    //Getters and Setters

    public String getName(){ return name; }
    public String getType(){ return type; }
}
