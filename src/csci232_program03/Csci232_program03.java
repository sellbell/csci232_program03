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
 * 
 *      2. Kruskal's Algorithm - to find minimum spanning tree for weighted graph
  *          >> print the edges in the minimum spanning tree to console ie: "AB BC CD DE"
  * 
 *      3. Floyd-Warshall's Algorithm - to find length of the shortest path b/t
 *          all pairs of vertices for weighted connected simple graph
 *          >> display step-by-step changes to adjacency matrix as console output
 *          >> start with initial matrix
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class csci232_program03 {
    
    private int counter = 0; //variable to count how big the array needs to be
    private dataArray array; //variable to hold the array that is the table

    public static void main(String[] args) {
        csci232_program03 go = new csci232_program03();
        go.readWord();
        go.primsAlgorithm();
        go.cv();
        
    }
    //reads the file line by line then separates it into word by word
    public void readWord()
    {
        int line1 = 1; //flag for when the first line of the file is being read in
        int num; //int for converting strings to ints
        
        try{ 
            //provides a path to the file being read from
            Path path =  Paths.get("./input/input.csv");
            
            //opens the bufferedreader to be able to read a file. The file location is held in the variable "path"
            try (BufferedReader reader = Files.newBufferedReader(path)){
               String line;
               
               //reads line by line until the line is null and the document is complete
               while ((line = reader.readLine()) != null)
               {
                   //splits the line every time there is a comma and puts each word into an array
                   String[] words = line.split(",");
                   if(line1 == 1) //if it's the first line read in, then it has the table labels
                   {
                       counter = words.length; //count the number of elements in line 1
                       array = new dataArray(counter); //creates a 2D array with the size of the elements in line 1
                       line1++; //increases the line count so that integers can be read in
                       for (String word : words) { //puts all the labels into the array
                       num = (int)word.charAt(0); //converts the labels to ascii numbers
                       array.fillMatrix(num); //inserts the labels into the first row of the array                     
                       }//end of for each loop
                   }//end of if statement
                   else{ //if it's not the first line then convert the string to an int
                        for (String word : words) {
                            if(word.equals(" ")) //if the string is a ? then it is infinity. Which is converted to the integer max value
                            {
                                num = Integer.MAX_VALUE;   
                            }
                            else{ //otherwise convert the string to an int
                                num = Integer.parseInt(word);
                            }
                        array.fillMatrix(num); //inserts the number into the array                    
                    }//end of for each loop
                   }//end of else
               }//end of while loop
               //array.print(); //print the array to see the table
           }//end of try for buffered reader
        }//end of try for paths
        catch (IOException e){
            System.out.println("File not found");
              }
    }//end of readWord method
    
    public void primsAlgorithm()
    {
        array.prims();
        array.printEdge();
    }
    public void cv()
    {
        array.createVertices();
    }
}//end of class
    

