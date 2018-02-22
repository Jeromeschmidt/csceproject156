package csce156project;

public class Equipment extends Service{
	
	private String cost;
	private String name;
	
	//creates constructor
	public Equipment(String code, String product, String name, String cost) {
		super(code, product);
		this.cost = cost;
		this.setName(name);
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
