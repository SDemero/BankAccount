
package bankaccountv7;


public class Name extends genName{
    private String  last;
    private String first;
    
    public Name() {
		
		last = "";
		first = "";}
    
    public Name(String myfirst,String mylast) {
		last = mylast;
                first = myfirst;
		
                
	}
    public Name(Name myname){
        last = myname.last;
        first = myname.first;
    }
    
//setters
    protected void setLast(String str) {
        last = str;
    }
    
    
    protected void setFirst(String str) {
        first = str;
    }
    
//getters
    public String getLast() {
        return last;
    }

    public String getFirst() {
        return first;
    }
 
    //toString() method 
	public String toString() {
		//String str = String.format("%-10s%-10s", first,last);
		String str = (""+first+" "+last);
		return str;
	}
    
    //.equals() method
	public boolean equals(Name myName) {
		if(last.equals(myName.last) && first.equals(myName.first))
			return true;			
		else
			return false;			
	}

}


    

