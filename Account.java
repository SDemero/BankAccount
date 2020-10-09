package bankaccountv7;

import java.util.Calendar;
import java.util.ArrayList;

public class Account extends genAccount{

    private Depositor DE;
    private int acctNum;
    private String acctType;
    private double balance;
    private Calendar c1 = Calendar.getInstance();
    private Boolean status;
    private ArrayList<TransactionReciept> history;

    public Account() {
        Calendar calendar = Calendar.getInstance();
        DE = new Depositor();
        acctNum = 0;
        acctType = " ";
        balance = 0.00;
        status = true;
        history = new ArrayList<>();

    }
    
public Account(Account c){
        c1=c.c1;
        DE = c.DE;
        acctNum = c.acctNum;
        acctType = c.acctType;
        balance = c.balance;
        status = c.status;
        history = c.history;
    
}
    public Account(int acctNum,
            double balance, String acctType, Depositor a) {
        
        this.acctNum = acctNum;

        this.acctType = acctType;

        this.balance = balance;

        DE = a;
        history = new ArrayList<>();
        status = true;

    }

    public Account(int acctNum, double balance, String acctType,
            Depositor a, Calendar calendar) {
        this.acctNum = acctNum;

        this.acctType = acctType;

        this.balance = balance;

        DE = a;
        c1 = calendar;
        history = new ArrayList<>();
         status = true;

    }

    

    protected void setMaturityDate(Calendar calendar) {
        c1 = calendar;
    }

    

    protected void SetAcctNum(int a) {
        acctNum = a;
    }

    private void setStatus(boolean orc) {
        status = orc;
    }

    public boolean getStatus() {
        return status;
    }

    

    protected void setDeposi(Depositor a) {
        DE = a;
    }

    public Depositor getDE() {
        return new Depositor(DE);
    }

    public Calendar getMaturityDate() {
        return c1;
    }

    

    protected void setAcctType(String b) {
        acctType = b;
    }


    protected void setBalance(double c) {
        balance = c;
    }

    public int getAcctNum() {
        return acctNum;
    }

    public String getAcctType() {
        return acctType;
    }

    public double getBalance() {
        return balance;
    }

    

    public TransactionReciept getBalance(TransactionTicket i,
            int index, Bank bank) {
        Account acct = new Account();

        TransactionReciept ticket;
        
        if (index == -1) {
            ticket = new TransactionReciept(i,false,"Invalid account number");
            
            return ticket;
        } else {

            acct = bank.getAcct(index);
       ticket = new TransactionReciept(i,true,"Did not fail",0.0,acct.balance);
            
            acct.history.add(ticket);

        }
        

        return ticket;
    }

    public TransactionReciept closeAcct(TransactionTicket i, int index,
            Bank bank) {
        Account acct = new Account();

        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.YEAR, 2020);
        c1.set(Calendar.MONTH, 4);

        TransactionReciept ticket1 = null; 
        

