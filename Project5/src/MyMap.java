//Implements a roadmap based on an input file using a graph with nodes and edges
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Stack;
import java.util.Iterator;

public class MyMap {

	private Graph graph;
	private Node startNode;
	private Node destNode;
	private int maxPrivate;
	private int maxConstruction;
	private Stack<Node> stack;

	public MyMap(String inputFile) throws MapException {
		try {
			stack = new Stack<Node>();
			BufferedReader input = new BufferedReader(new FileReader(inputFile));
			String line = input.readLine();	
			
			//Read the start node
			line = input.readLine();
			Node startNode = Node.parseNode(line);
			
			//Read the dest node
			line = input.readLine();
			Node destNode = Node.parseNode(line);
			
			//Read the width from the input file
			line = input.readLine();
			int width = Integer.parseInt(line);
			
			//Read the length from the input file
			line = input.readLine();
			int length = Integer.parseInt(line);
			
			//Initialize the graph with the number of nodes in the labyrinth
			graph = new Graph(width * length);
			
			//Read the max number of private roads from the input file
			line = input.readLine();
			int maxPrivate = Integer.parseInt(line);
			
			//Read the max number of construction roads from the input file
			line = input.readLine();
			int maxConstruction = Integer.parseInt(line);
			
			line = input.readLine();
			
			for(int i = 0; i < (length*2)-1; i++){
				for(int j = 0; j < (width*2)-1; j++){
					if (i%2 == 0){
						if (j%2 == 0){
							if(line.charAt(j) == '+'){
								startNode = graph.getNode((i/2*width) + (j/2));
							}
							else if(line.charAt(j) == '+'){
								destNode = graph.getNode((i/2*width) + (j/2));
							}
						}
						//and an odd numbered column, the char represents a private,public,block of houses or construction road
						else{
							if(line.charAt(j) == 'V'){
								graph.addEdge(graph.getNode((i/2*width) + ((j-1)/2)), graph.getNode((i/2*width) + ((j+1)/2)), "private road");
							}
							else if(line.charAt(j) == 'P'){
								graph.addEdge(graph.getNode((i/2*width) + ((j-1)/2)), graph.getNode((i/2*width) + ((j+1)/2)), "public road");
							}
							else if(line.charAt(j) == 'C'){
								graph.addEdge(graph.getNode((i/2*width) + ((j-1)/2)), graph.getNode((i/2*width) + ((j+1)/2)), "construction road");
							}
							else if(line.charAt(j) == 'B'){
								graph.addEdge(graph.getNode((i/2*width) + ((j-1)/2)), graph.getNode((i/2*width) + ((j+1)/2)), "Building");
							}
						}
					}
					else{
						if (j%2 == 0){
							if(line.charAt(j) == 'V'){
								graph.addEdge(graph.getNode((i/2*width) + ((j-1)/2)), graph.getNode((i/2*width) + ((j+1)/2)), "private road");
							}
							else if(line.charAt(j) == 'P'){
								graph.addEdge(graph.getNode((i/2*width) + ((j-1)/2)), graph.getNode((i/2*width) + ((j+1)/2)), "public road");
							}
							else if(line.charAt(j) == 'C'){
								graph.addEdge(graph.getNode((i/2*width) + ((j-1)/2)), graph.getNode((i/2*width) + ((j+1)/2)), "construction road");
							}
							else if(line.charAt(j) == 'B'){
								graph.addEdge(graph.getNode((i/2*width) + ((j-1)/2)), graph.getNode((i/2*width) + ((j+1)/2)), "Building");
							}
						}
					}
				}
				line = input.readLine();
			}
		} catch (Exception e) {	
			throw new MapException("Input file does not exist");
		}
	}
	public Graph getGraph() throws MapException{
		if (graph != null)
			return graph;
		else
			throw new MapException("Map not initalized");
	}
	public int getStartingNode() {
		return getNode(startNode);
	}
	public int getDestinationNode() {
		return getNode(destNode);
	}
	public int maxPrivateRoads() {
		return maxPrivate;
	}
	public int maxConstructionRoads() {
		return maxConstruction;
	}
	public Iterator<Node> findPath(int start,int destination,int maxPrivate,int maxConstruction) {
				path(startNode);
				if (stack.empty())
					return null;
				return stack.iterator();
			}
	private boolean path(Node u){
			stack.push(u);
			if (u == destNode){
				return true;
			}
			else{
				u.markNode(true);
				try {
					Iterator<Edge> incident_edges = graph.incidentEdges(u);
					while(incident_edges.hasNext()){
						Edge path = incident_edges.next();
						if (path.getLabel()!="path"){
							if (path.getType() == "V" || path.getType() == "C"){
								if (!path.secondNode().getMark()){
									path.setLabel("path");
									graph.getEdge(path.secondNode(), u).setLabel("path");
									if (path(path.secondNode()))
										return true;
									}	
								}
						}
					}
					Iterator<Edge> findpath = graph.incidentEdges(u);
					while(findpath.hasNext()){
						Edge path = findpath.next();
						if (path.getLabel() == "path"){
							path.setLabel(null);
							graph.getEdge(path.secondNode(), u).setLabel(null);	
							if (path.getType() == "V"){
								maxPrivate++;
							}
							else if (path.getType() == "C"){
								maxConstruction++;
							}
							break;
						}
					}
					u.markNode(false);
					stack.pop();	
					return false;	
				} catch (GraphException e) {
					System.out.println(e.getMessage());
				}
			return false;
				}
	}
}
	

