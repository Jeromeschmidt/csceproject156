package csce156project;

import Interface.ExecutiveReport;
import Interface.IndividualReport;

public class Persons implements ExecutiveReport, IndividualReport{

	public String firstName;
	public String lastName;
	private String id;
	private Address address;
	private Emails email;
	
	//creates constructor
	public Persons(String firstName, String lastName, String id, Address address, Emails email) 
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.address = address;
		this.email = email;
		
	}

	//getters and setters for new variables
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Emails getMail() {
		return email;
	}

	public void setMail(Emails mail) {
		this.email = mail;
	}

	@Override
	public void computeIReport() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void computeEReport() {
		// TODO Auto-generated method stub
		
	}
	

	
}
