// Name: Saina Shibili
// Date: 04/23/2021
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs3: EdgeList,
 *              Graphs4: DFS-BFS
 *          and Graphs5: EdgeListCities
 */

/* Graphs 3: EdgeList 
 */
interface VertexInterface
{
   String toString(); // Don't use commas in the list.  Example: "C [C D]"
   String getName();
   ArrayList<Vertex> getAdjacencies();
   void addAdjacent(Vertex v);
} 

class Vertex implements VertexInterface 
{
   private final String name;
   private ArrayList<Vertex> adjacencies;
  
  /* enter your code here  */
   public Vertex(String s){
      name = s;
      adjacencies = new ArrayList< >();
   }
   public String toString(){
      String temp = name + " [";
      for(Vertex v : adjacencies)
         temp += v.getName() + " ";
      if(temp.charAt(temp.length()-1) == '[')
         return temp + "]";
      return temp.substring(0, temp.length() - 1) + "]";
   }
   public String getName(){
      return name;
   }
   public ArrayList<Vertex> getAdjacencies(){
      return adjacencies;
   }
   public void addAdjacent(Vertex v){
      adjacencies.add(v);
   }
  
}   

interface AdjListInterface 
{ 
   List<Vertex> getVertices();
   Vertex getVertex(int i) ;
   Vertex getVertex(String vertexName);
   Map<String, Integer> getVertexMap();
   void addVertex(String v);
   void addEdge(String source, String target);
   String toString();  //returns all vertices with their edges (omit commas)
}

  
/* Graphs 4: DFS and BFS 
 */
interface DFS_BFS
{
   List<Vertex> depthFirstSearch(String name);
   List<Vertex> depthFirstSearch(Vertex v);
   List<Vertex> breadthFirstSearch(String name);
   List<Vertex> breadthFirstSearch(Vertex v);
   /*  three extra credit methods */
   List<Vertex> depthFirstRecur(String name);
   List<Vertex> depthFirstRecur(Vertex v);
   void depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable);
}

/* Graphs 5: Edgelist with Cities 
 */
interface EdgeListWithCities
{
   void graphFromEdgeListData(String fileName) throws FileNotFoundException;
   int edgeCount();
   int vertexCount(); //count the vertices in the object
   boolean isReachable(String source, String target);
   boolean isConnected();
}


public class AdjList implements AdjListInterface, DFS_BFS, EdgeListWithCities
{
   private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
   private Map<String, Integer> nameToIndex = new TreeMap<String, Integer>();
  
 /* enter your code here  */
   public List<Vertex> getVertices(){
      return vertices;
   }
   public Vertex getVertex(int i){
      return vertices.get(i);
   }
   public Vertex getVertex(String vertexName){
      return vertices.get(nameToIndex.get(vertexName));
   }
   public Map<String, Integer> getVertexMap(){
      return nameToIndex;
   }
   public void addVertex(String v){
      if(!nameToIndex.containsKey(v)){
         vertices.add(new Vertex(v));
         nameToIndex.put(v, vertices.size() - 1);}
   }
   public void addEdge(String source, String target){
      if(!nameToIndex.containsKey(target))
         addVertex(target);
      if(!nameToIndex.containsKey(source))
         addVertex(source);
      vertices.get(nameToIndex.get(source)).addAdjacent(vertices.get(nameToIndex.get(target)));
   }
   public String toString(){
      String temp = "";
      for(Vertex v : vertices)
         temp += v + "\n";
      return temp;
   }
   
   //DFS_BFS
   public List<Vertex> depthFirstSearch(String name){
      return depthFirstSearch(vertices.get(nameToIndex.get(name)));
   }
   public List<Vertex> depthFirstSearch(Vertex v){
      List<Vertex> temp = new ArrayList<Vertex>();
      Stack<Vertex> stack = new Stack<Vertex>();
      stack.push(v);
      while(!stack.isEmpty()){
         if(!temp.contains(stack.peek())){
            temp.add(stack.pop());
            for(Vertex a: temp.get(temp.size() - 1).getAdjacencies())
               stack.push(a); }
         else
            stack.pop();
      }
      return temp;
   }
   public List<Vertex> breadthFirstSearch(String name){
      return breadthFirstSearch(vertices.get(nameToIndex.get(name)));
   }
   public List<Vertex> breadthFirstSearch(Vertex v){
      List<Vertex> temp = new ArrayList<Vertex>();
      Queue<Vertex> q = new LinkedList<Vertex>();
      q.add(v);
      while(!q.isEmpty()){
         if(!temp.contains(q.peek())){
            temp.add(q.remove());
            for(Vertex a: temp.get(temp.size() - 1).getAdjacencies())
               q.add(a); }
         else
            q.remove();
      }
      return temp;
   } 
   
   //EdgeListCities
   public void graphFromEdgeListData(String fileName) throws FileNotFoundException{
      Scanner sc = new Scanner(new File(fileName));
      while(sc.hasNextLine()){
         String[] temp = sc.nextLine().split(" ");
         addVertex(temp[0]);
         for(int i = 1; i < temp.length; i++)
            addEdge(temp[0], temp[i]);
      }
   }
   public int edgeCount(){
      int count = 0;
      for(Vertex v : vertices)
         for(Vertex i : v.getAdjacencies())
            count++;
      return count;
   }
   //count the vertices in the object
   public int vertexCount(){ 
      return vertices.size();
   }
   
   public boolean isReachable(String source, String target){
      List<Vertex> reach = depthFirstSearch(source);
      for(Vertex v : reach)
         if(v.getName().equals(target))
            return true;
      return false;
   }
   public boolean isConnected(){
      for(String s : getVertexMap().keySet())
         for(String t : getVertexMap().keySet())
            if(!isReachable(s, t))
               return false;
      return true; 
   }
   
 
 /*  three extra credit methods, recursive version  */
   public List<Vertex> depthFirstRecur(String name)
   {
      return null;
   }
   
   public List<Vertex> depthFirstRecur(Vertex v)
   {
      return null;
   }
   
   public void depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable)
   {
      
   }   
}


