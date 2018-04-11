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
 * 
 * This class is for building an adjacency matrix
 */

import java.util.*;

public class dataArray {
    public int[][] matrix; //variable to hold the matrix array
    public int lineSize; //variable to hold the line size
    public int spotI = 0; //variable to keep track of which row the numbers are being inserted into the matrix
    public int spotJ = 0; //variable to keep track of which column the numbers are being inserted into the matrix
    public Vertex[] vertices;
    public Edge[] mstP;
    public Edge[] mstK;    
    //static int index = 0; //variable to hold the index of the minimum spanning tree array
    static int maxP; //variable to hold the maximum number of edges for a minimum spanning tree
    static int maxK; //variable to hold the maximum number of edges for a minimum spanning tree
    PriorityQueue<Edge> pq;
    
    
    public dataArray(int a)
    {
       matrix = new int[a+1][a]; //creates a matrix array that has one row more than the columns. The extra row holds the label values
       lineSize = a; //sets the line size
       mstP = new Edge[a-1]; //initializes the array to hold the edges to the size of how many vertices there are minus one. Most edges there can be are vertices minus one otherwise loops
       mstK = new Edge[a-1]; //initializes the array to hold the edges to the size of how many vertices there are minus one. Most edges there can be are vertices minus one otherwise loops
       maxP = a-1; //sets the maximum number of edges there can be without causing a loop
       maxK = a-1; //sets the maximum number of edges there can be without causing a loop
       pq = new PriorityQueue<>(); //initializes a priority queue
    }
    
    //Method to fill the matrix
    public void fillMatrix(int a)
    {
        if(spotJ < lineSize) //if there are open columns in the row insert the number into the open column spot
            {
                matrix[spotI][spotJ] = a;
                spotJ++; //increase the column spot
            }
        else{ //if there are no open columns left in the row then start a new row and put set the column number to zero
            spotI++; //increases the row
            spotJ = 0; //starts the next row at the beginning
            matrix[spotI][spotJ] = a; //puts the number into the first spot of the next available row
            spotJ++; //increases the column spot
        }//end of else
    }// end of fillMatrix method
    
    //Method to print the matrix
    public void print()
    {
        for(int i = 0; i < lineSize; i++) //go through each row
        {
            for(int j = 0; j < lineSize; j++) //go through each column
            {
                if(matrix[i][j] > 64 && matrix[i][j] < 91) //if the spot in the row and column is a capital letter then it is a label
                {
                    System.out.print((char) matrix[i][j] + " "); //print the label
                }//end of if
                else{ //otherwise it is a number
                    System.out.print(matrix[i][j] + " "); //print the number
                }//end of else
            }//end of inner for loop
            System.out.println(); //make it so the next row prints on the next line
        }//end of outer for loop
    }//end of print method
 
    //Method to create all the vertices
    public void createVertices()
    {
        vertices = new Vertex[lineSize]; //creates a vertice array to hold all the vertices
        for(int i = 0; i < lineSize; i++)
        {
            Vertex a;
            a = new Vertex((char) matrix[0][i],i); //creates a new vertex for each label in the matrix, passing the char value and the col number
            vertices[i] = a;
        }
    }
    
    //Method to create an edge: take beginning vertex, then ending vertex and the weight
    public Edge createEdge(Vertex x, Vertex y, int z)
    {
        Edge a = new Edge(x,y,z);
        return a;
    }
    
    //Method to start Prim's algorithm. Pass in a 2D array
    public void prims()
    {
        Vertex start; //creates a variable to track the starting vertex
        createVertices(); //calls the method that creates all the vertices
        //find a random vertex to start at
        int randomCol = (int)(Math.random()* lineSize);
        int index = 0;
        start = vertices[randomCol]; //picks a random column and grabs the vertex associated with that column
        System.out.println("Prim's Algorithm");
        System.out.println("--------------------------- ");
        System.out.println("The starting Vertex is " + start.label);
        while(maxP > 0) //While there are still edges that need to be accounted for
        {
            start.checked = true; //marks the vertex as having been checked
            for(int j = 1; j <= lineSize; j++) //goes through the column of the starting vertex looking for edges to another vertex
                {
                    if(matrix[j][start.col] < Integer.MAX_VALUE) //if there is a connection then the column will have a number less than Integer.MAX_VALUE
                    {
                        Edge e = createEdge(start,vertices[j-1], matrix[j][start.col]); //send in source Vertex, dest. Vertex and weight
                        pq.add(e); //put the edge into the queue
                    }
                }
            Edge temp = pq.remove(); //takes the lowest weighted edge and takes it out of the queue
            
            while(temp.destination.checked) //if the edge connects to a vertex that has already been checked then keep checking edges
            {
                temp = pq.remove(); //takes the lowest weighted edge and takes it out of the queue
            }
            mstP[index] = temp; //put the edge into the minimum spanning tree
            index++; //increase the index of the minimum spanning tree array
            maxP--; //decrease the amount of possible edges left to check
            start = temp.destination;
        }
        printEdge(mstP);
    }
    public void kruskal()
    {
        createVertices(); //calls the method that creates all the vertices
        //start = vertices[0]; //picks a random column and grabs the vertex associated with that column
        System.out.println();
        System.out.println("Kruskal's Algorithm");
        System.out.println("--------------------------- ");
        //System.out.println("The starting Vertex is " + start.label);
        
            //start.checked = true; //marks the vertex as having been checked
            for(int i = 0; i < lineSize; i++)
            {
                for(int j = 1; j < lineSize; j++) //goes through the column of the starting vertex looking for edges to another vertex
                    {
                        if(matrix[i+1][j] < Integer.MAX_VALUE) //if there is a connection then the column will have a number less than Integer.MAX_VALUE
                        {
                            Edge e = createEdge(vertices[i],vertices[j], matrix[i+1][j]); //send in source Vertex, dest. Vertex and weight
                            pq.add(e); //put the edge into the queue
                        }
                    }
            }
            int index = 0;
        while(maxK > 0) //While there are still edges that need to be accounted for
        {
            Edge temp = pq.remove(); //takes the lowest weighted edge and takes it out of the queue
            while(temp.destination.checked && temp.source.checked) //if the edge connects to a vertex that has already been checked then keep checking edges
            {
                temp = pq.remove(); //takes the lowest weighted edge and takes it out of the queue
            }
            temp.destination.checked = true;
            temp.source.checked = true;
            mstK[index] = temp; //put the edge into the minimum spanning tree
            index++; //increase the index of the minimum spanning tree array
            maxK--; //decrease the amount of possible edges left to check
            //start = temp.destination;
        }
    printEdge(mstK);
    }
    public void printEdge(Edge mst[])
    {
        System.out.print("These are the edges: ");
        for(int i = 0; i < mst.length; i++)
        {
            System.out.print(mst[i].source.label + "" + mst[i].destination.label);
            if(i < mst.length - 1)
            {
                System.out.print(",");
            }
        }
        System.out.println();
    }
    //fills the matrix for the floyd-Warshall method
    public int[][] refillMatrix(int warshall[][])
    {
        int length = warshall.length-1;
        int [][]a = new int[length][length];
        for(int i = 0; i < length; i++)
        {
            for(int j = 0; j < length; j++)
            {
                a[i][j] = warshall[i+1][j]; //copies the columns shifted one over so that the columns can be printed
            }
        }
        return a;
    }//end of refillMatrix method
}
