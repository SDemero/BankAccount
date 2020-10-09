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
class SavingsAccount extends Account{

    public SavingsAccount(){ 
        super();
        
    }
    public SavingsAccount(int acctNum,
            double balance, String acctType, Depositor a){
        super(acctNum,balance,acctType,a);
    }
    
    public SavingsAccount(int acctNum, double balance, String acctType,
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
        rec = super.makeDeposit(a, index, add, bank, c2);
        return rec;
    }
}