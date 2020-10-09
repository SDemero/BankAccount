/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaccountv7;
import java.util.Calendar;

public abstract class genTransactionTicket {
    private Calendar dateOfTransac;
    private String transactType;
    private double transactAmount;
    private int termofcd;
    private int account;
	
	 public abstract String getTransactionType();
         public abstract Calendar getDateOfTransaction();
	public abstract double getTransactionAmount();
	public abstract int gettermofCD();
        protected abstract void SetTransactionType(String i);
        protected abstract void SettermofCD(int i);
        protected abstract void Setransacamount(double i);
}
