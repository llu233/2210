import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.io.IOException;	

public class FindKeyWordsInFile {
	public static void computeWordFrequencies(String inputFileName, AVLTree<String, Integer> wordFrequencies) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))){
        String line;
        while ((line = reader.readLine()) != null) {
        	String[] words = line.split("\\s+");
            for (String word : words) {
            	word = word.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
            	if (word.length() > 0) {
                    Integer frequency = wordFrequencies.get(word);
                    if (frequency == null) { // word not in tree, set to 1
                        wordFrequencies.put(word, 1);
                    }
                    wordFrequencies.put(word, frequency + 1); // word in tree, increase frequency count by 1
                    }
                }
            }
        }catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
	
	public static void findKMostFrequentWords(int k, AVLTree<String, Integer> wordFrequencies, AVLTree<String, Integer> keywordFrequencies) {
	    // Create max-heap priority queue with a custom comparator 
	    PriorityQueue<Node> maxHeap = new PriorityQueue<>(Comparator.comparing(Node::getValue).reversed());
	    AVLTree.inOrderTraversal(root, maxHeap);  // AVL tree in-order traversal
	    // Find k most frequent words from the priority queue and add to keywordFrequencies AVL tree
	    for (int i = 0; i < k; i++) {
	        Node node = maxHeap.poll();
	        if (node == null)
	        	break; // If maxHeap is empty, break loop
	        keywordFrequencies.put(node.getKey(), node.getValue()); //put k most frequent words and their frequencies in keywordFrequencies AVLTree
	    }
	}
    
    public static void filterCommonEnglishWords(AVLTree<String, Integer> keywordFrequencies, AVLTree<String, Void> englishWords, String englishWordsFileName) {
        BufferedReader englishWordsFileReader = new BufferedReader(new FileReader(englishWordsFileName));
        String englishWord;
        while ((englishWord = englishWordsFileReader.readLine()) != null) {
            englishWords.put(englishWord, null); // Load common English words from file into englishWords AVL tree
        }
        englishWordsFileReader.close();
        for (Node<String, Integer> node : keywordFrequencies.inOrderTraversal()) {
            if (!englishWords.contains(node.getKey())) { // Filter out common English words from keywordFrequencies
                System.out.println(node.getKey() + " " + node.getValue());
            }
        }
    }
	
    public static void main(String[] args) {
    	// Check command-line arguments
        if (args.length != 3) {
            System.err.println("Usage: java FindKeyWordsInFile k file.txt MostFrequentEnglishWords.txt");
            System.exit(1);
        }

        int k = Integer.parseInt(args[0]);
        String inputFileName = args[1];
        String englishWordsFileName = args[2];
        
        AVLTree<String, Integer> wordFrequencies = new AVLTree<>();
        AVLTree<String, Void> englishWords = new AVLTree<>();
        AVLTree<String, Integer> keywordFrequencies = new AVLTree<>();
        
        try {
            //Part 1
            // function name => computeWordFrequencies 	
        	computeWordFrequencies(inputFileName, wordFrequencies);     
        	System.out.println("Word frequencies:");
            wordFrequencies.inOrderTraversal();
        	//Part 2
            // function name => findKMostFrequentWords
        	findKMostFrequentWords(k, wordFrequencies, keywordFrequencies);
            //Part 3
            // function name => filterCommonEnglishWords
        	filterCommonEnglishWords(keywordFrequencies, englishWords, englishWordsFileName);
                 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}