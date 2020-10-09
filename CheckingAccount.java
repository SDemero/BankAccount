
package bankaccountv7;

import java.util.Calendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author samueldemero
 */
class CheckingAccount extends Account{
    public CheckingAccount(){
        super();
    }
    public CheckingAccount(int acctNum,
            double balance, String acctType, Depositor a){
        super(acctNum,balance,acctType,a);
    }
    
    public CheckingAccount(int acctNum, double balance, String acctType,
            Depositor a, Calendar calendar){
        super(acctNum,balance,acctType,a,calendar);
    }
    
    public TransactionReciept makeDeposit(TransactionTicket a, int index,
            double add, Bank bank, Calendar c2){
        TransactionReciept rec = new TransactionReciept();
        rec = super.makeDeposit(a, index, add, bank, c2);
        return rec;
    }
    public TransactionReciept makeWithdrawal(TransactionTicket a, int index,
            double add, Bank bank, Calendar c2){
        TransactionReciept rec = new TransactionReciept();
        rec =super.makeWithdrawal(a, index, add, bank, c2);
        return rec;
    }
    public TransactionReciept clearCheck(TransactionTicket a, int index
            ,Bank bank,Check check, Calendar c2){
        TransactionReciept rec = new TransactionReciept();
        rec = super.clearCheck(a, index, bank, check, c2);
        return rec;
    }
}