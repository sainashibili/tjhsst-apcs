//Name: Saina Shibili         
//Date: 01/21/2020
import java.util.*;

public class AssemblyLine_Driver
{
   static int NDISKS = 50;
   static int MAXRADIUS = 100;
   public static void main(String[] args)
   {
      AssemblyLine a = new AssemblyLine(NDISKS, MAXRADIUS);
      a.showInput();
      a.process();
      a.showOutput();
   }
}
   
class AssemblyLine
{
   private Queue<Disk> assemblyLineIn;
   private Queue<Pyramid> assemblyLineOut;
   private Pyramid robotArm;
   	/**
   		* initializes this object so the assemblyLineIn contains 
   		* nDisks with random radii;  assemblyLineOut is initialized to * an empty Queue; robotArm is initialized to an empty Pyramid.
   		**/
   	//Part A
   public AssemblyLine( int nDisks, int maxRadius )
   {	
      assemblyLineIn = new LinkedList<Disk>();
      for(int i = 0; i < nDisks; i++)
         assemblyLineIn.add(new Disk((int)(Math.random() * maxRadius + 1)));
      assemblyLineOut = new LinkedList<Pyramid>();
      robotArm = new Pyramid();
   }
   
   	/**
   		* "flips over" the pyramid in the robotArm and adds it to the
   		* assemblyLineOut queue.
   		* Precondition:  robotArm is not empty and holds an inverted 
   		*				pyramid of disks
   		**/
   	// Part B
   private void unloadRobot()
   { 
      Pyramid t = (Pyramid)robotArm.clone();
      Queue<Disk> q = new LinkedList< >();
      while(!t.isEmpty())
         q.add(t.pop());
      while(!q.isEmpty())
         t.push(q.remove());
      assemblyLineOut.add(t);
   }
   
   	/**
   		* processes all disks from assemblyLineIn; a disk is processed
   		* as follows:  if robotArm is not empty and the next disk does
   		* not fit on top of robotArm (which must be an inverted 
   		* pyramid) then robotArm is unloaded first; the disk from
   		* assemblyLineIn is added to robotArm; when all the disks
   		* have been retrieved from assemblyLineIn, robotArm is unloaded.
   		*  Precondition:  robotArm is empty;
   		*				assemblyLineOut is empty
   		**/
   	//Part C
   public void process()
   {
      while(!assemblyLineIn.isEmpty()){
         if(!robotArm.isEmpty() && assemblyLineIn.peek().compareTo(robotArm.peek()) <= 0){
            unloadRobot();
            while(!robotArm.isEmpty())
               robotArm.pop();
            robotArm.push(assemblyLineIn.remove());
         }
         else
            robotArm.push(assemblyLineIn.remove());      
      }
      unloadRobot();
   }
      
   public void showInput()
   {
      System.out.println(assemblyLineIn);
   }
   public void showOutput()
   {
      System.out.println(assemblyLineOut);
   }
}
   //Disk is standard and straightforward.
class Disk implements Comparable<Disk>
{
   private int radius;
   
   public Disk(int r){
      radius = r;
   }
   
   public int getRadius(){
      return radius;
   }
   
   public void setRadius(int r){
      radius = r;
   }
   
   public int compareTo(Disk d){
      if(getRadius() > d.getRadius())
         return 1;
      if(getRadius() < d.getRadius())
         return -1;
      return 0;
   }
   
   public String toString(){
      return "" + radius;
   }  
}
   //Pyramid is sly.
class Pyramid extends Stack<Disk>
{
   public Pyramid invert(){
     
      LinkedList<Disk> temp = new LinkedList< >();
      Pyramid t = new Pyramid();
      while(!isEmpty()){
         temp.addFirst(pop());
      }
      for(int i = 0; i < temp.size(); i++){
         t.push(temp.get(i));
      }
      return t;
   }	
}