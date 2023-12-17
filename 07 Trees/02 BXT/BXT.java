// Name: Saina Shibili
// Date: 02/10/2021
/*  Represents a binary expression tree.
 *  The BXT builds itself from postorder expressions. It can
 *  evaluate and print itself.  Also prints inorder and postorder strings. 
 */
 
import java.util.*;

public class BXT
{
   private TreeNode root;   
   
   public BXT()
   {
      root = null;
   }
   public TreeNode getRoot()   //for Grade-It
   {
      return root;
   }
    
   public void buildTree(String str)
   {
      String[] s = str.split(" ");
      Stack<TreeNode> st = new Stack< >();
      if(s.length == 1){
         root = new TreeNode(s[0]);
         return;
      }
      for(String a: s){
         TreeNode t = new TreeNode(a);
         if(isOperator(a)){
            t.setRight(st.pop());
            t.setLeft(st.pop());
         }
         st.push(t);
      } 
      root = st.pop();   	
   }
   
   public double evaluateTree()
   {
      return evaluateNode(root);
   }
   
   private double evaluateNode(TreeNode t)  //recursive
   {
      
      if(t == null)
         return 0;  
      else if(isOperator(t.getValue().toString())){
         return computeTerm(t.getValue().toString(), evaluateNode(t.getLeft()), evaluateNode(t.getRight()));
      } 
      else
         return Double.parseDouble(t.getValue().toString());
   }
   
   private double computeTerm(String s, double a, double b)
   {
      if(s.equals("+"))
         return a + b;
      if(s.equals("-"))
         return a - b;
      if(s.equals("*"))
         return a * b;
      return a / b;
   }
   
   private boolean isOperator(String s)
   {
      if("+-*/".contains(s) && !"0123456789".contains(s))
         return true;
      return false;
   }
   
   public String display()
   {
      return display(root, 0);
   }
   
   private String display(TreeNode t, int level)
   {
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
    
   public String inorderTraverse()
   {
      return inorderTraverse(root);
   }
   
   private  String inorderTraverse(TreeNode t)
   {
      String toReturn = "";  
      if(t == null)
         return "";   
      toReturn += inorderTraverse(t.getLeft());    //recurse left
      toReturn += t.getValue() + " ";					//inorder visit
      toReturn += inorderTraverse(t.getRight());   //recurse right
      return toReturn;
   }
   
   public String preorderTraverse()
   {
      return preorderTraverse(root);
   }
   
   private String preorderTraverse(TreeNode root)
   {
      String toReturn = "";
      if(root == null)
         return "";
      toReturn += root.getValue() + " ";              //preorder visit
      toReturn += preorderTraverse(root.getLeft());   //recurse left
      toReturn += preorderTraverse(root.getRight());  //recurse right
      return toReturn;
   }
   
  /* extension */
   // public String inorderTraverseWithParentheses()
   // {
      // return inorderTraverseWithParentheses(root);
   // }
//    
   // private String inorderTraverseWithParentheses(TreeNode t)
   // {
      // return "";
   // }
}