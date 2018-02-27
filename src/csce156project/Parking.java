package csce156project;

public class Parking extends Service{

	private String cost;
	
	//creates constructor
	public Parking(String code, String product, String cost) {
		super(code, product);
		this.cost = cost;
	}
	
	//getters and setters for new variables
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
	
	@Override
	public double getDiscount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public double getTax() {
		// TODO Auto-generated method stub
		return 0.04;
	}

	@Override
	public String getProductName() {
		// TODO Auto-generated method stub
		return "Parking Pass";
	}

	
}
