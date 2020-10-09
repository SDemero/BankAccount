
package bankaccountv7;

import java.util.Calendar;

public class TransactionTicket extends genTransactionTicket{

    private Calendar dateOfTransac;
    private String transactType;
    private double transactAmount;
    private int termofcd;
    private int account;
    //no-arg constructor for transaction ticket

    public TransactionTicket() {
        dateOfTransac = Calendar.getInstance();
        dateOfTransac.clear();
        transactType = "";
        termofcd = 0;
        transactAmount = 0.0;
    }
    public TransactionTicket(TransactionTicket a){
        dateOfTransac = a.dateOfTransac;
        transactType = a.transactType;
        termofcd = a.termofcd;
        transactAmount = a.transactAmount;
    }
    //4-arg constructor for transaction ticket

    public TransactionTicket(String acctType, double transAmount,
            int l, String date) {
        transactType = acctType;
        transactAmount = transAmount;
        termofcd = l;
        dateOfTransac = Calendar.getInstance();
        dateOfTransac.clear();
        String[] dateArray = date.split("/");
        dateOfTransac.set(Integer.parseInt(dateArray[2]),
                Integer.parseInt(dateArray[0]) - 1,
                Integer.parseInt(dateArray[1]));
    }
    //3-arg constructor for transaction ticket

    public TransactionTicket(String TransactType, double transAmount, int l
            ,int acct)
    {
        transactType = TransactType;
        transactAmount = transAmount;
        termofcd = l;
        dateOfTransac = Calendar.getInstance();
        account = acct;
        
    }
    public TransactionTicket(String TransactType, double transAmount,int acct) {
        transactType = TransactType;
        transactAmount = transAmount;
        dateOfTransac = Calendar.getInstance();
        termofcd = 0;
        account = acct;
    }
    public TransactionTicket(String TransactType, double transAmount) {
        transactType = TransactType;
        transactAmount = transAmount;
        dateOfTransac = Calendar.getInstance();
        termofcd = 0;
        account = 0;
    }
    public String toString() {
		
                String str;
                if (account > 0 ){
		 str = ("Transaction Type: "+transactType+"\n"
                        + " Account Number: "+account);
                return str;
                }
                else {
                    str = ("Transaction Type: "+transactType);
                }
		return str;
	}
    //returns the calendar date of a transaction

    public Calendar getDateOfTransaction() {
        return Calendar.getInstance();
    }
    //returns the type of transaction

    public String getTransactionType() {
        return transactType;
    }
    //returns the amount of a transaction

    public double getTransactionAmount() {
        return transactAmount;
    }
    //returns the term of a CD account

    public int gettermofCD() {
        return termofcd;
    }
    //allows the setting of a transaction type

    protected void SetTransactionType(String i) {
        transactType = i;
    }
    //allows the setting of a CD accounts term

    protected void SettermofCD(int i) {
        termofcd = i;
    }
    //allows the setting of a transaction amount 

    protected void Setransacamount(double i) {
        transactAmount = i;
    }
}
