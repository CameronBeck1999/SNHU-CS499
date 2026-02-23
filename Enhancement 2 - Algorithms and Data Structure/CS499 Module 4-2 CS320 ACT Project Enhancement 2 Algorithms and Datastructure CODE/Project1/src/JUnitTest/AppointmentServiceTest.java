package JUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import main.AppointmentService;
import java.util.Date;

class AppointmentServiceTest {

	@Test
	public void testAddAppointment() {
		Date currentDate = new Date();
		AppointmentService service = new AppointmentService();
		assertTrue(service.addAppointment("AddAPPT.", currentDate, "Adding new appointment"));
		
		@SuppressWarnings("deprecation")
		Date dateTest = new Date(2025, 1, 1); //YYYY-MM-DD
		
		//All NULL values
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.addAppointment(null,null,null);
		});
		
		//Appointment ID too long
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.addAppointment("IDTOOLOOOOOONG",currentDate, "Appointment ID too long");
		});
		
		//Appointment ID is NULL
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.addAppointment(null,currentDate,"Appointment ID is NULL");
		});
		
		//Appointment Date is in the past
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.addAppointment("ApptID",dateTest,"Appointment Date is in the past");
		});
		
		//Appointment Date is NULL
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.addAppointment("ApptID",null,"Appointment Date is NULL");
		});
		
		//Appointment Description is too long
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.addAppointment("ApptID",currentDate,"Appointment ID is TOOOOOOOOOOOOOOOOOO LOOOOOOOOOOOOOOOOOOOONG");
		});
		
		//Appointment Description is NULL
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.addAppointment("ApptID",currentDate,null);
		});
	}
	
	@Test
	public void testDeleteAppointment() {
		AppointmentService service = new AppointmentService();
		service.addAppointment("DELETE-ID", new Date(), "Deleting appointment");
		assertTrue(service.deleteAppointment("DELETE-ID"));
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.deleteAppointment("AnotherID");
		});
		
	}

}
