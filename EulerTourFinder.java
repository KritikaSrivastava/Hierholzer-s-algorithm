/*
 * @author Kritika
 * This is a class that implements methods to find Euler tour or a Euler path in a graph 
 */
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class EulerTourFinder {

	
	
	public static LinkedList<CircularList<Vertex>> breakGraphIntoTours(Graph g) {
		if(!GraphOperations.getIsConnected(g)) //Run Iterative DFS to check if graph is connected and stop if it isn't
		{
			System.err.println("The graph isn't connected!!");
			System.exit(-1);
		}
		int oddVertexCount = GraphOperations.getOddVertexCount(g); //Can't be Eulerian if odd vertex count not equal to 0 or 2
		if(oddVertexCount != 0 && oddVertexCount != 2)
		{
			System.err.println("Graph is not Eulerian. It has " + oddVertexCount + 
					" vertices of odd degree"); // Odd count can never be 1 for undirected graph 
			System.exit(-1);
		}
		GraphOperations.MarkUnseen(g);
		GraphOperations.markEdgesUnseen(g);
		LinkedList<CircularList<Vertex>> tours = new LinkedList<CircularList<Vertex>>();
		CircularList<Vertex> subTour = new CircularList<Vertex>();
		Queue<Vertex> foundVertex = new LinkedList<Vertex>();
		Vertex start = g.verts.get(1);
		subTour.add(start);
		foundVertex.add(start);
		Vertex u = start;
		start.seen = true;
		while(!foundVertex.isEmpty()) {
			do{
					Edge e = GraphOperations.getUnvisitedEdge(u);
					if(e != null) {
						Vertex v = e.otherEnd(u);
						GraphOperations.markSeen(v,u);
						if(v.name != start.name) {
						  subTour.add(v);

						}
	                    if(!v.seen)
	                    	foundVertex.add(v);
						v.seen = true;
	                    u=v;
					}
					
					else {
						if(u.name == start.name) {
							foundVertex.poll();
							u.adjSeen = true;
						}
						break;
					}
			} while (u.name != start.name);
			if(!u.adjSeen)
				tours.add(subTour);
			start = foundVertex.peek();
			u=start;
			subTour.reset();
            subTour.add(start);
		}
		return tours;
		
	}
	
	@SuppressWarnings("unused")
	void stitchTours(LinkedList<CircularList<Vertex>> listOfTours) {
		CircularList<Vertex> eulerTour = new CircularList<Vertex>();
		if (eulerTour == null) {
			eulerTour = listOfTours.getFirst();
			listOfTours.removeFirst();
		}
         for(Vertex v:eulerTour) {
        	 for(CircularList<Vertex> i: listOfTours) {
        		 for(Vertex u:i) {
        			if(v.name == u.name) {
        				
        			}
        	 }
        	 }
	}
	}
	
	
	/*
	 * Main function expects either 0 or 1 arguments.
	 * If no arguments passed to the program, then the graph needs
	 * to be entered in command line.
	 * Or a graph file can be passed as command line argument.
	 */
	public static void main(String[] args) throws Exception {
		
		Scanner input = null;
		if(args.length < 1)
		{
			input = new Scanner(System.in);
		}
		else if(args.length == 1)
		{
			File inputFile = new File(args[0]);
			input = new Scanner(inputFile);
		}
		Graph g = Graph.readGraph(input, false);
		long start = System.currentTimeMillis();
		LinkedList<CircularList<Vertex>> eulerTour = new LinkedList<CircularList<Vertex>>();
		eulerTour = breakGraphIntoTours(g);
		long end = System.currentTimeMillis();
		for(CircularList<Vertex> list: eulerTour){
			for(Vertex u: list) {
				if(u != null)
				System.out.println(u.name);
			}
		}

	}

}
