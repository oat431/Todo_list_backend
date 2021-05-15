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

    public Optional<TodoTask> findTaskById(Long Task_no){
        return Optional.ofNullable(jdbcTemplate.queryForObject(
            "Select * from todo_task where Task_no=?",
                new Object[]{Task_no},
                new BeanPropertyRowMapper<TodoTask>(TodoTask.class)
        ));
    }

    public int addTodoTask(TodoTask todoTask,long todo_no){
        return jdbcTemplate.update(
                "Insert into todo_task(Todo_no,Task,Description)",
                new Object[]{
                        todo_no,todoTask.getTask(),todoTask.getDescription()
                }
        );
    }

    public List<TodoTask> getAllTaskByTodoList(long todo_no){
        return jdbcTemplate.query(
                "Select * from todo_task where Todo_no=?",new Object[]{todo_no},new TodoTaskMapper()
        );
    }

    public int update(TodoTask todoTask,long todo_no){
        return jdbcTemplate.update(
                "update todo_task set Task=?,Description=? where Todo_no=? and Task_no=?",
                new Object[]{
                    todoTask.getTask(),todoTask.getDescription(),todo_no,todoTask.getTask_no()
                });
    }

    public int deleteTask(TodoTask todoTask){
        return jdbcTemplate.update("Delete from todo_task where Task_no=?",new Object[]{todoTask.getTask_no()});
    }

    public int updateStatus(TodoTask todoTask,boolean status,long todo_no){
        return jdbcTemplate.update(
                "Update todo_task set Status=? where Todo_no=? and Task_no=?",
                new Object[]{status?1:0,todo_no,todoTask.getTask_no()
                });
    }
}
