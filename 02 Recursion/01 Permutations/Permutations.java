// Name: Saina Shibili
// Date: 10/02/2020
  
import java.util.*;
public class Permutations
{
   public static int count = 0;
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("\nHow many digits? ");
      int n = sc.nextInt();
      //leftRight("", n);  
             
      //oddDigits("", n);
      if(n == 0)
         System.out.println("Error: 0 digits");
      else
         superprime(n);
      if(count==0)
         System.out.println("no superprimes");
      else
         System.out.println("Count is "+count);
   }
   
    /**
     * Builds all the permutations of a string of length n containing Ls and Rs
     * @param s A string 
     * @param n An postive int representing the length of the string
     */
   private static int c = 0;
   public static void leftRight(String s, int n)
   {
      if(s.length() == n){
         System.out.println(s);
         return;     }
      String newStr = s + "L";
      leftRight(newStr, n);
      newStr = s + "R";
      leftRight(newStr, n);
   }
   
    /**
     * Builds all the permutations of a string of length n containing odd digits
     * @param s A string 
     * @param n A postive int representing the length of the string
     */
   public static void oddDigits(String s, int n)
   {
      String newStr = "";
      if(s.length() == n){
         System.out.println(s);
         return;     }
      for(int i = 1; i <= 9; i+=2){
         newStr = s + "" + i;
         oddDigits(newStr, n);   }   
   }
      
    /**
     * Builds all combinations of a n-digit number whose value is a superprime
     * @param n A positive int representing the desired length of superprimes  
     */
   public static void superprime(int n)
   {
      recur(2, n); //try leading 2, 3, 5, 7, i.e. all the single-digit primes
      recur(3, n); 
      recur(5, n);
      recur(7, n);
   }

    /**
     * Recursive helper method for superprime
     * @param k The possible superprime
     * @param n A positive int representing the desired length of superprimes
     */
   private static void recur(int k, int n)
   {
      String s = "" + k;
      if(s.length() == n){
         if(isSuperprime(k)){
            count++;
            System.out.println(s);  }
         return;  }    
      String newstr = s + "1";
      recur(Integer.parseInt(newstr), n);
      newstr = s + "3";
      recur(Integer.parseInt(newstr), n);
      newstr = s + "7";
      recur(Integer.parseInt(newstr), n);
      newstr = s + "9";
      recur(Integer.parseInt(newstr), n);
   }

    /**
     * Determines if the parameter is a prime number.
     * @param n An int.
     * @return true if prime, false otherwise.
     */
   public static boolean isPrime(int n)
   {
      for(int i = n/2; i >= 2; i--)
         if(n % i == 0)
            return false;
      return true;
   }
   
   public static boolean isSuperprime(int k)
   {
      if(k < 10 && isPrime(k))
         return true;
      if(isPrime(k))
         return isSuperprime(k/10);
      else
         return false;
   }  
}