        if (index == -1) {
            ticket1 = new TransactionReciept(i,false,"Account does not exist");
            
            return ticket1;
        }
        else if (index > -1) {
            acct = bank.getAcct(index);
            if (acct.status == false) {
  ticket1 = new TransactionReciept(i,false,"Account is already closed");
                
                acct.history.add(ticket1);
                return ticket1;
            }

            else  {
                acct.status = false;
            ticket1 = new TransactionReciept(i,true
                    ,"Account is now closed",acct.balance,acct.balance);
                return ticket1;
            }

        } return ticket1;
    }

    public TransactionReciept reopenAcct(TransactionTicket i, int index,
            Bank bank) {
        Account acct = new Account();

        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.YEAR, 2020);
        c1.set(Calendar.MONTH, 4);

        TransactionReciept ticket1 = null;
        

        if (index == -1) {

            
             ticket1 = new TransactionReciept(i,false,"Account does not exist");
            return ticket1;
        }
        if (index > -1) {
            acct = bank.getAcct(index);
            if (acct.status == true) {

           ticket1 = new TransactionReciept(i,false,"Account is already open");
                acct.history.add(ticket1);
                return ticket1;
            }

            if (acct.status == false) {

                acct.status = true;
    ticket1 = new TransactionReciept(i,true,"Account is now open",
            acct.balance,acct.balance);
               return ticket1;
            }

        }

        return ticket1;

    }

    public TransactionReciept makeDeposit(TransactionTicket i, int index,
            double add, Bank bank, Calendar c2) {
        Account acct;
        double balh;

        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.YEAR, 2020);
        c1.set(Calendar.MONTH, 4);

        TransactionReciept ticket1 = null;
       

        if (index == -1) {
ticket1 = new TransactionReciept(i,false,"Account does not exist");
            

            return ticket1;
        }
        if (index > -1) {
            acct = bank.getAcct(index);
            System.out.println(acct.DE);
            
           
            if (acct.status == false) {
                ticket1 = new TransactionReciept(i,false,"Account is closed");
                
                return ticket1;
            }
            if (add < 0) {
ticket1 = new TransactionReciept(i,false,"Invalid Deposit amount");
               
                acct.history.add(ticket1);
                return ticket1;
            }

            if (acct.acctType.equals("CD")) {
                if (c2.equals(acct.getMaturityDate())
                        || c2.after(acct.getMaturityDate())) {
                    if (add >= 0) {

                        balh = acct.getBalance();
                        acct.balance += add;
                        acct.c1 = c2;
  ticket1 = new TransactionReciept(i,true,"Did not fail",balh,acct.balance,c2);
                        acct.history.add(ticket1);
                        Bank.addTotalCD(add);
                        return ticket1;
                    }
                } else {
              ticket1 = new TransactionReciept(i,false,
                      "Maturity date not yet reached");
                    acct.history.add(ticket1);
                    return ticket1;

                }
            }
            if (!(acct.getAcctType().equals("CD"))) {
                balh = acct.getBalance();
                acct.balance += add;
                ticket1 = new TransactionReciept(i,true,
                        "Did not fail",balh,acct.balance,c2);
                
                acct.history.add(ticket1);
                if (acct.acctType.equals("Savings")){
                    Bank.addTotalSavings(add);
                }
                 if (acct.acctType.equals("Checking")){
                    Bank.addTotalChecking(add);
                }
                return ticket1;
            }

        }
        return ticket1;

    }

    public TransactionReciept makeWithdrawal(TransactionTicket i, int index,
            double subtract, Bank bank, Calendar c2) {
        Account acct = new Account();
        double balh;
        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.YEAR, 2020);
        c1.set(Calendar.MONTH, 4);

        TransactionReciept ticket = null;
        

        if (index == -1) {
           ticket = new TransactionReciept(i,false,
                      "Account does not exist");
            
            return ticket;

        }
        if (index > -1) {
            acct = bank.getAcct(index);
            if (acct.status == false) {
                ticket = new TransactionReciept(i,false,
                      "Account is closed");
                return ticket;
            }
            if (subtract < 0) {
               ticket = new TransactionReciept(i,false,
                      "Invalid Withdrawal amount");
                acct.history.add(ticket);
                return ticket;

            }

            if (subtract > bank.getBalance(index)) {
                ticket = new TransactionReciept(i,false,
                      "Insufficient Funds");
                acct.history.add(ticket);
                return ticket;

            }
            if (bank.getAcctType(index).equals("CD")) {
                
                if (c2.equals(acct.getMaturityDate())
                        || c2.after(acct.getMaturityDate())) {
                    if (subtract >= 0 && subtract <= acct.getBalance()) {
                        balh = acct.balance;
                        
                        acct.balance -= subtract;
                        acct.c1 = c2;
                        ticket = new TransactionReciept(i,true,"Did not fail",
                       balh,acct.balance,c2);
                        acct.history.add(ticket);
                         Bank.subTotalCD(subtract);
                        return ticket;
                    }
                } else {
                    ticket = new TransactionReciept(i,false,"Maturity "
                            + "date not yet reached");
                    acct.history.add(ticket);
                    return ticket;

                }
            }
            if (!(bank.getAcctType(index).equals("CD"))) {
                if (subtract >= 0 && subtract <= bank.getBalance(index)) {
                    balh =acct.balance;
                   
                    
                    
                     
                    
                    if (acct.acctType.equals("Savings")){
                        ticket = new TransactionReciept(i,true,"Did not fail",
                       balh,acct.balance,c2);
                    Bank.subTotalSavings(subtract);
                    acct.history.add(ticket);
                    return ticket;
                }
                 if (acct.acctType.equals("Checking")){
                     if (acct.balance<2500){
                        
                        acct.balance -= subtract;
                        acct.balance -= 1.50;
                        acct.c1 = c2;
               ticket = new TransactionReciept(i,true,"Did not fail"
                       + "\nAccount charged 1.50 for low balance",
                       balh,acct.balance,c2);
                        
                        acct.history.add(ticket);
                         Bank.subTotalCD(subtract);
                        return ticket;
                }
                     
                    Bank.subTotalChecking(subtract);
                }
                 
                    return ticket;

                }
            }
        }

        return ticket;
    }

    public TransactionReciept clearCheck(TransactionTicket i, int index,
            Bank bank, Check check, Calendar c2) {
        double balh;
        Account acct = new Account();

        TransactionReciept ticket = null;
        
        Calendar c1 = Calendar.getInstance();
        if (index == -1) {
            ticket = new TransactionReciept(i,false,"Account Doesn't exist");
            return ticket;
        }
        acct = bank.getAcct(index);
        if (acct.status == false) {
            ticket = new TransactionReciept(i,false,"Account is closed");
            return ticket;
        }
        if ((bank.getAcctType(index).equals("CD"))
                || bank.getAcctType(index).equals("Savings")) {
          
            ticket = new TransactionReciept(i,false,"Account "
                    + "doesn't have this feature");
            return ticket;
        }

        if (c1.after(c2)) {
            ticket = new TransactionReciept(i,false,"Check has expired");
            acct.history.add(ticket);
            return ticket;
        }

        if (i.getTransactionAmount() > bank.getBalance(index)) {
            
            balh = acct.balance;
            acct.balance -= 2.50;
            
            ticket = new TransactionReciept(i,false,"Account has insufficient"
                    + " funds\nAccount charged $2.50");
            acct.history.add(ticket);
            return ticket;
        }
        if (check.getCheckDate().after(c1)) {
            ticket = new TransactionReciept(i,false,"Check is postdated");
            acct.history.add(ticket);
            return ticket;
        } else {
            if(bank.getBalance(index)< 2500){
            balh=acct.balance;
            acct.balance -= i.getTransactionAmount();
            acct.balance -= 1.50;
            ticket = new TransactionReciept(i,true,"Did not fail"
                       + "\nAccount charged 1.50 for low balance",
                       balh,acct.balance,c2);
            acct.history.add(ticket);
            return ticket;
            }
            else {
             balh= acct.balance;
            acct.balance -= i.getTransactionAmount();
            ticket = new TransactionReciept(i,true,"Did not fail",
                       balh,acct.balance,c2);
            acct.history.add(ticket);
            return ticket;
            }
        }

    }

    public void addTransaction(TransactionReciept i) {
        history.add(i);
    }

    public ArrayList<TransactionReciept> getTransactionHistory
        (TransactionTicket i,Account acct) {
        return acct.history;
    }
        public String toString() {
            String str;
		if(acctType.equals("CD")){
	 str = String.format(DE+ "  "+acctNum+"     %7.2f"+"     "
         +acctType+"        "+c1.get(Calendar.MONTH) + "/"
                        + c1.get(Calendar.DATE) + "/" + c1.get(Calendar.YEAR)
                 ,balance);
         return str;
                }
                else {
                    str = String.format(DE+ "  "+acctNum+"     %7.2f"+"\t"
         +acctType+"       N/A",balance);
                }
		return str;
	}
        public boolean equals(Account c) {
		if( c.DE.equals(DE)&&(c.acctNum == acctNum)&&
                        c.acctType.equals(acctType)&& c.balance == balance)
			return true;			
		else
			return false;			
	}
}




