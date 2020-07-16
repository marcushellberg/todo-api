package com.example.demo;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class TodoService {
  private TodoRepository repo;

  TodoService(TodoRepository repo) {
    this.repo = repo;
  }

  @PostConstruct
  @Scheduled(fixedRate = 5 * 60 * 1000)
  void init() {
    repo.deleteAll();
    repo.save(new Todo("Build todo app"));
  }

  @GetMapping("/todos")
  public List<Todo> getTodos() {
    return repo.findAll();
  }

  @PostMapping(value = "/todos")
  public Todo addTodo(@RequestBody String task) {
    return repo.save(new Todo(task));
  }

  @DeleteMapping("/todos/{id}")
  public void deleteTodo(@PathVariable Long id) {
    repo.deleteById(id);
  }
}
