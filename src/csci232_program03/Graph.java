package csci232_program03;

/**
 *
 * @author Sell
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
    
     
    
    
    // Method to add a vertex to the graph
    public void addVertex(int data){
        vertexAr[size++] = new Vertex(data);
    }  
    
    // method to add an edge
    public void addEdge(int source, int destination, int weight){
        vertexAr[source - 1].adj = new Adjacent((destination - 1), weight, vertexAr[source - 1].adj);
        allEdges = new Edge(vertexAr[source - 1], vertexAr[destination - 1], weight, allEdges);
        edges++; // increment edge number
    }
    
    //method to swap
    public void swap(Edge[] e, int i1, int i2){
        Edge tmp = e[i1];
        e[i1] = e[i2];
        e[i2] = tmp;
    }
    
    // method to pivot
    public int pivot(Edge[] e, int start, int end){
        
        Edge pivot = e[end];
        int pivotIndex = start;
        
        for(int i = start; i < end; i++){
            if(e[i].weight < pivot.weight){
                swap(e, i, pivotIndex);
                pivotIndex++;
            }
        }
        swap(e, end,pivotIndex);
        return pivotIndex;
    }
    
    // method for sorting 
    public void quicksort(Edge[] e, int start, int end){
        if(start < end){
            swap(e, end, (start + (end - start)/2));
            
            int pivotIndex = pivot(e, start, end);
            
            quicksort(e, start, pivotIndex - 1);
            quicksort(e, pivotIndex + 1, end);
        }
    }
    
    // method to find set of vertices
    public Vertex findSet(Vertex v){
        // check if same vertex
        if(v.rep != v){
            v.rep = findSet(v.rep);
        }
        return v.rep;
    }
    
    
    
    
    
    
    /* IMPLEMENT ALORITHMS HEREE */
    
    /* PRIM */
   /// uses a nested class
    public class MST{
        
        public int v = 5;
        
        //method to get min
        public int minKey(int key[], boolean mstSet[]){
            int min = Integer.MAX_VALUE;
            int min_index = -1;
            
            for(int i = 0; i < v; i++){
                if(mstSet[i] == false && key[i] < min){
                    min = key[i];
                    min_index = i;
                }
            }
            return min_index;
        }
        
        // method to atually run prim algo
        public void prim(int graph[][]){
            int parent[] = new int[v];
            int key[] = new int[v];
            
            boolean mstSet[] = new boolean[v];
            
            for(int i = 0; i < v; i++){
                key[i] = Integer.MAX_VALUE;
                mstSet[i] = false;
            }
            key[0] = 0;
            parent[0] = -1;
            
            for(int i = 0; i < (v - 1); i++){
                int m = minKey(key, mstSet);
                mstSet[m] = true;
                
                for(int n = 0; n < v; n++){
                    if(graph[m][n] != 0 && mstSet[n] == false && graph[m][n] < key[n]){
                        parent[n] = m;
                        key[n]= graph[m][n];
                    }
                }
            } // end for 'i'
            
            printPrim(parent, v, graph);
        }
        
        // method to print prim MST
        public void printPrim(int parent[], int n, int graph[][]){
            for(int i = 1; i < v; i++){
                System.out.println(parent[i] + " - " + i + " " + graph[i][parent[i]]);
            }
            
        }
    }
    /* end of all prim */
    
    
    /* KRUSKAL */
    public void kruskal(){
        
        Edge[] edgeAr = new Edge[edges];
        
        int i = 0;
        
        // run thru edges in catalogue
        while(allEdges != null){
            edgeAr[i] = allEdges;
            i++;
            allEdges = allEdges.next;
        }
        
        quicksort(edgeAr, 0, (edges - 1));
        
        for(i = 0; i < edges; i++){
            Vertex vertex1 = findSet(edgeAr[i].source);
            Vertex vertex2 = findSet(edgeAr[i].destination);
            
            if(vertex1 != vertex2){
                System.out.println(edgeAr[i].source.data + " - " + edgeAr[i].destination.data + " weight = " + edgeAr[i].weight);
            }
        }
        
    } /* end of kruskal */
    
    
    /* FLOYD - WARSHALL */
    public void floydWarshall(int graph[][]){
        
        int v = graph.length;
        
        int dist[][] = new int[v][v];
        
        int i;
        int j;
        int k;
        
        for(i = 0; i < v; i++){
            for(j = 0; j < v; j++){
                dist[i][j] = graph[i][j];
            } // end for 'j'
        } // end for 'i'
        
        for(k = 0; k < v; k++){
            for(i = 0; i < v; i++){
                for(j = 0; j < v; j++){
                    if((dist[i][k] + dist[k][j]) < dist[i][j]){
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        } // end for 'k'
        
        printFloyd(dist, v);
    }
    
    // method to print floyd warshall results
    public void printFloyd(int dist[][], int v){
        
        System.out.println();
        //int inf = 99999;
        
        for(int i = 0; i < v; ++i){
            for(int j = 0; j < v; ++j){
                if(dist[i][j] == -Integer.MAX_VALUE){
                    System.out.print("INF ");
                }
                else{
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
        
    }
    /* end of all floyd */
     
}
