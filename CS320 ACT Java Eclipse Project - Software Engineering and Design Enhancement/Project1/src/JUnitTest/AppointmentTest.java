package JUnitTest;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import main.Appointment;


import java.util.Date;

class AppointmentTest {

	@Test
	public void testAppointment() {
		Date currentDate = new Date();
		
		Appointment newAppointment = new Appointment("APP.ID", currentDate, "Testing Appointment Description");
		assertTrue(newAppointment.getAppointmentID().equals("APP.ID"));
		assertTrue(newAppointment.getAppointmentDate().equals(currentDate));
		assertTrue(newAppointment.getAppointmentDescription().equals("Testing Appointment Description"));
	}
	
	@Test
	public void testAppointmentIDTooLong() {
		Date currentDate = new Date();
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Appointment("IDTOOLONGGGGGGGGGGGGGGG",currentDate,"Testing Appointment ID Length");
		}); 
		
	}
	
	@Test
	public void testAppointmentIDIsNULL() {
		Date currentDate = new Date();
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Appointment(null,currentDate,"Testing Appointment ID is null");
		});
	}
	
	@Test
	public void testAppointmentDateInThePast() {
		@SuppressWarnings("deprecation")
		Date dateTest = new Date(2025, 1, 1); //YYYY-MM-DD
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Appointment("APP.ID",dateTest,"Testing Appointment Date in the past");
		});
		
	}
	
	@Test
	public void testAppointmentDateIsNULL() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Appointment("APP.ID", null,"Testing Appointment Date Null");
		});
		
	}
	
	@Test
	public void testAppointmentDescriptionIsTooLong() {
		Date currentDate = new Date();
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Appointment("APP.ID", currentDate,"Appointment Description is TOOOOOOOOOOOOOO LOOOOOOOOOOOOOOOOOOOOONG");
		});
		
	}
	@Test
	public void testAppointmentDescriptionIsNULL() {
		Date currentDate = new Date();
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Appointment("APP.ID", currentDate,null);
		});
	}
	
	@Test
	public void testAllNULLValues() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Appointment(null,null,null);
		}); 
	}

}
