
package main;
import java.util.Scanner;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.Runtime;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

public class Project1 {
	
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
				System.out.println("ENTER NEW APPOINTMENT ID (Up to 10 Characters)");
				String appointmentID = scanner.nextLine();
				System.out.println("ENTER APPOINTMENT DATE (MM-dd-yyyy)");
				String appointmentDate = scanner.nextLine();
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
				
				try {//Try is used to test and see if the user entered the date in the correct format as the instructions asked.
					Date date = dateFormat.parse(appointmentDate);
					System.out.println("ENTER APPOINTMENT DESCRIPTION (Up to 50 Characters)");
					String appointmentDescription = scanner.nextLine();
					appointmentService.addAppointment(appointmentID, date, appointmentDescription);
					System.out.println("APPOINTMENT FOR "+date+" HAS BEEN ADDED!");
				}catch (ParseException e) {//catch means that the user entered the date in the wrong format and a message informs them so.
					System.out.println("INVALID DATE FORMAT. FORMAT MUST BE MM-dd-yyyy");
				}
				
			}
			else if(menuOption == '2') {//Reading appointment information based on appointmentID
				System.out.println("ENTER APPOINTMENT ID (Up to 10 Characters)");
				String appointmentID = scanner.nextLine();
				appointmentService.readAppointment(appointmentID);//Created a new readAppointment function for AppointmentService.java class
			}
			else if(menuOption == '3') {//Deleting appointment information based on appointmentID
				System.out.println("ENTER APPOINTMENT ID (Up to 10 Characters)");
				String appointmentID = scanner.nextLine();
				appointmentService.deleteAppointment(appointmentID);
			}
			else {
				if(menuOption == 'q' || menuOption == 'Q') {
					//program exits the Appointment function, goes back to the main function.
					System.out.println("EXITING APPOINTMENT, BACK TO MAIN MENU!!!\\n");
				}
				else {
					//The user's input is neither 1, 2, 3, nor q.
					//Therefore the prompt asks the user for another input
					System.out.println("INVALID INPUT, TRY AGAIN!!!\\n");
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
				System.out.println("ENTER NEW CONTACT ID (Up to 10 Characters)");
				String contactID = scanner.nextLine();
				System.out.println("ENTER FIRST NAME (Up to 10 Characters)");
				String firstName = scanner.nextLine();
				System.out.println("ENTER LAST NAME (Up to 10 Characters)");
				String lastName = scanner.nextLine();
				System.out.println("ENTER PHONE NUMBER (xxx-xxx-xxxx)");
				String phoneNumber = scanner.nextLine();
				System.out.println("ENTER ADDRESS (Up to 30 Characters)");
				String address = scanner.nextLine();
				
				contactService.addContact(contactID, firstName, lastName, phoneNumber, address);
				System.out.println("NEW CONTACT ADDED!!!");
			}
			else if(menuOption == '2') {//READING AN EXISTING CONTACT
				System.out.println("ENTER CONTACT ID (Up to 10 characters)");
				String contactID = scanner.nextLine();
				contactService.readContact(contactID); //created a readContact function in ContactService.java to display contact information
			}
			else if(menuOption == '3') {//UPDATING AN EXISTING CONTACT
				System.out.println("ENTER CONTACT ID (Up to 10 characters)");
				String contactID = scanner.nextLine();
				contactService.updateContact(contactID);
			}
			else if(menuOption == '4') {//DELETING A CONTACT
				System.out.println("ENTER CONTACT ID (Up to 10 characters)");
				String contactID = scanner.nextLine();
				contactService.deleteContact(contactID);
			}
			else {
				if(menuOption == 'q' || menuOption == 'Q') {
					//program exits the contact function, goes back to the main function.
					System.out.println("EXITING CONTACT, BACK TO MAIN MENU!!!\\n");
				}
				else {
					//The user's input is neither 1, 2, 3, 4, nor q.
					//Therefore the prompt asks the user for another input
					System.out.println("INVALID INPUT, TRY AGAIN!!!\\n");
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
				System.out.println("ENTER TASK ID (Up to 10 characters)");
				String taskID = scanner.nextLine();
				System.out.println("ENTER TASK NAME (Up to 20 Characters)");
				String taskName = scanner.nextLine();
				System.out.println("ENTER TASK DESCRIPTION (Up to 50 Characters)");
				String taskDescription = scanner.nextLine();
				
				taskService.addTasks(taskID, taskName, taskDescription);
				System.out.println("NEW TASK ADDED!!!");
			}
			else if(menuOption == '2') {//Reads the task based on taskID
				System.out.println("ENTER TASK ID (Up to 10 characters)");
				String taskID = scanner.nextLine();
				taskService.readTasks(taskID);
			}
			else if(menuOption == '3') {//Updates task based on taskID
				System.out.println("ENTER TASK ID (Up to 10 characters)");
				String taskID = scanner.nextLine();
				taskService.updateTasks(taskID);
			}
			else if(menuOption == '4') {//Deletes task based on taskID
				System.out.println("ENTER TASK ID (Up to 10 characters)");
				String taskID = scanner.nextLine();
				taskService.deleteTasks(taskID);
			}
			else {
				if(menuOption == 'q' || menuOption == 'Q') {
					//program exits the task function, goes back to the main function.
					System.out.println("EXITING TASK, BACK TO MAIN MENU!!!\\n");
				}
				else {
					//The user's input is neither 1, 2, 3, 4, nor q.
					//Therefore the prompt asks the user for another input
					System.out.println("INVALID INPUT, TRY AGAIN!!!\\n");
				}
			}
		}
		
	}
	
	public static void elapsedTime(long sec) {//This function calculates how long it took for the program to run before termination
		//Initialized hours (and it's remainder), minutes, and seconds
		//to convert the runtime into HH:MM:SS format
		long hours = 0;
		long hoursRemainder = 0;
		long minutes = 0;
		long seconds = 0;
		
		if(sec >= 3600) {//Seconds is an hour or over
			hours = sec / 3600;
			hoursRemainder = sec % 3600; //converted remainder to seconds from modulo division
			
			if(hoursRemainder >= 60) {//Between a minute to an hour
				minutes = hoursRemainder / 60;
				seconds = hoursRemainder % 60;
			}
			else {//Less than a minute
				seconds = hoursRemainder;
			}
		}
		else if(sec < 3600 && sec >= 60) {//Total seconds is between 1 minute to an hour
			hours = 0;
			minutes = sec / 60;
			seconds = sec % 60;
		}
		else if(sec < 60) {//Total seconds is under a minute
			hours = 0;
			minutes = 0;
			seconds = sec;
		}
		
		//Next step is to convert the numbers into strings
		String strHours;
		String strMinutes;
		String strSeconds;
		
		//The three if-else statements determine if the numbers less than 10.
		//If so, we concatenate the '0' before the number for the string variables. 
		//Otherwise, no additional concatenation needed.
		if(hours < 10) {
			strHours = "0" + Long.toString(hours);
		}
		else {
			strHours = Long.toString(hours);
		}
		
		if(minutes < 10) {
			strMinutes = "0" + Long.toString(minutes);
		}
		else {
			strMinutes = Long.toString(minutes);
		}
		
		if(seconds < 10) {
			strSeconds = "0" + Long.toString(seconds);
		}
		else {
			strSeconds = Long.toString(seconds);
		}
		
		//Final step is concatenating the numbers in between ':' towards each other
		//and print out the programs's total elapsed time.
		String totalTime = strHours+":"+strMinutes+":"+strSeconds;
		System.out.println("Program's elapsed time: "+totalTime);
		System.out.println("Program's elapsed time: "+sec+" second(s)"); //As a bonus to see the total number of seconds without conversion
	}
	public static void main(String[] args) {
		//Creating MongoClient
		
		MongoClient mongoClient = new MongoClient("localhost", 27107);
		System.out.println("ACT MongoDB CONNECTED SUCESSFULLY!!\n");
		
		
		Scanner scanner = new Scanner(System.in); //initializing scanner
		char menuOption = ' '; //initializing menuOption for while loop
		long start = System.currentTimeMillis();//initializing the program's stop watch in milliseconds
		
		
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
					System.out.println("HAVE A GOOD DAY!!!\n");
				}
				else {
					//The user's input is neither 1, 2, 3, nor q.
					//Therefore the prompt asks the user for another input
					System.out.println("INVALID INPUT, TRY AGAIN!!!\n");
				}
			}
		}
		
		long end = System.currentTimeMillis();//Initializing the end time in milliseconds.
		long timeDifference = (end - start)/1000;//converting the total time from milliseconds to seconds.
		elapsedTime(timeDifference);//Converts the timeDifference variable into HH:MM:SS and prints the programs duration time 
		
		
		scanner.close();
	}
}