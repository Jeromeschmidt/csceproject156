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

	
}
