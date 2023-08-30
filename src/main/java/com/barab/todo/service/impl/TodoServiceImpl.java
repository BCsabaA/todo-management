package com.barab.todo.service.impl;

import org.springframework.stereotype.Service;

import com.barab.todo.dto.TodoDto;
import com.barab.todo.entity.Todo;
import com.barab.todo.repository.TodoRepository;
import com.barab.todo.service.TodoService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

	private TodoRepository todoRepository;
	
	@Override
	public TodoDto addTodo(TodoDto todoDto) {
		
		//convert TodoDto into Todo Jpa entity
		Todo todo = new Todo();
		todo.setTitle(todoDto.getTitle());
		todo.setDescription(todoDto.getDescription());
		todo.setCompleted(todoDto.isCompleted());
		todo.setDeleted(todoDto.isDeleted());
		
		//Todo Jpa entity
		
		return null;
	}

}