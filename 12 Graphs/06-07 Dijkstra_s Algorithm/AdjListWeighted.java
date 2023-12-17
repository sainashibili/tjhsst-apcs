// Name: Saina Shibili
// Date: 05/21/2021
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs6: Dijkstra
 *              Graphs7: Dijkstra with Cities
 */

class Edge 
{
   public final wVertex target;  //if it's public, you don't need accessor methods
   public final double weight;   //if it's public, you don't need accessor methods
  
   public Edge(wVertex argTarget, double argWeight) 
   {
      target = argTarget;
      weight = argWeight;
   }
}

interface wVertexInterface 
{
   String getName();
   double getMinDistance();
   void setMinDistance(double m);
   wVertex getPrevious();   //for Dijkstra 7
   void setPrevious(wVertex v);  //for Dijkstra 7
   ArrayList<Edge> getAdjacencies();
   void addEdge(wVertex v, double weight);   
   int compareTo(wVertex other);
}

class wVertex implements Comparable<wVertex>, wVertexInterface
{
   private final String name;
   private ArrayList<Edge> adjacencies;
   private double minDistance = Double.POSITIVE_INFINITY;
   private wVertex previous;  //for building the actual path in Dijkstra 7
   public wVertex(String s){
      name = s;
      adjacencies = new ArrayList< >();
      previous = null;
   }
   
   /*  enter your code for this class here   */ 
   public String getName(){
      return name;
   }
   
   public double getMinDistance(){
      return minDistance;
   }
   
   public void setMinDistance(double m){
      minDistance = m;
   }
   
   public ArrayList<Edge> getAdjacencies(){
      return adjacencies;
   }
   
   public void addEdge(wVertex v, double weight){
      adjacencies.add(new Edge(v, weight));
   }
   
   public int compareTo(wVertex other){
      return (int)(minDistance - other.getMinDistance());
   }
   
   //Djikstra 7
   public wVertex getPrevious(){   //for Dijkstra 7
      return previous;
   }
   
   public void setPrevious(wVertex v){  //for Dijkstra 7
      previous = v;
   }
}

interface AdjListWeightedInterface 
{
   List<wVertex> getVertices();
   Map<String, Integer> getNameToIndex();
   wVertex getVertex(int i);   
   wVertex getVertex(String vertexName);
   void addVertex(String v);
   void addEdge(String source, String target, double weight);
   void minimumWeightPath(String vertexName);   //Dijkstra's
}

/* Interface for Graphs 7:  Dijkstra with Cities 
 */

interface AdjListWeightedInterfaceWithCities 
{       
   List<String> getShortestPathTo(wVertex v);
   AdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException ;
}
 

public class AdjListWeighted implements AdjListWeightedInterface, AdjListWeightedInterfaceWithCities
{
   private List<wVertex> vertices = new ArrayList<wVertex>();
   private Map<String, Integer> nameToIndex = new HashMap<String, Integer>();
   //the constructor is a no-arg constructor 
   public AdjListWeighted(){}
   
   /*  enter your code for Graphs 6 */ 
   public List<wVertex> getVertices(){
      return vertices;
   }
   
   public Map<String, Integer> getNameToIndex(){
      return nameToIndex;
   }
   
   public wVertex getVertex(int i){
      return vertices.get(i);
   }
   
   public wVertex getVertex(String vertexName){
      return vertices.get(nameToIndex.get(vertexName));
   }
   
   public void addVertex(String v){
      if(!nameToIndex.containsKey(v)){
         vertices.add(new wVertex(v));
         nameToIndex.put(v, vertices.size() - 1);}
   }
   
   public void addEdge(String source, String target, double weight){
      if(!nameToIndex.containsKey(target))
         addVertex(target);
      if(!nameToIndex.containsKey(source))
         addVertex(source);
      vertices.get(nameToIndex.get(source)).addEdge(vertices.get(nameToIndex.get(target)), weight);
   }
   
   public void minimumWeightPath(String vertexName){   //Dijkstra's
      minimumWeightPath(vertices.get(nameToIndex.get(vertexName)));
   }
   
   public void minimumWeightPath(wVertex source){
      PriorityQueue<wVertex> pq = new PriorityQueue< >();
      for(wVertex w : vertices)
         w.setMinDistance(Double.POSITIVE_INFINITY);
      source.setMinDistance(0);
      pq.add(source);
      while(!pq.isEmpty()){
         wVertex v = pq.remove();
         for(Edge e : v.getAdjacencies()){
            if(e.weight + v.getMinDistance() < e.target.getMinDistance()){
               e.target.setMinDistance(e.weight + v.getMinDistance());
               e.target.setPrevious(v);
               pq.add(e.target);
            }               
         }
      }
   }
   
   /*  enter your code for two new methods in Graphs 7 */
   public List<String> getShortestPathTo(wVertex v){
      Stack<String> s = new Stack< >();
      ArrayList<String> temp = new ArrayList<String>();
      wVertex w = v;
      while(w.getPrevious() != null){
         s.push(w.getPrevious().getName());
         w = w.getPrevious();  
      }
      while(!s.isEmpty())
         temp.add(s.pop());
      temp.add(v.getName());
      return temp;
   }
   
   public AdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException{
      AdjListWeighted temp = new AdjListWeighted();
      Scanner sc = new Scanner(vertexNames);
      sc.next();
      while(sc.hasNext())
         temp.addVertex(sc.next());   
      sc = new Scanner(edgeListData);
      while(sc.hasNextLine()){
         String[] s = sc.nextLine().split(" ");
         temp.addEdge(s[0], s[1], Double.parseDouble(s[2]));
      } 
      return temp;     
   }   
}   


