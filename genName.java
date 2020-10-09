/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaccountv7;

public abstract class genName {
	
	private String firstname;
	private String lastname;
	
	public abstract String getFirst();
	public abstract String getLast();
	protected abstract void setFirst(String i);
	protected abstract void setLast(String i);
}