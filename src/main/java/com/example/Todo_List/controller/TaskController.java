package com.example.Todo_List.controller;

import com.example.Todo_List.dto.TaskDTO;
import com.example.Todo_List.entity.Task;
import com.example.Todo_List.mappers.TodoMapper;
import com.example.Todo_List.services.TaskService;
import java.util.List;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/task")
@XSlf4j
public class TaskController {

	private final TaskService taskService;

	private final TodoMapper todoMapper;

	public TaskController(TaskService taskService, TodoMapper todoMapper) {
		this.taskService = taskService;
		this.todoMapper = todoMapper;
	}

	@PostMapping(value = "/createTask")
	ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO){
		log.entry(taskDTO);
		try{
			Task task  = taskService.createTask(taskDTO);
			TaskDTO responseDTO = todoMapper.tasktoTaskDTO(task);
			return log.exit(new ResponseEntity<>(responseDTO, HttpStatus.CREATED));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}
	@GetMapping(value = "/getTasks")
	ResponseEntity<List<TaskDTO>> getAllTaskByListId(@RequestParam Long listId) {
		log.entry(listId);
		try{
			List<Task> taskList  = taskService.getAllTaskInList(listId);
			List<TaskDTO> taskDTOList = todoMapper.tasksToTaskDTOs(taskList);
			return log.exit(new ResponseEntity<>(taskDTOList, HttpStatus.OK));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@DeleteMapping(value = "/deleteTask/{id}")
	ResponseEntity<TaskDTO> deleteTask(@PathVariable Long id) {
		log.entry(id);
		try{
			Task task  = taskService.deleteTask(id);
			TaskDTO responseDTO = todoMapper.tasktoTaskDTO(task);
			return log.exit(new ResponseEntity<>(responseDTO, HttpStatus.OK));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@PutMapping(value = "/updateTask")
	ResponseEntity<TaskDTO> updateTask(@RequestBody TaskDTO taskDTO) {
		log.entry(taskDTO);
		try{
      Task task = taskService.updateTask(taskDTO);
			TaskDTO responseDTO = todoMapper.tasktoTaskDTO(task);
			return log.exit(new ResponseEntity<>(responseDTO, HttpStatus.OK));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@PutMapping(value = "/completeTask/{id}")
	ResponseEntity<Void> completeTask(@PathVariable Long id) {
		log.entry(id);
		try{
			taskService.completeTask(id);
			return log.exit(new ResponseEntity<>(HttpStatus.OK));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@PutMapping(value = "/changePriority/{id}")
	ResponseEntity<TaskDTO> changePriorityOfTask(@PathVariable(value = "id") Long id, @RequestParam(value = "priority", required = true) Integer priority) {
		log.entry(id, priority);
		try{
			Task task = taskService.changePriority(id, priority);
			TaskDTO taskDTO = todoMapper.tasktoTaskDTO(task);
			return log.exit(new ResponseEntity<>(taskDTO, HttpStatus.OK));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

}
