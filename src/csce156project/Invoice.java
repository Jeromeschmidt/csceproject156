package csce156project;

import java.lang.reflect.Member;
import java.util.List;

import fileReader.flatFileReader;

public class Invoice<T> {

	private List<Persons> personList;
	private List<Membership> memberList;
	private List<Service> serviceList;
	private List<Invoice> invoiceList;

	private String invoiceCode;
	private String memberCode;
	private String pTCode;
	private String invoiceDate;
	private String[] productList;
	private Persons p;
	private Membership m;
	private String code;

	public Invoice() {

	}

	public Invoice(String invoiceCode, String memberCode, String pTCode, String invoiceDate, String[] productList) {
		super();
		this.invoiceCode = invoiceCode;
		this.memberCode = memberCode;
		this.pTCode = pTCode;
		this.invoiceDate = invoiceDate;
		this.productList = productList;

	}

	public Invoice(List<Persons> personList, List<Membership> memberList, List<Service> serviceList,
			List<Invoice> invoiceList, String code) {
		this.personList = personList;
		this.memberList = memberList;
		this.serviceList = serviceList;
		this.invoiceList = invoiceList;
		this.code = code;

		boolean found = false;
		while (!found) {
			for (int j = 0; j < memberList.size(); j++) {
				if (code.equals(memberList.get(j).getMembership())) {
					this.m = memberList.get(j);
					for (int i = 0; i < personList.size(); i++) {
						if (m.getId().equals(personList.get(i).getId()))
							this.p = personList.get(i);
						found = true;
					}

				}
			}
		}

		for (int l = 0; l < invoiceList.size(); l++) {
			if (code.equals(invoiceList.get(l).getMemberCode())) {
				this.productList = invoiceList.get(l).getProductList();
				this.invoiceCode = invoiceList.get(l).getInvoiceCode();
				this.pTCode = invoiceList.get(l).getpTCode();
			}

		}

		this.memberCode = code;

	}

	public List<Persons> getPersonList() {
		return personList;
	}

	public void setPersonList(List<Persons> personList) {
		this.personList = personList;
	}

