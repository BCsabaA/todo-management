package com.barab.todo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.barab.todo.dto.TodoDto;
import com.barab.todo.entity.Todo;
import com.barab.todo.exception.ResourceNotFoundException;
import com.barab.todo.repository.TodoRepository;
import com.barab.todo.service.TodoService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

	private TodoRepository todoRepository;
	
	private ModelMapper modelMapper;
	
	@Override
	public TodoDto addTodo(TodoDto todoDto) {
		
		//convert TodoDto into Todo Jpa entity
		Todo todo = modelMapper.map(todoDto, Todo.class);
		
		//Todo Jpa entity
		Todo savedTodo = todoRepository.save(todo);
		
		//convert savedTodo JPA Entity into TodoDto object
		TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);
		
		return savedTodoDto;
	}

	@Override
	public TodoDto getTodo(Long id) {
		
		Todo todo = todoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Todo not found with id " + id));
		
		return modelMapper.map(todo, TodoDto.class);
	}

	@Override
	public List<TodoDto> getAllTodos() {
		List<Todo> todos = todoRepository.findAll();
		return todos.stream().map((todo) -> modelMapper.map(todo, TodoDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public TodoDto updateTodo(TodoDto todoDto, Long id) {
		Todo todo = todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo not found with id " + id));
		todo.setTitle(todoDto.getTitle());
		todo.setDescription(todoDto.getDescription());
		todo.setCompleted(todoDto.isCompleted());
		todo.setDeleted(todoDto.isDeleted());
		Todo updatedTodo = todoRepository.save(todo);
		return modelMapper.map(updatedTodo, TodoDto.class);
	}

	@Override
	public void deleteTodo(Long id) {
		Todo todo = todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo not found with id " + id));
		todo.setDeleted(true);
		Todo deletedTodo = todoRepository.save(todo);
	}

	@Override
	public TodoDto completeTodo(Long id) {
		Todo todo = todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo not found with id " + id));
		todo.setCompleted(Boolean.TRUE);
		Todo updatedTodo = todoRepository.save(todo);
		return modelMapper.map(updatedTodo, TodoDto.class);
	}

	@Override
	public TodoDto inCompleteTodo(Long id) {
		Todo todo = todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo not found with id " + id));
		todo.setCompleted(Boolean.FALSE);
		Todo updatedTodo = todoRepository.save(todo);
		return modelMapper.map(updatedTodo, TodoDto.class);
	}

}
