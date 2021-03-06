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

	/**
	 * constructs invoice information from invoice.dat
	 * @param invoiceCode
	 * @param memberCode
	 * @param pTCode
	 * @param invoiceDate
	 * @param productList
	 */
	public Invoice(String invoiceCode, String memberCode, String pTCode, String invoiceDate, String[] productList) {
		super();
		this.invoiceCode = invoiceCode;
		this.memberCode = memberCode;
		this.pTCode = pTCode;
		this.invoiceDate = invoiceDate;
		this.productList = productList;

	}

	/**
	 * passes created lists, selects all information from them that pertains to member whos code is passed
	 * @param personList
	 * @param memberList
	 * @param serviceList
	 * @param invoiceList
	 * @param code
	 */
	public Invoice(List<Persons> personList, List<Membership> memberList, List<Service> serviceList,
			List<Invoice> invoiceList, String code) {
		this.personList = personList;
		this.memberList = memberList;
		this.serviceList = serviceList;
		this.invoiceList = invoiceList;
		this.code = code;

		/**
		 * sets member and person variables to member whose information we want
		 */
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
		
		/**
		 * sets up invoice variables to specified member
		 */
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

	/**
	 * retrieves company name of desired member
	 * @return name
	 */
	public String getName() {
		this.name = m.getName();
		return name;
	}

	/**
	 * finds type of membership
	 * @return membershipType
	 */
	public String getType() {
		this.membershipType = m.getType();
		return membershipType;
	}

	/**
	 * finds name of desired member
	 * @return person
	 */
	public String getPersonName() {

		person = p.getLastName() + ", " + p.getFirstName();

		return person;
	}

	/**
	 * finds Address class of desired member
	 * @return addy
	 */
	public Address getAddress() {

		this.addy = m.getAddress();

		return addy;
	}

	/**
	 * builds string for individual report info on member
	 * @return member
	 */
	public String getMember() {

		this.member = m.getName() + " [" + m.getType() + "] ";

		return member;

	}

	
	/**
	 * finds trainer that signed the certain member
	 * @return pile
	 */
	public String getTrainer() {
		for (int i = 0; i < personList.size(); i++) {
			if (pTCode.equals(personList.get(i).getId()))
				this.pile = personList.get(i).getLastName() + ", " + personList.get(i).getFirstName();
		}
		return pile;
	}

	double subtotal = 0;

	double total = 0;

	/**
	 * finds information on products of the given member and calculates a subtotal before tax,fee,and discount
	 * @return subtotal
	 */
	public double getSubtotal() {

		boolean isMem = true;
		boolean isYear = false;
		int numFree = 0;
		/**
		 * loops through list of products purchased by certain member
		 */
		for (int i = 0; i < productList.length; i++) {
			String[] bits = productList[i].split(":");
			String cod = bits[0];
			int quant = Integer.parseInt(bits[1]);
			double disc = 1;
			String third = "";
			boolean park = true;
			boolean free = true;
			
			/**
			 * checks if product has a membership type its assigned to 
			 */
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
			/**
			 * checks type of product and calculates values based on free passes and quantity * cost
			 */
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

	/**
	 * finds fees related to membership type student
	 * @return fees
	 */
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

	/**
	 * calculates tax on products given student type or product type
	 * @return taxes
	 */
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

	double disc = 0;
/**
 * gets discount associated with Student memberships
 * @return disc
 */
	public double getDisc() {

		if (m.getType().equals("Student")) {
			disc = taxes + (total * 0.08);
		}

		return disc;

	}

	/**
	 * gets total of all purchases
	 * @return grandtotal
	 */
	public double getGrandTotal() {

		if (m.getType().equals("Student")) {
			disc = getDisc() + taxes;
		} else {
			disc = getDisc();
		}

		return total + fees + taxes - disc;

	}

	/**
	 * creates individual invoice for specified member
	 */
	public void writeLong() {

		System.out.println("Invoice " + invoiceCode);
		System.out.println("========================");
		System.out.println("Personal Trainer: " + getTrainer());
		System.out.println("Member Info: ");
		System.out.println(" " + m.getName() + "  (" + m.getMembership() + ")");
		System.out.println(" [" + m.getType() + "]");
		System.out.println(" " + m.getAddress().getAddress());// member address part 1
		System.out.println(
				" " + m.getAddress().getCity() + " " + m.getAddress().getState() + " " + m.getAddress().getZip());// member
																													// city
																													// state
																													// zip
		System.out.println("-------------------------------------------");
		System.out.printf("%-10s %-10s %66s %10s %10s\n", "Code", "Item", "Subtotal", "Tax", "Total");

		String code = "";
		String[] further = null;
		int quantity = 0;
		String prodName = "";
		double cost = 0;
		String addedInfo = "";
		String moreInfo = "";
		double subtotal = 0.0;
		int numFree = 0;
		boolean isMem = true;
		boolean isYear = false;
		double subsubtotal = 0.0;
		double subtax = 1.0;
		double subtotals = 0.0;
		double subtaxes = 0.0;
		double totalTotals = 0.0;
		for (int i = 0; i < productList.length; i++) {
			further = productList[i].split(":");
			code = further[0];
			quantity = Integer.parseInt(further[1]);
			
			for(int j = 0; j < serviceList.size(); j++) {
				if(code.equals(serviceList.get(j).getCode())) {
					prodName = serviceList.get(j).getProductName();
					cost = serviceList.get(j).getCostt();
					
					if(serviceList.get(j).getProduct().equals("R")) {
						moreInfo = "/unit";					
						}
					double disc = 1;
					String third = "";
					boolean park = true;
					boolean free = true;
					if (further.length == 3) {
						third = further[2];
						for (int k = 0; k < serviceList.size(); k++) {
							if (third.equals(serviceList.get(k).getCode())) {
								if (serviceList.get(k).getProduct().equals("D")) {
									numFree = 1;
								} else if (serviceList.get(k).getProduct().equals("Y")) {
									numFree = quantity;
								}
							}

						}

					}
					for (int l = 0; l < serviceList.size(); l++) {

						if (code.equals(serviceList.get(l).getCode())) {
							if (serviceList.get(l).getProduct().equals("D")) {

							}
							if (serviceList.get(l).getProduct().equals("Y") || (serviceList.get(j).getProduct().equals("D"))) {
								isMem = false;
								disc = serviceList.get(l).getDiscount();
								tax = 0.06;
							}

							if (serviceList.get(l).getProduct().equals("P")) {
								quantity = quantity - numFree;
								tax = 0.04;
							}

							if (serviceList.get(l).getProduct().equals("Y")) {
								isYear = true;
							}
							if (serviceList.get(l).getProduct().equals("R") && quantity - numFree == 0) {
								disc = 0.95;
								tax = 0.04;
							}
							subtotal = quantity * serviceList.get(l).getCostt() * disc;
							
							subtax = quantity * serviceList.get(l).getCostt() * disc * tax;

						}

					}
					subsubtotal += subtotal;
					addedInfo = "(" + quantity + " units @ $" + cost + moreInfo+ ")";
					
				}
				
			}
			
			System.out.printf("%-10s %-10s %-20s %66.2f %10.2f %12.2f\n", code, prodName + " \r", addedInfo, subtotal, subtax , subtotal + subtax);
			subtaxes += subtax;
		}
		subtotals += subsubtotal;
		
		
		System.out.println("                                                                                ====================================");
		System.out.printf("%-10s %-10s %68.2f %10.2f %10.2f\n", "SUB-Totals", "", subtotals, subtaxes, subtotals + subtaxes);
		if(getType().equals("Student")) {
		System.out.printf("%-10s %75s %-75.2f\n", "DISCOUNT (8% STUDENT & NO TAX)", "$-",   (0.08 *subtotals)+subtaxes);
		System.out.printf("%-10s %80s %-81s\n", "ADDITIONAL FEE (Student)", "$",   "10.50");
		System.out.printf("%-10s %94s %-81.2f\n", "TOTAL", "$",   (subtotals + subtaxes + getFees() - ((0.08 *subtotals)+subtaxes)));
		}
		else {
			System.out.printf("%-10s %95s %-95.2f\n", "TOTAL", "$",   subtotals + subtaxes);
		}
		
		System.out.println();
		System.out.println();
		System.out.println("			Thank you for your purchase!");
		System.out.println();
		System.out.println();

	}}
