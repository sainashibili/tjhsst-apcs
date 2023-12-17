// Name: Saina Shibili  
// Date: 12/16/2020
// This program takes a text file, creates an index (by line numbers)
// for all the words in the file and writes the index
// into the output file.  The program prompts the user for the file names.

import java.util.*;
import java.io.*;

public class IndexMaker
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nEnter input file name: ");
      String inFileName = keyboard.nextLine().trim();
      Scanner inputFile = new Scanner(new File(inFileName));
      String outFileName = "fishIndex.txt";
      PrintWriter outputFile = new PrintWriter(new FileWriter(outFileName));
      indexDocument(inputFile, outputFile);
      inputFile.close(); 						
      outputFile.close();
      System.out.println("Done.");
   }
   public static void indexDocument(Scanner inputFile, PrintWriter outputFile)
   {
      DocumentIndex index = new DocumentIndex();
      String line = null;
      int lineNum = 0;
      while(inputFile.hasNextLine())
      {
         lineNum++;
         index.addAllWords(inputFile.nextLine(), lineNum);
      }
      for(IndexEntry entry : index)
         outputFile.println(entry);
   }   
}
class DocumentIndex extends ArrayList<IndexEntry>
{
    //constructors
   public DocumentIndex()
   {
      super();
   }
   public DocumentIndex(int i)
   {
      super(i);
   }
      
  /** extracts all the words from str, skipping punctuation and whitespace 
 and for each word calls addWord().  In this situation, a good way to 
 extract while also skipping punctuation is to use String's split method, 
 e.g., str.split("[., \"!?]")       */
   public void addAllWords(String str, int lineNum) 
   {
      if(str.equals(""))
         return;
      String[] s = str.split("[., \"!?]");
      for(String t : s)
         if(t.equals("") == false)
            addWord(t, lineNum);
   }
    
   /** calls foundOrInserted, which returns a position.  At that position,  
   updates that IndexEntry's list of line numbers with lineNum. */
   public void addWord(String word, int lineNum)
   {
      this.get(foundOrInserted(word)).add(lineNum);
   }
        
    /** traverses this DocumentIndex and compares word to the words in the 
    IndexEntry objects in this list, looking for the correct position of 
    word. If an IndexEntry with word is not already in that position, the 
    method creates and inserts a new IndexEntry at that position. The 
    method returns the position of either the found or the inserted 
    IndexEntry.*/
   private int foundOrInserted(String word)
   {
      for(int i = 0; i < size(); i++){
         if(get(i).getWord().equalsIgnoreCase(word))
            return i;
         if(i == 0 && get(i).getWord().compareTo(word.toUpperCase()) > 0){
            add(i, new IndexEntry(word));
            return i;   }
         if(i + 1 < size() && get(i).getWord().compareTo(word.toUpperCase()) < 0 && get(i + 1).getWord().compareTo(word.toUpperCase()) > 0){
            add(i + 1, new IndexEntry(word));
            return i + 1;  }   
         if(i + 1 == size() && get(i).getWord().compareTo(word.toUpperCase()) > 0){
            add(i, new IndexEntry(word));
            return i;   }}    
      add(new IndexEntry(word));
      return this.size() - 1;
      
   }   
}
   
class IndexEntry implements Comparable<IndexEntry>
{
     //fields
   private String word;
   private ArrayList<Integer> numsList;

     //constructors
   public IndexEntry(String s)
   {
      word = s.toUpperCase();
      numsList = new ArrayList<Integer>();
   }
   
   
     /**  appends num to numsList, but only if it is not already in that list.    
          */
   public void add(int num)
   {
      if(numsList.contains(num) == false)
         numsList.add(num);
   }
      
   	/** this is a standard accessor method  */
   public String getWord()
   {
      return word;
   }
   
   public int compareTo(IndexEntry e)
   {
      if(word.compareTo(e.getWord()) < 0)
         return -1;
      if(word.compareTo(e.getWord()) > 0)
         return 1;
      return 0;
   }
      
     /**  returns a string representation of this Index Entry.  */
   public String toString()
   {
      String temp = word;
      temp += " ";
      for(int i = 0; i < numsList.size() - 1; i++)
         temp += numsList.get(i) + ", ";
      return temp + numsList.get(numsList.size() - 1);
   }
}

