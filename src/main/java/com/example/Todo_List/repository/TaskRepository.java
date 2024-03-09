package com.example.Todo_List.repository;

import com.example.Todo_List.entity.Task;
import com.example.Todo_List.entity.TodoList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
//	@Query("select t from Task t where t.list_id = ?1")
	List<Task> findByTodoList(TodoList todoList);
}
