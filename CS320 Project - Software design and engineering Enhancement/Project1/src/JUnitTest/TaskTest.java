package JUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;


import org.junit.jupiter.api.Test;

import main.Task;

class TaskTest {

	@Test
	public void testTask() {
		Task task = new Task("9232025", "Test Task", "Testing to see if it works");
		assertTrue(task.getTaskID().equals("9232025"));
		assertTrue(task.getTaskName().equals("Test Task"));
		assertTrue(task.getTaskDescription().equals("Testing to see if it works"));
	}
	
	@Test
	public void testTaskIDTooLong() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task("TaskIDHasMoreThanTenCharacters","TaskID","Testing TaskID to see if it's too long");
		});}
	
	@Test
	public void testTaskNameTooLong() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task("TaskID","TaskNameIsWAYYYYYYYYYYYYTooLong","Testing TaskName to see if it's too long");
	});}
	
	@Test
	public void testTaskDescriptionTooLong() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task("TaskID","TaskDescriptionTest","TaskDescriptionIsWAYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYTooLong");
	});}
	
	
	@Test
	public void testTaskIDIsNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task(null,"TaskIDNull","Testing TaskID to see if it's null");
	});}
	
	@Test
	public void testTaskNameIsNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task("TaskID",null,"Testing TaskName to see if it's null");
	});}
	
	@Test
	public void testTaskDescriptionIsNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task("TaskID","TaskDescNull",null);
	});}
	
	@Test
	public void testTaskAllNullValues() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task(null,null,null);
	});}
}
