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
		String isMem = null;
		for (int i = 0; i < productList.length; i++) {
			String[] bits = productList[i].split(":");
			String cod = bits[0];
			int quant = Integer.parseInt(bits[1]);
			
			for (int j = 0; j < serviceList.size(); j++) {
				if (cod.equals(serviceList.get(j).getCode())) {
					if(serviceList.get(j).getProduct().equals("Y") || serviceList.get(j).getProduct().equals("D")){
						isMem = "No";
						if(isMem != "No" || serviceList.get(j).getProduct().equals("P")) {
						subtotal = quant * serviceList.get(j).getCostt();
						}
					}else {
					
					}
				}
			}
			total += subtotal;
		}

		return total;
	}

}
