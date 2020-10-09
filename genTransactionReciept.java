/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaccountv7;

import java.util.Calendar;

/**
 *
 * @author samueldemero
 */
public abstract class genTransactionReciept {

    private TransactionTicket ticket;
    private boolean successIndFlag;
    private String failReason;
    private double pretransacBal;
    private double postransacBal;
    private Calendar postransacmaturity;

    public abstract TransactionTicket getTransactionTicket();

    public abstract boolean getTransactionSuccessIndicatorFlag();

    public abstract String getTransactionFailureReason();

    public abstract double getPreTransactionBalance();

    public abstract double getPostTransactionBalance();

    public abstract Calendar getPostTransactionMaturityDate();

    protected abstract void setindflag(boolean i);

    protected abstract void settransfailreason(String i);

    protected abstract void setpretransacbal(Double i);

    protected abstract void setpostransacbal(Double i);

    protected abstract void setransacticket(TransactionTicket i);

    protected abstract void settransacmaturity(Calendar i);

}
