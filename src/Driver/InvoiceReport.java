package Driver;

import java.util.List;


import csce156project.Invoice;
import csce156project.Membership;
import csce156project.Organization;
import csce156project.Persons;
import csce156project.Service;
import fileReader.flatFileReader;

public class InvoiceReport {
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		
		
		List<Invoice> invoiceList = flatFileReader.readInvoice();
		Organization org = new Organization();
		System.out.println("Executive Summary Report");
		System.out.println("=========================");
		
		System.out.println("Invoice   Member                                 Personal Trainer                 Subtotal        Fees       Taxes    Discount       Total");
		
		
		for(int i = 0; i < invoiceList.size(); i++) {
			System.out.println(invoiceList.get(i).getInvoiceCode() + " " + org.getMember(invoiceList.get(i).getMemberCode()) + "      "  + org.getTrainer(invoiceList.get(i).getpTCode()) +"      " + org.getSubtotal(invoiceList.get(i).getProductList()));
		
		}
		
		
	}
}
