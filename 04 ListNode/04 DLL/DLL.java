// Name: Saina Shibili
// Date: 12/4/2020

//  implements some of the List and LinkedList interfaces: 
//	 	  size(), add(i, o), remove(i);  addFirst(o), addLast(o); 
//  This class also overrides toString().
//  the list is zero-indexed.
//  Uses DLNode.

public class DLL        //DoubleLinkedList
{
   private int size = 0;
   private DLNode head = new DLNode(); //dummy node--very useful--simplifies the code
   
   public int size()
   {
      DLNode temp = head;
      if(temp == null)
         return 0;
      else if(size != 0)
         return size;
      else{
         while(temp.getNext() != head){
            temp = temp.getNext();  
            size++;  }
         size++;  }
      return size;
   }
   
   /* appends obj to end of list; increases size;
   	  @return true  */
   public boolean add(Object obj)
   {
      addLast(obj);
      return true;   
   }
   
   /* inserts obj at position index.  increments size. 	*/
   public void add(int index, Object obj) throws IndexOutOfBoundsException  //this the way the real LinkedList is coded
   {
      if( index > size || index < 0 )
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      if(head == null)
         head = new DLNode(obj, head, head);
      DLNode temp = head.getNext();
      for(int i = 1; i < index; i++)
         temp = temp.getNext();             
      temp.setPrev(new DLNode(obj, temp.getPrev(), temp));
      temp.getPrev().getPrev().setNext(temp.getPrev());
      size++;              
   }
   
   /* return obj at position index. 	*/
   public Object get(int index)
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode temp = head.getNext();
      for(int i = 1; i < index; i++)
         temp = temp.getNext();
      return temp.getValue();
   }
   
   /* replaces obj at position index. 
        returns the obj that was replaced*/
   public Object set(int index, Object obj)
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode temp = head.getNext();
      for(int i = 1; i < index; i++)
         temp = temp.getNext(); 
      Object t = temp.getValue();              
      temp.setValue(obj);
      return t;
   }
   
   /*  removes the node from position index (zero-indexed).  decrements size.
       @return the object of the node that was removed.    */
   public Object remove(int index)
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode temp = head;
      for(int i = 0; i < index; i++)
         temp = temp.getNext();
      temp = temp.getNext();   
      Object t = temp.getValue();          
      temp.getNext().setPrev(temp.getPrev());
      temp.getPrev().setNext(temp.getNext());
      size--;
      return t;
   }
   
   /* inserts obj at front of list, increases size   */
   public void addFirst(Object obj)
   {
      if(head == null)
         head = new DLNode(obj, head, head);
      head.setNext(new DLNode(obj, head, head.getNext()));
      head.getNext().getNext().setPrev(head.getNext());
      size++;
   }
   
   /* appends obj to end of list, increases size    */
   public void addLast(Object obj)
   {
      if(head == null)
         head = new DLNode(obj, head, head);
      head.setPrev(new DLNode(obj, head.getPrev(), head));
      head.getPrev().getPrev().setNext(head.getPrev());
      size++;
   }
   
   /* returns the first element in this list  */
   public Object getFirst()
   {
      return head.getNext().getValue();
   }
   
   /* returns the last element in this list  */
   public Object getLast()
   {
      return head.getPrev().getValue();
   }  
   
   /* returns and removes the first element in this list, or
       returns null if the list is empty  */
   public Object removeFirst()
   {
      if(head == null)
         return null;
      Object temp = head.getNext().getValue();
      head.getNext().getNext().setPrev(head.getPrev());
      head.setNext(head.getNext().getNext());
      head.getNext().setPrev(head);
      size--;
      return temp;
   }
   
   /* returns and removes the last element in this list, or
       returns null if the list is empty  */
   public Object removeLast()
   {
      if(head == null)
         return null;
      Object temp = head.getPrev().getValue();
      DLNode t = head.getPrev();
      head.setPrev(head.getPrev().getPrev());
      head.getPrev().setNext(head);
      size--;
      return temp;
   }
   
   /*  returns a String with the values in the list in a 
       friendly format, for example   [Apple, Banana, Cucumber]
       The values are enclosed in [], separated by one comma and one space.
    */
   public String toString()
   {
      DLNode temp = head.getNext();
      String s = "[";
      while(temp.getNext() != head){
         s += temp.getValue() + ", ";
         temp = temp.getNext();  }
      s += temp.getValue() + "]";
      return s;
   }
}