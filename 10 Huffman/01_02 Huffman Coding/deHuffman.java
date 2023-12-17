//name: Saina Shibili   date: 04/07/2021
import java.util.*;
import java.io.*;
public class deHuffman
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary message (middle part)? ");
      String middlePart = keyboard.next();
      Scanner sc = new Scanner(new File("message."+middlePart+".txt")); 
      String binaryCode = sc.next();
      Scanner huffLines = new Scanner(new File("scheme."+middlePart+".txt")); 
      	
      TreeNode root = huffmanTree(huffLines);
      String message = dehuff(binaryCode, root);
      System.out.println(message);
      	
      sc.close();
      huffLines.close();
   }
   public static TreeNode huffmanTree(Scanner huffLines)
   {
      TreeNode root = new TreeNode("");
      while(huffLines.hasNextLine()){
         char[] c = huffLines.next().toCharArray();
         if(c[0] == '1' || c[0] == '0'){
            char[] space = new char[c.length + 1];
            space[0] = ' ';
            for(int i = 0; i < c.length; i++)
               space[i + 1] = c[i];      
            c = space;
         }
         TreeNode pointer = root;
         for(int i = 1; i < c.length; i++){
            if(c[i] == '1'){
               if(pointer.getRight() == null){
                  pointer.setRight(new TreeNode(""));
                  pointer = pointer.getRight();
               }  
               else
                  pointer = pointer.getRight();
            }
            else{
               if(pointer.getLeft() == null){
                  pointer.setLeft(new TreeNode(""));
                  pointer = pointer.getLeft();
               }
               else
                  pointer = pointer.getLeft();
            }   
         }
         pointer.setValue(c[0] + "");
      }
      return root;
   }
   public static String dehuff(String text, TreeNode root)
   {
      char[] c = text.toCharArray();
      String s = "";
      TreeNode pointer = root;
      for(char temp : c){
         if(!pointer.getValue().equals("")){
            s += pointer.getValue();
            pointer = root;   }
         if(temp == '0' && pointer.getLeft() != null)
            pointer = pointer.getLeft();
         else if(pointer.getRight() != null)
            pointer = pointer.getRight();
      }
      if(!pointer.getValue().equals("")){
         s += pointer.getValue();
         pointer = root;   }
      return s;
   }
}

 /* TreeNode class for the AP Exams */
class TreeNode
{
   private Object value; 
   private TreeNode left, right;
   
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getValue()
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      return left; 
   }
   
   public TreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      right = theNewRight;
   }
}
