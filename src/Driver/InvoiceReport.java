package Driver;

import java.util.List;

import Interface.ExecutiveReport;
import csce156project.Invoice;
import csce156project.Membership;
import csce156project.Persons;
import csce156project.Service;
import fileReader.flatFileReader;

public class InvoiceReport {
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		
		
		List<Invoice> invoiceList = flatFileReader.readInvoice();
		
		System.out.println("Executive Summary Report");
		System.out.println("=========================");
		
		System.out.println("Invoice   Member                                            Personal Trainer                 Subtotal        Fees       Taxes    Discount       Total");
		
		
		for(int i = 0; i < 4; i++) {
		
		
		}
		System.out.println(invoiceList.get(0).getProductList());
		
	}
}
