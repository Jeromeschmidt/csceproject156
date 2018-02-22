package csce156project;

import java.util.List;



public class Invoice<T>{

	private String invoiceCode;
	private String memberCode;
	private String pTCode;
	private String invoiceDate;
	private String[] productList;
	
	
	public Invoice(String invoiceCode, String memberCode, String pTCode,String invoiceDate, String[] productList) {
		super();
		this.invoiceCode = invoiceCode;
		this.memberCode = memberCode;
		this.pTCode = pTCode;
		this.invoiceDate = invoiceDate;
		this.productList = productList;

	}


	public String getInvoiceCode() {
		return invoiceCode;
	}


	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}


	public String getMemberCode() {
		return memberCode;
	}


	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}


	public String getpTCode() {
		return pTCode;
	}


	public void setpTCode(String pTCode) {
		this.pTCode = pTCode;
	}


	public String getInvoiceDate() {
		return invoiceDate;
	}


	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}


	public String[] getProductList() 
	{
		
		
		
		return productList;
	}


	public void setProductList(String[] productList) {
		this.productList = productList;
	}


	
	
}
