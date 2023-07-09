public class BNode <Pel extends Pel<Pel>>{
	BNode left;
	BNode right;
	BNode parent;
	Pel value;
		
	public BNode (Pel value, BNode left, BNode right, BNode parent) { //Stores the Pel value in the node and sets left child, right child, and parent to the specified values
		this.left = left;
		this.right = right;
		this.parent = parent;
		this.value = value;
	}
	public BNode () { //initializes a leaf node
		value = null;
		left = right = null;
		parent = null;
	}
	public BNode parent() { //Returns the parent of this node.
		return this.parent;
	}
	public void setParent(BNode newParent) { //Sets the parent of this node to the specified value
		this.parent = newParent;
	}
	public void setLeftChild (BNode p) { //Sets the left child of this node to the specified value
		this.left = p;
        if (p != null) {
            p.setParent(this);
        }
	}
	public void setRightChild (BNode p) { //Sets the right child of this node to the specified value
		this.right = p;
        if (p != null) {
            p.setParent(this);
        }
	}
	public void setContent (Pel value) { //Stores the given Pel object in this node
		this.value = value;
	}
	public boolean isLeaf(BNode p) { //Returns true if this node is a leaf; returns false otherwise
		 if (p == null) {
		    return false;  
		 }
		 if (p.right == null && p.left == null) {
		    return true;
		 }
		 return false; 
	}
	public Pel getData() { //Returns the Pel object stored in this node
		return this.value;
	}
	public BNode leftChild() { //Returns the left child of this node
		return this.left;
	}
	public BNode rightChild() { //Returns the right child of this node
		return this.right;
	}
}
