 //Name: Saina Shibili
 //Date: 03/06/2021
 
import java.util.*;


/* implement the API for java.util.PriorityQueue
 * test this class with HeapPriorityQueue_Driver.java.
 * test this class with LunchRoom.java.
 * add(E) and remove()  must work in O(log n) time
 */
public class HeapPriorityQueue<E extends Comparable<E>> 
{
   private ArrayList<E> myHeap;
   
   public HeapPriorityQueue()
   {
      myHeap = new ArrayList<E>();
      myHeap.add(null);
   }
   
   public boolean add(E obj)
   {
      myHeap.add(obj);
      heapUp(myHeap.size() - 1); 
      return true;
   }
   
   public E remove()
   {
      E temp = myHeap.get(1);
      swap(1, myHeap.size() - 1);
      myHeap.remove(myHeap.size() - 1);
      heapDown(1, myHeap.size());
      return temp;
   }
   
   public E peek()
   {
      if(isEmpty())
         return null;
      return myHeap.get(1);
   }
   
   public boolean isEmpty()
   {
      if(myHeap.size() == 1 && myHeap.get(0) == null)
         return true;
      return false;
   }
   
   private void heapUp(int k)
   {
      if(k != 1 && myHeap.get(k).compareTo(myHeap.get(k/2)) < 0){
         swap(k, k/2);
         heapUp(k/2);
      } 
   }
   
   private void swap(int a, int b)
   {
      E temp = myHeap.get(a);
      myHeap.set(a, myHeap.get(b));
      myHeap.set(b, temp);
   }
   
   private void heapDown(int k, int size)
   {
      if(2 * k + 1 < size){
         if(myHeap.get(k).compareTo(myHeap.get(2 * k)) > 0 || myHeap.get(k).compareTo(myHeap.get(2 * k + 1)) > 0){
            if(myHeap.get(2 * k).compareTo(myHeap.get(2 * k + 1)) < 0){
               swap(k, 2 * k);
               heapDown(2 * k, size);
            }
            else{
               swap(k, 2 * k + 1);
               heapDown(2 * k + 1, size);
            }}}
      else if(2 * k < size){
         if(myHeap.get(k).compareTo(myHeap.get(2 * k)) > 0){
            swap(k, 2 * k);
            heapDown(2 * k, size);
         }}
   }
   
   public String toString()
   {
      return myHeap.toString();
   }  
}
