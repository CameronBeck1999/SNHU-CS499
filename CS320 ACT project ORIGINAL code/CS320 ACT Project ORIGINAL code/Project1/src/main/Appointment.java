package main;
import java.util.Date;


public class Appointment {
	//Setting up the private variables
	private final String appointmentID;
	private Date appointmentDate;
	private String appointmentDescription;
	
	//Constructor
	public Appointment(String appointmentID, Date appointmentDate, String appointmentDescription) {
		Date currentDate = new Date();
		
		if(appointmentID == null || appointmentID.length() > 10) {
			throw new IllegalArgumentException("Appointment ID cannot be longer than 10 characters or NULL.");
		}
		if(appointmentDate == null || currentDate.before(appointmentDate)) {
			throw new IllegalArgumentException("The appointment date cannot be in the past or NULL.");
		}
		if(appointmentDescription == null || appointmentDescription.length() > 50) {
			throw new IllegalArgumentException("Appointment Description cannot be longer than 50 characters or NULL");
		}
		
		this.appointmentID = appointmentID;
		this.appointmentDate = appointmentDate;
		this.appointmentDescription = appointmentDescription;
	}
	
	//setters
	public void setAppointmentDate(Date date) {
		Date currentDate = new Date();
		if(currentDate.before(appointmentDate) || appointmentDate == null) {
			throw new IllegalArgumentException("The appointment date cannot be in the past or NULL.");
		}
		this.appointmentDate = date;
	}
	public void setAppointmentDescription(String description) {
		if(appointmentDescription.length() > 50 || appointmentDescription == null) {
			throw new IllegalArgumentException("Appointment Description cannot be longer than 50 characters or NULL");
		}
		this.appointmentDescription = description;
	}
	
	//getters
	public String getAppointmentID() {
		return appointmentID;
	}
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public String getAppointmentDescription() {
		return appointmentDescription;
	}
	
	
}
