package com.example.todo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.todo.domain.ToDo;

public interface ToDoRepository extends CrudRepository<ToDo, String> {

}
