// Name: Saina Shibili
// Date: 10/14/2020

import java.util.*;
import java.io.*;

public class AreaFill
{
   private static char[][] grid = null;
   private static String filename = null;

   public static void main(String[] args) 
   {
      Scanner sc = new Scanner(System.in);
      while(true)  //while sc is open prompts for the filename
      {
         System.out.print("Fill the Area of (-1 to exit): ");
         filename = sc.next();
         if(filename.equals("-1"))
         {
            sc.close();
            System.out.println("Good-bye");
            //System.exit(0); 
            return;  
         }
         grid = read(filename);
         String theGrid = display(grid);
         System.out.println( theGrid );
         System.out.print( "1-Fill or 2-Fill-and-Count: ");
         int choice = sc.nextInt();
         switch(choice)
         {
            case 1:
            {
               System.out.print("\nEnter ROW COL to fill from: ");
               int row = sc.nextInt();
               int col = sc.nextInt(); 
               fill(grid, row, col, grid[row][col]);
               System.out.println( display(grid) );
               break;
            }
            case 2:
            {
               System.out.print("\nEnter ROW COL to fill from: ");
               int row = sc.nextInt();
               int col = sc.nextInt();
               int count = fillAndCount(grid, row, col, grid[row][col]);
               System.out.println( display(grid) );
               System.out.println("count = " + count);
               System.out.println();
               break;
            }
            default:
               System.out.print("\nTry again! ");
         }
      }
   }
   
   /**
    * Reads the contents of the file into a matrix.
    * Uses try-catch.
    * @param filename The string representing the filename.
    * @returns A 2D array of chars.
    */
   public static char[][] read(String filename)
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(filename));
      }
      catch (Exception e)
      {
         System.out.println("File not found");
         return null;
      }
      /* enter your code here */
      int row = infile.nextInt();
      int col = infile.nextInt();
      char[][] temp = new char[row][col];
      for(int k = 0; k < row; k++){
         String s = infile.next();
         for(int i = 0; i < s.length(); i++)
            temp[k][i] = s.charAt(i);  }
      return temp;
   }
   
   /**
    * @param g A 2-D array of chars.
    * @returns A string representing the 2D array.
    */
   public static String display(char[][] g)
   {
      String s = "";
      for(int i = 0; i < g.length; i++)
         for(int k = 0; k < g[0].length; k++){
            s += "" + g[i][k];
            if(k == g[1].length - 1)
               s += "\n";}
      return s;
   }
   
   /**
    * Fills part of the matrix with a different character.
    * @param g A 2D char array.
    * @param r An int representing the row of the cell to be filled.
    * @param c An int representing the column of the cell to be filled.
    * @param ch The char which is being replaced.
    */
   public static void fill(char[][] g, int r, int c, char ch)
   {
      g[r][c] = '*';
      if(r + 1 < g.length && ("" + g[r+1][c]).compareTo("" + ch) == 0)
         fill(g, r+1, c, g[r+1][c]);
      if(!(r - 1 < 0) && ("" + g[r-1][c]).compareTo("" + ch) == 0)
         fill(g, r-1, c, g[r-1][c]);
      if(c + 1 < g[0].length && ("" + g[r][c+1]).compareTo("" + ch) == 0)
         fill(g, r, c+1, g[r][c+1]);
      if(!(c - 1 < 0) && ("" + g[r][c-1]).compareTo("" + ch) == 0)
         fill(g, r, c-1, g[r][c-1]);
   }
   
   /**
    * Fills part of the matrix with a different character.
    * Counts as you go.  Does not use a static variable.
    * @param g A 2D char array.
    * @param r An int representing the row of the cell to be filled.
    * @param c An int representing the column of the cell to be filled.
    * @param ch The char which is being replaced.
    * @return An int representing the number of characters that were replaced.
    */
   public static int fillAndCount(char[][] g, int r, int c, char ch)
   {
      int count = 1;
      g[r][c] = '*';
      if(r + 1 < g.length && ("" + g[r+1][c]).compareTo("" + ch) == 0)
         count+= fillAndCount(g, r+1, c, g[r+1][c]);
      if(!(r - 1 < 0) && ("" + g[r-1][c]).compareTo("" + ch) == 0)
         count+= fillAndCount(g, r-1, c, g[r-1][c]);
      if(c + 1 < g[0].length && ("" + g[r][c+1]).compareTo("" + ch) == 0)
         count+= fillAndCount(g, r, c+1, g[r][c+1]);
      if(!(c - 1 < 0) && ("" + g[r][c-1]).compareTo("" + ch) == 0)
         count+= fillAndCount(g, r, c-1, g[r][c-1]);
           //never reached
      return count;
   }
}