// Write your code here
package com.example.todo.repository;

import java.util.*;
import com.example.todo.model.Todo;

public interface TodoRepository {
    ArrayList<Todo> getTodos();

    Todo todoById(int Id);

    Todo addTodo(Todo todo);

    Todo updateTodo(int Id, Todo todo);

    void deleteTodo(int Id);
}