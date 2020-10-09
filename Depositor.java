package bankaccountv7;

public class Depositor extends genDepositer{

    private Name name;
    private String SSN;

    public Depositor() {
        name = new Name();
        SSN = " ";
    }

    public  Depositor(String mySSN, Name myname) {
        SSN = mySSN;
        name = myname;
       
    }
    
    public Depositor(Depositor D){
        SSN = D.SSN;
        name= D.name;
    }

    protected void setSSN(String str) {
        SSN = str;
    }

    protected void setName(Name n) {
        name = n;
    }

    public Name getName() {
        return new Name(name);
    }

    public String getSSN() {
        return SSN;
    }
public String toString() {
		//String str = String.format("%-10s%-10s", first,last);
		String str = (" "+ name+"\t"+SSN);
		return str;
	}
public boolean equals(Depositor d) {
		if( d.name.equals(name)&& d.SSN.equals(SSN))
			return true;			
		else
			return false;			
	}
}
