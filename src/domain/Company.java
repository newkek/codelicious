package domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Company extends Contact{
	
	private int numSiret;
	
	public Company(){
		
	}
	
	public Company(String firstname, String lastname, String email, Address address, int numSiret){
		super();
		this.firstName = firstname;
		this.lastName = lastname;
		this.email = email;
		this.address = address;
		this.numSiret = numSiret;
	}

	public int getNumSiret() {
		return numSiret;
	}

	public void setNumSiret(int numSiret) {
		this.numSiret = numSiret;
	}
	
	public void init() { 

	}
	
	public void cleanup(){
		
	}

}
