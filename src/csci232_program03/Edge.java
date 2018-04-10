package csci232_program03;

/**
 * Authors: Selene Smith, Dallas LeGrande
 * Date: 4/10/18
 * 
 * Overview:
 * - Program to gain experience with graphs and 
 *      implementation of common graph algorithms
 * 
 * - read graphs from comma separated files containing adjacency matrices
 *      using file relative paths
 * 
 * - implement 3 graphing methods:
 *      1. Prim's Algorithm - to find minimum spanning tree for weighted graph
 *          >> print the edges in the minimum spanning tree to console ie: "AB BC CD DE"
 *          start with a vertex and put it into the tree. 
 *          repeat the following steps
 *              find all the edges from the newest vertex to the other vertices that aren't in the tree and put into the queue
 *              pick the edge with the lowest weight and add this edge and its destination vertex to the tree
 *              repeat until all the vertices are in the tree
 *              newest means most recently installed into the tree
 * 
 *      2. Kruskal's Algorithm - to find minimum spanning tree for weighted graph
  *          >> print the edges in the minimum spanning tree to console ie: "AB BC CD DE"
  * 
 *      3. Floyd-Warshall's Algorithm - to find length of the shortest path b/t
 *          all pairs of vertices for weighted connected simple graph
 *          >> display step-by-step changes to adjacency matrix as console output
 *          >> start with initial matrix
 */

public class Edge implements Comparable<Edge>{
    
    public int weight;
    
    public Edge next;
    
    public Vertex source;
    public Vertex destination;
    
    public Edge(Vertex source, Vertex destination, int weight, Edge next){
        
        this.source = source;
        this.destination = destination;
        this.weight = weight;
        this.next = next;
    }
    
    //constructor used for Prims algorithm
    public Edge(Vertex source, Vertex destination, int weight){
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
    // used with the priority queue
    // this tells the queue to base the priority on the edge weight
    @Override
    public int compareTo(Edge o) {
        return weight - o.weight;
    }
    
}
