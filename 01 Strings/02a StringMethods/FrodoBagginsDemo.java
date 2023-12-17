import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class FrodoBagginsDemo
{
   public static void main (String[] args)
   {
      String frodo = "frodoBaggins";
      System.out.println(" 9: " + frodo);
      String gandalf;
      gandalf = "gandalfTheGrey";
      System.out.println("12: " + gandalf);
      frodo = gandalf;
      System.out.println("14: " + frodo);
      if (frodo == gandalf)
         System.out.println("16: " + "frodo has the same reference as gandalf");
      if (frodo.equals(gandalf)) // also: equalsIgnoreCase()
         System.out.println("18: " + "frodo is an identical string to gandalf");
      for(int i = 0; i < frodo.length(); i++)
         System.out.print("   " + frodo.substring(i,i+1));	
   }}