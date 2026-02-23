package main;

import java.util.ArrayList;
import java.util.Scanner;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class ContactService {
	//The following instance variables are set up for MongoDB
	//replacing the ArrayList approach of storing records
		
	//The connection URI set up to connect to MongoDB
	private static final String URI = "mongodb://localhost:27017/";
	
	//ACT database and Contact collection are set up
	MongoClient mongoClient = MongoClients.create(URI);
	MongoDatabase database = mongoClient.getDatabase("ACT");
	MongoCollection<Document> contact = database.getCollection("Contact");
	
	//All the information is added to the contactList array as new contact
	public boolean addContact(String contactID, String firstName, String lastName, String phoneNumber, String address) {
		//Checks if contactID is unique
		Document unique = contact.find(Filters.eq("_contactID")).first();
		if(unique != null) {//contact ID is unique and thus added to the contact Collection in the database
			contact newContact = new contact(contactID, firstName, lastName, phoneNumber, address);//verifies all inputs are legal
			//After input verification, the contact inputs are then stored and added to the database
			Document contactDoc = new Document("_contactID: ",contactID);
			contactDoc.append("_contactFirstName: ", firstName);
			contactDoc.append("_contactLastName: ", lastName);
			contactDoc.append("_contactPhoneNumber: ", phoneNumber);
			contactDoc.append("_contactAddress: ", address);
			contact.insertOne(contactDoc);
			return true;
		}
		else {//contact ID already exists in the database
			throw new IllegalArgumentException("contact ID must be unique");
		}
	}
	
	
	//Deletes contact based on contactID
	public boolean deleteContact(String contactID) {
		//The try method deletes the one contact we are looking for via contactID
		try(MongoCursor<Document> cursor = contact.deleteOne(Filters.eq("_contactID", contactID))){
			System.out.println("Contact "+contactID+" has been deleted!");//It tells the user the contact is deleted from the database
		}
		catch(Exception e) {//The contactID that was entered doesn't exist so therefore it can't be deleted
			throw new IllegalArgumentException("Contact ID not found");
		}
	}
	
	//Updates contact based on contactID
	public boolean updateContact(String contactID) {//removed String firstName, String lastName, String phoneNumber, String address so only the contactID is scanned
		Scanner scanner = new Scanner(System.in); //added a scanner variable to update contact information
		
		//The try method searches the database for a matching contactID to update all the information within said contactID
		try(Bson updateFilter = Filters.eq("_contactID: ", contactID)){
				
			//The following if statements below makes sure the updates that are being made
			//to make sure the string length rules are still being followed. Copied from Contact.java
				
			//Updating first name
			System.out.println("UPDATE FIRST NAME: (Up to 10 characters)");
			String newFirstName = scanner.nextLine();
			if(newFirstName == null || newFirstName.length() > 10) {
				scanner.close();
				throw new IllegalArgumentException("First name cannot be null. First name must be up to 10 characters");
			}
			contact.updateOne(Filters.eq(updateFilter),Updates.set("_contactFirstName: ", newFirstName));
					
			//Updating last name
			System.out.println("UPDATE LAST NAME: (up to 10 characters)");
			String newLastName = scanner.nextLine();
			if(newLastName == null || newLastName.length() > 10) {
				scanner.close();
				throw new IllegalArgumentException("Last name cannot be null. Last name must be up to 10 characters");
			}
			contact.updateOne(Filters.eq(updateFilter),Updates.set("_contactLastName: ", newLastName));
					
			//Updating phone number
			System.out.println("UPDATE PHONE NUMBER: (exactly 10 characters)");
			String newPhoneNumber = scanner.nextLine();
			if(newPhoneNumber == null || newPhoneNumber.length() != 10) {
				scanner.close();
				throw new IllegalArgumentException("Phone number cannot be null. Phone number must be exactly 10 characters");
			}
			contact.updateOne(Filters.eq(updateFilter),Updates.set("_contactPhoneNumber: ", newPhoneNumber));
					
			//Updating Address
			System.out.println("UPDATE ADDRESS: ");
			String newAddress = scanner.nextLine();
			if(newAddress == null || newAddress.length() > 30) {
				scanner.close();
				throw new IllegalArgumentException("Address cannot be null. Address must be up to 30 characters");
			}
			contact.updateOne(Filters.eq(updateFilter),Updates.set("_contactAddress: ", newAddress));
			
			//The system informs the user the contact within the database has been successfully updated
			System.out.println("Contact "+contactID+"has been sucessfully updated!");
			scanner.close();
			return true;
		
		}
		catch(Exception e){//The contact ID cannot be found
			scanner.close();
			throw new IllegalArgumentException("Contact ID not found");
		}
	}
	
	//Created a readContact function to read the contact information
	public boolean readContact(String contactID) {
		//The try method searches the database via contactID
		try(MongoCursor<Document> cursor = contact.find(Filters.eq("_contactID", contactID)).iterator()){
			while(cursor.hasNext()) {
				System.out.print(cursor.next().toJson());//Displays the document
			}
			return true;
		}
		catch(Exception e) {//The contact can't be found
			e.printStackTrace();
		}
	}
}
