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
            if(!uniqueWords.contains(word)){
                uniqueWords.add(word);
            } else {
                repeatedWords.add(word);
            }
        }

        // We still have one copy for each of the repeated Words
        for(String word : repeatedWords){
            uniqueWords.remove(word);
        }

        int count = uniqueWords.size();
        System.out.println("There are " + count + " unique words in that sentence.");
    }

}

// [PART II]
// Asymptotic Runtime Complexity of OLD UniqueWords.java (the solution):  O(n^2)
// Asymptotic Runtime Complexity of NEW UniqueWords.java (the above):     O(n)

// -----------------------------------------------------------
// PROBLEM 2:
// -----------------------------------------------------------
/*
 * Recurrence #1:
 * T_1(n) = 2 * T(n / 3) + n
 * a = 2; b = 3; f = n;
 * T_1(n) = \Theta(n) by case 3 (REC dominant)
/*
/*
 * Recurrence #2:
 * T_2(n) = 4 * T(n / 2) + n^2
 * a = 4; b = 2; f = n^2;
 * T_2(n) = \Theta(n^2 * log(n)) by case 2 (NEITHER dominant)
/*
