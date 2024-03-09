package com.example.Todo_List.entity;

import static jakarta.persistence.ConstraintMode.CONSTRAINT;

import com.example.Todo_List.util.PriorityLevel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long taskId;

	@Column(name = "task_title")
	@NonNull
	private String title;

	@Column(name = "task_description")
	@NonNull
	private String description;

	@Column(name = "task_isComplete")
	private Boolean isComplete = false;

	@Column(name = "task_priority")
	@NonNull
	private PriorityLevel priority = PriorityLevel.LOW;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "list_id", foreignKey = @ForeignKey(value = CONSTRAINT, foreignKeyDefinition = "FOREIGN KEY (list_Id) REFERENCES list(id) ON DELETE CASCADE"), nullable = false)
	@NonNull
	private TodoList todoList;
}
