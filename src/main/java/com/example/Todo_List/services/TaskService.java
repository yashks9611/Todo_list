package com.example.Todo_List.services;

import com.example.Todo_List.dto.TaskDTO;
import com.example.Todo_List.entity.Task;
import java.util.List;

public interface TaskService {

	Task createTask(TaskDTO taskDTO);

	List<Task> getAllTaskInList(Long listId);

	Task deleteTask(Long taskId);

	Task updateTask(TaskDTO taskDTO);

	void completeTask(Long taskId);

	Task changePriority(Long taskId, Integer priority);
}
