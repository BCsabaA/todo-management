package com.barab.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barab.todo.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{
}
