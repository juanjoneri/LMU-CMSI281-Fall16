/*
 * Split a string of words and detect the number of unique ones
 */

import java.util.Scanner;
import java.util.HashSet;

public class WordSplit {

    public static void main(String[] args) {
        System.out.println("# Please provide the text in question");

        Scanner reader = new Scanner(System.in);
        String sentence = reader.nextLine();
        String[] words = sentence.split(" ");

        HashSet<String> uniqueWords = new HashSet<String>();
        HashSet<String> repeatedWords = new HashSet<String>();

        for(String word : words){
            if(uniqueWords.contains(word)){
                repeatedWords.add(word);
            } else {
                uniqueWords.add(word);
            }
        }

        for(String word : repeatedWords){
            uniqueWords.remove(word);
        }

        System.out.println("There are " + uniqueWords.size() + " unique words in that string");
    }
}
