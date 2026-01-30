package JUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import main.TaskService;
class TaskServiceTest {

	@Test
	public void testAddTaskService() {
		TaskService service = new TaskService();
		assertTrue(service.addTasks("TaskID", "TaskName", "TaskDescription"));

		//All null values
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.addTasks(null, null, null);
		});
		//TaskID is null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.addTasks(null, "TaskName", "TaskDescription");
		});
		//Task Name is null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.addTasks("TaskID", null, "TaskDescription");
		});
		//Task Description is null
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.addTasks("TaskID", "TaskName", null);
		});
		//Task ID is too long
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.addTasks("TaskIDWAYYYYTOOOOLOOOOONG", "TaskName", "TaskDescription");
		});
		//Task name is too long
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.addTasks("TaskID", "TaskNameTOOOOOOOLOOOOOOONG", "TaskDescription");
		});
		//Task description is too long
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.addTasks("TaskID", "TaskName", "TaskDescriptionTOOOOOOOOOOLOOOOOOOOOOOOONG");
		});
	
}
	
	@Test
	public void testDeleteTask() {
		TaskService service = new TaskService();
		service.addTasks("TaskID", "TaskName", "TaskDescription");
		assertTrue(service.deleteTasks("TaskID"));
		
		//TaskID can't be found;
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.deleteTasks("TaskID-2.0");
		});
	}
	
	@Test
	public void updateTask() {
		TaskService service = new TaskService();
		service.addTasks("TaskID", "TaskName", "TaskDescription");
		service.addTasks("TaskID2", "TaskName2", "TaskDescription2");
		service.addTasks("TaskID3", "TaskName3", "TaskDescription3");
		service.addTasks("TaskID4", "TaskName4", "TaskDescription4");
		assertTrue(service.updateTasks("TaskID", "TaskNameUpdated", "TaskDescriptionUpdated"));
		
		service.updateTasks("TaskID2", "TskName2Updated", "TaskDescription2Updated");
		//service.displayTasks();
		
		//Task ID can't be found;
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			service.deleteTasks("TaskID-2.0");
		});
	}
	
	
}
