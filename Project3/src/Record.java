public class Record {
	
	private String Key;
	private int Score;
	private int Level;
	
	public Record(String key, int score, int level) {
		this.Key = key;
		this.Score = score;
		this.Level = level;
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

}
