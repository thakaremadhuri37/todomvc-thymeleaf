package com.wimdeblauwe.examples.todomvcthymeleaf.todoitem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
 
}
