public class Pel{
	Location p;
	int color;
	
	public Pel(Location p, int color) { //Initializes the new Pel with the specified coordinates and color
		this.p = p;
		this.color = color;
	}
	public Location getLocus() { //Returns the Location of this Pel
		return p;
	}
	public int getColor() { // Returns the color of this Pel object
		return color;
	}

}
