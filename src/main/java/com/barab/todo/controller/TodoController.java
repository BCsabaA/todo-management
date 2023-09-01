package com.barab.todo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barab.todo.dto.TodoDto;
import com.barab.todo.service.TodoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {

	private TodoService todoService;
	
	//build Add Todo Rest API
	@PostMapping
	public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
		TodoDto savedTodo = todoService.addTodo(todoDto);
		return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
	}
	
	//build Get Todo Rest API
	@GetMapping("{id}")
	public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId) {
		TodoDto todoDto = todoService.getTodo(todoId);
		return new ResponseEntity<TodoDto>(todoDto, HttpStatus.OK);
	}
	
	//build Get All Todos Rest API
	@GetMapping
	public ResponseEntity<List<TodoDto>> getAllTodos() {
		List<TodoDto> todos = todoService.getAllTodos();
		//return new ResponseEntity<List<TodoDto>>(todos,HttpStatus.OK);
		return ResponseEntity.ok(todos);
	}
	
	//build Update Todo Rest API
	@PutMapping("{id}")
	public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable("id") Long id) {
		TodoDto updatedTododto = todoService.updateTodo(todoDto, id);
		return ResponseEntity.ok(updatedTododto);
	}

	//build Delete Todo Rest API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId) {
		todoService.deleteTodo(todoId);
		return ResponseEntity.ok("Todo deleted succesfully!");
	}
	
	//build Complete Todo Rest API
	//@PatchMapping - partially update, @PutMapping - full update
	@PatchMapping("{id}/complete")
	public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long id) {
		TodoDto completedTodoDto = todoService.completeTodo(id);
		return ResponseEntity.ok(completedTodoDto);
	}
	
	@PatchMapping("{id}/incomplete")
	public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long id) {
		TodoDto inCompletedTodoDto = todoService.inCompleteTodo(id);
		return ResponseEntity.ok(inCompletedTodoDto);
	}

}
