package csci232_program03;
/**
* Authors: Selene Smith, Dallas LeGrande
 * Date: 4/10/18
 * 
 * Overview:
 * - Program to gain experience with graphs and 
 *      implementation of common graph algorithms
 * 
 * This class is used to hold adjacents in graph
 */
public class Adjacent {
    
    public int index;
    public int weight;
    
    public Adjacent next;
    
    public Adjacent(int index, int weight, Adjacent next){
        this.index = index;
        this.weight = weight;
        this.next = next;
    }
    
}
