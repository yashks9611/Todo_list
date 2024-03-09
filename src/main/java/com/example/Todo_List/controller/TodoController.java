package com.example.Todo_List.controller;

import com.example.Todo_List.dto.ToDoListDTO;
import com.example.Todo_List.entity.TodoList;
import com.example.Todo_List.mappers.TodoMapper;
import com.example.Todo_List.services.TodoListService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "todoController")
@RequestMapping(value = "/todo")
@XSlf4j
public class TodoController {

	private TodoListService todoListService;

	private TodoMapper todoMapper;

	public TodoController(TodoListService todoListService, TodoMapper todoMapper) {
		this.todoMapper = todoMapper;
		this.todoListService = todoListService;
	}

	@PostMapping(value = "/createList")
	ResponseEntity<ToDoListDTO> createList(@RequestBody ToDoListDTO toDoListDTO){
		log.entry(toDoListDTO);
		try{
			TodoList todoList = todoListService.createList(toDoListDTO);
			ToDoListDTO newToDoListDTO = todoMapper.todoListToToDoListDTO(todoList);
			return log.exit(new ResponseEntity<>(newToDoListDTO, HttpStatus.CREATED));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@GetMapping(value = "/getLists")
	ResponseEntity<List<ToDoListDTO>> getLists() {
		log.entry();
		try{
			List<TodoList> todoLists = todoListService.getLists();
			List<ToDoListDTO> toDoListDTOList = todoMapper.todoListsToTodoListDTOs(todoLists);
			return log.exit(new ResponseEntity<>(toDoListDTOList, HttpStatus.OK));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@DeleteMapping(value = "/deleteList/{id}")
	ResponseEntity<ToDoListDTO> deleteList(@PathVariable Long id) {
		log.entry(id);
		try{
			TodoList deletedList = todoListService.deleteList(id);
			ToDoListDTO toDoListDTO = todoMapper.todoListToToDoListDTO(deletedList);
			return log.exit(new ResponseEntity<>(toDoListDTO, HttpStatus.OK));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@PutMapping(value = "/updateListDetails")
	ResponseEntity<ToDoListDTO> updateList(@RequestBody ToDoListDTO toDoListDTO) {
		log.entry(toDoListDTO);
		try{
			TodoList updatedList = todoListService.updateList(toDoListDTO);
			ToDoListDTO updatedListDTO = todoMapper.todoListToToDoListDTO(updatedList);
			return log.exit(new ResponseEntity<>(updatedListDTO, HttpStatus.OK));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

}
