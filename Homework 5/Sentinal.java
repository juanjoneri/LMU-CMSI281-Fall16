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
        throw new UnsupportedOperationException();
    }


    // -----------------------------------------------------------
    // Helper Methods
    // -----------------------------------------------------------

    //Returns wether the file in the scanner is empty
    private boolean isEmpty (Scanner scanner) {
        return !scanner.hasNextLine();
    }

}
