package main;

public class Task {
	private final String taskID;
	private String taskName;
	private String taskDescription;
	
	public Task(String id, String name, String description){
		if(id == null|| id.length() > 10) {
			throw new IllegalArgumentException("Task ID cannot be greater than 10 characters or empty");
		}
		if(name == null || name.length() > 20) {
			throw new IllegalArgumentException("Task name cannot be greater than 20 characters or empty");
		}
		if(description == null || description.length() > 50) {
			throw new IllegalArgumentException("Task description cannot be greater than 50 characters or empty");
		}
		
		this.taskID = id;
		this.taskName = name;
		this.taskDescription = description;
	}
	
	//setters
	public void setTaskName(String name) {
		if(name.length() > 20) {
			throw new IllegalArgumentException("Task name cannot be greater than 20 characters");
		}
		if(name.equals(null)) {
			throw new IllegalArgumentException("Task name cannot be empty");
		}
		this.taskName = name;
	}
	
	public void setTaskDescription(String description) {
		if(description.length() > 50) {
			throw new IllegalArgumentException("Task description cannot be greater than 50 characters");
		}
		if(description.equals(null)){
			throw new IllegalArgumentException("Task description cannot be empty");
		}
		this.taskDescription = description;
	}
	
	//getters
	public String getTaskID() {
		return taskID;
	}
	public String getTaskName() {
		return taskName;
	}
	
	public String getTaskDescription() {
		return taskDescription;
	}
}
