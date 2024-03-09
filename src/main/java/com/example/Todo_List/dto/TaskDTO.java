package com.example.Todo_List.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskDTO {

	private String taskId;

	private String title;

	private String description;

	private Integer priority = 0;

	private Boolean isComplete = false;

	private Long listId = null;
}
