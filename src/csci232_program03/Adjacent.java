package csci232_program03;

/**
 *
 * @author Sell
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
