package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;

@RestController
@CrossOrigin(origins = "*")
public class TodoService {
  private ArrayList<Todo> todos = new ArrayList<>();

  @PostConstruct
  @Scheduled(fixedRate = 5 * 60 * 1000)
  void init() {
    todos.clear();
    todos.add(new Todo("Build todo app"));
  }

  @GetMapping("/todos")
  public List<Todo> getTodos() {
    return todos;
  }

  @PostMapping(value = "/todos")
  public Todo addTodo(@RequestBody String task) {
    Todo todo = new Todo(task);
    todos.add(todo);
    return todo;
  }

  @DeleteMapping("/todos/{id}")
  public void deleteTodo(@PathVariable String id) {
    todos.removeIf(t -> t.getId().equals(UUID.fromString(id)));
  }
}
