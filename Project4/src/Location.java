public class Location {
	int x;
	int y;
	
	public Location(int x, int y) { //Initializes this Location object with the specified coordinates.
		this.x = x;
		this.y = y;		
	}
	public int getx() { //Returns the x coordinate of this Location.
		return x;
	}
	public int gety() { //Returns the y coordinate of this Location.
		return y;
	}
	public int compareTo(Location p) { //compares x and y coordinates and returns a value
		if (this.gety() > p.gety()){
			return 1;
		}
		if ((this.gety() == p.gety()) && (this.getx() > p.getx())){
			return 1;
		}
		if ((this.getx() == p.getx()) && (this.gety() == p.gety())){
			return 0;
		}
		if (this.gety() < p.gety()) {
			return -1;
		}
		if ((this.gety() == p.gety()) && (this.getx() == p.getx())){
			return -1;
		}
	}
}
