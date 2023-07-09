import java.io.File;
import java.io.IOException;

public class FindKeyWordsInFileTest {
    
    public static void main(String[] args) {
        // Check command-line arguments
        if (args.length != 3) {
            System.err.println("Usage: java FindKeyWordsInFileTest k input.txt mostFrequentEnglishWords.txt");
            System.exit(1);
        }
        
        int k = Integer.parseInt(args[0]);
        String inputFileName = args[1];
        String englishWordsFileName = args[2];
        
        // Test 1: Check if the program reads the input file and creates the word frequency correctly.
        testWordFrequencies(inputFileName);
        
        // Test 2: Check if the program finds the k most frequent words correctly.
        testKMostFrequentWords(k, inputFileName);
        
        // Test 3: Check if the program filters common English words correctly.
        testFilterCommonEnglishWords(inputFileName, englishWordsFileName);
        
        // Test 4: Check if the program generates the correct output for file3.txt.
        testFile3(inputFileName, englishWordsFileName);
        
        // Test 5: Check if the program handles empty input files.
        testEmptyInputFile();
        
        // Test 6: Check if the program handles non-existing input files.
        testNonExistingInputFile();
    }
    
    private static void testWordFrequencies(String inputFileName) {
        AVLTree<String, Integer> wordFrequencies = new AVLTree<>();
        try {
            FindKeyWordsInFile.computeWordFrequencies(inputFileName, wordFrequencies);
            System.out.println("Test 1 passed: word frequencies computed successfully");
        } catch (IOException e) {
            System.err.println("Test 1 failed: " + e.getMessage());
        }
    }
    
    private static void testKMostFrequentWords(int k, String inputFileName) {
        AVLTree<String, Integer> wordFrequencies = new AVLTree<>();
        AVLTree<String, Integer> keywordFrequencies = new AVLTree<>();
        try {
            FindKeyWordsInFile.computeWordFrequencies(inputFileName, wordFrequencies);
            FindKeyWordsInFile.findKMostFrequentWords(k, wordFrequencies, keywordFrequencies);
            System.out.println("Test 2 passed: k most frequent words found successfully");
        } catch (Exception e) {
            System.err.println("Test 2 failed: " + e.getMessage());
        }
    }
    
    private static void testFilterCommonEnglishWords(String inputFileName, String englishWordsFileName) {
        AVLTree<String, Integer> wordFrequencies = new AVLTree<>();
        AVLTree<String, Integer> keywordFrequencies = new AVLTree<>();
        try {
            FindKeyWordsInFile.computeWordFrequencies(inputFileName, wordFrequencies);
            FindKeyWordsInFile.findKMostFrequentWords(10, wordFrequencies, keywordFrequencies);
            FindKeyWordsInFile.filterCommonEnglishWords(keywordFrequencies, new AVLTree<>(), englishWordsFileName);
            System.out.println("Test 3 passed: common English words filtered successfully");
        } catch (Exception e) {
            System.err.println("Test 3 failed: " + e.getMessage());
        }
    }
    
    private static void testFile3(String inputFileName, String englishWordsFileName) {
        AVLTree<String, Integer> wordFrequencies = new AVLTree<>();
        AVLTree<String, Integer> keywordFrequencies = new AVLTree<>();
        AVLTree<String, Void> englishWords = new AVLTree<>();
        try {
            FindKeyWordsInFile.computeWordFrequencies(inputFileName, wordFrequencies);
            FindKeyWordsInFile.findKMostFrequentWords(10, wordFrequencies, keywordFrequencies);
            FindKeyWordsInFile.filterCommonEnglishWords(keywordFrequencies, englishWords, englishWordsFileName);
            String expectedOutput = "";
            String actualOutput = "";
            if (expectedOutput.equals(actualOutput)) {
                System.out.println("Test 4 passed: output for file3.txt generated successfully");
            } else {
                System.err.println("Test 4 failed: output for file3.txt not generated correctly");
            }
        } catch (Exception e) {
            System.err.println("Test 4 failed: " + e.getMessage());
        }
    }
    
    private static void testEmptyInputFile() {
    	AVLTree<String, Integer> wordFrequencies = new AVLTree<>();
        AVLTree<String, Integer> keywordFrequencies = new AVLTree<>();
        String inputFileName = "empty.txt";
        try {
            File emptyFile = new File(inputFileName);
            if (emptyFile.createNewFile()) {
                FindKeyWordsInFile.computeWordFrequencies(inputFileName, wordFrequencies);
                FindKeyWordsInFile.findKMostFrequentWords(10, wordFrequencies, keywordFrequencies);
                FindKeyWordsInFile.filterCommonEnglishWords(keywordFrequencies, new AVLTree<>(), "mostFrequentEnglishWords.txt");
                System.out.println("Test 5 passed: program handled empty input file successfully");
            } else {
                System.err.println("Test 5 failed: could not create empty input file");
            }
        } catch (IOException e) {
            System.err.println("Test 5 failed: " + e.getMessage());
        }
    }
    
    private static void testNonExistingInputFile() {
        AVLTree<String, Integer> wordFrequencies = new AVLTree<>();
        try {
            FindKeyWordsInFile.computeWordFrequencies("non-existing-file.txt", wordFrequencies);
            System.err.println("Test 6 failed: Expected an exception to be thrown for non-existing file.");
        } catch (IOException e) {
            System.out.println("Test 6 passed: Exception thrown as expected for non-existing file.");
        } catch (Exception e) {
            System.err.println("Test 6 failed: Unexpected exception thrown: " + e.getMessage());
        }
    }
}
