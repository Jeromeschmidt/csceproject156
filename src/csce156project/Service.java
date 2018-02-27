package csce156project;

import Interface.Cost;
import Interface.Discount;
import Interface.ProductName;


public abstract class Service implements Cost, Discount, ProductName{

	protected String code;
	protected String product;
	
	//creates constructor
	public Service(String code, String product) {
		this.code = code;
		this.product = product;
	}

	//getters and setters for new variables
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public abstract double getTax();
	


	

		
	
	
	
	
	
}
