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
        // if an invalid vertex is entered, throw an exception
        if(src >= vertices || src < 0 || tar >= vertices || tar < 0){
            throw new Exception();
        }
        // add the edge to the matrix
        matrix[src][tar] = 1;
    }

    public List<Integer> topologicalSort(){
        System.out.print("Solution: "); // to print the solution to console
        // create the solution list to be returned
        List<Integer> solution = new ArrayList<>();
        // creates and populates an array of the sums of the values per columns in the matrix
        int[] sum = new int[matrix.length];
        for(int i = 0; i<vertices; i++){
            for(int j=0; j<vertices; j++){
                sum[i] += matrix[j][i];
            }
        }

        // loops until every vertex is in the solution
        for(int count=0; count<vertices; count++){
            int n = findZero(sum); // finds the next 0 in the sum array to add to the solution
            if(n == -1){ // if no 0 was found, ie, there is a cycle
                System.out.println("\nAn ordering of this graph is not possible.");
                return solution;
            }
            solution.add(n);
            System.out.print(n+" "); // prints solutions to console
            sum[n] = -1;
            // resets the sum array after adding n to the solution list
            for(int i=0; i<vertices; i++){
                sum[i] -= matrix[n][i];
            }
        }
        System.out.println();   // prints \n to console
        return solution;
    }

    public List<Integer> neighbors(int vertex) throws Exception{
        // checks to make sure the vertex exists
        if(vertex >= vertices || vertex < 0){
            throw new Exception();
        }
        // create the list of neighbors to be returned
        List<Integer> neighbors = new ArrayList<>();
        // loops through the row of the vertex
        for(int i=0; i<vertices; i++){
            // if a cell value is greater than 0
            if(matrix[vertex][i] > 0){
                // then that column's index is a neighbor to vertex
                neighbors.add(i);
            }
        }
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
