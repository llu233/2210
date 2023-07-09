import java.util.*;
public class Dictionary {
	
	private Node[] table;
	private int size;
	
	public Dictionary(int size) {
		this.table = new Node[size];
		this.size = size;
		for(int i = 0; i < size; i++) {
			table[i] = null;
		}
	}
	public int put(Record rec) throws DuplicatedKeyException {
		int index = hash(rec.getKey());
		Node headNode = table[index];
		while (headNode != null) {
			if(headNode.getKey().equals(rec.getKey())) {
				throw new DuplicatedKeyException("Key is already stored in the dictionary.");
			}
			headNode = headNode.getNextNode();
		}
		headNode = table[index];
		Node newNode = new Node(rec);
		if (headNode == null) {
			table[index] = newNode;
			return 0;
		}
		else {
			newNode.setNextNode(headNode);
			table[index] = newNode;
			return 1;
		}
	}
	public void remove(String key) throws InexistentKeyException {
		int index = hash(key);
		Node headNode = table[index];
		Node prevNode = null;
		while (headNode != null) {
			if(headNode.getKey().equals(key)) {
				break;
			}
			prevNode = headNode;
			headNode = headNode.getNextNode();
		}
		if(headNode == null) {
			throw new InexistentKeyException("Key is not in the hash table."); //does not store any Record object with given key value
		}
		if(prevNode != null) {
			prevNode.setNextNode(headNode.getNextNode());
		}
		else {
			table[index] = headNode.getNextNode();
		}
	}
	public Record get(String key) {
		int index = hash(key);
		Node headNode = table[index];
		while(headNode != null) {
			if(headNode.getKey().equals(key)) {
				return headNode.getScore();
			}
			headNode = headNode.getNextNode();
		}
		return null; //no Record object stored in the hash table contains the given key value
	}
	public int numRecords() {
		return hash.size();
	}
	private int hash(String key) {
        int hash = 0;
        int prime = 11;
        
        for (int i = 0; i < key.length(); i++ ) {
            int c = key.charAt(i);
            hash += prime * hash + c; 
    }
        return (hash % size);
    }
}
