package csce156project;




public class Membership{

	public String membership;
	public String type;
	private String id;
	private String name;
	private Address address;

	public Membership(String membership, String type, String id, String name, Address addr) {

		this.membership = membership;
		if (type.equals("G")) {
			this.type = "General";
		} else {
			this.type = "Student";
		}
		this.id = id;
		this.setName(name);
		this.address = addr;

	}

	public String getId() {
		return id;
	}

	public String getMembership() {
		return membership;
	}

	public void setMembership(String membership) {
		this.membership = membership;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if (type.equals("G")) {
			this.type = "General";
		} else {
			this.type = "Student";
		}
	}

	public void setId(String id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
