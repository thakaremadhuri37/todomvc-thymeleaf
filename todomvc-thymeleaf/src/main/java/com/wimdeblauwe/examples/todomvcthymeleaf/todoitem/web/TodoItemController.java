package com.wimdeblauwe.examples.todomvcthymeleaf.todoitem.web;

import com.wimdeblauwe.examples.todomvcthymeleaf.todoitem.TodoItem;
import com.wimdeblauwe.examples.todomvcthymeleaf.todoitem.TodoItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
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
        model.addAttribute("item", new TodoItemFormData());
        model.addAttribute("todos", getTodoItems());
        model.addAttribute("totalNumberOfItems", repository.count());
        return "index";
    }

    @PostMapping
    public String addNewTodoItem(@Valid @ModelAttribute("item") TodoItemFormData formData) {
        repository.save(new TodoItem(formData.getTitle(), false));

        return "redirect:/";
    }

    private List<TodoItemDto> getTodoItems() {
        return repository.findAll()
                         .stream()
                         .map(todoItem -> new TodoItemDto(todoItem.getId(),
                                                          todoItem.getTitle(),
                                                          todoItem.isCompleted()))
                         .collect(Collectors.toList());
    }

    public static record TodoItemDto(long id, String title, boolean completed) {
    }
}