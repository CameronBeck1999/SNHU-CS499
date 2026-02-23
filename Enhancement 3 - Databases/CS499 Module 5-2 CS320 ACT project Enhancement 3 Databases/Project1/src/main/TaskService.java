package main;

import java.util.ArrayList;
import java.util.Scanner;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class TaskService {
	//The following instance variables are set up for MongoDB
	//replacing the ArrayList approach of storing records
			
	//The connection URI set up to connect to MongoDB
	private static final String URI = "mongodb://localhost:27017/";
		
	//ACT database and Task collection are set up
	MongoClient mongoClient = MongoClients.create(URI);
	MongoDatabase database = mongoClient.getDatabase("ACT");
	MongoCollection<Document> task = database.getCollection("Task");
	
	public boolean addTasks(String ID, String name, String description) {
		//Checks if taskID is unique
		Document unique = task.find(Filters.eq("_taskID")).first();
		if(unique != null) {//task ID is unique and thus added to the task Collection in the database
			task newtask = new task(ID, name, description);//verifies all inputs are legal
			//After input verification, the task inputs are then stored and added to the database
			Document taskDoc = new Document("_taskID: ",ID);
			taskDoc.append("_taskName: ", name);
			taskDoc.append("_taskDescription: ", description);
			task.insertOne(taskDoc);
			return true;
		}
		else {//task ID already exists in the database
			throw new IllegalArgumentException("task ID must be unique");
		}
	}
	
	public boolean deleteTasks(String ID) {
		//The try method deletes the one task we are looking for via taskID
		try(MongoCursor<Document> cursor = task.deleteOne(Filters.eq("_taskID", taskID))){
			System.out.println("Task "+ID+" has been deleted!");//It tells the user the task is deleted from the database
		}
		catch(Exception e) {//The taskID that was entered doesn't exist so therefore it can't be deleted
			throw new IllegalArgumentException("task ID not found");
		}
	}
	
	public boolean updateTasks(String taskID) {
		Scanner scanner = new Scanner(System.in);
		
		//The try method searches the database for a matching taskID to update all the information within said taskID
		try(Bson updateFilter = Filters.eq("_taskID: ", taskID)){
			//The two if statements below makes sure that the updates the user
			//makes follows the rules and abides by the string length limit.
				
			//Updating task name
			System.out.println("UPDATE TASK NAME: (Up to 20 Characters)");
			String newTaskName = scanner.nextLine();
			if(newTaskName == null || newTaskName.length() > 20) {
				scanner.close();
				throw new IllegalArgumentException("Task name cannot be greater than 20 characters or empty");
			}
			task.updateOne(Filters.eq(updateFilter),Updates.set("_taskName: ", newTaskName));	
			
			//Updating Task Description
			System.out.println("UPDATE TASK DESCRIPTION: (Up to 50 Characters)");
			String newTaskDescription = scanner.nextLine();
			if(newTaskDescription == null || newTaskDescription.length() > 50) {
				scanner.close();
				throw new IllegalArgumentException("Task description cannot be greater than 50 characters or empty");
			}
			task.updateOne(Filters.eq(updateFilter),Updates.set("_taskDescription: ", newTaskDescription));	
				
			System.out.println("TASK "+taskID+" SUCCESSFULLY UPDATED!"); //The user is informed the task is successfully updated
			scanner.close();
			return true;
		}
		catch(Exception e) {//The taskID cannot be found
			scanner.close();
			throw new IllegalArgumentException("Task ID not found.");
		}
	}
	
	//Created a readTasks function to read all the tasks based on task ID.
	public boolean readTasks(String taskID) {
		//The try method searches the database via taskID
		try(MongoCursor<Document> cursor = task.find(Filters.eq("_taskID", taskID)).iterator()){
			while(cursor.hasNext()) {
			System.out.print(cursor.next().toJson());//Displays the document
		}
		return true;
		}
		catch(Exception e) {//The task can't be found
			e.printStackTrace();
		}
	}
}
