import java.util.ArrayList;

public class Autocompleter implements AutocompleterInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
    TTNode root;


    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------
    Autocompleter () {
        root = null;
    }


    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------

    public boolean isEmpty () {
        return root == null;
    }

    public void addTerm (String toAdd) {
        int length = toAdd.length();
        TTNode current = root;

        for(int i = 0; i < length - 1 ; i++) {
            char c = toAdd.charAt(i);
            current = addChar( c, current, false );
        }
        addChar( toAdd.charAt(length - 1), current, true );
    }

    public boolean hasTerm (String query) {
        throw new UnsupportedOperationException();
    }

    public String getSuggestedTerm (String query) {
        throw new UnsupportedOperationException();
    }

    public ArrayList<String> getSortedTerms () {
        throw new UnsupportedOperationException();
    }


    // -----------------------------------------------------------
    // Helper Methods
    // -----------------------------------------------------------

    private String normalizeTerm (String s) {
        // Edge case handling: empty Strings illegal
        if (s == null || s.equals("")) {
            throw new IllegalArgumentException();
        }
        return s.trim().toLowerCase();
    }

    /*
     * Returns:
     *   int less than 0 if c1 is alphabetically less than c2
     *   0 if c1 is equal to c2
     *   int greater than 0 if c1 is alphabetically greater than c2
     */
    private int compareChars (char c1, char c2) {
        return Character.toLowerCase(c1) - Character.toLowerCase(c2);
    }

    // [!] Add your own helper methods here!

    private TTNode addChar (char c, TTNode node, boolean wordEnd) {
        // Adds the char to wherever it corresponds in the node and returns the new node so another char can be inserted to it
        if ( node == null ){
            //System.out.println("add " + c);
            node = new TTNode(c, wordEnd);
            return node.mid;
        } else {
            int comp = compareChars ( c, node.letter );
            if ( comp == 0 ){
                return node.mid;
            } else if ( comp > 0 ) {
                // c is alphabetically greater than node.char
                return addChar( c, node.right, wordEnd );
            } else {
                // c is alphabetically less than node.char
                return addChar( c, node.left, wordEnd );
            }
        }
    }

    // -----------------------------------------------------------
    // TTNode Internal Storage
    // -----------------------------------------------------------

    /*
     * Internal storage of autocompleter search terms
     * as represented using a Ternary Tree with TTNodes
     */
    private class TTNode {

        boolean wordEnd;
        char letter;
        TTNode left, mid, right;

        TTNode (char c, boolean w) {
            letter  = c;
            wordEnd = w;
            left    = null;
            mid     = null;
            right   = null;
        }

    }

}