	public List<Membership> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<Membership> memberList) {
		this.memberList = memberList;
	}

	public List<Service> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<Service> serviceList) {
		this.serviceList = serviceList;
	}

	public List<Invoice> getInvoiceList() {
		return invoiceList;
	}

	public void setInvoiceList(List<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
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

	public String[] getProductList() {

		return productList;
	}

	public void setProductList(String[] productList) {
		this.productList = productList;
	}

	Address addy = null;
	String member = null;
	String pile = null;
	String person = null;
	String name = null;
	String membershipType = null;
	double taxes = 0;

	public String getName() {
		this.name = m.getName();
		return name;
	}

	public String getType() {
		this.membershipType = m.getType();
		return membershipType;
	}

	public String getPersonName() {

		person = p.getLastName() + ", " + p.getFirstName();

		return person;
	}

	public Address getAddress() {

		this.addy = m.getAddress();

		return addy;
	}

	public String getMember() {

		this.member = m.getName() + " [" + m.getType() + "] ";

		return member;

	}

	public String getTrainer() {
		for (int i = 0; i < personList.size(); i++) {
			if (pTCode.equals(personList.get(i).getId()))
				this.pile = personList.get(i).getLastName() + ", " + personList.get(i).getFirstName();
		}
		return pile;
	}

	double subtotal = 0;

	double total = 0;

	public double getSubtotal() {

		boolean isMem = true;
		boolean isYear = false;
		int numFree = 0;
		for (int i = 0; i < productList.length; i++) {
			String[] bits = productList[i].split(":");
			String cod = bits[0];
			int quant = Integer.parseInt(bits[1]);
			double disc = 1;
			String third = "";
			boolean park = true;
			boolean free = true;
			if (bits.length == 3) {
				third = bits[2];
				for (int k = 0; k < serviceList.size(); k++) {
					if (third.equals(serviceList.get(k).getCode())) {
						if (serviceList.get(k).getProduct().equals("D")) {
							numFree = 1;
						} else if (serviceList.get(k).getProduct().equals("Y")) {
							numFree = quant;
						}
					}

				}

			}
			for (int j = 0; j < serviceList.size(); j++) {

				if (cod.equals(serviceList.get(j).getCode())) {
					if (serviceList.get(j).getProduct().equals("D")) {

					}
					if (serviceList.get(j).getProduct().equals("Y") || (serviceList.get(j).getProduct().equals("D"))) {
						isMem = false;
						disc = serviceList.get(j).getDiscount();

					}

					if (serviceList.get(j).getProduct().equals("P")) {
						quant = quant - numFree;
					}

					if (serviceList.get(j).getProduct().equals("Y")) {
						isYear = true;
					}
					if (serviceList.get(j).getProduct().equals("R") && quant - numFree == 0) {
						disc = 0.95;
					}
					subtotal = quant * serviceList.get(j).getCostt() * disc;
					disc = 1;

				}

			}
			total += subtotal;
		}

		return total;
	}

	double fees = 0;

	@SuppressWarnings("unlikely-arg-type")
	public double getFees() {

		if (m.getType().equals("General")) {
			fees = 0;
		} else {
			fees = 10.50;
		}
		return fees;

	}

	double tax = 0;

	public double getTax() {

		double total = 0;
		boolean isMem = true;
		boolean isYear = false;
		int numFree = 0;
		for (int i = 0; i < productList.length; i++) {
			String[] bits = productList[i].split(":");
			String cod = bits[0];
			int quant = Integer.parseInt(bits[1]);
			double disc = 1;
			String third = "";
			boolean park = true;
			boolean free = true;
			double tax = 0;

			if (bits.length == 3) {
				third = bits[2];
				for (int k = 0; k < serviceList.size(); k++) {
					if (third.equals(serviceList.get(k).getCode())) {
						if (serviceList.get(k).getProduct().equals("D")) {
							numFree = 1;
						} else if (serviceList.get(k).getProduct().equals("Y")) {
							numFree = quant;
						}
					}

				}

			}
			for (int j = 0; j < serviceList.size(); j++) {

				if (cod.equals(serviceList.get(j).getCode())) {
					if (serviceList.get(j).getProduct().equals("Y") || (serviceList.get(j).getProduct().equals("D"))) {
						isMem = false;
						disc = serviceList.get(j).getDiscount();

						tax = 0.06;

					}

					if (serviceList.get(j).getProduct().equals("P")) {

						quant = quant - numFree;
						tax = 0.04;

					}

					if (serviceList.get(j).getProduct().equals("Y")) {
						isYear = true;
						tax = 0.06;
					}
					if (serviceList.get(j).getProduct().equals("R")) {
						tax = 0.04;
					}
					if (serviceList.get(j).getProduct().equals("R") && quant - numFree == 0) {
						disc = 0.95;
						tax = 0.04;
					}
					subtotal = quant * serviceList.get(j).getCostt() * disc * tax;
					disc = 1;

				}

			}
			taxes += subtotal;
		}

		return taxes;
	}

	String codes = "";

	public String getProductVal() {
		for (int i = 0; i < productList.length; i++) {
			String[] bits = productList[i].split(":");
			String yo = bits[0];
			for (int j = 0; j < serviceList.size(); j++) {
				if (yo.equals(serviceList.get(j).getCode())) {
					codes = codes.concat(serviceList.get(j).getCode() + "\n");
				}

			}

		}
		return codes;
	}

	double disc = 0;

	public double getDisc() {

		if (m.getType().equals("Student")) {
			disc = tax + (total * 0.08);
		}

		return disc;

	}

	public double getGrandTotal() {
		
	
		if (m.getType().equals("Student")) {
			disc = getDisc() + taxes;
		} else {
			disc = getDisc();
		}

		return total + fees + taxes - disc;
		
	}
	
	
	
	

	public void writeLong() {

			System.out.println("Invoice " + invoiceCode);
			System.out.println("========================");
			System.out.println("Personal Trainer: " +  getTrainer());
			System.out.println("Member Info: ");
			System.out.println(" " + m.getName());
			System.out.println(" ["+m.getType()+"]");
			System.out.println(" " + m.getAddress().getAddress());// member address part 1
			System.out.println(" " + m.getAddress().getCity() + " " + m.getAddress().getState() + " " + m.getAddress().getZip());// member city state zip
			System.out.println("-------------------------------------------");
			System.out.printf("%5s %5s %40s %10s %10s\n", "Code", "Item", "Subtotal", "Tax", "Total");
			
			for(Service s: serviceList) {
				System.out.println(s.getCode());
			}
			
			
			System.out.printf("%5s %5s %40s %10s %10s\n", "Code", "Item", "Subtotal", "Tax", "Total");
			
			
			System.out.println();
			System.out.println();
		
	}
}
