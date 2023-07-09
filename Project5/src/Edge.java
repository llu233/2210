//Data structure for storing the edges of a graph
public class Edge {
    private Node u;     
    private Node v;     
    private String type;
    private String label;            
    
    public Edge (Node u, Node v, String type) {         
    	this.u = u;
    	this.v = v;
    	this.type = type;
    	label = "";
    }     
    public Node firstNode() {         
    	return u;     
    	}     
    public Node secondNode () {         
    	return v;     
    	}     
    public String getType () {        
    	return type;     
    	}
    public void setLabel(String label){
		this.label = label;
	}
    public String getLabel(){
		return label;
	}
}
   