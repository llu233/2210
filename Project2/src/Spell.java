// Name: Laurence Lu
// Program Name: Spell-Checker
// Program Function: The first file name is the dictionary with correctly spelled words. The second file is the text to be spell-checked. 
// The program check all words in fileToCheck.txt. 
// Nothing needs to be done for words that are correctly spelled. And suggest possible correct spellings for words not in the dictionary file
// Using methods of Substitution, Omission, Insertion, and Reversal
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.ArrayList;


public class Spell {
	static Hashtable<String, Boolean> dictionary = new Hashtable<>();

    Spell(String s, String k){
        // Load dictionary words from file into Hashtable
        try {
            Scanner scanner = new Scanner(new File(s));
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine().trim().toLowerCase();
                dictionary.put(word,true);
            }
        } catch (FileNotFoundException e) { // print error if not found
            System.out.println("Dictionary file not found.");
        }
        // Load words in fileToCheck.txt
        try {
            Scanner scanner = new Scanner(new File(k));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split("\\s+");
                for (String word : words) {
                    checkSpelling(word);
                }
            }
        } catch (FileNotFoundException e) { // print error if not found
            System.out.println("File to check not found.");
        }
    }
    
    public static void main(String[] args) {
    	if (args.length != 2) { // check if enough arguments
            System.out.println("Usage: <dictionary_file> <file_to_check>");
            return;
        }
    	// init an object of type Spell
    	Spell spell = new Spell(args[0], args[1]);
    }

    // this function check if the dictionary is loaded or not
    public static Hashtable<String, Boolean> getDictionary(){
    	if (dictionary != null) {
    	    System.out.println("Dictionary loaded successfully.");
    	} 
    	else {
    	    System.out.println("Dictionary could not be loaded.");
    	}
		return dictionary;
    }
    // This function takes a String word as an argument to check if the word exists in the dictionary. 
    // If the word exists, it will print it with a message "Incorrect Spelling:" to the console.
    // Else it will call the suggestCorrections function to provide the correct word from the words given in the dictionary file.
    public static boolean checkSpelling(String word){
    	if (dictionary.containsKey(word.toLowerCase())) {
            System.out.println("Incorrect Spelling: " + word);
            return true;
        } else {
            suggestCorrections(word);
            return false;
        }
    }
    // This function takes a String input word as argument.
    // It starts by printing the message word: Incorrect Spelling to the console.
    // The function also uses four different methods (correctSpellingWithSubstitution,
    // correctSpellingWithOmission, correctSpellingWithInsertion, correctSpellingWithReversal)
    // to generate possible corrected spellings for the input word.
    public static boolean suggestCorrections(String word) {
    	System.out.println(word + ": Incorrect Spelling");

        String correctedWord = correctSpellingWithSubstitution(word); //call correctSpellingWithSubstitution function
        if (checkSpelling(correctedWord)) { //check spelling again
            System.out.println(word + "=>" + correctedWord);
            return true;
        }

        correctedWord = correctSpellingWithOmission(word); //call correctSpellingWithOmission function
        if (checkSpelling(correctedWord)) { //check spelling again
            System.out.println(word + "=>" + correctedWord);
            return true;
        }

        ArrayList<String> correctedWords = correctSpellingWithInsertion(word); //call correctSpellingWithInsertion function
        for (String wordlist : correctedWords) { 
            if (checkSpelling(wordlist)) { //check spelling again
                System.out.println(word + "=>" + wordlist);
                return true;
            }
        }

        correctedWord = correctSpellingWithReversal(word); //call correctSpellingWithReversal function
        if (checkSpelling(correctedWord)) { //check spelling again
            System.out.println(word + "=>" + correctedWord);
            return true;
        }
		return false;
    }

    // This function takes in a string word and tries to correct the spelling by substituting letters and 
    // check if the resulting new word is in the dictionary.
    static String correctSpellingWithSubstitution(String word) {
    	for (int i = 0; i < word.length(); i++) {
            char[] chars = word.toCharArray();
            for (char letter = 'a'; letter <= 'z'; letter++) {
                if (letter != chars[i]) {
                    chars[i] = letter;
                    String newWord = new String(chars);
                    if (dictionary.contains(newWord)) {
                        dictionary.put(newWord,true);
                        return newWord;
                    }
                }
            }
        }
    	return word;
    }
    // This function tries to omit (in turn, one by one) a single character in the misspelled word 
    // and check if the resulting new word is in the dictionary.
    static String correctSpellingWithOmission(String word) {
    	for (int i = 0; i < word.length(); i++) {
            String newWord = word.substring(0, i) + word.substring(i+1);
            if (dictionary.contains(newWord)) {
                dictionary.put(newWord,true);
                return newWord;
            }
    	}
    	return word;
    }
    // This function tries to insert a letter in the misspelled word
    // and check if the resulting new word is in the dictionary and stores in array list.
    static ArrayList<String> correctSpellingWithInsertion(String word) {
    	ArrayList<String> correctedWords = new ArrayList<String>();
    	for (int i = 0; i <= word.length(); i++) {
            for (char letter = 'a'; letter <= 'z'; letter++) {
                String newWord = word.substring(0, i) + letter + word.substring(i);
                if (dictionary.contains(newWord)) {
                	correctedWords.add(newWord);
                }
            }
    	}
    	return correctedWords;
    }
    // This function tries swapping every pair of adjacent characters 
    // and check if the resulting new word is in the dictionary.
    static String correctSpellingWithReversal(String word) {
    	for (int i = 0; i < word.length()-1; i++) {
            char[] chars = word.toCharArray();
            char tmp = chars[i];
            chars[i] = chars[i+1];
            chars[i+1] = tmp;
            String newWord = new String(chars);
            if (dictionary.contains(newWord)) {
                dictionary.put(newWord,true);
                return newWord;
            	}
            }
    	return word;
    	}
    }


    
    
          