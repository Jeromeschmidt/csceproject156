package com.sf.ext;

import com.sf.ext.InvoiceData;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Start");
		InvoiceData.addEmail("5955c", "kevo@gmail.com");
		InvoiceData.addDayPassToInvoice("IN001", "1239", 3);
		
		System.out.println("End");
	}

}
