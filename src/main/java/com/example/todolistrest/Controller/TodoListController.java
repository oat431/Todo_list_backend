package com.example.todolistrest.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.todolistrest.Model.TodoList;
import com.example.todolistrest.Config.ResourceNotFound;
import com.example.todolistrest.Repository.TodoListRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todo")
public class TodoListController {
    @Autowired
    private TodoListRepo todoListRepo;

    @GetMapping("/todolist")
    public List<TodoList> getAllTodoList(){
        return todoListRepo.allTodoList();
    }

    @PostMapping("/todolist")
    public int addNewTodoList(@Valid @RequestBody TodoList newTodoList){
        return todoListRepo.addTodoList(newTodoList);
    }

    @PutMapping("/todolist/{id}")
    public ResponseEntity<Integer> updateTodoList(@PathVariable(value="id")Long todo_no, @RequestBody TodoList updateData) throws ResourceNotFound {
        TodoList updateTodoList = todoListRepo.TodoListDetailById(todo_no).orElseThrow(()->new ResourceNotFound("don't have this todo please check again"));
        updateTodoList.setTitle(updateData.getTitle());
        updateTodoList.setDescription(updateData.getDescription());
        updateTodoList.setDate(updateData.getDate());
        System.out.println(updateTodoList.toString());
        return ResponseEntity.ok(todoListRepo.updateTodoList(updateTodoList));
    }

    @DeleteMapping("/todolist/{id}")
    public Map<String,Boolean> deleteTodoList(@PathVariable(value="id")Long todo_no) throws ResourceNotFound{
        TodoList deleteTodoList = todoListRepo.TodoListDetailById(todo_no).orElseThrow(()->new ResourceNotFound("Not have this todo list yet"));
        todoListRepo.deleteTodoList(deleteTodoList);
        Map<String,Boolean> res = new HashMap<String,Boolean>();
        res.put("Deleted",Boolean.TRUE);
        return res;
    }
}
