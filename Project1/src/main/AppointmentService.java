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
	
	public int searchAppointmentIndex(String ID) {
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
}
