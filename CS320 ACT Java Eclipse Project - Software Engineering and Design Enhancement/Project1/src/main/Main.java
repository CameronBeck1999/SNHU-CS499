
package main;
import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class Main {
	
	public static void appointment(){
		Scanner scanner = new Scanner(System.in); //scanner set up.
		AppointmentService appointmentService = new AppointmentService(); //Calls the appointment service class to set up the options
		char menuOption = ' ';
		
		while(menuOption != 'q') {
			//Menu option for Appointment
			System.out.println("SELECT OPTION FOR APPOINTMENT");
			System.out.println("(1) CREATE");
			System.out.println("(2) READ");
			System.out.println("(3) DELETE");
			System.out.println("(q) RETURN TO MAIN MENU");
			menuOption = scanner.nextLine().charAt(0);
			
			if(menuOption == '1') {//CREATING A NEW APPOINTMENT
				System.out.println("ENTER NEW APPOINTMENT ID");
				String appointmentID = scanner.nextLine();
				System.out.println("ENTER APPOINTMENT DATE (MM-dd-yyyy)");
				String appointmentDate = scanner.nextLine();
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
				
				try {//Try is used to test and see if the user entered the date in the correct format as the instructions asked.
					Date date = dateFormat.parse(appointmentDate);
					System.out.println("ENTER APPOINTMENT DESCRIPTION");
					String appointmentDescription = scanner.nextLine();
					appointmentService.addAppointment(appointmentID, date, appointmentDescription);
					System.out.println("APPOINTMENT FOR "+date+" HAS BEEN ADDED!");
				}catch (ParseException e) {//catch means that the user entered the date in the wrong format and a message informs them so.
					System.out.println("INVALID DATE FORMAT. FORMAT MUST BE MM-dd-yyyy");
				}
				
			}
			else if(menuOption == '2') {//Reading appointment information based on appointmentID
				System.out.println("ENTER APPOINTMENT ID");
				String appointmentID = scanner.nextLine();
				appointmentService.readAppointment(appointmentID);//Created a new readAppointment function for AppointmentService.java class
			}
			else if(menuOption == '3') {//Deleting appointment information based on appointmentID
				System.out.println("ENTER APPOINTMENT ID");
				String appointmentID = scanner.nextLine();
				appointmentService.deleteAppointment(appointmentID);
			}
			else {
				if(menuOption == 'q' || menuOption == 'Q') {
					//program exits the Appointment function, goes back to the main function.
					System.out.println("EXITING APPOINTMENT, BACK TO MAIN MENU!!!");
				}
				else {
					//The user's input is neither 1, 2, 3, nor q.
					//Therefore the prompt asks the user for another input
					System.out.println("INVALID INPUT, TRY AGAIN!!!");
				}
			}
		}
		
	}
	
	public static void contact () {
		Scanner scanner = new Scanner(System.in);//scanner set up
		ContactService contactService = new ContactService();//calling a class variable for ContactService.java
		
		char menuOption = ' '; //Initialized menu option for the while loop
		
		while(menuOption != 'q') {
			//Contact menu Options
			System.out.println("SELECT OPTION FOR CONTACT");
			System.out.println("(1) CREATE");
			System.out.println("(2) READ");
			System.out.println("(3) UPDATE");
			System.out.println("(4) DELETE");
			System.out.println("(q) RETURN TO MAIN MENU");
			menuOption = scanner.nextLine().charAt(0);//menu option input value
			
			if(menuOption == '1') {//CREATING A NEW CONTACT
				System.out.println("ENTER NEW CONTACT ID");
				String contactID = scanner.nextLine();
				System.out.println("ENTER FIRST NAME");
				String firstName = scanner.nextLine();
				System.out.println("ENTER LAST NAME");
				String lastName = scanner.nextLine();
				System.out.println("ENTER PHONE NUMBER (xxx-xxx-xxxx)");
				String phoneNumber = scanner.nextLine();
				System.out.println("ENTER ADDRESS");
				String address = scanner.nextLine();
				
				contactService.addContact(contactID, firstName, lastName, phoneNumber, address);
				System.out.println("NEW CONTACT ADDED!!!");
			}
			else if(menuOption == '2') {//READING AN EXISTING CONTACT
				System.out.println("ENTER CONTACT ID");
				String contactID = scanner.nextLine();
				contactService.readContact(contactID); //created a readContact function in ContactService.java to display contact information
			}
			else if(menuOption == '3') {//UPDATING AN EXISTING CONTACT
				System.out.println("ENTER CONTACT ID");
				String contactID = scanner.nextLine();
				contactService.updateContact(contactID);
			}
			else if(menuOption == '4') {//DELETING A CONTACT
				System.out.println("ENTER CONTACT ID");
				String contactID = scanner.nextLine();
				contactService.deleteContact(contactID);
			}
			else {
				if(menuOption == 'q' || menuOption == 'Q') {
					//program exits the contact function, goes back to the main function.
					System.out.println("EXITING CONTACT, BACK TO MAIN MENU!!!");
				}
				else {
					//The user's input is neither 1, 2, 3, 4, nor q.
					//Therefore the prompt asks the user for another input
					System.out.println("INVALID INPUT, TRY AGAIN!!!");
				}
			}
		}
		
	}
	
	public static void task() {
		Scanner scanner = new Scanner(System.in);//scanner set up
		TaskService taskService = new TaskService();//class variable for TaskService.java
		
		char menuOption = ' ';
		
		while(menuOption != 'q') {
			//TASK MENU
			System.out.println("SELECT OPTION FOR TASK");
			System.out.println("(1) CREATE");
			System.out.println("(2) READ");
			System.out.println("(3) UPDATE");
			System.out.println("(4) DELETE");
			System.out.println("(q) RETURN TO MAIN MENU");
			menuOption = scanner.next().charAt(0);

			if(menuOption == '1') {//Creating a new task
				System.out.println("ENTER TASK ID");
				String taskID = scanner.nextLine();
				System.out.println("ENTER TASK NAME");
				String taskName = scanner.nextLine();
				System.out.println("ENTER TASK DESCRIPTION");
				String taskDescription = scanner.nextLine();
				
				taskService.addTasks(taskID, taskName, taskDescription);
				System.out.println("NEW TASK ADDED!!!");
			}
			else if(menuOption == '2') {//Reads the task based on taskID
				System.out.println("ENTER TASK ID");
				String taskID = scanner.nextLine();
				taskService.readTasks(taskID);
			}
			else if(menuOption == '3') {//Updates task based on taskID
				System.out.println("ENTER TASK ID");
				String taskID = scanner.nextLine();
				taskService.updateTasks(taskID);
			}
			else if(menuOption == '4') {//Deletes task based on taskID
				System.out.println("ENTER TASK ID");
				String taskID = scanner.nextLine();
				taskService.deleteTasks(taskID);
			}
			else {
				if(menuOption == 'q' || menuOption == 'Q') {
					//program exits the task function, goes back to the main function.
					System.out.println("EXITING TASK, BACK TO MAIN MENU!!!");
				}
				else {
					//The user's input is neither 1, 2, 3, 4, nor q.
					//Therefore the prompt asks the user for another input
					System.out.println("INVALID INPUT, TRY AGAIN!!!");
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in); //initializing scanner
		char menuOption = ' '; //initializing menuOption for while loop
		
		//Menu loop
		while(menuOption != 'q') {
			menuOption = ' ';
			//Displays list of options for the user to choose from
			System.out.println("WHICH THREE CATEGORIES YOU WISH TO ACCESS:");
			System.out.println("(1) APPOINTMENT");
			System.out.println("(2) CONTACT");
			System.out.println("(3) TASK");
			System.out.println("(q) QUIT");
			menuOption = scanner.nextLine().charAt(0);
			
			if(menuOption == '1') {
				appointment();
			}
			else if(menuOption == '2') {
				contact();
			}
			else if(menuOption == '3') {
				task();
			}
			else {
				if(menuOption == 'q' || menuOption == 'Q') {
					//program terminated with a farewell message
					System.out.println("HAVE A GOOD DAY!!!");
				}
				else {
					//The user's input is neither 1, 2, 3, nor q.
					//Therefore the prompt asks the user for another input
					System.out.println("INVALID INPUT, TRY AGAIN!!!");
				}
			}
		}	
		scanner.close();
	}
}
