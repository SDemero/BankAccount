package bankaccountv7;

import java.util.Calendar;
import java.util.Scanner;
import java.util.ArrayList;

public class Bank {

    private final Name name;
    private final Depositor DE;
    private final Account Accts;
    private Calendar c1 = Calendar.getInstance();
    private final ArrayList<Account> Bank;
    private static double totalAmountInSavingsAccts =0.00;
    private static double totalAmountInCheckingAccts=0.00;
    private static double totalAmountInCDAccts=0.00;
    private static double totalAmountInAllAccts=0.00;
    
    
    public Bank() {
        name = new Name();
        DE = new Depositor();
        Accts = new Account();
        c1 = Calendar.getInstance();
        Bank = new ArrayList<>();  
}
    
    

    public void fillBank(int a, Account accts) {
         Bank.add(a,accts);

    }
    public int getNumAccts(){
        return Bank.size();
    }

    public String getFirst(int a) {
       String name1= Bank.get(a).getDE().getName().getFirst();
       
        return name1;
    }

    public int findAcct(Bank bank, int account, int numAccts) {
        int index = 0;
        boolean found = true;

        for (int count = 0; count < numAccts; count++) {

            if (bank.getAcctNum(count) == account) {
                return count;
            }
        }
        return -1;

    }


    

    public String getLast(int a) {
        String name1 = Bank.get(a).getDE().getName().getLast();
        //System.out.println(name);
        return name1;
    }

    public int getAcctNum(int a) {
        int name1 = Bank.get(a).getAcctNum();
        //System.out.println(name);
        return name1;
    }

    public double getBalance(int a) {
        double name1 = Bank.get(a).getBalance();
        //System.out.println(name);
        return name1;
    }

    public Calendar getMaturityDate(int a) {
        Calendar c1 = Bank.get(a).getMaturityDate();
        return c1;
    }

    public String getAcctType(int a) {
        String name1 = Bank.get(a).getAcctType();
        //System.out.println(name);
        return name1;
    }

    public String getSSN(int a) {
        String SSN = Bank.get(a).getDE().getSSN();
        //System.out.println(name);
        return SSN;
    }


    public Account getAcct(int a) {
        return new Account(Bank.get(a));
    }

    public TransactionReciept deleteAcct(TransactionTicket i,
            int index, boolean flag,int numAccts, Bank bank) {
       TransactionReciept ticket;
        
        if (index == -1) {
            ticket = new TransactionReciept(i,false,"Account doesn't exist");
            
            
            return ticket;
        }
        Account acct = bank.getAcct(index);
        if (acct.getBalance() == 0.00) {
            
            
            System.out.println(acct.getAcctType());
             
             
            
            if (acct.getAcctType().equals("CD")){
                totalAmountInCDAccts -= acct.getBalance();
            }
            if (acct.getAcctType().equals("Checking")){
                totalAmountInCheckingAccts -= acct.getBalance();
            }
            if (acct.getAcctType().equals("Savings")){
                totalAmountInSavingsAccts -= acct.getBalance();
            }
            ticket = new TransactionReciept(i,true,"Account deleted");
            Bank.remove(index);
            return ticket;
        }
       else  {
          ticket = new TransactionReciept(i,false,"Account still has balance");
            return ticket;
        }

        
    }

    public TransactionReciept openNewAcct(TransactionTicket i, int account,
            int index, int numAccts, Bank bank, Scanner sc) {

        TransactionReciept ticket;
        
        double balance;
        

        if (index == -1) {

            Name myName;
            Depositor DE;

            Account Acct;

            String first = sc.next();
            String last = sc.next();
            myName = new Name(first, last);

            String SSN = sc.next();
            DE = new Depositor(SSN, myName);
            

           
            balance = (Double.parseDouble(sc.next()));
            String hold = sc.next();
            

            if (hold.equals("CD")) {
                c1.add(Calendar.MONTH, Integer.parseInt(sc.next()));
                Acct = new CDAccount(account,balance,hold,DE,c1);
                
                
                totalAmountInCDAccts +=Acct.getBalance();
      ticket = new TransactionReciept(i,true,"Account Created",0.0,balance,c1);
            } else {
                
                if (hold.equals("Checking")){
                    Acct = new CheckingAccount(account,balance,hold,DE);
                    totalAmountInCheckingAccts +=Acct.getBalance();
                }
                else {
                     Acct = new SavingsAccount(account,balance,hold,DE);
                    totalAmountInSavingsAccts +=Acct.getBalance();
                }
            }

            bank.fillBank(numAccts, Acct);
          ticket = new TransactionReciept(i,true,"Account Created",0.0,balance);
            Acct.addTransaction(ticket);
            return ticket;
        } else {
            
            ticket = new TransactionReciept(i,false,"Account already exist");
            
        }

        
        
        return ticket;
    }
    
public static void addTotalSavings(double budget) {
        totalAmountInSavingsAccts+= budget;					
	}
public static void subTotalSavings(double budget) {
        totalAmountInSavingsAccts-= budget;					
	}
public static double getTotalSavings(){
    return totalAmountInSavingsAccts;
}
public static void addTotalChecking(double budget) {
        totalAmountInCheckingAccts+= budget;					
	}
public static void subTotalChecking(double budget) {
        totalAmountInCheckingAccts-= budget;					
	}
public static double getTotalChecking(){
    return totalAmountInCheckingAccts;
}
public static void addTotalCD(double budget) {
        totalAmountInCDAccts+= budget;					
	}
public static void subTotalCD(double budget) {
        totalAmountInCDAccts-= budget;					
	}
public static double getTotalCD(){
    return totalAmountInCDAccts;
}
public static void addTotalBank() {
        totalAmountInAllAccts= totalAmountInCDAccts+
                totalAmountInCheckingAccts+totalAmountInSavingsAccts ;					
	}
public static double getTotalBank(){
    return totalAmountInAllAccts;
}
}
