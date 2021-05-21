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
            todo.setDate(resultSet.getDate("Dates"));
            return todo;
        }
    }

    public List<TodoList> allTodoList(){
        return jdbcTemplate.query("Select * From todo_list",new TodoListMapper());
    }

    public Optional<TodoList> TodoListDetailById(long todo_no){
        return Optional.ofNullable(jdbcTemplate.queryForObject(
                "Select * From todo_list Where Todo_no=?",
                new Object[] {todo_no},
                new BeanPropertyRowMapper<TodoList>(TodoList.class)
        ));
    }

    public int addTodoList(TodoList todoList){
        return jdbcTemplate.update(
                "Insert into todo_list (Owner,Description,dates,Title) values(?,?,?,?)",
                new Object[]{
                        todoList.getOwner(),todoList.getDescription(),todoList.getDate(),todoList.getTitle()
                }
        );
    }

    public int updateTodoList(TodoList todoList){
        System.out.println(todoList.toString());
        return jdbcTemplate.update(
            "Update todo_list set Description=?,dates=?, Title=? Where Todo_no=?",
            new Object[] {
                todoList.getDescription(),todoList.getDate(),todoList.getTitle(),todoList.getTodo_no()
        });
    }

    public int deleteTodoList(TodoList todoList){
        deleteAllTaskByTodoList(todoList.getTodo_no());
        return jdbcTemplate.update(
                "Delete From todo_list where Todo_no=?",
                new Object[] {todoList.getTodo_no()}
        );
    }

   private int deleteAllTaskByTodoList(long todo_no){
        return jdbcTemplate.update("Delete From todo_task where Todo_no=?",new Object[]{todo_no});
   }
}
