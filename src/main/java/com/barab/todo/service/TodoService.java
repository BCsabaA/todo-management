package com.barab.todo.service;

import java.util.List;

import com.barab.todo.dto.TodoDto;

public interface TodoService {
	TodoDto addTodo(TodoDto todoDto);
	TodoDto getTodo(Long id);
	List<TodoDto> getAllTodos();
}
