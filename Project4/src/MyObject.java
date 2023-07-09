public class MyObject implements MyObjectADT {
	int id;
	int width;
	int height;
	String type;
	Location pos;
	
	public void setType(String type) {
		this.type = type;
	}
	/*
	 * Returns the width of this figure
	 */
	public int getWidth() {
		return this.width;
	}

	/*
	 * Returns the height of this figure
	 */
	public int getHeight() {
		return this.height;
	}
	/*
	 * Returns the type of this figure
	 */
	public String getType() {
		return this.type;
	}
	/*
	 * Returns the id of this figure
	 */
	public int getId() {
		return this.id;
	}
	/*
	 * Returns the offset or position of this figure
	 */
	public Location getLocus() {
		return this.pos;
	}
	/*
	 * Changes the offset of this figure to the specified value.
	 */
	public void setLocus(Location value) {
		this.pos = value;
	}
	/*
	 * Adds the given Pel object into the binary search tree associated with
	 * this figure. A DuplicatedKeyException is thrown if the figure already has
	 * a Pel with the same key as pix.
	 */
	public void addPel(Pel pix) throws DuplicatedKeyException{
		if(r == pix) {
			throw new DuplicatedKeyException("DuplicatedKeyException");
		}
		else {
			BNode node = r;
			while (true) {
				if (node.left == null) {
					node.left = new BNode(pix);
					return;
				}
				else {
					node = node.left;
				}
			}
				if (node.right == null) {
					node.right = new BNode(pix);
					return;
				}
				else {
					node = node.right;
				}
			}
		}
	/*
	 * Returns true if this figure intersects the one specified in the
	 * parameter; it returns false otherwise.
	 */
	public boolean intersects(MyObject fig) {
		int i = BinarySearchTree.smallest(pos);
		for (i;i<pos;i++) {
			if (this.getLocus() == fig.getLocus()) {
			return true;
			}
			else {
				return false;
			}
			i = BinarySearchTree.successor(pos);
		}
	}
}
