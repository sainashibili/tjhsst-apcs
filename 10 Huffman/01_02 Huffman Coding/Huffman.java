//name: Saina Shibili   date: 04/07/2021
import java.util.*;
import java.io.*;
public class Huffman
{
   public static Scanner keyboard = new Scanner(System.in);
   public static void main(String[] args) throws IOException
   {
      //Prompt for two strings 
      System.out.print("Encoding using Huffman codes");
      System.out.print("\nWhat message? ");
      String message = keyboard.nextLine();
   
      System.out.print("\nEnter middle part of filename:  ");
      String middlePart = keyboard.next();
   
      huffmanize( message, middlePart );
   }
   public static void huffmanize(String message, String middlePart) throws IOException
   {
         //Make a frequency table of the letters
      	//Put each letter-frequency pair into a HuffmanTreeNode. Put each 
   		//        node into a priority queue (or a min-heap).     
      	//Use the priority queue of nodes to build the Huffman tree
      	//Process the string letter-by-letter and search the tree for the 
   		//       letter. It's recursive. As you recur, build the path through the tree, 
   		//       where going left is 0 and going right is 1.
         //System.out.println the binary message 
      	//Write the binary message to the hard drive using the file name ("message." + middlePart + ".txt")
         //System.out.println the scheme from the tree--needs a recursive helper method
      	//Write the scheme to the hard drive using the file name ("scheme." + middlePart + ".txt")
      char[] str = message.toCharArray();
      Map<Character, Integer> freq = new HashMap< >();
      for(char temp : str){
         if(freq.containsKey(temp))
            freq.put(temp, freq.get(temp) + 1);
         else
            freq.put(temp, 1);
      }    
      PriorityQueue<HuffmanTreeNode> pq = new PriorityQueue< >();
      for(char temp : freq.keySet()){
         pq.add(new HuffmanTreeNode(temp, freq.get(temp)));
      }
      HuffmanTreeNode node = makeTree(pq);
      String t = "";
      t = huffman(node, t);
         // s += temp + t + "\n";  
      // } 
      System.out.println(t);
      PrintWriter outfile = new PrintWriter(new File("scheme." + middlePart + ".txt"));
      outfile.print(t);
      outfile.close();
      
      PrintWriter outfile2 = new PrintWriter(new File("message." + middlePart + ".txt"));
      outfile2.print(t);
      outfile2.close();
         
   }
   
   //helper method to make binary code
   
   //helper method to recursively make a tree
   private static HuffmanTreeNode makeTree(PriorityQueue<HuffmanTreeNode> pq){
      if(pq.size() == 1)
         return pq.poll();
      HuffmanTreeNode a = pq.poll();
      HuffmanTreeNode b = pq.poll();
      HuffmanTreeNode node = new HuffmanTreeNode('*', a.getValue() + b.getValue(), a, b);
      pq.add(node);
      return makeTree(pq);
   }
   
   //helper method to pass through the String
   private static String huffman(HuffmanTreeNode h, String s){
      String temp = s;
      huffmanVoid(h, temp);
      return temp;
   }
   
   private static void huffmanVoid(HuffmanTreeNode h, String s){
      if(h.getRight() == null && h.getLeft() == null && Character.isLetter((char)h.getKey())){
         System.out.println(h.getKey() + s);
         return;  }
      if(h.getRight() != null)
         huffmanVoid(h.getRight(), s += "1");
      if(h.getLeft() != null)
         huffmanVoid(h.getLeft(), s += "0");
   }
   
}
	/*
	  * This tree node stores two values.  Look at TreeNode's API for some help.
	  * The compareTo method must ensure that the lowest frequency has the highest priority.
	  */
class HuffmanTreeNode implements Comparable<HuffmanTreeNode>
{
   private Object key; 
   private int value;
   private HuffmanTreeNode left, right;
   
   public HuffmanTreeNode(Object initKey, int initValue)
   { 
      key = initKey;
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public HuffmanTreeNode(Object initKey, int initValue, HuffmanTreeNode initLeft, HuffmanTreeNode initRight)
   { 
      key = initKey;
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getKey(){
      return key;
   }
   
   public int getValue()
   { 
      return value; 
   }
   
   public HuffmanTreeNode getLeft() 
   { 
      return left; 
   }
   
   public HuffmanTreeNode getRight() 
   { 
      return right; 
   }
   
   
   public void setKey(Object theNewKey){
      key = theNewKey;  
   }  
   
   public void setValue(int theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(HuffmanTreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(HuffmanTreeNode theNewRight)
   { 
      right = theNewRight;
   }
   
   public int compareTo(HuffmanTreeNode other){
      return getValue() - other.getValue();
   }
     
}
