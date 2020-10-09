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
class CDAccount extends Account{
    private Calendar maturityDate = Calendar.getInstance();
    public CDAccount(){
        super();
    }
    public CDAccount(int acctNum,
            double balance, String acctType, Depositor a){
        super(acctNum,balance,acctType,a);
    }
    
    public CDAccount(int acctNum, double balance, String acctType,
            Depositor a, Calendar calendar){
        
        super(acctNum,balance,acctType,a,calendar);
        maturityDate = calendar;
    }
}
