package csce156project;

import Interface.Cost;


public abstract class Service implements Cost{

	private String code;
	private String product;
	
	//creates constructor
	public Service(String code, String product) {
		this.code = code;
		this.setProduct(product);
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

	
	


	

		
	
	
	
	
	
}
