/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 *
 */

// Write your code here
package com.example.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.todo.repository.TodoRepository;
import com.example.todo.model.Todo;
import com.example.todo.model.TodoRowMapper;

@Service
public class TodoH2Service implements TodoRepository {
    @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Todo> getTodos() {
        List<Todo> todosList = db.query("select * from TODOLIST", new TodoRowMapper());
        ArrayList<Todo> todos = new ArrayList<>(todosList);
        return todos;
    }

    @Override
    public Todo todoById(int Id) {
        try {
            Todo todo = db.queryForObject("select * from TODOLIST where id=?", new TodoRowMapper(), Id);
            return todo;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Todo updateTodo(int Id, Todo todo) {
        if (todo.getTodo() != null) {
            db.update("update TODOLIST set todo=? where id=?", todo.getTodo(), Id);
        }
        if (todo.getStatus() != null) {
            db.update("update TODOLIST set status=? where id=?", todo.getStatus(), Id);
        }
        if (todo.getPriority() != null) {
            db.update("update TODOLIST set priority=? where id=?", todo.getPriority(), Id);
        }
        return todoById(Id);

    }

    @Override
    public Todo addTodo(Todo todo) {
        db.update("insert into TODOLIST(todo,status,priority) values(?,?,?)", todo.getTodo(), todo.getStatus(),
                todo.getPriority());
        Todo savedTodo = db.queryForObject("select * from TODOLIST where todo=? and status=? and priority=?",
                new TodoRowMapper(), todo.getTodo(), todo.getStatus(), todo.getPriority());
        return savedTodo;
    }

    @Override
    public void deleteTodo(int Id) {
        db.update("delete from TODOLIST where id=?", Id);
    }

}
