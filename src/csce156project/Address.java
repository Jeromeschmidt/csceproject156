package csce156project;


public class Address{
	
	private String address;
	private String city;
	private String state;
	private String zip;
	private String country;
	

	//creates constructor
	public Address(String address, String city, String state, String zip, String country) 
	{
		this.setAddress(address);
		this.city = city;
		this.state = state;
		this.zip= zip;
		this.country = country;
	}
	
	//getters and setters for new variables
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	
	

}
