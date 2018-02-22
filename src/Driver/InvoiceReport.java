package Driver;

import java.util.List;

import csce156project.Invoice;
import csce156project.Organization;
import csce156project.Persons;
import fileReader.flatFileReader;

public class InvoiceReport {
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		
		
		List<Invoice> invoiceList = flatFileReader.readInvoice();
		Organization org = new Organization();
		System.out.println("Executive Summary Report");
		System.out.println("=========================");
		
		System.out.printf("%5s %10s %45s %20s %20s %20s %20s %20s\n", "Invoice", "Member", "Personal Trainer", "Subtotal", "Fees", "Taxes", "Discount", "Invoice Total");
		
		
		String invoiceCode;
		String memberCode;
		String memberName;
		String trainer;
		double subtotal;
		double subtotal2 = 0;
		double fees;
		double fees2 = 0;
		double taxes;
		double taxes2 = 0;
		double discount;
		double discount2 = 0;
		double total;
		double total2 = 0;
		
		for(int i = 0; i < invoiceList.size(); i++) 
		{
			invoiceCode = invoiceList.get(i).getInvoiceCode();
			memberCode = org.getMember(invoiceList.get(i).getMemberCode());
			trainer = org.getTrainer(invoiceList.get(i).getpTCode());
			subtotal = org.getSubtotal(invoiceList.get(i).getProductList());
			fees = org.getFees(invoiceList.get(i).getMemberCode());
			taxes = 0;
			discount = 0;
			total = 0;

			System.out.println(String.format("%-5s %-40s %-20s $%13.2f $%20.2f $%20.2f $%20.2f $%15.2f", invoiceCode, memberCode, trainer, subtotal, fees, taxes, discount, total));
			subtotal2 += subtotal;
			fees2 += fees;
			taxes2 += taxes;
			discount2 += discount;
			total2 += total;		
			
		}
		
		System.out.printf("=========================================================================================================================================================================\n");
		
		System.out.printf("Totals:  %61s %12.2f $%20.2f $%20.2f $%20.2f $%15.2f", "$", subtotal2, fees2, taxes2, discount2, total2);
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		System.out.println("Individual Invoice Detail Reports");
		System.out.println("==================================================");
		
		
		for(int j = 0; j < invoiceList.size(); j++)
		{
			invoiceCode = invoiceList.get(j).getInvoiceCode();
			memberCode = org.getMember(invoiceList.get(j).getMemberCode());
			memberName = 
			trainer = org.getTrainer(invoiceList.get(j).getpTCode());
			
			System.out.println("Invoice " + invoiceCode);
			System.out.println("========================");
			System.out.println("Personal Trainer: " + trainer);
			System.out.println("Member Info: ");
			System.out.println(memberCode);
			System.out.println();//memberName
			System.out.println();//member address part 1
			System.out.println();//member city state zip
			System.out.println("-------------------------------------------");
			System.out.printf("%5s %5s %40s %10s %10s\n", "Code","Item","Subtotal", "Tax", "Total");
			
			for(int i = 0; i < 10; i++)
			{
				
			}
			
			
			System.out.printf("	%20s\n", "Thank you for your purchase!\n");
		}
		
	}
}
