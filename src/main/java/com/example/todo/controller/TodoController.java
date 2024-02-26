/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 */

// Write your code here
package com.example.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.todo.service.TodoH2Service;
import com.example.todo.model.Todo;

@RestController
public class TodoController {
    @Autowired
    public TodoH2Service todoh2service;

    @GetMapping("/todos")
    public ArrayList<Todo> getTodos() {
        return todoh2service.getTodos();
    }

    @GetMapping("/todos/{Id}")
    public Todo todoById(@PathVariable("Id") int Id) {
        return todoh2service.todoById(Id);
    }

    @PostMapping("/todos")
    public Todo addTodo(@RequestBody Todo todo) {
        return todoh2service.addTodo(todo);
    }

    @PutMapping("/todos/{Id}")
    public Todo updateTodo(@PathVariable("Id") int Id, @RequestBody Todo todo) {
        return todoh2service.updateTodo(Id, todo);
    }

    @DeleteMapping("/todos/{Id}")
    public void deleteTodo(@PathVariable("Id") int Id) {
        todoh2service.deleteTodo(Id);
    }

}
