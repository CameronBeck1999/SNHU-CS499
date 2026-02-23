package JUnitTest;

import static org.junit.Assert.*;




import org.junit.Test;
import org.junit.jupiter.api.Assertions;


import main.ContactService;

public class ContactServiceTest {

	@Test
	public void testAddContact() {
		ContactService service = new ContactService();
		
		assertTrue(service.addContact("ContactID","FirstName","LastName", "PhoneNumb.", "Address"));
		
		//All null values
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.addContact(null, null, null, null, null);
		});
		
		//ContactID is null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.addContact(null,"FirstName","LastName", "PhoneNumb.", "Address");
		});
		
		//FirstName is null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.addContact("ContactID", null, "LastName", "PhoneNumb.", "Address");
		});
		
		//LastName is null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.addContact("ContactID","FirstName", null,"PhoneNumb.", "Address");
		});
		
		//Phone number is null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.addContact("ContactID", "FirstName","LastName", null, "Address");
		});
		
		//Address is null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.addContact("ContactID", "FirstName","LastName", "PhoneNumb.", null);
		});
		
		//Contact ID is Too long
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.addContact("ContactIDIsToooooooLoooooooong", "FirstName","LastName", "PhoneNumb.", "Address");
		});
		
		//First Name is too long
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.addContact("ContactID", "FirstNameIsToooooLoooong", "LastName", "PhoneNumb.", null);
		});
		
		//Last Name is too long
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.addContact("ContactID", "FirstName", "LastNameIsToooooLooooong", "PhoneNumb.", null);
		});
		
		//Phone number is too long
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.addContact("ContactID", "FirstName", "LastName", "PhoneNumberIsTooooooLooooong", null);
		});
		
		//Phone number is too short
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.addContact("ContactID", "FirstName", "LastName", "phone", null);
		});
		
		//Address is too long
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.addContact("ContactID", "FirstName", "LastName", "PhoneNumb.", "AddressIsTooooooLoooooong");
		});
		
	}
	
	@Test
	public void testDeleteContactID() {
		ContactService service = new ContactService();
		service.addContact("ContactID","FirstName","LastName", "PhoneNumb.", "Address");
		assertTrue(service.deleteContact("ContactID"));
		
		//Contact ID not found
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.deleteContact("AnotherContactID");
		});
	}
	
	@Test
	public void testUpdateContact() {
		ContactService service = new ContactService();
		service.addContact("ContactID","FirstName","LastName", "PhoneNumb.", "Address");
		assertTrue(service.updateContact("ContactID", "Cameron", "Beck", "1112223333", "UpdatedAddress"));
		
		//Contact ID not found
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.updateContact("AnotherContactID", "Cameron", "Beck", "1112223333", "UpdatedAddress");
		});}
}
