import java.util.*;

/**
 * @author Kritika
 * This class contains utility functions that perform specific operations 
 * on graphs. 
 */

public class GraphOperations {
	
	
	/*
	 * This is a utility function that marks all vertices of the given
	 * graph as unseen.
	 */
	public static void MarkUnseen(Graph g)
	{
		for(Vertex u : g)
		{
			u.seen = false;
			u.adjSeen = false;
		}		
	}
	
	public static void markSeen(Vertex v,Vertex u) {
		for(Edge e:v.Adj) {
			if(e.To == u) {
				e.visited = true;
				break;
			}
		}
	}


	/*
	 * This function checks if the given graph is connected
	 * Returns true if the graph is connected
	 */
	public static boolean getIsConnected(Graph g)
	{
		MarkUnseen(g);
		GraphOperations.checkGraph(g, g.verts.get(1));
		for(Vertex v : g)
		{
			if(!v.seen)
				return false;
		}
		return true;		
	}
	
	/*
	 * This function returns the number of odd vertices in the given graph g
	 */
	public static int getOddVertexCount(Graph g)
	{
		return getOddVertices(g).size();
	}
	
	/*
	 * This function returns all the odd vertices of the given graph g
	 */
	public static List<Vertex> getOddVertices(Graph g)
	{
		List<Vertex> oddVertexList = new ArrayList<>();
		if(!getIsConnected(g))
			return null;
		for(Vertex v : g)
		{
			if(v.Adj.size()%2 != 0)
				oddVertexList.add(v);
		}
		return oddVertexList;
	}
	
	/*
	 * Given a vertex as a parameter, get the next edge that
	 * is not visited and mark it as visited.
	 */
	public static Edge getUnvisitedEdge(Vertex u)
	{
		for(Edge e : u.Adj)
		{
			if(!e.visited)
			{
				e.visited = true;
				return e;
			}
		}
		return null;
	}
	
	/*
	 * This function marks all edges of graph as unseen
	 */
	public static void markEdgesUnseen(Graph g)
	{
		for(Edge e : g.edges)
			e.visited = false;
	}
	

	 public static void checkGraph(Graph g, Vertex src) {
		 	MarkUnseen(g);
	        Deque<Vertex> stack = new LinkedList<Vertex>();
	        src.seen = true;
	        stack.push(src);
	        while (!stack.isEmpty()) {
	            Vertex u = stack.peek();
	            if(!u.adjSeen){	
	            	for(Edge e : u.Adj){
	            		Vertex v = e.otherEnd(u); 
	            		if(!v.seen){
	            			v.seen = true;
	            			stack.push(v);
	            		}
	        		}
	            	u.adjSeen = true;
	            }
	    		else {
	    				stack.pop();
	            }
	        }
	 }
}