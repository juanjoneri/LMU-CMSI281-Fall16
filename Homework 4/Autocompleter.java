import java.util.ArrayList;

public class Autocompleter implements AutocompleterInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------
    TTNode root;
    private ArrayList<String> terms;

    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------
    Autocompleter () {
        root = null;
        terms = null;
    }


    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------

    public boolean isEmpty () {
        return root == null;
    }

    public void addTerm (String toAdd) {
        root = addTerm(normalizeTerm(toAdd), root, 0);
    }

    public boolean hasTerm (String query) {
        return hasTerm(root, normalizeTerm(query), 0);
    }

    public String getSuggestedTerm (String query) {
        if ( !hasPrefix(root, normalizeTerm(query), 0) ) { return null; }
        return getSuggestedTerm(root, normalizeTerm(query), 0);
    }

    public ArrayList<String> getSortedTerms () {
        terms = new ArrayList<>();
        getSortedTerms(root, new String());
        return terms;
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

    private TTNode addTerm (String toAdd, TTNode node, int index) {
        char[] word = toAdd.toCharArray();

        if (node == null){ node = new TTNode(word[index], false); }

        int comp = compareChars(word[index], node.letter);
        if (comp < 0){
            node.left = addTerm(toAdd, node.left, index);
        } else if (comp > 0) {
            node.right = addTerm(toAdd, node.right, index);
        } else {
            if (index < word.length - 1) {
                node.mid = addTerm(toAdd, node.mid, index + 1);
            } else {
                node.wordEnd = true;
            }
        }
        return node;
    }

    private boolean hasTerm (TTNode node, String query, int index) {
        char[] word = query.toCharArray();

        if (node == null){ return false; }

        int comp = compareChars(word[index], node.letter);
        if (comp < 0){
            return hasTerm(node.left, query, index);
        } else if (comp > 0) {
            return hasTerm(node.right, query, index);
        } else {
            if (index + 1 == word.length) {
                return node.wordEnd;
            } else {
                return hasTerm(node.mid, query, index + 1);
            }
        }
    }

    private boolean hasPrefix (TTNode node, String query, int index) {
        //Same as hasTerm but does not require the last char to be a word end
        char[] word = query.toCharArray();

        if (node == null){ return false; }

        int comp = compareChars(word[index], node.letter);
        if (comp < 0){
            return hasPrefix(node.left, query, index);
        } else if (comp > 0) {
            return hasPrefix(node.right, query, index);
        } else {
            if (index + 1 == word.length) {
                return true;
            } else {
                return hasPrefix(node.mid, query, index + 1);
            }
        }
    }

    private String getSuggestedTerm (TTNode node, String query, int index) {
        //Same as hasPrefix but once it has found it, returns the first complete word it finds corresponding to that prefix
        char[] word = query.toCharArray();

        if (node == null){ return null; }

        int comp = compareChars(word[index], node.letter);
        if (comp < 0){
            return getSuggestedTerm(node.left, query, index);
        } else if (comp > 0) {
            return getSuggestedTerm(node.right, query, index);
        } else {
            if (index + 1 == word.length) {
                return trimLast(query) + getEnding(node);
            } else {
                return getSuggestedTerm(node.mid, query, index + 1);
            }
        }
    }

    private String getEnding (TTNode node) {
        // Will find the first complete end of word leading from that node
        if (node == null) { return null; }
        String ending = String.valueOf(node.letter);
        return node.wordEnd ? ending : ending + getEnding (node.mid);
    }

    private void getSortedTerms (TTNode node, String prefix) {
        if (node == null){ return; }

        getSortedTerms(node.left, prefix);

        prefix += node.letter;
        if (node.wordEnd) { terms.add(prefix); }

        getSortedTerms(node.mid, prefix);
        prefix = trimLast(prefix);

        getSortedTerms(node.right, prefix);
    }

    private String trimLast (String toTrim) {
        //will remove the last char of a string
        return toTrim.substring(0, toTrim.length() - 1);
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
