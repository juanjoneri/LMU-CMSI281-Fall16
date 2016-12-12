import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Sentinal implements SentinalInterface {

    // -----------------------------------------------------------
    // Fields
    // -----------------------------------------------------------

    private PhraseHash posHash, negHash;


    // -----------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------

    Sentinal (String posFile, String negFile) throws FileNotFoundException {
        this.posHash = new PhraseHash();
        loadSentimentFile(posFile, true);
        this.negHash = new PhraseHash();
        loadSentimentFile(negFile, false);
    }


    // -----------------------------------------------------------
    // Methods
    // -----------------------------------------------------------

    public void loadSentiment (String phrase, boolean positive) {
        if (positive) {
            this.posHash.put(phrase);
        } else {
            this.negHash.put(phrase);
        }
    }

    public void loadSentimentFile (String filename, boolean positive) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        if (isEmpty(scanner)) return;

        do {
            loadSentiment(scanner.nextLine(), positive);
        } while (scanner.hasNextLine());

        scanner.close();
    }

    public String sentinalyze (String filename) throws FileNotFoundException {
        //returns "positive", "negative" or "neutral"
        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        if (isEmpty(scanner)) return "neutral";
        int positive = 0;
        int negative = 0;

        do {
            String line = scanner.nextLine();
            String[] uniqueWords = wordsInLine(line);

            for (int i = 0; i < uniqueWords.length; i ++){
                String iWord = uniqueWords[i];
                if (posHash.get(iWord) != null){
                    positive ++;
                } else if (negHash.get(iWord) != null){
                    negative ++;
                }
                if (i < uniqueWords.length - 1) {
                    String iiWord = uniqueWords[i] + " " + uniqueWords[i + 1];
                    if (posHash.get(iiWord) != null){
                        positive ++;
                    } else if (negHash.get(iiWord) != null){
                        negative ++;
                    }
                }
            }

        } while (scanner.hasNextLine());

        switch ( Integer.signum(positive - negative) ){
            case 1: return "positive";
            case -1: return "negative";
            default: return "neutral";
        }
    }


    // -----------------------------------------------------------
    // Helper Methods
    // -----------------------------------------------------------

    //Returns wether the file in the scanner is empty
    private boolean isEmpty (Scanner scanner) {
        return !scanner.hasNextLine();
    }

    private String[] wordsInLine (String line){
        String[] words = new String[length(line)];
        String word = "";
        int i = 0; // from 0 to length(line)

        for (char c : line.toCharArray()){
            if (c == ' '){
                words[i] = word;
                word = "";
                i ++;
            }
            else {
                word += c;
            }
        }
        words[i] = word; // the last word does not have a space, and in the last line it has no \n
        return words;
    }

    //Apparently the length of a String is the number of words it contains
    private int length (String s) {
        int spaces = 0;
        for (char c : s.toCharArray()){
            if (c == ' ') spaces ++;
        }
        return spaces + 1;
    }
}
