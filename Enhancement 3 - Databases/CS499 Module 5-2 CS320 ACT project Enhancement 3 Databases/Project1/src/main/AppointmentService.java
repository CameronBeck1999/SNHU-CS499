package main;
import java.util.ArrayList;
import java.util.Date;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;


import org.bson.Document;

public class AppointmentService {
	//The following instance variables are set up for MongoDB
	//replacing the ArrayList approach of storing records
	
	//The connection URI set up to connect to MongoDB
	private static final String URI = "mongodb://localhost:27017/";
	
	//ACT database and Appointment collection are set up
	MongoClient mongoClient = MongoClients.create(URI);
	MongoDatabase database = mongoClient.getDatabase("ACT");
	MongoCollection<Document> appointment = database.getCollection("Appointment");
	
	
	public boolean addAppointment(String appointmentID, Date appointmentDate, String appointmentDescription) {
			
		//Checks if appointmentID is unique
		Document unique = appointment.find(Filters.eq("_appointmentID")).first();
		if(unique != null) {//appointment ID is unique and thus added to the Appointment Collection in the database
			Appointment newAppointment = new Appointment(appointmentID, appointmentDate, appointmentDescription);//verifies all inputs are legal
			//After input verification, the appointment inputs are then stored and added to the database
			Document appointmentDoc = new Document("_appointmentID: ",appointmentID);
			appointmentDoc.append("_appointmentDate: ", appointmentDate);
			appointmentDoc.append("_appointmentDescription: ", appointmentDescription);
			appointment.insertOne(appointmentDoc);
			return true;
		}
		else {//Appointment ID already exists in the database
			throw new IllegalArgumentException("Appointment ID must be unique");
		}
	}
	
	public boolean deleteAppointment(String appointmentID) {
		//The try method deletes the one appointment we are looking for via appointmentID
		try(MongoCursor<Document> cursor = appointment.deleteOne(Filters.eq("_appointmentID", appointmentID))){
			System.out.println("Appointment "+appointmentID+" has been deleted!");//It tells the user the appointment is deleted from the database
		}
		catch(Exception e) {//The inputed ID doesn't exist so therefore it can't be deleted
			throw new IllegalArgumentException("Appointment ID not found");
		}
	}
	
	//Added a readAppointment function for the main class to read the information
	//the user is searching for
	public boolean readAppointment(String appointmentID) {
		//The try method searches the database via appointmentID
		try(MongoCursor<Document> cursor = appointment.find(Filters.eq("_appointmentID", appointmentID)).iterator()){
			while(cursor.hasNext()) {
				System.out.print(cursor.next().toJson());//Displays the document
			}
		}
		catch(Exception e) {//The appointment can't be found
			e.printStackTrace();
		}
	}
}
