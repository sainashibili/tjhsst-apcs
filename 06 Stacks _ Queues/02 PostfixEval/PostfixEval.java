// Name: Saina Shibili
// Date: 01/08/2021

import java.util.*;

public class PostfixEval
{
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args)
   {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList<String> postfixExp = new ArrayList<String>();
      /*  build your list of expressions here  */
      postfixExp.add("3 4 5 * +");
      postfixExp.add("3 4 * 5 +");
      postfixExp.add("10 20 + -6 6 * +");
      postfixExp.add("3 4 + 5 6 + *");
      postfixExp.add("3 4 5 + * 2 - 5 /");
      postfixExp.add("8 1 2 * + 9 3 / -");
      postfixExp.add("2 3 ^");
      postfixExp.add("20 3 %");
      postfixExp.add("21 3 %");
      postfixExp.add("22 3 %");
      postfixExp.add("23 3 %");
      postfixExp.add("5 !");		
      postfixExp.add("1 1 1 1 1 + + + + !");
      for( String pf : postfixExp )
      {
         System.out.println(pf + "\t\t" + eval(pf));
      }
   }
   public static double eval(String pf)
   {
      List<String> postfixParts = new ArrayList<String>(Arrays.asList(pf.split(" ")));
      Stack<String> s = new Stack< >();
      /*  enter your code here  */
      for(int i = 0; i < postfixParts.size(); i++){
         if(!isOperator(postfixParts.get(i)))
            s.push(postfixParts.get(i));
         else if(postfixParts.get(i).equals("!"))
            s.push("" + factorial(s.pop()));
         else
            s.push("" + eval(Double.parseDouble(s.pop()), Double.parseDouble(s.pop()), postfixParts.get(i)));
      }
      return Double.parseDouble(s.pop());
   }
   
   public static double eval(double a, double b, String ch)
   {
      if(ch.equals("+"))
         return a + b;
      else if(ch.equals("-"))
         return b - a;
      else if(ch.equals("*"))
         return a * b;
      else if(ch.equals("/"))
         return b / a;
      else if(ch.equals("^"))
         return Math.pow(b, a);
      else if(ch.equals("%"))
         return b % a;
      return 0.0;
   }
   
   public static boolean isOperator(String op)
   {
      if(operators.contains(op))
         return true;
      return false;
   }
   
   public static double factorial(String op)
   {
      double d = 1.0;
      for(double i = Double.parseDouble(op); i > 0; i--)
         d *= i;
      return d;
   }
}

/**********************************************
Postfix  -->  Evaluate
 3 4 5 * +		23
 3 4 * 5 +		17
 10 20 + -6 6 * +		-6
 3 4 + 5 6 + *		77
 3 4 5 + * 2 - 5 /		5
 8 1 2 * + 9 3 / -		7
 2 3 ^		8
 20 3 %		2
 21 3 %		0
 22 3 %		1
 23 3 %		2
 5 !		120
 1 1 1 1 1 + + + + !		120
 
 
 *************************************/