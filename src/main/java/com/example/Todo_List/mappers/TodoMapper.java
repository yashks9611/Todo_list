package com.example.Todo_List.mappers;

import com.example.Todo_List.dto.TaskDTO;
import com.example.Todo_List.dto.ToDoListDTO;
import com.example.Todo_List.entity.Task;
import com.example.Todo_List.entity.TodoList;
import com.example.Todo_List.util.PriorityLevel;
import com.example.Todo_List.util.TodoUtils;
import java.util.List;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class TodoMapper {

	@BeforeMapping
	void enrichEntityWithPriorityAndList(TaskDTO taskDTO, @MappingTarget Task task) {
		task.setPriority(TodoUtils.getPriorityMap().get(taskDTO.getPriority()));
	}

	@BeforeMapping
	void enrichTaskDTOWithPriorityAndListId(Task task, @MappingTarget TaskDTO taskDTO) {
		taskDTO.setListId(task.getTodoList().getId());
		if(task.getPriority().equals(PriorityLevel.LOW)) {
			taskDTO.setPriority(0);
		} else if(task.getPriority().equals(PriorityLevel.HIGH)) {
			taskDTO.setPriority(1);
		} else if(task.getPriority().equals(PriorityLevel.HIGHEST)) {
			taskDTO.setPriority(2);
		}
	}

	@Mapping(target = "priority", ignore = true)
	@Mapping(target = "todoList", ignore = true)
	public abstract Task taskDTOtoTask(TaskDTO taskDTO);

	@Mapping(target = "priority", ignore = true)
	@Mapping(target = "listId", ignore = true)
	public abstract TaskDTO tasktoTaskDTO(Task task);

	public abstract List<TaskDTO> tasksToTaskDTOs(List<Task> tasks);

	public abstract List<ToDoListDTO> todoListsToTodoListDTOs(List<TodoList> todoLists);

	@Mapping(source = "id", target = "listId")
	public abstract ToDoListDTO todoListToToDoListDTO(TodoList todoList);

	@Mapping(source = "listId", target = "id")
	public abstract TodoList toDoListDTOToTodoList(ToDoListDTO toDoListDTO);
}
