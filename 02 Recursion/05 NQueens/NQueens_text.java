    // mlbillington@fcps.edu,  10-20-2019

import javax.swing.JFrame;
import java.util.*;
public class NQueens_text
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("N-Queens");
      frame.setSize(400, 400);
      frame.setLocation(200, 100);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Scanner s = new Scanner(System.in);
      System.out.println("NQueens Size: ");
      int size =  s.nextInt();
      NQueens nqueens = new NQueens(size);
      //NQueens nqueens = new NQueens(8);
      frame.setContentPane(nqueens);
      frame.setVisible(true);
                  
      if(nqueens.solve())
         System.out.println("solved");
      else
         //System.out.println("Not solved");
         System.out.println("Solution not Found");
   }
}