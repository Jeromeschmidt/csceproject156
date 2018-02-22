package csce156project;

public class Equipment extends Service{
	
	private String cost;
	
	//creates constructor
	public Equipment(String code, String product, String cost) {
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
	public void computeEReport() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void computeIReport() {
		// TODO Auto-generated method stub
		
	}

	
	
}
