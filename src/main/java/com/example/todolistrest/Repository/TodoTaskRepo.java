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

import com.example.todolistrest.Model.TodoTask;

@Repository
public class TodoTaskRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class TodoTaskMapper implements RowMapper<TodoTask> {
        @Override
        public TodoTask mapRow(ResultSet resultSet, int i) throws SQLException {
            TodoTask todoTask = new TodoTask();
            todoTask.setTodo_no(resultSet.getInt("Todo_no"));
            todoTask.setTask_no(resultSet.getInt("Task_no"));
            todoTask.setTask(resultSet.getString("Task"));
            todoTask.setDescription(resultSet.getString("Description"));
            todoTask.setStatus(resultSet.getInt("Status"));
            return todoTask;
        }
    }
}
