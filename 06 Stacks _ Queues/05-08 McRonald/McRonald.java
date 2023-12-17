//Updated on 12.14.2020 v2

//Name:Saina Shibili  Date:01/15/2020
import java.util.*;
import java.io.*;
public class McRonald
{
   public static final int TIME = 1080;     //18 hrs * 60 min
   public static double CHANCE_OF_CUSTOMER = .2;
   public static int customers = 0;
   public static int totalMinutes = 0;
   public static int longestWaitTime = 0;
   public static int longestQueue = 0;
   public static int serviceWindow = 0;      // to serve the front of the queue
   public static int thisCustomersTime;
   public static PrintWriter outfile = null; // file to display the queue information
      
   public static int timeToOrderAndBeServed()
   {
      return (int)(Math.random() * 6 + 2);
   }
  
   public static void displayTimeAndQueue(Queue<Customer> q, int min)
   { 
      //Billington's
      outfile.println(min + ": " + q);	
      //Jurj's
      //outfile.println("Customer#" + intServiceAreas[i] + 
      //                            " leaves and his total wait time is " + (intMinute - intServiceAreas[i]));                     	
      
   }
   
   public static int getCustomers()
   {
      return customers;
   }
   public static double calculateAverage()
   {
      return (int)(1.0 * totalMinutes/customers * 10)/10.0;
   }
   public static int getLongestWaitTime()
   {
      return longestWaitTime;
   }
   public static int getLongestQueue()
   {
      return longestQueue;
   }
            
   public static void main(String[] args)
   {     
    //set up file      
      try
      {
         outfile = new PrintWriter(new FileWriter("McRonald 1 Queue 1 ServiceArea.txt"));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
      
      mcRonald(TIME, outfile);   //run the simulation
      
      outfile.close();	
   }
   
   public static void mcRonald(int TIME, PrintWriter of)
   {
      /***************************************
           Write your code for the simulation   
      **********************************/
      Queue<Customer> q = new LinkedList< >();
      for(int i = 0; i < TIME; i++){
         if(Math.random() <= CHANCE_OF_CUSTOMER){
            q.add(new Customer(i, timeToOrderAndBeServed()));
            customers++;   }
         if(!q.isEmpty()){
            if(q.peek().getOrderAndBeServed() > 0)
               q.peek().setOrderAndBeServed(q.peek().getOrderAndBeServed() - 1); 
            else{
               int a = q.peek().getArrivedAt();
               thisCustomersTime = i - q.peek().getArrivedAt();
               q.remove();
               totalMinutes += thisCustomersTime;
               if(thisCustomersTime > longestWaitTime)
                  longestWaitTime = thisCustomersTime;
               if(q.size() > longestQueue)
                  longestQueue = q.size();
            }
            displayTimeAndQueue(q, i);
         }   
      }
      int i = 0;
      while(!q.isEmpty()){
         while(q.peek().getOrderAndBeServed() > 0){
            i++;
            q.peek().setOrderAndBeServed(q.peek().getOrderAndBeServed() - 1);
            displayTimeAndQueue(q, TIME + i);
         }
         thisCustomersTime = i - q.peek().getArrivedAt();
         q.remove();
         totalMinutes += thisCustomersTime;
         if(thisCustomersTime > longestWaitTime)
            longestWaitTime = thisCustomersTime;
         if(q.size() > longestQueue)
            longestQueue = q.size();
      }
              
      /*   report the data to the screen    */  
      System.out.println("1 queue, 1 service window, probability of arrival = "+ CHANCE_OF_CUSTOMER);
      System.out.println("Total customers served = " + getCustomers());
      System.out.println("Average wait time = " + calculateAverage());
      System.out.println("Longest wait time = " + longestWaitTime);
      System.out.println("Longest queue = " + longestQueue);
   }
   
   static class Customer      
   {
      private int arrivedAt;
      private int orderAndBeServed;
      
    /**********************************
       Complete the Customer class with  
       constructor, accessor methods, toString.
    ***********************************/
      public Customer(int a, int o){
      
         arrivedAt = a;
         orderAndBeServed = o;
      
      }
   
      public int getArrivedAt(){
         return arrivedAt;
      }
   
      public int getOrderAndBeServed(){
         return orderAndBeServed;
      }
      
      public void setArrivedAt(int a){
         arrivedAt = a;
      }
   
      public void setOrderAndBeServed(int a){
         orderAndBeServed = a;
      }
      
      public String toString(){
         return arrivedAt + "";
      }
   }
}