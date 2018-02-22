package csce156project;

public class Year extends Service{

	private String endDate;
	private String startDate;
	private Address address;
	private String cost;
	
	//creates constructor
	public Year(String code, String product, String endDate, String startDate, Address a, String cost) {
		super(code, product);
		this.endDate = endDate;
		this.startDate = startDate;
		this.address = a;
		this.cost = cost;
	}

	//getters and setters for new variables
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
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

	@Override
	public void computeEReport() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void computeIReport() {
		// TODO Auto-generated method stub
		
	}

}
