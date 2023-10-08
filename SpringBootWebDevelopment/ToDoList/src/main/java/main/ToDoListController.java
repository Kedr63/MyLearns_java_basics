package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Vector;
import main.model.Task;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToDoListController {

  @Autowired
  public TaskRepository taskRepository;

  public ToDoListController(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  /* если зайдем в браузере в http://localhost:8080/, то контроллер выведет на странице время */
  @GetMapping("/")
  public String index() {
    LocalDateTime timeNow = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    return timeNow.format(formatter);
  }

  /* добавление нового task в лист. Task отправляем в формате JSON (проверить можно через Postman прогу) */
  @PostMapping(value = "/tasks", consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<String> addTask(@RequestBody Task task) {
    Task newTask = new Task(task.getTitle(), task.getDescription());
    taskRepository.save(newTask);
    System.out.println(newTask.getTitle());
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  /* получение task по id */
  @GetMapping("/tasks/{id}")
  public ResponseEntity<Task> getTask(@PathVariable int id) {
    Optional<Task> optionalTask = taskRepository.findById(id);
    if (!optionalTask.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return new ResponseEntity<>(optionalTask.get(), HttpStatus.OK);
  }

  /* контроллер выведет список всех дел */
  @GetMapping("/tasks")
  public List<Task> getListTasks() {
    Iterable<Task> taskIterable = taskRepository.findAll();
    List<Task> taskList = new Vector<>();
    for (Task task : taskIterable) {
      taskList.add(task);
    }
    return taskList;
  }

  /* изменит существующую задачу (task) */
  // @PutMapping - альтернативен @PatchMapping
  @PatchMapping("/tasks/{id}")
  public ResponseEntity<String> updateTask(@PathVariable int id, @RequestBody Task task) {
    Optional<Task> optionalTask = taskRepository.findById(id);
    if (optionalTask.isPresent()) {
      taskRepository.delete(optionalTask.get());
      taskRepository.save(task);
      return ResponseEntity.ok("OK");
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  @DeleteMapping("/tasks/{id}")
  public ResponseEntity<String> deleteTask(@PathVariable int id) {
    Optional<Task> optionalTask = taskRepository.findById(id);
    if (optionalTask.isPresent()) {
      taskRepository.delete(optionalTask.get());
      return ResponseEntity.ok("task delete");
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

}