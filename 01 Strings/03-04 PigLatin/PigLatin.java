// Name: Saina Shibili
// Date: 09/18/2020
import java.util.*;
import java.io.*;
public class PigLatin
{
   public static void main(String[] args) 
   {
      //part_1_using_pig();
      part_2_using_piglatenizeFile();
      
      /*  extension only    */
      String pigLatin = pig("What!?");
      System.out.print(pigLatin + "\t\t" + pigReverse(pigLatin));   //Yahwta!?
      pigLatin = pig("{(Hello!)}");
      System.out.print("\n" + pigLatin + "\t\t" + pigReverse(pigLatin)); //{(Yaholle!)}
      pigLatin = pig("\"McDonald???\"");
      System.out.println("\n" + pigLatin + "  " + pigReverse(pigLatin));//"YaDcmdlano???"
   }

   public static void part_1_using_pig()
   {
      Scanner sc = new Scanner(System.in);
      while(true)
      {
         System.out.print("\nWhat word? ");
         String s = sc.next();
         if(s.equals("-1"))
         {
            System.out.println("Goodbye!"); 
            System.exit(0);
         }
         String p = pig(s);
         System.out.println( p );
      }		
   }

   public static final String punct = ",./;:'\"?<>[]{}|`~!@#$%^&*()";
   public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
   public static final String vowels = "AEIOUaeiou";
   public static String pig(String s)
   {
      String vowels2 = new String(vowels);
      if(s.length() == 0)
         return "";
   
      //remove and store the beginning punctuation 
      int indexLet = Integer.MAX_VALUE;
      for(int i = 0; i < s.length(); i++)
         for(int j = 0; j < letters.length(); j++)
            if(s.charAt(i) == letters.charAt(j))
               if(i < indexLet)
                  indexLet = i;
      String punctBeg = s.substring(0, indexLet);
      s = s.substring(indexLet);  
           
      //remove and store the ending punctuation 
      int indexLetLast = Integer.MIN_VALUE;
      for(int i = 0; i < s.length(); i++)
         for(int j = 0; j < letters.length(); j++)
            if(s.charAt(i) == letters.charAt(j))
               if(i > indexLetLast)
                  indexLetLast = i;
      String punctEnd = s.substring(indexLetLast + 1);
      s = s.substring(0, indexLetLast + 1);
         
      //START HERE with the basic case:
      //     y is a vowel if it is not the first letter
      if(s.contains("y") || s.contains("Y"))
         if(!("" + s.charAt(0)).equalsIgnoreCase("y"))
            vowels2 += "Yy";
      //     find the index of the first vowel
      int indexVow = Integer.MAX_VALUE;
      for(int i = 0; i < s.length(); i++)
         for(int j = 0; j < vowels2.length(); j++)
            if(s.charAt(i) == vowels2.charAt(j))
               if((i == 0) || !(s.substring(i-1, i+1).equalsIgnoreCase("qu")))//qu
                  if(i < indexVow)
                     indexVow = i;
      if(indexVow == 0)
         return punctBeg + s + "way" + punctEnd;
      //if no vowel has been found
      if(indexVow == Integer.MAX_VALUE)
         return "**** NO VOWEL ****";
      //is the first letter capitalized?
      if(Character.isUpperCase(s.charAt(0)))
      {
         s = String.valueOf(s.charAt(0)).toLowerCase() + s.substring(1);
         return punctBeg + String.valueOf(s.charAt(indexVow)).toUpperCase() + s.substring(indexVow + 1) + s.substring(0, indexVow) + "ay" + punctEnd;
      }
      //return the piglatinized word
      return punctBeg + s.substring(indexVow) + s.substring(0, indexVow) + "ay" + punctEnd;
   }


   public static void part_2_using_piglatenizeFile() 
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("input filename including .txt: ");
      String fileNameIn = sc.next();
      System.out.print("output filename including .txt: ");
      String fileNameOut = sc.next();
      piglatenizeFile( fileNameIn, fileNameOut );
      System.out.println("Piglatin done!");
   }

/****************************** 
*  piglatinizes each word in each line of the input file
*    precondition:  both fileNames include .txt
*    postcondition:  output a piglatinized .txt file 
******************************/
   public static void piglatenizeFile(String fileNameIn, String fileNameOut) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(fileNameIn));  
      }
      catch(IOException e)
      {
         System.out.println("oops");
         System.exit(0);   
      }
   
      PrintWriter outfile = null;
      try
      {
         outfile = new PrintWriter(new FileWriter(fileNameOut));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
   	//process each word in each line
      while(infile.hasNextLine())
      {
         String next = infile.nextLine();
         String[] line = next.split(" ");  
         for(String str: line)
            outfile.print(pig(str) + " ");
         outfile.println();
      }   
   
      outfile.close();
      infile.close();
   }
   
   /** EXTENSION: Output each PigLatin word in reverse, preserving before-and-after 
       punctuation.  
   */
   public static String pigReverse(String s)
   {
      if(s.length() == 0)
         return "";
      //remove and store the beginning punctuation 
      int indexLet = Integer.MAX_VALUE;
      for(int i = 0; i < s.length(); i++)
         for(int j = 0; j < letters.length(); j++)
            if(s.charAt(i) == letters.charAt(j))
               if(i < indexLet)
                  indexLet = i;
      String punctBeg = s.substring(0, indexLet);
      s = s.substring(indexLet);  
           
      //remove and store the ending punctuation 
      int indexLetLast = Integer.MIN_VALUE;
      for(int i = 0; i < s.length(); i++)
         for(int j = 0; j < letters.length(); j++)
            if(s.charAt(i) == letters.charAt(j))
               if(i > indexLetLast)
                  indexLetLast = i;
      String punctEnd = s.substring(indexLetLast + 1);
      s = s.substring(0, indexLetLast + 1);
      
      String empty = "";
      for(int i = 0; i < s.length(); i++) 
         empty += String.valueOf(s.charAt(s.length() - i - 1));
         
      //is the first letter capitalized?
      if(Character.isUpperCase(s.charAt(0)))
      {
         return punctBeg + String.valueOf(s.charAt(s.length() - 1)).toUpperCase() + empty.substring(1, empty.length() - 1) + String.valueOf(s.charAt(0)).toLowerCase() + punctEnd;
      }
        
      return punctBeg + empty + punctEnd;
   }
}
