public class PracticeIt
{
   public static void main(String[] args)
   {
      System.out.println(m6(5, 3)); 
   }
   public static int m6(int x, int y)
   {
      if(y==0||x==y)
         return 1;
      else if(y > x)
         return 0;
      else 
         return m6(x-1, y-1) + m6(x-1, y);
   }
}