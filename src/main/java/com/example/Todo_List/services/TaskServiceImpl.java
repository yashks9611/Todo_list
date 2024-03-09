package com.example.Todo_List.services;

import com.example.Todo_List.dto.TaskDTO;
import com.example.Todo_List.entity.Task;
import com.example.Todo_List.entity.TodoList;
import com.example.Todo_List.mappers.TodoMapper;
import com.example.Todo_List.repository.TaskRepository;
import com.example.Todo_List.repository.TodoListRepository;
import com.example.Todo_List.util.PriorityLevel;
import com.example.Todo_List.util.TodoUtils;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.stereotype.Service;

@Service
@XSlf4j
public class TaskServiceImpl implements TaskService {

	private final TaskRepository taskRepository;

	private final TodoListRepository todoRepository;

	private final TodoMapper todoMapper;

	public TaskServiceImpl(TaskRepository taskRepository, TodoListRepository todoRepository,
			TodoMapper todoMapper) {
		this.taskRepository = taskRepository;
		this.todoRepository = todoRepository;
		this.todoMapper = todoMapper;
	}

	private static final String ID_NOT_FOUND =  "List with id %s not found";

	@Override
	public Task createTask(TaskDTO taskDTO) {
		log.entry(taskDTO);
		try {
			TodoList todoList = todoRepository.findById(taskDTO.getListId()).orElseThrow(() -> new Exception(String.format(
					ID_NOT_FOUND,
					taskDTO.getListId())));

			Task newTask = todoMapper.taskDTOtoTask(taskDTO);
			newTask.setTodoList(todoList);
			return log.exit(taskRepository.save(newTask));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Task> getAllTaskInList(Long listId) {
		log.entry(listId);
		try {
			TodoList todoList = todoRepository.findById(listId).orElseThrow(() -> new Exception(String.format(
					ID_NOT_FOUND, listId)));
			return log.exit(taskRepository.findByTodoList(todoList));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public Task deleteTask(Long taskId) {
		log.entry(taskId);
		try {
			Task task = taskRepository.findById(taskId).orElseThrow(() -> new Exception(String.format(
					ID_NOT_FOUND, taskId)));
			taskRepository.deleteById(taskId);
			return log.exit(task);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		}

	}

	@Override
	public Task updateTask(TaskDTO taskDTO) {
		log.entry(taskDTO);
		try {
			if (taskRepository.existsById(Long.valueOf(taskDTO.getTaskId()))) {
				Task taskToUpdate = todoMapper.taskDTOtoTask(taskDTO);
        taskToUpdate.setTodoList(taskRepository.getReferenceById(taskToUpdate.getTaskId()).getTodoList());
				return log.exit(taskRepository.save(taskToUpdate));
			} else {
				throw new Exception(String.format(ID_NOT_FOUND, taskDTO.getTaskId()));
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public void completeTask(Long taskId) {
		log.entry(taskId);
		try {
			Task task = taskRepository.findById(taskId).orElseThrow(() -> new Exception(String.format(
					ID_NOT_FOUND, taskId)));
			task.setIsComplete(true);
			taskRepository.save(task);
			log.exit();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	public Task changePriority(Long taskId, Integer priority) {
		log.entry(taskId, priority);
		try {
			Map<Integer, PriorityLevel> priorityLevelHashMap = TodoUtils.getPriorityMap();
			Task task = taskRepository.findById(taskId).orElseThrow(() -> new Exception(String.format(
					ID_NOT_FOUND, taskId)));
			task.setPriority(priorityLevelHashMap.get(priority));
			return log.exit(taskRepository.save(task));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
