/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaccountv7;

import java.util.ArrayList;
import java.util.Calendar;

public abstract class genAccount {

    private Depositor DE;
    private int acctNum;
    private String acctType;
    private double balance;
    private Calendar c1 = Calendar.getInstance();
    private boolean status;
    private ArrayList<TransactionReciept> history;
	
	public abstract int getAcctNum();
	public abstract String getAcctType();
	public abstract boolean getStatus();
	public abstract double getBalance();
        protected abstract void setMaturityDate(Calendar calendar);
        protected abstract void SetAcctNum(int a);
        protected abstract void setDeposi(Depositor a);
	protected abstract void setAcctType(String b);
        protected abstract void setBalance(double c);
}
