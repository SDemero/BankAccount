/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaccountv7;

public abstract class genDepositer {

	private Name name;
	private String SSN;
	
	public abstract String getSSN();
	public abstract Name getName();
	protected abstract void setName(Name a);
	protected abstract void setSSN(String i);
}