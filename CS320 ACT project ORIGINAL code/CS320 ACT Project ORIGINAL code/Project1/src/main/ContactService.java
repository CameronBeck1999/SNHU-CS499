package main;

import java.util.ArrayList;

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
	public boolean updateContact(String contactID, String firstName, String lastName, String phoneNumber, String address) {
		//for loop iterates through contact list 
		//in search of the inputed contactID to update
		for(Contact contact: contactList) {
			if(contact.getContactID() == contactID) {
				//All attributes except contactID are updated
				contact.setFirstName(firstName);
				contact.setLastName(lastName);
				contact.setPhoneNumber(phoneNumber);
				contact.setAddress(address);
				return true;
			}
		}
		throw new IllegalArgumentException("Contact ID not found");
	}
}
