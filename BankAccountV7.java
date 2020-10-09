/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaccountv7;


import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author samueldemero
 */
public class BankAccountV7 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        PrintStream ps = new PrintStream("output.txt");
        int usedAccts;
        char choice;

        Name myName = new Name();
        Depositor DE = new Depositor();
        Bank bank = new Bank();
        Account Acct = new Account();

        Scanner sc = new Scanner(new File("choices1.txt"));

        /* calls method readAccts*/
        usedAccts = readAccts(bank);

        /* calls method printAccts*/
        printAccts(usedAccts, ps, bank);

        boolean notDone = true;

        do {
            /* calls method menu*/
            menu();
            choice = sc.next().charAt(0);

            switch (choice) {

                case 'w':
                case 'W':
                    /* calls method withdrawal*/
                    withdrawal(usedAccts, ps, sc, bank);
                    break;

                case 'i':
                case 'I':
                    /*calls method AccountInfo*/
                    accountInfo(usedAccts, sc, ps, bank);
                    break;
                    
                case 'h':
                case 'H':
                    /*calls method AccountInfo w/ History*/
                    acctInfoHistory(usedAccts, sc, ps, bank);
                    break;
                    
                  case 's':
                  case 'S':
                    /*calls method AccountInfo w/ History*/
                    closeAcct(usedAccts, ps, sc, bank);
                    break;
                    
                  case 'r':
                  case 'R':
                    /*calls method Reopen a closed Account*/
                    reopenAcct(usedAccts, ps, sc, bank);
                    break;

                case 'd':
                case 'D':
                    /* calls method deposit*/
                    deposit(usedAccts, ps, sc, bank);
                    break;

                case 'n':
                case 'N':
                    /* calls method newAcct*/
                    usedAccts = newAcct(usedAccts, ps, sc, bank);
                    break;

                case 'b':
                case 'B':
                    /* calls method balance*/
                    balance(usedAccts, ps, sc, bank);
                    break;

                case 'x':
                case 'X':
                    /* calls method deleteAcct*/
                    usedAccts = deleteAccts(usedAccts, ps, sc, bank);
                    break;

                case 'c':
                case 'C':
                    /* calls method ClearCheck*/
                    clearCheck(usedAccts, ps, sc, bank);
                    break;

                case 'q':
                case 'Q':
                    /* calls method printAccts*/
                    Bank.addTotalBank();
                    printAccts(bank.getNumAccts(), ps, bank);
                    notDone = false;
                    break;

                default:
                    ps.println("Invalid selection\n");
                    break;

            }

        } while (notDone == true);

    }

    /* Method readAccts
input:
Bank bank an array of accounts


Process:
   method reads in ints,double's and strings from a file and
writes them to their perspective object classes
Output:
  returns the number of accounts used in the array
     */
    public static int readAccts(Bank bank) throws IOException {

        String line;
        int usedAccts = 0;
        int month;

        Scanner sc = new Scanner(new File("myinput1.txt"));

        while (sc.hasNext()) {
            Calendar c1 = Calendar.getInstance();
            c1.set(Calendar.MONTH, 10);
            //Account Acct = new Account();
            line = sc.nextLine();
            String[] tokens = line.split(" ");
            Account acct;

            Name myName = new Name(tokens[0], tokens[1]);

            Depositor DE = new Depositor (tokens[2], myName);

            if (tokens[5].equals("CD")) {

                c1.add(Calendar.MONTH, Integer.parseInt(tokens[6]));
       
                acct = new CDAccount(Integer.parseInt(tokens[3]),
                       Double.parseDouble(tokens[4]),
                       tokens[5], DE, c1);
                bank.addTotalCD(Double.parseDouble(tokens[4]));
            }
            else if (tokens[5].equals("Savings")){
            
            acct = new SavingsAccount(Integer.parseInt(tokens[3]),
                    Double.parseDouble(tokens[4]),
                    tokens[5], DE);
            
            bank.addTotalSavings(Double.parseDouble(tokens[4]));
            }
            else {
            
            acct = new CheckingAccount(Integer.parseInt(tokens[3]),
                    Double.parseDouble(tokens[4]),
                    tokens[5], DE);
            
             bank.addTotalChecking(Double.parseDouble(tokens[4]));
            }
            
            bank.fillBank(usedAccts, acct);
            usedAccts++;
            
            
             

        }
          bank.addTotalBank();
          
        return usedAccts;

    }

    /* Method printAccts
input:

 numAccts - the number of spots used in array
 PrintStream ps - file object
 bank - array of accounts

Process:
   this method prints all the accounts stored in the array

Output:
  doesn't return anything to main but prints each account
and balnce to the screen
     */
    public static void printAccts(int numAccts,
            PrintStream ps, Bank bank) {
        ArrayList<TransactionReciept> history;
        Calendar c1;
        ps.println(" ");
        ps.println("\t\tContestants in the Database");
        ps.println();
//print table column headings
        ps.println("Name\t       SSN\t    Account   Balance  Account "
                + "Type  CD Date\n");
        
        for (int i = 0; i < numAccts; i++) {
            ps.println(bank.getAcct(i));
            ps.println(" ");
 }
 ps.printf("Total amount in Savings accounts $ %.2f\n",Bank.getTotalSavings());
 ps.printf("Total amount in Checking accounts $ %.2f\n",Bank.getTotalChecking());
 ps.printf("Total amount in CD accounts $ %.2f\n",Bank.getTotalCD());
 ps.printf("Total amount in all Account $ %.2f\n",Bank.getTotalBank());
  ps.println(" ");
        
    }

    /* Method menu
input:
 no inputs
Process:
   prints various statements to the screen
Output:
  prints various statements to the screen
     */
    public static void menu() {
        System.out.println("W - Withdrawal");
        System.out.println("D - Deposit");
        System.out.println("C - Clear Check");
        System.out.println("N - New Account");
        System.out.println("B - Balance");
        System.out.println("I - Account Info");
        System.out.println("H - Account Info with History");
        System.out.println("S - Close Account");
        System.out.println("R - Reopen a Closed Account");
        System.out.println("X - Delete Account");
        System.out.println("Q - Quit\n");
    }

    /* Method balance
input:
 acctNum - is an array of ints storing account number
 bank - class with array
 numAccts - the number of spots used in array
 PrintStream ps - file object

Process:
the method determines if its a valid account and if it's
 a valid account it prints the account balance

Output:
  doesn't return anything to main but does send bank println statements
   to the screen
     */
    public static void balance(int numAccts, PrintStream ps, Scanner sc,
            Bank bank) {
        int index, account;
        String line;
        TransactionTicket ticket;
        TransactionTicket ticket1;
        TransactionReciept reciept = new TransactionReciept();
        Account Acct = new Account();

        System.out.println("Enter your account number");
        account = sc.nextInt();
        index = bank.findAcct(bank, account, numAccts);

        
       

        if (reciept.getTransactionSuccessIndicatorFlag()) {
        ticket1 = new TransactionTicket("Balance", 0.00,account);
        reciept = Acct.getBalance(ticket1, index, bank);
            ps.println(reciept);
            ps.println(" ");

        } else {
        ticket1 = new TransactionTicket("Balance", 0.00,account);
        reciept = Acct.getBalance(ticket1, index, bank);
           ps.println(reciept);
            ps.println(" ");
        }
    }

    /* Method deposit
input:
 bank- bank class of accounts
 numAccts - the number of spots used in array
 PrintStream ps - file object

Process:
   the method determines if its a valid account and if it's a valid
deposit amount before making a deposit

Output:
  doesn't return anything to main but does send bank println statements
to the screen
     */
    
    public static void deposit(int numAccts, PrintStream ps, Scanner sc,
            Bank bank) {
        Account acct = new Account();
        TransactionTicket ticket;
        TransactionTicket ticket1;
        TransactionReciept reciept = new TransactionReciept();
        int account, i;

        double sum;

        Calendar c2 = Calendar.getInstance();
        c2.set(Calendar.YEAR, 2020);
        c2.set(Calendar.MONTH, 4);

        double add = 0;

        System.out.println("Enter your Account Number");
        account = sc.nextInt();
        add = sc.nextDouble();
            ticket1 = new TransactionTicket("Deposit", add,account);
            int month = sc.nextInt();
            c2.add(Calendar.MONTH, month);
            
        i = bank.findAcct(bank, account, numAccts);
        reciept = acct.makeDeposit(ticket1, i, add, bank, c2);
        
        if (reciept.getTransactionSuccessIndicatorFlag()) {
            acct = bank.getAcct(i);
            ps.println(reciept);
        if (acct.getAcctType().equals("CD")){
            ps.println("New Maturity Date is " + c2.get(Calendar.MONTH)
                    + "/" + c2.get(Calendar.DATE)
                    + "/" + c2.get(Calendar.YEAR)); 
            }
            ps.println(" ");
        }
        else {
           
        ps.println(reciept);
        ps.println(" ");
        }
        
           bank.addTotalBank();  
        }
    
    

    /* Method withdrawal
input:
 bank- bank class of accounts
 numAccts - the number of spots used in array
 PrintStream ps - file object

Process:
   the method determines if its a valid account and if it's a valid
deposit amount before making a withdrawal

Output:
  doesn't return anything to main but does send bank println statements
to the screen
     */
    public static void withdrawal(int numAccts, PrintStream ps, Scanner sc,
            Bank bank) {
        Account acct = new Account();
        TransactionTicket ticket;
        TransactionTicket ticket1;
        TransactionReciept reciept = new TransactionReciept();
        int account, i;
        Calendar c2 = Calendar.getInstance();
        c2.set(Calendar.YEAR, 2020);
        c2.set(Calendar.MONTH, 4);
        double subtract = 0;

        System.out.println("Enter your Account Number");
        account = sc.nextInt();
        subtract = sc.nextDouble();
            ticket1 = new TransactionTicket("Withdrawal", subtract,account);
            int month = sc.nextInt();
            c2.add(Calendar.MONTH, month);
            
        i = bank.findAcct(bank, account, numAccts);
        reciept = acct.makeWithdrawal(ticket1, i, subtract, bank, c2);
        
        if (reciept.getTransactionSuccessIndicatorFlag()) {
           
           
            ps.println(reciept);
            
        if (acct.getAcctType().equals("CD")){
            
            ps.println("New Maturity Date is " + c2.get(Calendar.MONTH)
                    + "/" + c2.get(Calendar.DATE)
                    + "/" + c2.get(Calendar.YEAR));
        }ps.println(" ");
        }
        else {
            ps.println(reciept);
        
        ps.println(" ");
        bank.addTotalBank();
        }
        
            
        }
    /* Method accountInfo
input:

 numAccts - the number of spots used in array
 PrintStream ps - file object
 bank- class ith account array

Process:
   this method prints all the accounts that have the given SSN

Output:
  doesn't return anything to main but prints each account
and balance to the screen
     */
  
    public static void accountInfo(int numAccts, Scanner sc,
            PrintStream ps, Bank bank) {

        int found = 0;

        String SSN, line;
        System.out.println();
        System.out.print("Enter your Social Security Number : ");
        SSN = sc.next();
        System.out.println();
        ps.println("Transaction Type: Account Info");
        line = SSN;
        TransactionTicket ticket;
        ticket = new TransactionTicket("Account Info ", 0.00);
        String[] tokens = line.split("-");
        for (int count = 0; count < numAccts; count++) {

            if (bank.getSSN(count).equals(SSN)) {
                ps.println(" ");

                ps.print(bank.getFirst(count));
                ps.print(" " + bank.getLast(count));

                ps.print("\t" + bank.getSSN(count));
                ps.print("\t" + bank.getAcctNum(count));
                ps.printf("\t%10.2f", bank.getBalance(count));
                ps.print("\t   " + bank.getAcctType(count));
             
                found++;

            }
        }
        if (found == 0) {
            ps.println("Social Security Number: XXX-XX-" + tokens[2]);
            ps.println("No accounts match this information\n");
        } else {
            ps.println(" ");
            ps.println(found + " accounts found\n");
        }

    }
    
    /* Method accountInfo with history
input:

 numAccts - the number of spots used in array
 PrintStream ps - file object
 bank- class ith account array

Process:
   this method prints all the accounts that have the given SSN

Output:
  doesn't return anything to main but prints each account
and balance to the screen with thier history
     */
    public static void acctInfoHistory(int numAccts, Scanner sc,
            PrintStream ps, Bank bank) {

        int found = 0;

        String SSN, line;
        System.out.println();
        System.out.print("Enter your Social Security Number : ");
        SSN = sc.next();
        System.out.println();
        ps.println("Transaction Type: Account Info");
        line = SSN;
        TransactionTicket ticket;
        ticket = new TransactionTicket("Account Info w/ History", 0.00);

        String[] tokens = line.split("-");
        for (int count = 0; count < numAccts; count++) {

            if (bank.getSSN(count).equals(SSN)) {
                ps.println("");
                ps.print(bank.getFirst(count));
                ps.print(" " + bank.getLast(count));
                ps.print("\t" + bank.getSSN(count));
                ps.print("\t" + bank.getAcctNum(count));
                ps.printf("\t%15.2f", bank.getBalance(count));
                ps.print("\t   " + bank.getAcctType(count));
                ps.println();
                
                Account accts = bank.getAcct(count);
                ps.println("Transaction History");
                for (TransactionReciept Re:
                     bank.getAcct(count).getTransactionHistory(ticket, accts))
                    ps.println("\n"+Re);
                
                
                found++;

            }
        }
        if (found == 0) {
            ps.println("Social Security Number: XXX-XX-" + tokens[2]);
            ps.println("No accounts match this information\n");
        } else {
            ps.println(" ");
            ps.println(found + " accounts found\n");
        }

    }


    /* Method newAcct
input:
 bank - class of account array
 numAccts - the number of spots used in array
 PrintStream ps - file object

Process:
   the method determines if there's already an account by calling
findAcct and if there isn't it adds it to the array with the balance of
0.00

Output:
  returns the new number of arrays after it added a new one
     */
    public static int newAcct(int numAccts, PrintStream ps, Scanner sc,
            Bank bank) throws IOException {
        int account, index;
        TransactionTicket ticket;
        TransactionTicket ticket1;
        TransactionReciept reciept = new TransactionReciept();

        Scanner sc1 = new Scanner(new File("NewAccount.txt"));
        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.MONTH, 10);
        String line;

        System.out.println("Please enter a number you would like you "
                + "account to be");
        System.out.println("Please enter your first and last name"
                + " followed by your Social Security number ");
        System.out.println("Please enter "
                + "your intial balance and type of account ");
        account = Integer.parseInt(sc.next());

        index = bank.findAcct(bank, account, numAccts);
        if (index == -1) {

            ticket = new TransactionTicket("New Account", 0.00,account);
            reciept = 
                 bank.openNewAcct(ticket, account, index, numAccts, bank, sc);

            ps.println("Transaction Type:"
                    + reciept.getTransactionTicket().getTransactionType());
            ps.println("Account Number: " + account);
            ps.println("Initial Balance $" +
                    reciept.getPostTransactionBalance());
            
            ps.println("Account created\n");

            return numAccts + 1;

        } else {
            ticket1 = new TransactionTicket("New Account", 0.00,account);
            reciept = 
                bank.openNewAcct(ticket1, account, index, numAccts, bank, sc);
            ps.println("Transaction Type:"
                    + reciept.getTransactionTicket().getTransactionType());
            ps.println("Failure Reason: "
                    + reciept.getTransactionFailureReason());
            ps.println(" ");
           
            return numAccts;
        
        }

    }

    /* Method deleteAccts
input:
 bank - class of account array
 numAccts - the number of spots used in array
 PrintStream ps - file object
 Scanner sc - file scanner

Process:
the method determines if its a valid account and if it's a
valid account deletes the account if its a valid account number
but has a balancesends an error and if its an in valid account number
 prints an error

Output:
  return's the new value of numAccts
     */
    public static int deleteAccts(int numAccts, PrintStream ps, Scanner sc,
            Bank bank) {

        int account, index;
        int accts = 0;
        TransactionTicket ticket;
        TransactionReciept reciept = new TransactionReciept();

        System.out.println("Please enter your Account Number");
        
        account = sc.nextInt();

        index = bank.findAcct(bank, account, numAccts);
       ticket = new TransactionTicket("Delete Account", 0.00,account);
        if (index != -1) {

            if (bank.getBalance(index) == 0.00) {

                
            reciept = bank.deleteAcct(ticket, index, true, numAccts, bank);
            ps.println("Transaction Type: "
                    + reciept.getTransactionTicket().getTransactionType());
            ps.println("Account Number: " + account);
            ps.println("Account deleted\n");

            return numAccts - 1;
        }

        if (bank.getBalance(index) != 0.00) {


            reciept = bank.deleteAcct(ticket, index, true, numAccts, bank);
            ps.println("Transaction Type: "
                    + reciept.getTransactionTicket().getTransactionType());
            ps.println("Account Number: " + account);
            ps.println("Failure reason: " +
                    reciept.getTransactionFailureReason());
                ps.println(" ");

                return numAccts;
            }
        }

       
        reciept = bank.deleteAcct(ticket, index, true, numAccts, bank);
        ps.println("Transaction Type: "
                + reciept.getTransactionTicket().getTransactionType());
        ps.println("Account Number: " + account);
        ps.println("Failure reason: " + reciept.getTransactionFailureReason());
        ps.println(" ");

        return numAccts;

    }

    /* Method clearCheck
input:
 bankAccount - array of BankAccount
 numAccts - the number of spots used in array
 PrintStream ps - file object
 Scanner sc - file scanner

Process:
the method determines if the check is valid (Checking
the expiration date and the validity of the check) and
also determines if the account has access to this feature

Output:
doesn't return anything to the screen but prints the proper statements
     */
    public static void clearCheck(int numAccts, PrintStream ps, Scanner sc,
            Bank bank) throws IOException {
        Calendar c1 = Calendar.getInstance();
        TransactionTicket ticket;
        TransactionReciept reciept = new TransactionReciept();

        int acct = sc.nextInt();
        String line;
        System.out.println(acct);
        double checkamount = sc.nextDouble();
        String checkdate = sc.next();
        System.out.println(checkdate);

        Check check = new Check(acct, checkamount, checkdate);
        line = checkdate;

        String[] tokens = line.split("/");
        Account Account = new Account();

        Calendar c2 = Calendar.getInstance();
        c2.set(Integer.parseInt(tokens[2]),
                (Integer.parseInt(tokens[0]) - 1), 
                Integer.parseInt(tokens[1]));

        c2.add(Calendar.MONTH, 6);

        int index = bank.findAcct(bank, acct, numAccts);
        ticket = new TransactionTicket("Clear Check", checkamount,acct);
        if (index == -1) {
            reciept = Account.clearCheck(ticket, index, bank, check, c2);
            ps.println("Transaction Type: "
                    + reciept.getTransactionTicket().getTransactionType());
            ps.println("Account Number: " + acct);
            ps.println("Failure reason: " + 
                    reciept.getTransactionFailureReason());
            ps.println(" ");

        } else if (bank.getAcctType(index).equals("CD")
                || bank.getAcctType(index).equals("Savings")) {
            reciept = Account.clearCheck(ticket, index, bank, check, c2);
            ps.println("Transaction Type: "
                    + reciept.getTransactionTicket().getTransactionType());
            ps.println("Account Number: " + acct);
            ps.println("Failure reason: " +
                    reciept.getTransactionFailureReason());

            ps.println(" ");
        } else if (c1.after(c2)) {
            reciept = Account.clearCheck(ticket, index, bank, check, c2);
            ps.println("Transaction Type: "
                    + reciept.getTransactionTicket().getTransactionType());
            ps.println("Account Number: " + acct);
            ps.println("Failure reason: " + 
                    reciept.getTransactionFailureReason());
            ps.println(" ");

        } else if (checkamount > bank.getBalance(index)) {
            reciept = Account.clearCheck(ticket, index, bank, check, c2);
            ps.println("Transaction Type: "
                    + reciept.getTransactionTicket().getTransactionType());
            ps.println("Failure reason: " + 
                    reciept.getTransactionFailureReason());
            ps.println("Account has been charged $2.50 for bounced check");
            ps.println(" ");
        }else if (check.getCheckDate().after(c1)) {
            reciept = Account.clearCheck(ticket, index, bank, check, c2);
            ps.println("Transaction Type: "
                    + reciept.getTransactionTicket().getTransactionType());
            ps.println("Account Number: " + acct);
            ps.println("Failure reason: " +
                    reciept.getTransactionFailureReason());
            ps.println(" ");

        } else {
            if (bank.getBalance(index)< 2500) {
            ticket = new TransactionTicket("Clear Check", checkamount,acct);
            reciept = Account.clearCheck(ticket, index, bank, check, c2);
            ps.println("Transaction Type: "
                    + reciept.getTransactionTicket().getTransactionType());
            ps.println("Account Number: " + acct);
            ps.println("Old Account Balance $ " + 
                    reciept.getPreTransactionBalance());
            ps.println("Check Amount $"
                    + reciept.getTransactionTicket().getTransactionAmount());
            ps.println("Check Cleared"); 
            ps.println("Account has been charged $1.50 for low balance");
            ps.println("New Account Balance $" + 
                    reciept.getPostTransactionBalance());
            ps.println(" ");
           
            
        } else{
            ticket = new TransactionTicket("Clear Check", checkamount,acct);
            reciept = Account.clearCheck(ticket, index, bank, check, c2);
            ps.println("Transaction Type: "
                    + reciept.getTransactionTicket().getTransactionType());
            ps.println("Account Number: " + acct);
            ps.println("Old Account Balance $ " + 
                    reciept.getPreTransactionBalance());
            ps.println("Check Amount $"
                    + reciept.getTransactionTicket().getTransactionAmount());
            ps.println("Check Cleared");
            ps.println("New Account Balance $" + 
                    reciept.getPostTransactionBalance());
            ps.println(" ");

        }
    }
    }
    /* Method closeAccts
input:
 bank - class of account array
 numAccts - the number of spots used in array
 PrintStream ps - file object
 Scanner sc - file scanner

Process:
the method determines if its a valid account and if it's a
valid account closes the account  and if its an in valid account number
 prints an error

Output:
  prints statements from reciepts
     */
    public static void closeAcct(int numAccts, PrintStream ps, Scanner sc,
            Bank bank){
        Account acct = new Account();
        Account acct2 = new SavingsAccount();
        TransactionTicket ticket;
        int index;
        
        TransactionReciept reciept = new TransactionReciept();
        int account, i;System.out.println("Enter your account number");
        account = sc.nextInt();
        
        index = bank.findAcct(bank, account, numAccts);

        ticket = new TransactionTicket("Close Account", 0.00,account);
        reciept = acct.closeAcct(ticket, index, bank);
       

        if (reciept.getTransactionSuccessIndicatorFlag()) {

            ps.println("Transaction Type: "
                    + reciept.getTransactionTicket().getTransactionType());
            ps.println("Account Number: " + account);
            ps.println("Account Status: closed");
            ps.println(" ");

        } else {

            ps.println("Transaction Type: "
                    + reciept.getTransactionTicket().getTransactionType());
            ps.println("Account Number: " + account);
            ps.println("Failure Reason: "
                    + reciept.getTransactionFailureReason());
            ps.println(" ");
        }
    }
    /* Method reopenAccts
input:
 bank - class of account array
 numAccts - the number of spots used in array
 PrintStream ps - file object
 Scanner sc - file scanner

Process:
the method determines if its a valid account and if it's a
valid account and if account is closed opens the account
    and if its an in valid account number prints an error

Output:
  prints statements from reciepts
     */
    public static void reopenAcct(int numAccts, PrintStream ps, Scanner sc,
            Bank bank){
        Account acct = new Account();
        TransactionTicket ticket;
        int index;
        
        TransactionReciept reciept = new TransactionReciept();
        int account, i;System.out.println("Enter your account number");
        account = sc.nextInt();
        
        index = bank.findAcct(bank, account, numAccts);

        ticket = new TransactionTicket("Reopen Account", 0.00,account);
        reciept = acct.reopenAcct(ticket, index, bank);
       

        if (reciept.getTransactionSuccessIndicatorFlag()) {

            ps.println("Transaction Type: "
                    + reciept.getTransactionTicket().getTransactionType());
            ps.println("Account Number: " + account);
            ps.println("Account Status: Open");
            ps.println(" ");

        } else {

            ps.println("Transaction Type: "
                    + reciept.getTransactionTicket().getTransactionType());
            ps.println("Account Number: " + account);
            ps.println("Failure Reason: "
                    + reciept.getTransactionFailureReason());
            ps.println(" ");
        }
    }
}
       
       
    


    
    

