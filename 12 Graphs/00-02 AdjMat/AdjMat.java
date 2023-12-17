// Name: Saina Shibili
// Date: 04/21/2021
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graph0 AdjMat_0_Driver,
 *              Graph1 WarshallDriver,
 *          and Graph2 FloydDriver
 */

interface AdjacencyMatrix
{
   void addEdge(int source, int target);
   void removeEdge(int source, int target);
   boolean isEdge(int from, int to);
   String toString();   //returns the grid as a String
   int edgeCount();
   List<Integer> getNeighbors(int source);
   public List<String> getReachables(String from);  //Warshall extension
}

interface Warshall      //User-friendly functionality
{
   boolean isEdge(String from, String to);
   Map<String, Integer> getVertices();     
   void readNames(String fileName) throws FileNotFoundException;
   void readGrid(String fileName) throws FileNotFoundException;
   void displayVertices();
   void allPairsReachability();  // Warshall's Algorithm
}

interface Floyd
{
   int getCost(int from, int to);
   int getCost(String from, String to);
   void allPairsWeighted(); 
}

public class AdjMat implements AdjacencyMatrix, Warshall//, Floyd 
{
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> vertices = null;   // name maps to index (for Warshall & Floyd)
   /*for Warshall's Extension*/  ArrayList<String> nameList = null;  //reverses the map, index-->name
	  
   /*  enter your code here  */  
   public AdjMat(int size){
      grid = new int[size][size];
      for(int i = 0; i < size; i++){
         for(int j = 0; j < size; j++){
            grid[i][j] = 0;
         }}
   }
   public void addEdge(int source, int target){
      grid[source][target] = 1;
   }
   public void removeEdge(int source, int target){
      grid[source][target] = 0;
   }
   public boolean isEdge(int from, int to){
      return grid[from][to] == 1;
   }
   public String toString(){
      String temp = "";
      for(int i = 0; i < grid.length; i++){
         for(int j = 0; j < grid.length; j++){
            temp += grid[i][j] + "\t";
            if(j == grid.length - 1)
               temp += "\n";
         }
      }
      return temp;
   }
   public int edgeCount(){
      int edges = 0;
      for(int i = 0; i < grid.length; i++){
         for(int j = 0; j < grid.length; j++){
            if(isEdge(i, j))
               edges++;
         }
      }
      return edges;
   }
   public List<Integer> getNeighbors(int source){
      ArrayList<Integer> n = new ArrayList< >();
      for(int i = 0; i < grid.length; i++){
         if(isEdge(source, i))
            n.add(i);
      }
      return n;
   }
   
   //Warshall Methods
   public boolean isEdge(String from, String to){
      return grid[vertices.get(from)][vertices.get(to)] == 1;
   }
   public Map<String, Integer> getVertices(){
      return vertices;
   }
   public void readNames(String fileName) throws FileNotFoundException{
      Scanner sc = new Scanner(new File(fileName));
      nameList = new ArrayList<String>();
      vertices = new HashMap<String, Integer>();
      int size = sc.nextInt();
      for(int i = 0; i < size; i++){
         nameList.add(sc.next());
         vertices.put(nameList.get(i), i);
      }
   }
   public void readGrid(String fileName) throws FileNotFoundException{
      Scanner sc = new Scanner(new File(fileName));
      int size = sc.nextInt();
      for(int i = 0; i < size; i++){
         for(int j = 0; j < size; j++){
            int temp = sc.nextInt();
            grid[i][j] = temp;
         }}
   }
   public void displayVertices(){
      for(int i = 0; i < nameList.size(); i++)
         System.out.print(i + "-" + nameList.get(i) + "\n");   
   }
   public void allPairsReachability(){
      for(int i = 0; i < grid.length; i++){
         for(int j = 0; j < grid.length; j++){
            for(int k = 0; k < grid.length; k++){
               if(isEdge(i, j) && isEdge(j, k))
                  addEdge(i, k);
            }}}
   }
   
   public List<String> getReachables(String from){
      List<String> temp = new ArrayList< >();
      for(int i = 0; i < grid.length; i++)
         if(grid[vertices.get(from)][ i] == 1)
            temp.add(nameList.get(i));
      return temp;
   }
}
