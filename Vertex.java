/**
 * Class to represent a vertex of a graph
 * 
 *
 */

import java.util.*;

public class Vertex {
    public int name; // name of the vertex
    public boolean seen; // flag to check if the vertex has already been visited
    public List<Edge> Adj; // adjacency list; use LinkedList or ArrayList
    public boolean adjSeen; //set to true only if all vertices in its Adjacency list are seen

    /**
     * Constructor for the vertex
     * 
     * @param n
     *            : int - name of the vertex
     */
    Vertex(int n) {
	name = n;
	seen = false;
	Adj = new ArrayList<Edge>();
	adjSeen = false;
    }

    /**
     * Method to represent a vertex by its name
     */
    public String toString() {
	return Integer.toString(name);
    }
}