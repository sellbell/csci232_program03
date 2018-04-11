
package csci232_program03;

/**
 * Authors: Selene Smith, Dallas LeGrande
 * Date: 3/29/18
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

public class Vertex {
    public int data; // identifier
    public int rank;
    public Vertex rep; // representative
    public Adjacent adj; // neighboring item
    public char label;
    public boolean checked = false;
    public int col; //Keeps track of the row that the vertex is in for indexing purposes
    

    public Vertex(int data){
            this.data = data;
            rep = this;
        }

    //contructor to label the vertex and track the column that the vertex is in in the matrix
    public Vertex(char a, int i)
    {
        label = a; //set the label name of the vertex
        col = i;
    }
    public void checked(Vertex v)
    {
        v.checked = true;
    }    
}
