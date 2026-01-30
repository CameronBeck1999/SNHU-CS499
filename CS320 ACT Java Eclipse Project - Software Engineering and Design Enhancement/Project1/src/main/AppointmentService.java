package main;
import java.util.ArrayList;
import java.util.Date;

public class AppointmentService {
	//Created a new ArrayList to store all appointment information.
	private ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();
	
	public boolean addAppointment(String appointmentID, Date appointmentDate, String appointmentDescription) {
		//Checks if appointmentID is unique
		for(Appointment appointment : appointmentList) {
			if(appointment.getAppointmentID().equals(appointmentID)) {
				//Appointment ID already exists
				throw new IllegalArgumentException("Appointment ID must be unique");
			}
		}

		//appointment ID is unique and thus added to the appointment list
		Appointment newAppointment = new Appointment(appointmentID, appointmentDate, appointmentDescription);
		appointmentList.add(newAppointment);
		return true;
	}
	
	public int searchAppointmentIndex(String ID) {//This function is for the deleteAppointment function since we can only delete an item through an integer
		//Iterates through appointment list to find the index
		for(int index = 0; index < appointmentList.size(); index++) {
			if(ID.equals(appointmentList.get(index).getAppointmentID())) {
				//The index is found and returned to deleteAppointment()
				return index;
			}
		}
		//The index was not found
		throw new IllegalArgumentException("Appointment Index not found.");
	}
	
	public boolean deleteAppointment(String appointmentID) {
		//Iterates through appointment list to search inputed ID
		for(Appointment appointment : appointmentList) {
			if(appointment.getAppointmentID().equals(appointmentID)) {
				//ID is found and the searchAppointmentIndex retrieves the index
				//for the ArrayList remove function to delete the appointment Altogether
				appointmentList.remove(searchAppointmentIndex(appointmentID));
				return true;
			}
		}
		//The inputed ID doesn't exist so therefore it can't be deleted
		throw new IllegalArgumentException("Appointment ID not found");
	}
	
	//Added a readAppointment function for the main class to read the information
	//the user is searching for
	public boolean readAppointment(String appointmentID) {
		//For loop iterates through the appointmentList searching for the appointment ID that was inputed.
		for(Appointment appointment: appointmentList) {
			if(appointment.getAppointmentID().equals(appointmentID)){//If statement triggering means we found a match
				//The information from that appointmentID is then displayed.
				System.out.println("APPOINTMENT INFORMATION FOR "+ appointment.getAppointmentID());
				System.out.println("DATE: "+appointment.getAppointmentDate());
				System.out.println("DESCRIPTION: "+appointment.getAppointmentDescription());
				return true;
			}
		}
		//The ID cannot be found because it doesn't exist in the appointmentList
		throw new IllegalArgumentException("Appointment ID not found");
	}
}
