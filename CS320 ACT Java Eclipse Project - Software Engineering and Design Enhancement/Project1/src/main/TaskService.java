package main;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskService {
	private ArrayList<Task> taskList = new ArrayList<Task>();
	
	public boolean addTasks(String ID, String name, String description) {
		//checks for existing taskID
		for(Task taskID : taskList) {
			if(taskID.getTaskID().equals(ID)) {
				throw new IllegalArgumentException("Task ID must be unique.");
			}
		}
		//If for-loop doesn't find existing ID, then it's unique
		//and will be added to the task list.
		Task newTask = new Task(ID, name, description);
		taskList.add(newTask);
		//return true meaning the addition of tasks was a success
		return true;
	
	}
	public int searchTaskIndex(String ID){
		//This function was created to search and return the index ID 
		//for the deleteTasks(String ID) function to run properly
		for(int index = 0; index < taskList.size(); index++) {
			if(ID.equals(taskList.get(index).getTaskID())) {
				return index;
			}
		}
		throw new IllegalArgumentException("Task Index not found");
	}
	
	public boolean deleteTasks(String ID) {
		//Searching through the taskList for the matching ID
		for (Task task : taskList) {
			if(task.getTaskID().equals(ID)) {
				//The task is removed.
				taskList.remove(searchTaskIndex(ID));
				return true;
			}
		}
		//Task ID wasn't found in the for-loop
		throw new IllegalArgumentException("Task ID not found.");
	}
	
	public boolean updateTasks(String ID) {//Originally (String ID, String name, String description) but shortened to (String ID) just to get only the ID for the update
		Scanner scanner = new Scanner(System.in);
		
		//Iterates through task list via for loop
		for(Task task : taskList){
			//If the task ID is found, then the name and description will update
			if(task.getTaskID().equals(ID)) {
				
				//The two if statements below makes sure that the updates the user
				//makes follows the rules and abides by the string length limit.
				
				//Updating task name
				System.out.println("UPDATE TASK NAME");
				String taskName = scanner.nextLine();
				if(taskName == null || taskName.length() > 20) {
					scanner.close();
					throw new IllegalArgumentException("Task name cannot be greater than 20 characters or empty");
				}
				task.setTaskName(taskName);
				
				//Update Task Description
				System.out.println("UPDATE TASK DESCRIPTION");
				String taskDescription = scanner.nextLine();
				if(taskDescription == null || taskDescription.length() > 50) {
					scanner.close();
					throw new IllegalArgumentException("Task description cannot be greater than 50 characters or empty");
				}
				task.setTaskDescription(taskDescription);
				
				System.out.println("TASK "+task.getTaskID()+" SUCCESSFULLY UPDATED!");
				scanner.close();
				return true;
			}
		}
		scanner.close();
		//The for loop couldn't find said ID
		throw new IllegalArgumentException("Task ID not found.");
	}
	
	//Created a readTasks function to read all the tasks based on task ID.
	public boolean readTasks(String TaskID) {
		//Iterating through the taskList
		for(Task task : taskList) {
			if(task.getTaskID().equals(TaskID)) {//The TaskID finds a match in the list
				//Displays the information for the task.
				System.out.println("TASK INFORMATION FOR "+ TaskID);
				System.out.println("TASK NAME: "+ task.getTaskName());
				System.out.println("TASK DESCRIPTION: "+task.getTaskDescription());
				return true;
			}
		}
		//Task doens't exist.
		throw new IllegalArgumentException("Task ID not found.");
	}
}
