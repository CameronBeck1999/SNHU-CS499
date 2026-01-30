package main;

public class Contact {
	//declaring the relevant variables as private
	private final String contactID;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String address;
	
	//Setting up constructors
	public Contact(String contactID, String firstName, String lastName, String phoneNumber, String address) {
		if(contactID == null || contactID.length() > 10) {
			throw new IllegalArgumentException("Contact ID cannot be null. ContactID must contain up to 10 characters");
		}
		if(firstName == null || firstName.length() > 10) {
			throw new IllegalArgumentException("First name cannot be null. First name must be up to 10 characters");
		}
		if(lastName == null || lastName.length() > 10) {
			throw new IllegalArgumentException("Last name cannot be null. Last name must be up to 10 characters");
		}
		if(phoneNumber == null || phoneNumber.length() != 10) {
			throw new IllegalArgumentException("Phone number cannot be null. Phone number must be exactly 10 characters");
		}
		if(address == null || address.length() > 30) {
			throw new IllegalArgumentException("Address cannot be null. Address must be up to 30 characters");
		}
		this.contactID = contactID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	//setters
	public void setFirstName(String newFirstName) {
		if(newFirstName == null || newFirstName.length() > 10) {
			throw new IllegalArgumentException("First name cannot be null. First name must be up to 10 characters");
		}
		else {
			firstName = newFirstName;
		}
	}
	public void setLastName(String newLastName) {
		if(newLastName == null || newLastName.length() > 10) {
			throw new IllegalArgumentException("Last name cannot be null. Last name must be up to 10 characters");
		}
		else {
			lastName = newLastName;
		}
	}
	public void setPhoneNumber(String newPhoneNumber) {
		if(newPhoneNumber == null || newPhoneNumber.length() != 10) {
			throw new IllegalArgumentException("Phone number cannot be null. Phone number must be exactly 10 characters");
		}
		else {
			phoneNumber = newPhoneNumber;
		}
	}
	public void setAddress(String newAddress) {
		if(newAddress == null || newAddress.length() > 30) {
			throw new IllegalArgumentException("Address cannot be null. Address must be up to 30 characters");
		}
		else {
			address = newAddress;
		}
	}
			
	//getters
	public String getContactID() {
		return this.contactID;
	}
	public String getFirstName() {
		return this.firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	public String getAddress() {
		return this.address;
	}
	
}
