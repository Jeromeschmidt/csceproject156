package csce156project;

public class Day extends Service{

	private String date;
	private String time;
	private Address address;
	private String cost;
	
	//creates constructor
	public Day(String code, String product, String date, String time, Address address, String cost) {
		super(code, product);
		this.date = date;
		this.time = time;
		this.address = address;
		this.cost = cost;
				
	}
	
	//getters and setters for new variables
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}
	
}
