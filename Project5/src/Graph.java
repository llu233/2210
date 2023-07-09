//class for creating graphs consisting of nodes and edges
import java.util.Iterator;
import java.util.Stack;

public class Graph implements GraphADT{
	private Node nodes[]; //Array storing all the nodes in the graph
	private Edge edges[][]; //Adjacency matrix to store all edges between nodes
	
	public Graph(int n){
		nodes = new Node[n];
		edges = new Edge[n][n];
		
		for (int i=0; i < n; i++){
			nodes[i] = new Node(i);
		}
	}
    /* Returns the node with the specified id. Throws a GraphException if the node does not exist. */
	public Node getNode(int id) throws GraphException{
		if (id>=0 && id<nodes.length)
			return nodes[id];
		else
			throw new GraphException("Invalid node index");
	}
	/* Adds to the graph an edge connecting the given nodes.
    The type of the edge is as indicated. Throws a GraphException 
    if either node does not exist or if the edge is already 
	in the graph.*/
	public void addEdge(Node nodeu, Node nodev, String edgeType) throws GraphException{
		if (nodeu.getId()>=0 && nodev.getId()>=0 && nodeu.getId()<nodes.length && nodev.getId()<nodes.length){
			//Check that the edge doesn't already exist
			if (edges[nodeu.getId()][nodev.getId()] == null && edges[nodev.getId()][nodeu.getId()] == null){
				edges[nodeu.getId()][nodev.getId()] = new Edge(nodeu, nodev, edgeType);
				edges[nodev.getId()][nodeu.getId()] = new Edge(nodev, nodeu, edgeType);
			}
			else
				throw new GraphException("Edge already exists");
		}
		else
			throw new GraphException("Node does not exist");
	}
	/* Returns a Java Iterator storing all the edges incident
	   on the specified node. It returns null if the node does
	   not have any edges incident on it. Throws a GraphException
	   if the node does not exist. */
	public Iterator<Edge> incidentEdges(Node u) throws GraphException{
		if (u.getId()>=0 && u.getId()<nodes.length){
			Stack<Edge> incident_edges = new Stack<Edge>();
			for (int i=0; i<nodes.length; i++){
				if (edges[u.getId()][i] != null){
					incident_edges.push(edges[u.getId()][i]);
				}
			}
			if (incident_edges.isEmpty())
				return null;
			return incident_edges.iterator();
		}
		else
			throw new GraphException("Invalid node index");
		}
	/* Returns the edge connecting the given nodes. Throws 
	   a GraphException if there is no edge connecting the given 
	   nodes, or if u or v do not exist. */
	public Edge getEdge(Node u, Node v) throws GraphException{
		if (u.getId()>=0 && v.getId()>=0 && u.getId()<nodes.length && v.getId()<nodes.length){
			if (edges[u.getId()][v.getId()] == null)
				throw new GraphException("No edge between specified nodes");
			else
				return edges[u.getId()][v.getId()];
		}
		else
			throw new GraphException("Invalid node index");
		}
	/* Returns true is u and v are adjacent, and false otherwise. 
       It throws a GraphException if either node does not exist. */
	public boolean areAdjacent(Node u, Node v) throws GraphException{
		//Check if nodes exist
		if (u.getId()>=0 && v.getId()>=0 && u.getId()<nodes.length && v.getId()<nodes.length)
			return edges[u.getId()][v.getId()] != null;
		else
			throw new GraphException("Invalid node index");
	}
}
             


