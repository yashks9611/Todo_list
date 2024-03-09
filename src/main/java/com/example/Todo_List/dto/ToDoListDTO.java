package com.example.Todo_List.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ToDoListDTO {

	private String listId;

	private String title;

	private String description;
}
