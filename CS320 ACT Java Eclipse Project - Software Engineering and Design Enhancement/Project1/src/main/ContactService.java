package main;

import java.util.ArrayList;
import java.util.Scanner;

public class ContactService {
	//class ArrayList from Contact.java
	private ArrayList<Contact> contactList = new ArrayList<Contact>();
	
	//All the information is added to the contactList array as new contact
	public boolean addContact(String contactID, String firstName, String lastName, String phoneNumber, String address) {
		//Checks for existing contact
		for(Contact contact: contactList) {
			if(contact.getContactID().equals(contactID)) {
				//Contact already exists
				throw new IllegalArgumentException("Contact must be unique");
			}
		}
		//If for-loop doesn't find an already existing matching contactID, then the contactID is unique
		//and the new contact will be instated.
		Contact newContact = new Contact(contactID, firstName, lastName, phoneNumber, address);
		contactList.add(newContact);
		return true;
		
	}
	public int searchContactIndex(String ID){
		//This function was created to search and return the index ID 
		//for the deleteContacts(String ID) function to run properly
		for(int index = 0; index < contactList.size(); index++) {
			if(ID.equals(contactList.get(index).getContactID())) {
				return index;
			}
		}
		throw new IllegalArgumentException("Contact index not found");
	}
	
	//Deletes contact based on contactID
	public boolean deleteContact(String contactID) {
		//For-loop Iterates though contactList in search of the inputed contactID
		for(Contact contact: contactList) {
			if(contact.getContactID().equals(contactID)) {
				//the contact is removed from contact list
				contactList.remove(searchContactIndex(contactID));
				return true;
			}
		}
		throw new IllegalArgumentException("Contact ID not found");
	}
	
	//Updates contact based on contactID
	public boolean updateContact(String contactID) {//removed String firstName, String lastName, String phoneNumber, String address so only the contactID is scanned
		Scanner scanner = new Scanner(System.in); //added a scanner variable to update contact information
		
		//for loop iterates through contact list 
		//in search of the inputed contactID to update
		for(Contact contact: contactList) {
			if(contact.getContactID().equals(contactID)) {
				
				//The following if statements below makes sure the updates that are being made
				//to make sure the string length rules are still being followed. Copied from Contact.java
				
				//Updating first name
				System.out.println("UPDATE FIRST NAME");
				String firstName = scanner.nextLine();
				if(firstName == null || firstName.length() > 10) {
					scanner.close();
					throw new IllegalArgumentException("First name cannot be null. First name must be up to 10 characters");
				}
				contact.setFirstName(firstName);
				
				//Updating last name
				System.out.println("UPDATE LAST NAME");
				String lastName = scanner.nextLine();
				if(lastName == null || lastName.length() > 10) {
					scanner.close();
					throw new IllegalArgumentException("Last name cannot be null. Last name must be up to 10 characters");
				}
				contact.setLastName(lastName);
				
				//Updating phone number
				System.out.println("UPDATE PHONE NUMBER");
				String phoneNumber = scanner.nextLine();
				if(phoneNumber == null || phoneNumber.length() != 10) {
					scanner.close();
					throw new IllegalArgumentException("Phone number cannot be null. Phone number must be exactly 10 characters");
				}
				contact.setPhoneNumber(phoneNumber);
				
				//Updating Address
				System.out.println("UPDATE ADDRESS");
				String address = scanner.nextLine();
				if(address == null || address.length() > 30) {
					scanner.close();
					throw new IllegalArgumentException("Address cannot be null. Address must be up to 30 characters");
				}
				contact.setAddress(address);
				scanner.close();
				return true;
			}
		}
		//The contact ID cannot be found
		scanner.close();
		throw new IllegalArgumentException("Contact ID not found");
	}
	
	//Created a readContact function to read the contact information
	public boolean readContact(String contactID) {
		//The for loop iterates through the contact list.
		for(Contact contact: contactList) {
			if(contact.getContactID().equals(contactID)){//The if-statement shows we've found a match in the contactList
				//Displaying all of the information from the contactID
				System.out.println("CONTACT INFORMATION FOR "+ contact.getContactID());
				System.out.println("FIRST NAME: " + contact.getFirstName());
				System.out.println("LAST NAME: " + contact.getLastName());
				System.out.println("PHONE NUMBER: " + contact.getPhoneNumber());
				System.out.println("ADDRESS: " + contact.getAddress());
				return true;
			}
		}
		//The contact ID we're looking for doensn't exist
		throw new IllegalArgumentException("Contact ID not found");
	}
}
