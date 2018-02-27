package Driver;

import java.util.List;

import csce156project.Invoice;
import csce156project.Membership;

import csce156project.Persons;
import csce156project.Service;
import fileReader.flatFileReader;

public class InvoiceReport {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {

		List<Persons> personList = flatFileReader.readPersons();
		List<Membership> memberList = flatFileReader.readMembers();
		List<Service> serviceList = flatFileReader.readService();
		List<Invoice> invoiceList = flatFileReader.readInvoice();
		
		Invoice invoice = null;
		Invoice invoiceSingle = null;
		
		System.out.println("Executive Summary Report");
		System.out.println("=========================");

		System.out.printf("%5s %10s %45s %20s %20s %20s %20s %20s\n", "Invoice", "Member", "Personal Trainer",
				"Subtotal", "Fees", "Taxes", "Discount", "Invoice Total");
		double grandTotal =0.0;
		double subtotal2 = 0;
		double fees2 = 0;
		double taxes2 = 0;
		double discount2 = 0;
		
		for(int i = 0 ; i < invoiceList.size(); i++) {	
		String inCode = "";
		String name = "";
		String trainer = "";
		double subtotal = 0;
		double fee = 0;
		double tax = 0;
		double disc = 0;
		double overTotal = 0;
		 invoice = new Invoice(personList, memberList, serviceList, invoiceList, invoiceList.get(i).getMemberCode());
		 
		 inCode = invoice.getInvoiceCode();
		 name = invoice.getName();
		 trainer = invoice.getTrainer();
		 subtotal = invoice.getSubtotal();
		 fee = invoice.getFees();
		 tax = invoice.getTax();
		 disc = invoice.getDisc();
		 overTotal = invoice.getGrandTotal();
		 
		 System.out.println(String.format("%-5s %-40s %-20s $%13.2f $%20.2f $%20.2f $-%20.2f $%15.2f", inCode,
					name , trainer, subtotal, fee, tax, 
					disc , overTotal));
		 subtotal2 += subtotal;
		 fees2 += fee;
		 taxes2 += tax;
		 discount2 += disc;
		 grandTotal+= overTotal;
		 }
		
		System.out.printf(
				"=========================================================================================================================================================================\n");

		System.out.printf("Totals:  %61s %12.2f $%20.2f $%20.2f $%20.2f $%15.2f", "$", subtotal2, fees2, taxes2,
				discount2, grandTotal);
		
		System.out.println();
		System.out.println();
		System.out.println();
		

		System.out.println("Individual Invoice Detail Reports");
		System.out.println("==================================================");
		
		for(int k = 0; k < invoiceList.size(); k++) {
			invoiceSingle = new Invoice(personList, memberList, serviceList, invoiceList, invoiceList.get(k).getMemberCode());
			invoiceSingle.writeLong();
		}
		
		
		
		
		
		
	}
}
