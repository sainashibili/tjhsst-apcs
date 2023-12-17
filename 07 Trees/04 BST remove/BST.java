// Name: Saina Shibili
// Date: 02/17/2021

interface BSTinterface
{
   public int size();
   public boolean contains(String obj);
   public void add(String obj);
   //public void addBalanced(String obj);
   public void remove(String obj);
   public String min();
   public String max();
   public String display();
   public String toString();
}

/*******************
Copy your BST code.  Implement the remove() method.
Test it with BST_Delete.java
**********************/
public class BST implements BSTinterface
{
   private TreeNode root;
   private int size;
   public BST()
   {
      root = null;
      size = 0;
   }
   public int size()
   {
      return size; 
   }
   public TreeNode getRoot()   //for Grade-It
   {
      return root;
   }
   /***************************************
   @param s -- one string to be inserted
   ****************************************/
   public void add(String s) 
   {
      root = add(root, s);
      size++;
   }
   private TreeNode add(TreeNode t, String s) //recursive helper method
   {      
      if(t == null)
         return new TreeNode(s); 
      if(s.compareTo(t.getValue().toString()) <= 0)
         t.setLeft(add(t.getLeft(), s));
      else
         t.setRight(add(t.getRight(), s));
      return t;
   
   }
   
   public String display()
   {
      return display(root, 0);
   }
   private String display(TreeNode t, int level) //recursive helper method
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
   
   public boolean contains( String obj)
   {
      return contains(root, obj);
   }
   private boolean contains(TreeNode t, String x) //recursive helper method
   {
      if(t == null)
         return false;
      if(x.compareTo(t.getValue().toString()) <  0)
         return contains(t.getLeft(), x);
      if(x.compareTo(t.getValue().toString()) >  0)
         return contains(t.getRight(), x);
      return true; 
   
   }
   
   public String min()
   {
      return min(root);
   }
   private String min(TreeNode t)  //use iteration
   {
      if(t == null)
         return null;	
      while(t.getLeft() != null)
         t = t.getLeft();
      return t.getValue().toString(); 
   
   }
   
   public String max()
   {
      return max(root);
   }
   private String max(TreeNode t)  //recursive helper method
   {
      if(t == null)
         return null;
      if(t.getRight() == null)
         return t.getValue().toString();
      return max(t.getRight()); 
   
   }
   
   public String toString()
   {
      return toString(root);
   }
   private String toString(TreeNode t)  //an in-order traversal.  Use recursion.
   {
      String toReturn = "";  
      if(t == null)
         return "";   
      toReturn += toString(t.getLeft());    //recurse left
      toReturn += t.getValue() + " ";					//inorder visit
      toReturn += toString(t.getRight());   //recurse right
      return toReturn;
   
   }

  //remove method 
   public void remove(String target)
   {
      root = remove(root, target);
      size--;
   }
   private TreeNode remove(TreeNode current, String target)
   {
      //Case 1b
      if(current.getValue().toString().equals(target) && current.getLeft() == null && current.getRight() == null)
         return null;
         
      //Find the value
      TreeNode parent = current;
      TreeNode c = current;
      while(!c.getValue().toString().equals(target)){
         if(target.compareTo(c.getValue().toString()) <= 0){
            parent = c;
            c = c.getLeft();  }
         else if(target.compareTo(c.getValue().toString()) > 0){
            parent = c;
            c = c.getRight(); }
      }
      
      //Case 1a
      if(c.getLeft() == null && c.getRight() == null && c != current){
         if(parent.getLeft() == c)
            parent.setLeft(null);
         else
            parent.setRight(null);
         return current;
      }
      
      //Case 2d
      if(c.getLeft() == null && c.getRight() != null && c == current){
         return c.getRight();
      }
      //Case 2c
      if(c.getLeft() != null && c.getRight() == null && c == current){
         return c.getLeft();
      }
      //Case 2b
      if(c.getLeft() != null && c.getRight() == null){
         if(parent.getLeft() == c)
            parent.setLeft(c.getLeft());
         else
            parent.setRight(c.getLeft());
         return current;
      } 
      //Case 2a
      if(c.getLeft() == null && c.getRight() != null){
         if(parent.getLeft() == c)
            parent.setLeft(c.getRight());
         else
            parent.setRight(c.getRight());
         return current;
      }  
      
      //Case 3
      if(c.getLeft() != null && c.getRight() != null){
      //Case 3c
         if(c.getLeft().getRight() == null && c.getLeft().getLeft() == null){
            c.setValue(c.getLeft().getValue());
            c.setLeft(null);
            return current;
         }
      //findMax
         TreeNode max = c.getLeft();
         TreeNode mParent = c;
         while(max.getRight() != null){
            mParent = max;
            max = max.getRight();
         }
      
         //Case 3a, 3d
         if(mParent.getLeft() == max){
            c.setValue(max.getValue());
            //3a
            if(max.getLeft() == null && max.getRight() == null)
               mParent.setLeft(null);
            //3d
            else
               mParent.setLeft(max.getLeft());
         }
         
         //Case 3a, 3b
         else{
            c.setValue(max.getValue());
            //3a
            if(max.getLeft() == null && max.getRight() == null)
               mParent.setRight(null);
            //3d
            else
               mParent.setRight(max.getLeft());
         }
      }
      
      return current;
   }
}
