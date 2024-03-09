package com.example.Todo_List.services;

import com.example.Todo_List.dto.ToDoListDTO;
import com.example.Todo_List.entity.TodoList;
import java.util.List;

public interface TodoListService {
	TodoList createList(ToDoListDTO toDoListDTO);
	List<TodoList> getLists();

	TodoList updateList(ToDoListDTO toDoListDTO);

	TodoList deleteList(Long listId);
}
