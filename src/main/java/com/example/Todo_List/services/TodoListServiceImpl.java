package com.example.Todo_List.services;

import com.example.Todo_List.dto.ToDoListDTO;
import com.example.Todo_List.entity.TodoList;
import com.example.Todo_List.mappers.TodoMapper;
import com.example.Todo_List.repository.TodoListRepository;
import java.util.List;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.stereotype.Service;

@Service
@XSlf4j
public class TodoListServiceImpl implements TodoListService{

	TodoListRepository todoListRepository;

	TodoMapper todoMapper;

	public TodoListServiceImpl(TodoListRepository todoListRepository, TodoMapper todoMapper) {
		this.todoListRepository = todoListRepository;
		this.todoMapper = todoMapper;
	}

	@Override
	public TodoList createList(ToDoListDTO toDoListDTO) {
		log.entry(toDoListDTO);
		try{
			TodoList newTodoList = todoMapper.toDoListDTOToTodoList(toDoListDTO);
    	return log.exit(todoListRepository.save(newTodoList));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	@Override
	public List<TodoList> getLists() {
		log.entry();
		try {
			List<TodoList> todoLists = todoListRepository.findAll();
			if (!todoLists.isEmpty()) {
				return log.exit(todoLists);
			} else {
				throw new Exception("No Lists found");
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public TodoList updateList(ToDoListDTO toDoListDTO) {
		log.entry(toDoListDTO);
		try{
			if (todoListRepository.existsById(Long.valueOf(toDoListDTO.getListId()))) {
				TodoList newTodoList = todoMapper.toDoListDTOToTodoList(toDoListDTO);
				return log.exit(todoListRepository.save(newTodoList));
			} else {
				throw new Exception(String.format("List with id %s is not present.", toDoListDTO.getListId()));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
				throw new RuntimeException(e);
		}
	}

	@Override
	public TodoList deleteList(Long listId) {
		log.entry(listId);
		try{
			TodoList todoList = todoListRepository.findById(listId).orElseThrow(() -> new Exception(String.format("List with id %s is not present.", listId)));
			todoListRepository.deleteById(todoList.getId());
			return log.exit(todoList);
		} catch(Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
