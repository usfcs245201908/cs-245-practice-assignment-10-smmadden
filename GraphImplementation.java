import java.lang.Exception;
import java.util.*;

public class GraphImplementation implements Graph{
    private int[][] matrix;
    private int vertices;

    public GraphImplementation(int numVertex){
        vertices = numVertex;
        matrix = new int[vertices][vertices];
    }

    public void addEdge(int src, int tar) throws Exception {
        if(src >= vertices || src < 0 || tar >= vertices || tar < 0){
            throw new Exception();
        }
        matrix[src][tar] = 1;
    }

    public List<Integer> topologicalSort(){ // IS THIS SUPPOSED TO PRINT PRINT OR JUST RETURN OR WHAT   ????
        List<Integer> schedule = new ArrayList<>();
        int[] sum = new int[matrix.length];
        for(int i = 0; i<vertices; i++){
            for(int j=0; j<vertices; j++){
                sum[i] += matrix[j][i];
            }
        }

        for(int count=0; count<vertices; count++){
            int n = findZero(sum);
            if(n == -1){ // if no 0 was found, ie, there is a cycle
                System.out.println("\nAn ordering of this graph is not possible.");
                return schedule;
            }
            schedule.add(n);
            System.out.print(n+" ");
            sum[n] = -1;
            for(int i=0; i<vertices; i++){
                sum[i] -= matrix[n][i];
            }
        }
        System.out.println();
        return schedule;
    }

    public List<Integer> neighbors(int vertex) throws Exception{
        // checks to make sure the vertex exists
        if(vertex >= vertices || vertex < 0){
            throw new Exception();
        }
        List<Integer> neighbors = new ArrayList<>();
        for(int i=0; i<vertices; i++){
            if(matrix[vertex][i] > 0){
                neighbors.add(i);
            }
        }
        // go down the row, find the cells with ints more than 0 and add the row index of that cell to the returned list
        return neighbors;

    }

    private int findZero(int[] sum) {
        // finds the next zero in the sum to add to the schedule
        for(int i=0; i<vertices; i++){
            if(sum[i] == 0){
                return i;
            }
        }
        // if there is no zero then return -1
        return -1;
    }

}
