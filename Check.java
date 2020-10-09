
package bankaccountv7;

import java.util.Calendar;

public class Check {

private int acctNum;
	private double checkAmount;
	private Calendar date;
	
	//no-arg check constructor
	public Check() {
		acctNum = 0;
		checkAmount = 0.0;
		date = Calendar.getInstance();
		date.clear();}
        
        public Check(Check c){
                acctNum = c.acctNum;
		checkAmount = c.checkAmount;
		date = c.date;
		
        }
        public String toString() {
		//String str = String.format("%-10s%-10s", first,last);
		String str = ("check for the amount of $"+checkAmount+" "
                        + "belongs to the account "+acctNum+" Check date is "
                        +date.get(Calendar.MONTH) + "/"
                        + date.get(Calendar.DATE) + "/" 
                        + date.get(Calendar.YEAR));
		return str;
	}
        
	//3-arg check constructor
	public Check(int i, double j, String k) {
		acctNum = i;
		checkAmount = j;
		date = Calendar.getInstance();
		date.clear();
		String [] dateArray = k.split("/");
		date.set(Integer.parseInt(dateArray[2]), 
		(Integer.parseInt(dateArray[0])-1) , 
                Integer.parseInt(dateArray[1]));
        }
	//returns check account number
	public int getacc() {
		return acctNum;
	}
	//returns double amount of check
	public double getcheckamount() {
		return checkAmount;
	}
	//allows the setting of a check account number
	 private void setacc(int i) {
		acctNum = i;
	}
	//allows the setting of the check amount
	 private void setcheckamount(double i) {
		checkAmount = i;
	}
	//returns the calendar date of a check
	public Calendar getCheckDate() {
		return date;
	}
	//allows the setting of the check date from a string
	private void setdate(String i) {
		date.clear();
		String [] dateArray = i.split("/");
		date.set(Integer.parseInt(dateArray[2]), 
	Integer.parseInt(dateArray[0]) - 1, Integer.parseInt(dateArray[1]));}}
