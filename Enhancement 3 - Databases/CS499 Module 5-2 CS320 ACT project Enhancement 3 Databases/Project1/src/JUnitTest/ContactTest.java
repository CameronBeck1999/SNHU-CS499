package JUnitTest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;


import main.Contact;

public class ContactTest {
	
	@Test
	public void testContact() {
		Contact contact = new Contact("01171999","Cameron","Beck","PhoneNumb.", "Address");
		assertTrue(contact.getContactID().equals("01171999"));
		assertTrue(contact.getFirstName().equals("Cameron"));
		assertTrue(contact.getLastName().equals("Beck"));
		assertTrue(contact.getPhoneNumber().equals("PhoneNumb."));
		assertTrue(contact.getAddress().equals("Address"));
	}
	
	@Test
	public void testContactIDTooLong() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("0123456789876543210","Cameron","Beck","PhoneNumb.", "Address");
		});
	}
	
	@Test
	public void testFirstNameTooLong() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("123456789","Cameronnnnnnnnnnnnnnnnn","Beck","PhoneNumb.", "Address");
		}); }
	
	@Test
	public void testLastNameTooLong() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("123456789","Cameron","Beckkkkkkkkkkkkkkkk","PhoneNumb.", "Address");
		}); }
	
	@Test
	public void testPhoneNumberNotTenCharacters() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("123456789","Cameron","Beck","PhoneNumberrrrrrrrrrrrrrrrrrrrrrrrrrrr", "Address");
		}); }
	
	@Test
	public void testAddressTooLong() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("123456789","Cameron","Beck","PhoneNumb.", "Addressssssssssssssssssssssssssssssssssssssssss");
		}); }
	
	@Test
	public void testAllNullValues() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact(null,null,null,null,null);
		}); }
	
	@Test
	public void testNullContactID() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact(null,"Cameron","Beck","PhoneNumb.", "Address");
		}); }
	
	@Test
	public void testNullFirstName() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("123456789",null,"Beck","PhoneNumb.", "Address");
		}); }
	
	@Test
	public void testNullLastName() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("123456789","Cameron",null,"PhoneNumb.", "Address");
		}); }
	
	@Test
	public void testNullPhoneNumber() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("123456789","Cameron","Beck",null, "Address");
		}); }
	
	@Test
	public void testNullAddress() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Contact("123456789","Cameron","PhoneNumb.","3372814576", null);
		}); }
}
