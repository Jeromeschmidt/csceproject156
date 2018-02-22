package csce156project;

public class Year extends Service{

	private String endDate;
	private String startDate;
	private Address address;
	private String cost;
	private String pack;
	
	//creates constructor
	public Year(String code, String product, String endDate, String startDate, Address a, String pack, String cost) {
		super(code, product);
		this.endDate = endDate;
		this.startDate = startDate;
		this.address = a;
		this.cost = cost;
		this.setPack(pack);
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
	public double getCostt() {
		// TODO Auto-generated method stub
		return Double.parseDouble(cost);
	}

	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}


}
