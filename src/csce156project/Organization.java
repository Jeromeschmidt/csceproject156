package csce156project;

import java.util.List;

import fileReader.flatFileReader;

public class Organization {

	public Organization() {

	}

	List<Persons> personList = flatFileReader.readPersons();
	List<Membership> memberList = flatFileReader.readMembers();
	List<Service> serviceList = flatFileReader.readService();

	Address addy = null;
	String member = null;
	String pile = null;
	String person = null;
	String name = null;

	public String getName(String code) {
		for (int j = 0; j < memberList.size(); j++) {
			if (code.equals(memberList.get(j).getMembership())) {
				name = memberList.get(j).getName();
			}
		}
		return name;
	}

	public String getType(String code) {
		for (int j = 0; j < memberList.size(); j++) {
			if (code.equals(memberList.get(j).getMembership())) {
				name = memberList.get(j).getType();

			}

		}
		return name;
	}

	public String getPerson(String Code) {
		for (int j = 0; j < memberList.size(); j++) {
			if (Code.equals(memberList.get(j).getMembership())) {
				for (int i = 0; i < personList.size(); i++) {
					if (memberList.get(j).getId().equals(personList.get(i).getId()))
						person = personList.get(i).getLastName() + ", " + personList.get(i).getFirstName();
				}
			}
		}
		return person;
	}

	public String getAddress(String Code) {
		for (int j = 0; j < memberList.size(); j++) {
			if (Code.equals(memberList.get(j).getMembership())) {
				person = memberList.get(j).getAddress().getAddress() + "\n" + " "
						+ memberList.get(j).getAddress().getCity() + " " + memberList.get(j).getAddress().getState()
						+ " " + memberList.get(j).getAddress().getZip() + " "
						+ memberList.get(j).getAddress().getCountry();
			}
		}

		return person;
	}

	public String getMember(String code) {

		for (int j = 0; j < memberList.size(); j++) {
			if (code.equals(memberList.get(j).getMembership())) {
				member = memberList.get(j).getName() + " [" + memberList.get(j).getType() + "] ";
			} else {

			}
		}

		return member;

	}

	public String getTrainer(String ptCode) {
		for (int j = 0; j < personList.size(); j++) {
			if (ptCode.equals(personList.get(j).getId())) {
				pile = personList.get(j).getLastName() + ", " + personList.get(j).getFirstName();
			}
		}

		return pile;
	}

	double subtotal = 0;

	public double getSubtotal(String[] productList) {
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
	public double getFees(String code) {
		for (int k = 0; k < memberList.size(); k++) {
			if (code.equals(memberList.get(k).getMembership())) {
				if (memberList.get(k).getType().equals("General")) {
					fees = 0;
				} else {
					fees = 10.50;
				}
			}
		}
		return fees;

	}

	double tax = 0;

	public double getTax(String[] productList) {

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
			total += subtotal;
		}

		return total;
	}

	String codes = "";

	public String getProductVal(String[] productList) {
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

	public double getDisc(String[] productList, String code) {
		double disc = 0;
		for (int j = 0; j < memberList.size(); j++) {
			if (code.equals(memberList.get(j).getMembership())) {
				if (memberList.get(j).getType().equals("Student")) {
					disc = getTax(productList) + (getSubtotal(productList) * 0.08);
				}

			}

		}
		return disc;

	}

	String code;
	String[] bits;
	String[][] all;
//	public String[][] codeBuild(String[] productList) {
//	for(int k = 0; k < 2; k++) {	
//		for (int i = 0; i < productList.length; i++) {
//			bits = productList[i].split(":");
//			
//			
//		}
//		all[k]= bits;
//	}
//		return all;
//
//	}

}
