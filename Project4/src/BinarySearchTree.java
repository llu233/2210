public class BinarySearchTree implements BinarySearchTreeADT {
	BNode r = null;
	public Pel get(BNode r, Location key) { //Returns the Pel object storing the given key, if the key is stored in the tree; returns null otherwise.
		this.r = r;
		if (r == null) {
			return null;
		}
		else 
			if (r.key = key) {
				return get(r,key);
			}
			else if (key < r.key) {
				return get(r.left,key);
			}
			else {
				return get(r.right,key);
			}
		return null;
	}
	public void put(BNode r, Pel newData) throws DuplicatedKeyException{ //Inserts newData in the tree 
	    BNode node = r;
		while (node != null) {
	      if (newData > node.data) {
	        if (node.right == null) {
	          node.right = new BNode(newData);
	          throw new DuplicatedKeyException("DuplicatedKeyException");
	        } else {
	          node = node.right;
	        }
	      } else {
	        if (node.left == null) {
	          node.left = new BNode(newData);
	          throw new DuplicatedKeyException("DuplicatedKeyException")
	        } else {
	          node = node.left;
	        }
	      }
	    }
	    return new BNode(data);
	  }
	public void remove(BNode r, Location key) throws InexistentKeyException{ //Removes the data item with the given key
		if (r == null) {
			throw new EmptyTreeException("EmptyTreeException");
		}
		if (key == r.key) {
			throw new EmptyTreeException("EmptyTreeException");
		}
		if (key < r.key) {
			r.left = remove(r.left, key);
			return r;
		}
		if (key > r.key) {
			r.right = remove(r.right, key);
			return r;	
		}
		}
	public Pel successor(BNode r, Location key) { //Returns the Pel object with the smallest key larger than the given one
		if (r == null) {
			return null;
		}
		if (r.right == key) {
			if (r.right != null) {
				return smallest(r.right);
			}
		}
		else if (key < r.key) {
			return successor(r.left,key);
		}
		else {
			return successor(r.right,key);
		}
		return null;
	}
	
	public Pel predecessor(BNode r, Location key) { //Returns the Pel object with the largest key smaller than the given one
		if (r == null) {
			return null;
		}
		if (r.left == key) {
			if (r.left != null) {
				return largest(r.left);
			}
		}
		else if (key > r.key) {
			return predecessor(r.right,key);
		}
		else {
			return predecessor(r.left,key);
		}
		return null;
	}
	public Pel smallest(BNode r)throws EmptyTreeException{ //Returns the Pel object in the tree with the smallest key
		if (r == null) {
			throw new EmptyTreeException("EmptyTreeException");
		}
		else {
			BNode node = r;
			while (node.left != null) {
				node = node.left;
			}
			return node.data;
	}
	public Pel largest(BNode r) throws EmptyTreeException{ //Returns the Pel object in the tree with the largest key
		if (r == null) {
			throw new EmptyTreeException("EmptyTreeException");
		}
		BNode node = r;
		while (node.right != null) {
			node = node.right;
		}
		return node.data;
		
	}
	public BNode getRoot() { //Returns the root of the binary search tree
		return this.r;
	}
}

