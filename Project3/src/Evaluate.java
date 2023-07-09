import java.util.Arrays;

public class Evaluate {
	int size;
	char[][] gameBoard;
	
	public Evaluate (int size, int tilesToWin, int maxLevels) {
		this.size = size;
		this.gameBoard = new char[size][size];
		for(char[] row: gameBoard) {
			Arrays.fill(row, 'e'); //every position of board is empty
		}
	}
	public Dictionary createDictionary() {
		return new Dictionary(577); //return size 577(prime) empty dictionary
	}
	public Record repeatedState(Dictionary dict) {
		String code = Arrays.deepToString(gameBoard).replace("[", "").replace("]", "").replace(",", "").replace(" ", "");
		return dict.get(code);
	}
	public void insertState(Dictionary dict, int score, int level) {
		String code = Arrays.deepToString(gameBoard).replace("[", "").replace("]", "").replace(",", "").replace(" ", "");
		Record data = new Record(code, score, level);
		try {
			dict.put(data);
		}
		catch (DuplicatedKeyException e) { //cannot store two records with same key attribute
		}
	}
	public void storePlay(int row, int col, char symbol) {
		gameBoard[row][col] = symbol;
	}
	public boolean squareIsEmpty (int row, int col) {
		return gameBoard[row][col] == 'e';
	}
	public boolean tileOfComputer (int row, int col) {
		return gameBoard[row][col] == 'c';
	}
	public boolean tileOfHuman (int row, int col) {
		return gameBoard[row][col] == 'h';
	}
	public boolean wins (char symbol) {
		for(int row = 0; row < size; row++) { //first check rows for any win
			int count = 0;
			for(int column = 0; column < size; column++) {
				if(gameBoard[row][column] == symbol) 
					count++;
			}
			if(count == size) 
				return true;
		}
		for(int column = 0; column < size; column++) { //check columns for any win
			int count = 0;
			for(int row = 0; row < size; row++) {
				if(gameBoard[row][column] == symbol) 
					count++;
			}
			if(count == size) 
				return true;
		}
		int downdiagonal = 0;
		for(int row = 0, column = 0; row < size; row++, column++) { //check top left to bottom right diagonal 
			if(gameBoard[row][column] == symbol) 
				downdiagonal++;
			if(downdiagonal == size) 
				return true;
		}
		int updiagonal = 0;
		for(int row = size - 1, column = 0; column < size; row--, column++) { //check bottom left to top right diagonal
			if(gameBoard[row][column] == symbol) 
				updiagonal++;
			if(updiagonal == size) 
				return true;
		}
		return false;
	}
	public boolean isDraw(char symbol, int empty_positions) {
		int fullspaces = 0;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(gameBoard[i][j] != 'e') fullspaces++;
			}
		}
		if(empty_positions == 0 && fullspaces == (size*size)) 
			return true;
		else if(empty_positions > 0 && fullspaces == (size*size) - empty_positions) {
			for(int i = 0; i < size; i++) {
				for(int j = 0; j < size; j++) {
					if(gameBoard[i][j] == 'e') {
						if(gameBoard[i+1][j] == symbol) 
							return false;
						if(gameBoard[i-1][j] == symbol) 
							return false;
						if(gameBoard[i][j+1] == symbol) 
							return false;
						if(gameBoard[i][j-1] == symbol) 
							return false;
						if(gameBoard[i+1][j+1] == symbol) 
							return false;
						if(gameBoard[i+1][j-1] == symbol) 
							return false;
						if(gameBoard[i-1][j+1] == symbol) 
							return false;
						if(gameBoard[i-1][j-1] == symbol) 
							return false;
						else {
							return true;
						}
					}
				}
			}
		}
			return false;
	}
				
	public int evalBoard(char symbol, int empty_positions) {
		if(wins('c')) { //computer wins
			return 3;
		}
		else if(wins('h')) { //human wins
			return 0;
		}
		else if(isDraw(symbol, empty_positions)) { //game is a draw
			return 2;
		}
		else {
			return 1; //game is still undecided
		}
	}
}

