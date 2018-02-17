package fileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import csce156project.Address;
import csce156project.Day;
import csce156project.Emails;
import csce156project.Equipment;
import csce156project.Invoice;
import csce156project.Membership;
import csce156project.Parking;
import csce156project.Persons;
import csce156project.Service;
import csce156project.Year;

public class flatFileReader {
	
	//reads through Persons.dat flat file for needed information
	public static ArrayList<Persons> readPersons() {
		int i = 0;
		ArrayList<Persons> result = new ArrayList<Persons>();
		String fileName = "Persons.dat";
		Scanner s = null;
		try {
			s = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (s.hasNextLine()) {
			if (i == 0) {
				// skip first line
				s.nextLine();
			} else {
				Persons p;
				Address address;
				
				Emails email = null;
				String line = s.nextLine();
				String[] data = line.split(";");
				String temp = data[1];
				String[] data2 = temp.split(", ");
				String lastName = data2[0];
				String firstName = data2[1];
				String id = data[0];
				String bits = data[2];
				String[] data3 = bits.split(",");
				String street = data3[0];
				String city = data3[1];
				String state = data3[2];
				String zip = data3[3];
				String country = data3[4];
				if(data.length == 4) {	
				String tempt = data[3];
				String[] data4 = tempt.split(",");
				email = new Emails(data4);
				}
				address = new Address(street, city, state, zip, country);
				p = new Persons(firstName, lastName, id, address, email);
				result.add(p);
			}

			i++;
		}
		s.close();
		return result;
	}
	//reads through Members.dat flat file for needed information
	public static ArrayList<Membership> readMembers() {
		int j = 0;
		ArrayList<Membership> result1 = new ArrayList<Membership>();
		String fileNome = "Members.dat";
		Scanner t = null;
		try {
			t = new Scanner(new File(fileNome));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while (t.hasNextLine()) {
			if (j == 0) {
				// skip first line
				t.nextLine();
			} else {
				Address address;
				Membership per1;
				String line = t.nextLine();
				String[] data4 = line.split(";");
				String member = data4[0];
				String type = data4[1];
				String id = data4[2];
				String name = data4[3];
				String addre = data4[4];
				String[] data5 = addre.split(",");
				String street = data5[0];
				String city = data5[1];
				String state = data5[2];
				String zip = data5[3];
				String country = data5[4];
				
				address = new Address(street, city, state, zip, country);
				per1 = new Membership(member, type, id, name, address);
				result1.add(per1);
			}

			j++;
		}
		t.close();
		return result1;
	}
	//reads through Products.dat flat file for needed information
	public static ArrayList<Service> readService() {
		int j = 0;
		ArrayList<Service> result1 = new ArrayList<Service>();
		String fileNime = "Products.dat";
		Scanner t = null;
		try {
			t = new Scanner(new File(fileNime));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while (t.hasNextLine()) {
			if (j == 0) {
				// skip first line
				t.nextLine();
			} else {
				Year y;
				String line = t.nextLine();
				String[] data = line.split(";");
				if(data[1].equals("Y")) {
					Address address;
					String code = data[0];
					String product = "Y";
					String endDate = data[2];
					String startDate = data[3];
					String[] addComp = data[4].split(",");
					String street = addComp[0];
					String city = addComp[1];
					String state = addComp[2];
					String zip = addComp[3];
					String country = addComp[4];
					String cost = data[5];
					address = new Address(street, city, state, zip, country);
					y = new Year(code, product, endDate, startDate, address, cost);
					result1.add(y);
				}
				else if(data[1].equals("P")) {
					Parking p;
					String code = data[0];
					String product = "P";
					String cost = data[2];
					p = new Parking(code, product, cost);
					result1.add(p);
				}
				else if(data[1].equals("D")) {
					Day d;
					Address address;
					String code = data[0];
					String product = "P";
					String[] timeDate = data[2].split(" ");
					String date = timeDate[0];
					String time = timeDate[1];
					String[] addComp = data[3].split(",");
					String street = addComp[0];
					String city = addComp[1];
					String state = addComp[2];
					String zip = addComp[3];
					String country = addComp[4];
					String cost = data[4];
					address = new Address(street, city, state, zip, country);
					d = new Day(code, product, date, time, address, cost);
					result1.add(d);
				}
				else {
					Equipment e;
					String code= data[0];
					String product = "R";
					String cost = data[2];
					e = new Equipment(code, product, cost);
					result1.add(e);
				}
				
			}

			j++;
		}
		t.close();
		return result1;
	}

	
	@SuppressWarnings({ "null", "unchecked", "rawtypes" })
	public static <Invoices, T> ArrayList<Invoice> readInvoice() 
	{
		int j = 0;
		ArrayList<Invoice> result = new ArrayList<Invoice>();
		String fileName = "Invoices.dat";
		Scanner t = null;
		try {
			t = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while (t.hasNextLine()) {
			if (j == 0) {
				// skip first line
				t.nextLine();
			} else {
				
				String line = t.nextLine();
				String[] data4 = line.split(";");
				String invoiceCode = data4[0];
				String memberCode = data4[1];
				String PTCode = data4[2];
				String InvoiceDate = data4[3];
				String temp = data4[4];
				List<T> ProductList = null;
				
				for(int i = 0; i < temp.length(); i++)
				{
					String[] data = temp.split(",");
					ProductList.add((T) data);
				}
			
				
				Invoice<T> invoice = new Invoice<T>(invoiceCode, memberCode, PTCode, InvoiceDate, ProductList);
				
				//per1 = new Membership(member, type, id, name, address);
				result.add(invoice);
			}

			j++;
		}
		t.close();
		//return result;
		
		return result;
	}

	
}
