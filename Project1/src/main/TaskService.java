package main;

import java.util.ArrayList;

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
	
	public boolean updateTasks(String ID, String name, String description) {
		//Iterates through task list via for loop
		for(Task task : taskList){
			//If the task ID is found, then the name and description will update
			if(task.getTaskID().equals(ID)) {
				task.setTaskName(name);
				task.setTaskDescription(description);
				return true;
			}
		}
		
		//The for loop couldn't find said ID
		throw new IllegalArgumentException("Task ID not found.");
	}
}
