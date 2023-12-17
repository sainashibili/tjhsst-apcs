// Name:Saina Shibili 
// Date:12/9/2020

/**
 * Implements the cheat sheet's List interface.  Implements generics.
 * The backing array is an array of (E[]) new Object[10];
 * @override toString()
 */ 
public class TJArrayList<E>
{
   private int size;							//stores the number of objects
   private E[] myArray;
   public TJArrayList()                //default constructor makes 10 cells
   {
      myArray = (E[]) new Object[10];
      size = 0;
   }
   public int size()
   {
      int count = 0;
      if(size == 0)
         for(int i = 0; i < myArray.length; i++)
            if(myArray[i] != null)
               count++;
      return size;   
   }
 	/* appends obj to end of list; increases size;
      must be an O(1) operation when size < array.length, 
         and O(n) when it doubles the length of the array.
	  @return true  */
   public boolean add(E obj)
   {
      if(size < size())
         return false;
      E[] temp = (E[]) new Object[size() + 1];
      for(int i = 0; i < size; i++)
         temp[i] = myArray[i];
      temp[size()] = obj;
      myArray = temp;
      size++;
      return true;         
   }
   /* inserts obj at position index.  increments size. 
		*/
   public void add(int index, E obj) throws IndexOutOfBoundsException  //this the way the real ArrayList is coded
   {
      if(index > size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      E[] temp = (E[]) new Object[size() + 1];
      for(int i = 0; i < index; i++)
         temp[i] = myArray[i]; 
      temp[index] = obj;
      for(int i = index + 1; i < size() + 1; i++)
         temp[i] = myArray[i - 1];
      myArray = temp;
      size++;
   }

   /* return obj at position index.  
		*/
   public E get(int index) throws IndexOutOfBoundsException
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      return myArray[index];
   }
   /**
    * Replaces obj at position index. 
    * @return the object is being replaced.
    */  
   public E set(int index, E obj) throws IndexOutOfBoundsException  
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      E temp = myArray[index];
      myArray[index] = obj;
      return temp;
   }
 /*  removes the node from position index. shifts elements 
     to the left.   Decrements size.
	  @return the object at position index.
	 */
   public E remove(int index) throws IndexOutOfBoundsException  
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      E[] temp = (E[]) new Object[size()-1];
      E t = myArray[index];
      for(int i = 0; i < index; i++)
         temp[i] = myArray[i]; 
      for(int i = index + 1; i < myArray.length; i++)
         temp[i - 1] = myArray[i];
      myArray = temp;
      size--;
      return t;
   }
	   /*
		   This method compares objects.
         Must use .equals(), not ==
     	*/
   public boolean contains(E obj)
   {
      for(E temp: myArray)
         if(temp == obj)
            return true;
      return false;
   }
	 /*returns a String of E objects in the array with 
       square brackets and commas.
     	*/
   public String toString()
   {
      String s = "[";
      for(int i = 0; i < myArray.length - 1; i++)
         s += myArray[i] + ", ";
      s += myArray[size() - 1] + "]";
      return s;   
   }
}