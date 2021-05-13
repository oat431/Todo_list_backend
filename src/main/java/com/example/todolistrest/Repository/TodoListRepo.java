package com.example.todolistrest.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.todolistrest.Model.TodoList;

@Repository
public class TodoListRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class TodoListMapper implements RowMapper<TodoList> {
        @Override
        public TodoList mapRow(ResultSet resultSet, int i) throws SQLException {
            TodoList todo = new TodoList();
            todo.setTodo_no(resultSet.getInt("Todo_no"));
            todo.setTitle(resultSet.getString("Title"));
            todo.setDescription(resultSet.getString("Description"));
            todo.setOwner(resultSet.getString("Owner"));
            todo.setDate(resultSet.getDate("Date"));
            return todo;
        }
    }
}
