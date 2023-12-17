//Name: Saina Shibili
//Date: 02/24/2021

interface BSTinterface
{
   public int size();
   public boolean contains(String obj);
   public void add(String obj);
   public void addBalanced(String obj);
   //public boolean remove(String obj);
   public String min();
   public String max();
   public String toString();
}

/*******************
Represents a binary search tree holding Strings. 
Implements (most of) BSTinterface, above. 
The recursive methods all have a public method calling a private helper method. 
Copy the display() method from TreeLab. 
**********************/
class BST implements BSTinterface
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
   
   public void addBalanced(String value)  //new method
   {
      add(value);
      root = recurBalance(value, root); 
   } 
   private TreeNode recurBalance(String value, TreeNode t){
      if(t == null)
         return null;
      t.setLeft(recurBalance(value, balanceTree(value, t.getLeft())));
      t.setRight(recurBalance(value, balanceTree(value, t.getRight())));
      return balanceTree(value, t);
   }

   private TreeNode balanceTree(String value, TreeNode t){
      //right
      if(calcBalance(t) < -1){
         //right
         if(value.compareTo((String)t.getRight().getValue()) > 0)
            return rightRotate(t);
         //left
         t.setRight(leftRotate(t.getRight()));
         return rightRotate(t);        
      }
      //left
      else if(calcBalance(t) > 1){
         //left
         if(value.compareTo((String)t.getLeft().getValue()) <= 0)
            return leftRotate(t);
         //right
         t.setLeft(rightRotate(t.getLeft()));
         return leftRotate(t);        
      }
      
      return t;
   }
   
   private int calcBalance(TreeNode t){
      if(t == null)
         return 0;
      return height(t.getLeft()) - height(t.getRight());
   }
   private int height(TreeNode t){  //from TreeLab
      if(t == null)
         return -1;
      else{
         int a = 1 + height(t.getLeft());
         int b = 1 + height(t.getRight());
         if(a > b)
            return a;
         return b;
      }
   }
   
   private TreeNode leftRotate(TreeNode t){
      if((t.getLeft() == null))
         return t;
         
      TreeNode a = t.getLeft();
      if(a.getRight() != null){
         TreeNode b = a.getRight();
      
         t.setLeft(b);  }
      else
         t.setLeft(null);
   
      a.setRight(t);
   
      return a;      
   }
   private TreeNode rightRotate(TreeNode t){
      if(t.getRight() == null)
         return t;
      
      TreeNode a = t.getRight();
      if(a.getLeft() != null){
         TreeNode b = a.getLeft();
      
         t.setRight(b); }
      else
         t.setRight(null);
      
      a.setLeft(t);
   
      return a;
   
   }
}
