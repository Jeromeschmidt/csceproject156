package csce156project;

import java.util.List;

import fileReader.flatFileReader;

public class Organization {

	public Organization() {

	}

	List<Persons> personList = flatFileReader.readPersons();
	List<Membership> memberList = flatFileReader.readMembers();
	List<Service> serviceList = flatFileReader.readService();

	String member = null;
	String pile = null;

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
		String isMem = "";
		boolean isYear = false;

		for (int i = 0; i < productList.length; i++) {
			String[] bits = productList[i].split(":");
			String cod = bits[0];
			int quant = Integer.parseInt(bits[1]);
			double disc = 1;

			for (int j = 0; j < serviceList.size(); j++) {

				if (cod.equals(serviceList.get(j).getCode())) {
					if (serviceList.get(j).getProduct().equals("D")) {

					}
					if (serviceList.get(j).getProduct().equals("Y") || (serviceList.get(j).getProduct().equals("D"))) {
						isMem = "No";
						disc = serviceList.get(j).getDiscount();

					}

					if (isMem.equals("No") && serviceList.get(j).getProduct().equals("P")) {
						quant = 0;

					}
					if (serviceList.get(j).getProduct().equals("Y")) {
						isYear = true;
					}
					if (isYear == true && serviceList.get(j).getProduct().equals("R")) {
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
		
		for (int i = 0; i < productList.length; i++) {
			String[] bits = productList[i].split(":");
			String cod = bits[0];
			int quant = Integer.parseInt(bits[1]);
			
			for (int j = 0; j < serviceList.size(); j++) {

				if (cod.equals(serviceList.get(j).getCode())) {
					
					
					
				}

			}
			tax += subtotal;
		}

		return total;
	}

}
