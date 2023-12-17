// Name: Saina Shibili
// Date: 09/11/2020
 
import java.text.DecimalFormat;
public class SmartCard_Driver
{
   public static void main(String[] args) 
   {
      Station downtown = new Station("Downtown", 1);
      Station center = new Station("Center City", 1);
      Station uptown = new Station("Uptown", 2);
      Station suburbia = new Station("Suburb", 4);
   
      SmartCard jimmy = new SmartCard(20.00); 
      jimmy.board(center);                    //Boarded at Center City.  SmartCard has $20.00
      jimmy.disembark(suburbia);              //From Center City to Suburb costs $2.75.  SmartCard has $17.25
      jimmy.disembark(uptown);				//Error:  did not board?!
      System.out.println();
   			
      SmartCard susie = new SmartCard(1.00); 
      susie.board(uptown);            		//Boarded at Uptown.  SmartCard has $1.00
      susie.disembark(suburbia);				//Insuffient funds to exit. Please add more money.
      System.out.println();
   
      SmartCard kim = new SmartCard(.25);    
      kim.board(uptown);            		    //Insuffient funds to board. Please add more money.
      System.out.println();
   
      SmartCard b = new SmartCard(10.00);   
      b.board(center);            		    //Boarded at Center City.  SmartCard has $10.00
      b.disembark(downtown);					//From Center City to Downtown costs $0.50.  SmartCard has $9.50
      System.out.println();
        
      SmartCard mc = new SmartCard(10.00);  
      mc.board(suburbia);            		    //Boarded at Suburbia.  SmartCard has $10.00
      mc.disembark(downtown);					//From Suburbia to Downtown costs $2.75.  SmartCard has $7.25
      System.out.println();
    
      SmartCard penny = new SmartCard(0.50);
      penny.board(center);                //Boarded at Suburbia. SmartCard has $0.50
      penny.board(downtown);              //Error: Already Boarded?
      System.out.println();
      
      /*SmartCard jeff = new SmartCard(13.50);
      jeff.disembark(center);*/                   //Error: did not board?!
      
      //Make more test cases.  What else needs to be tested?
   }
} 	

//Note SmartCard is not denoted as public.  Why?
class SmartCard 
{
   public final static DecimalFormat df = new DecimalFormat("$0.00");
   public final static double MIN_FARE = 0.5;
   private double balance;
   private int zone;
   private Station station;
   private boolean onboard;
   /* enter your code below */
   public SmartCard(double b)
   {
      balance = b;
      zone = 0;
      onboard = false;
      station = new Station();
   }
   
   public void addMoney(double d)
   {
      balance += d;
   }
   
   public String getBalance()
   {
      return df.format(balance);
   }
   
   public boolean isBoarded()
   {
      return onboard;
   }
   
   public void board(Station s)
   {
      if(onboard == true)
         System.out.println("Error: already boarded?!");
      else if(balance < 0.5)
         System.out.println("Insufficient funds to board. Please add more money.");
      else
      {
         System.out.println("Boarded at " + s.getName() + ". SmartCard has " + getBalance());
         onboard = true;
         zone = s.getZone();
         station.setName(s.getName());
         station.setZone(zone);
      }
   }
   
   public double cost(Station s)
   {
      int high, low;
      if(s.getZone() >= zone)
      {
         high = s.getZone();
         low = zone;
      }
      else
      {
         high = zone;
         low = s.getZone();
      }
      return 0.5 + (0.75*(high - low));
   }
   
   public void disembark(Station s)
   {
      if(onboard == false)
      {
         
         System.out.println("Error: Did not board?!");
      }
      else if(balance - cost(s) < 0)
         System.out.println("Insufficient funds to exit. Please add more money.");
      else
      {
         balance -= cost(s);
         double cost = cost(s);
         onboard = false;
         String board = station.getName();
         station.setName(s.getName());
         zone = s.getZone();
         station.setZone(zone);
         System.out.println("From " + board + " to " + station.getName() + "costs " + df.format(cost) + ". Smartcard has " + df.format(balance) + ".");
       
      }
   }
   //the next 3 methods are for use ONLY by Grade-It
   //these accessor methods only return your private data
   //they do not make any changes to your data
   double getMoneyRemaining()
   {
    //enter your code here and replace the return with yours
      return balance;
   }
   
   Station getBoardedAt()
   {
    //enter your code here and replace the return with yours
      return station;   
   }
  
   boolean getIsOnBoard()
   {
   //enter your code here and replace the return with yours
      return onboard;
   }
}
   
//Note Station is not a public class.  Why?
class Station
{
   private int zone;
   private String name;
   
   public Station()
   {
      zone = 1;
      name = null;
   }
   
   public Station(String name, int zone)
   {
      this.zone = zone;
      this.name = name;
   }
   
   public int getZone(){
      return zone;
   }
   
   public String getName(){
      return name;
   }
   
   public void setZone(int z){
      zone = z;
   }
   
   public void setName(String n){
      name = n;
   }
}

 /*******************  Sample Run ************

 Boarded at Center City.  SmartCard has $20.00
 From Center City to Suburb costs $2.75.  SmartCard has $17.25
 Error: did not board?!
 
 Boarded at Uptown.  SmartCard has $1.00
 Insufficient funds to exit. Please add more money.
 
 Insufficient funds to board. Please add more money.
 
 Boarded at Center City.  SmartCard has $10.00
 From Center City to Downtown costs $0.50.  SmartCard has $9.50
 
 Boarded at Suburb.  SmartCard has $10.00
 From Suburb to Downtown costs $2.75.  SmartCard has $7.25
 
 ************************************************/