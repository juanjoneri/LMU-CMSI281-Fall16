// -----------------------------------------------------------
// PROBLEM 1:
// -----------------------------------------------------------
// [PART I]

import java.util.Scanner;
import java.util.HashSet;

public class UniqueWords {

    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a sentence.");

        String[] words = input.nextLine().split(" ");

        HashSet<String> uniqueWords = new HashSet<>();
        HashSet<String> repeatedWords = new HashSet<>();

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

        int count = uniqueWords.size();
        System.out.println("There are " + count + " unique words in that sentence.");
    }

}

// [PART II]
// Asymptotic Runtime Complexity of OLD UniqueWords.java (the solution):  O(???)
// Asymptotic Runtime Complexity of NEW UniqueWords.java (the above):     O(n)

// -----------------------------------------------------------
// PROBLEM 2:
// -----------------------------------------------------------
// T_1(n) = \Theta(???)
// T_2(n) = \Theta(???)
