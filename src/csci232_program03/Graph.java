package csci232_program03;

/**
* Authors: Selene Smith, Dallas LeGrande
 * Date: 4/10/18
 * 
 * Overview:
 * - Program to gain experience with graphs and 
 *      implementation of common graph algorithms
 * 
 * This class represents the graph
 */
public class Graph {
    
    public int maxSize;
    public int size; // size of graph
    public int edges; // # of edges
    
    public Edge allEdges; // track/list edges
    public Vertex[] vertexAr;
    
    
    public Graph(int maxSize){
        this.maxSize = maxSize;
        vertexAr = new Vertex[maxSize];
    }
    
    
    /* FLOYD - WARSHALL */
    public void floydWarshall(int graph[][]){
        int v = graph.length;
        int dist[][] = graph; //copies the graph to a new matrix
        printFloyd(dist,v);// prints the original graph before any changes are made
        for(int k = 0; k < v; k++){ //goes through the matrix checking each spot against any other spots that connect the same vertices
            for(int i = 0; i < v; i++){
                for(int j = 0; j < v; j++){
                    if(dist[i][k] !=  Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE){
                        if(dist[i][j] > (dist[i][k] + dist[k][j])){
                            dist[i][j] = dist[i][k] + dist[k][j];
                            printFloyd(dist,v); //Prints the matrix after something is changed
                        }//end of if inner if
                    }//end of outer if
                }//end of inner for loop
            }//end of middle for loop
        }//end of outer for loop
        
        printFloyd(dist, v); //prints the final matrix
    }
    
    // method to print floyd warshall results
    public void printFloyd(int dist[][], int v){
        
        System.out.println();//puts a blank line in between each printed matrix
        System.out.println("Floyd-Warshall Algorithm");
        System.out.println("--------------------------- ");
        int count = 0;
        for(int i = 0; i < v; ++i){
            for(int j = 0; j < v; ++j){                   
                   while(count < v){ //prints the heading to the table to visualize
                       if(count == 0) //moves the headings over to line up with the columns
                       {
                           System.out.print("    ");
                       }
                    System.out.print((char)(65 + count) + "     "); //prints the columns starting with A and continuing until the end of the array row
                    count++;
                   }
                   if(count == v) //once you get to the end the row start a new line to print the table
                   {
                        System.out.println();
                        count++;
                   }
                   if(j == 0){ //Print the row heading before printing the row
                       System.out.print((char) (65 + i) + "  |");
                   }
                   if(dist[i][j] == Integer.MAX_VALUE){ //prints INF instead of the integer max value
                    System.out.print("INF  |");
                    }
                else{
                       if(dist[i][j] > 9) //prints one less space if the numbers are two digits. This keeps the columns lined up when printing
                       {
                           System.out.print(dist[i][j] + "   |");
                       }
                       else{
                        System.out.print(dist[i][j] + "    |");
                       }
                }
            }
            System.out.println();
            }
    }
    /* end of all floyd */
     
}

