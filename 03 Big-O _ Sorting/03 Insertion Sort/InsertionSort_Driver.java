 //Name: Saina Shibili  
 //Date: 10/30/2020

import java.util.*;
import java.io.*;

public class InsertionSort_Driver
{
   public static void main(String[] args) throws Exception
   {
      //Part 1, for doubles
      int n = (int)(Math.random()*100)+20;
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random()*100;	
      
      Insertion.sort(array);
      print(array);
      
      if( isAscending(array) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
      System.out.println();
      
      //Part 2, for Strings
      int size = 100;
      Scanner sc = new Scanner(new File("declaration.txt"));
      Comparable[] arrayStr = new String[size];
      for(int k = 0; k < arrayStr.length; k++)
         arrayStr[k] = sc.next();	
   
      Insertion.sort(arrayStr);
      print(arrayStr);
      System.out.println();
      
      if( isAscending(arrayStr) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
   }
   
   public static void print(double[] a)
   {
      for(double d: a)         // for-each loop
         System.out.print(d+" ");
      System.out.println();
   }
   
   public static void print(Object[] papaya)
   {
      for(Object abc : papaya)    
         System.out.print(abc+" ");
   }
   
   public static boolean isAscending(double[] a)
   {
      boolean temp = false;
      for(int i = 1; i < a.length; i++)
         if(a[i] > a[i-1])
            temp = true;
      return temp;
   }
   
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   public static boolean isAscending(Comparable[] a)
   {
      boolean temp = false;
      for(int i = 1; i < a.length; i++)
         if(a[i].compareTo(a[i-1]) > 0)
            temp = true;
      return temp;
   }
}

//**********************************************************

class Insertion
{
   public static void sort(double[] array)
   { 
      for(int i = 0; i < array.length; i++){
         double val = array[i];
         array[shift(array, i, array[i])] = val;   }
   }
 
   private static int shift(double[] array, int index, double value)
   {
      double temp = value;
      int ind = index;
      for(int i = index; i >= 0; i--){
         if(array[i] > temp){
            array[i + 1] = array[i];
            ind--;   }}
      return ind;   
   }
 
   @SuppressWarnings("unchecked")
   public static void sort(Comparable[] array)
   { 
      for(int i = 0; i < array.length; i++){
         Comparable val = array[i];
         array[shift(array, i, array[i])] = val;   }
   }
   
   @SuppressWarnings("unchecked")
   private static int shift(Comparable[] array, int index, Comparable value)
   {
      Comparable temp = value;
      int ind = index;
      for(int i = index; i >= 0; i--){
         if(array[i].compareTo(temp) > 0){
            array[i + 1] = array[i];
            ind--;   }}
      return ind;
   }
}
