
package bankaccountv7;

import java.io.*;
import java.util.Calendar;

public class TransactionReciept extends genTransactionReciept{

    private TransactionTicket ticket;
    private boolean successIndFlag;
    private String failReason;
    private double pretransacBal;
    private double postransacBal;
    private Calendar postransacmaturity;

    //no-arg constructor for transaction receipt
    public TransactionReciept() {
        ticket = new TransactionTicket();
        successIndFlag = false;
        failReason = "";
        pretransacBal = 0.0;
        postransacBal = 0.0;
        postransacmaturity = Calendar.getInstance();
        postransacmaturity.clear();
    }
    public TransactionReciept(TransactionReciept x){
        ticket = x.ticket;
        successIndFlag = x.successIndFlag;
        failReason = x.failReason;
        pretransacBal = x.pretransacBal;
        postransacBal = x.postransacBal;
        postransacmaturity = x.postransacmaturity;
    }public TransactionReciept(TransactionTicket o, boolean i, String j) {
        ticket = o;
        successIndFlag = i;
        failReason = j;
        pretransacBal = 0.00;
        postransacBal = 0.00;
        postransacmaturity = Calendar.getInstance();
        
    }
    //5-arg constructor for transaction receipt

    public TransactionReciept(TransactionTicket o,boolean i, String j, double k, double l) {
        ticket = o;
        successIndFlag = i;
        failReason = j;
        pretransacBal = k;
        postransacBal = l;
        postransacmaturity = Calendar.getInstance();
        
    }
    //7-arg constructor for transaction reciept For CD Accounts

    public TransactionReciept(TransactionTicket o,boolean i, String j, double k, double l, 
            Calendar date) {
        ticket = o;
        successIndFlag = i;
        failReason = j;
        pretransacBal = k;
        postransacBal = l;
        postransacmaturity = date;
        

    }

    //for when input fails
    public TransactionReciept(boolean i, String j) {
        ticket = new TransactionTicket();
        successIndFlag = i;
    }

    //returns transaction ticket object
    public TransactionTicket getTransactionTicket() {
        return new TransactionTicket(ticket);
    }
    //returns the indicator flag

    public boolean getTransactionSuccessIndicatorFlag() {
        return successIndFlag;
    }
    //returns a failure reason string

    public String getTransactionFailureReason() {
        return failReason;
    }
    //returns balance before a transaction

    public double getPreTransactionBalance() {
        return pretransacBal;
    }
    //returns balance after a transaction

    public double getPostTransactionBalance() {
        return postransacBal;
    }
    //returns CD maturity date after a transaction

    public Calendar getPostTransactionMaturityDate() {
        return postransacmaturity;
    }
    //allows the setting of the indicator flag

    protected void setindflag(boolean i) {
        successIndFlag = i;
    }
    //allows the setting of the failure string

    protected void settransfailreason(String i) {
        failReason = i;
    }
    //allows the setting of balance before transaction

    protected void setpretransacbal(Double i) {
        pretransacBal = i;
    }
    //allows the setting of balance after transaction

    protected void setpostransacbal(Double i) {
        postransacBal = i;
    }
    //allows the setting of a transaction ticket

    protected void setransacticket(TransactionTicket i) {
        ticket = i;
    }
    //allows the setting of a CD transaction maturity

    protected void settransacmaturity(Calendar i) {
        postransacmaturity = i;
    }

    public void printfailreciept(TransactionReciept i,int account,
            PrintStream ps) {
        
        ps.println("Transaction Type : " + 
                i.getTransactionTicket().getTransactionType());
        ps.println("Account Number: " + account);
        ps.println("Failure Reason: " + i.getTransactionFailureReason());
        ps.println();
    }
    
     public void printBalanceReciept(TransactionReciept i,
             int account, PrintStream ps) {
        
        ps.println("Transaction Type : " +
                i.getTransactionTicket().getTransactionType());
        ps.println("Account Number: " + account);
        ps.println("Balance $ " +
                i.getTransactionTicket().getTransactionAmount());
        ps.println();
    }
     public void printDepositReciept(TransactionReciept i ,
             int account,double prebalance, double sum, PrintStream ps){
                ps.println("Transaction Type: Deposit");
                ps.println("Account Number: " + account);
                ps.println("Amount to Deposit: $" + 
                        i.getTransactionTicket().getTransactionAmount());
                ps.println("Old Balance: $" + prebalance);
                ps.println("New Balance: $" + sum);
                ps.println(" ");
     }
     public String toString() {
		
                String str="";
                
                if(successIndFlag == false){
                    str = (ticket+"\nFailure Reason: "+failReason);
                    return str;
                }
                if (ticket.getTransactionType().equals("Deposit")){
                    
		 str = (ticket+"\nAmount to Deposit $ "+ticket.getTransactionAmount()+
                         "\n"
                        + "Old Balance $"+pretransacBal+"\nNew Balance $"+
                         postransacBal);
                return str;
                }
                if (ticket.getTransactionType().equals("Withdrawal")){
                    
		 str = (ticket+"\nAmount to Withdrawal $ "+ticket.getTransactionAmount()+
                         "\n"
                        + "Old Balance $"+pretransacBal+"\n Fail reason: "
                         +failReason+"\nNew Balance $"+
                         postransacBal);
                 return str;
                }
                if (ticket.getTransactionType().equals("Balance")){
                    
		 str = (ticket+"\nBalance $"+postransacBal);
                return str;
                }
                
              return str;  
     }   
	
     


}
