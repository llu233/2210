public class Node {
	
	private String Key;
	private int Score;
	private int Level;
	private Node next;
	
	public Node(Record data) {
		this.Key = data.getKey();
		this.Score = data.getScore();
		this.Level = data.getLevel();
	}
	public String getKey() {
		return this.Key;
	}
	public int getScore() {
		return this.Score;
	}
	public int getLevel() {
		return this.Level;
	}
	public Node getNextNode() {
		return this.next;
	}
	public void setNextNode(Node data) {
		this.next = data;
	}
}
