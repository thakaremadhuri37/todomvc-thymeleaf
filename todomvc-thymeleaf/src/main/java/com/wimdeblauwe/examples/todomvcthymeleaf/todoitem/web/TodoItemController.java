package com.wimdeblauwe.examples.todomvcthymeleaf.todoitem.web;

import com.wimdeblauwe.examples.todomvcthymeleaf.todoitem.TodoItem;
//import com.wimdeblauwe.examples.todomvcthymeleaf.todoitem.TodoItemNotFoundException;
import com.wimdeblauwe.examples.todomvcthymeleaf.todoitem.TodoItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class TodoItemController {

	private final TodoItemRepository repository;

	public TodoItemController(TodoItemRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public String index(Model model) {
		addAttributesForIndex(model);
		return "index";
	}

	private void addAttributesForIndex(Model model) {
		model.addAttribute("item", new TodoItemFormData());
		model.addAttribute("todos", getTodoItems());
		model.addAttribute("totalNumberOfItems", repository.count());
	}

	@PostMapping
	public String addNewTodoItem( @ModelAttribute("item") TodoItemFormData formData) {
		repository.save(new TodoItem(formData.getTitle(), false));

		return "redirect:/";
	}

	@PutMapping("/{id}/toggle")
	public String toggleSelection(@PathVariable("id") Long id) {
		Optional<TodoItem> todoItemOptional = repository.findById(id);

		if (todoItemOptional.isPresent()) {
			TodoItem todoItem = todoItemOptional.get();
			todoItem.setCompleted(!todoItem.isCompleted());
			repository.save(todoItem);
		}
		return "redirect:/";
	}

	@DeleteMapping("/{id}")
	public String deleteTodoItem(@PathVariable("id") Long id) {
		repository.deleteById(id);

		return "redirect:/";
	}

	private List<TodoItemDto> getTodoItems() {
		return convertToDto(repository.findAll());

	}

	private List<TodoItemDto> convertToDto(List<TodoItem> todoItems) {

		List<TodoItemDto> list = new ArrayList<>();
		for (TodoItem todoItem : todoItems) {
			list.add(new TodoItemDto(todoItem.getId(), todoItem.getTitle(), todoItem.isCompleted()));
		}
		return list;
	}

	public static record TodoItemDto(long id, String title, boolean completed) {
	}

	// data transfer obj
}
