package com.example.todolistrest.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.example.todolistrest.Model.TodoTask;
import com.example.todolistrest.Repository.TodoTaskRepo;
import com.example.todolistrest.Config.ResourceNotFound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/todo")
public class TodoTaskController {
    @Autowired
    private TodoTaskRepo todoTaskRepo;

    @GetMapping("/{todo_no}/allTasks")
    public List<TodoTask> getAllTask(@PathVariable(value="todo_no")Long todo_no){
        return todoTaskRepo.getAllTaskByTodoList(todo_no);
    }

    @PostMapping("/{todo_no}/addTask")
    public int addNewTask(@Valid @RequestBody TodoTask newTask,@PathVariable(value="todo_no")Long todo_no){
        return todoTaskRepo.addTodoTask(newTask,todo_no);
    }

    @PutMapping("/{todo_no}/updateTask/{task_no}")
    public ResponseEntity<Integer> updateTask(@Valid @RequestBody TodoTask updatedTask,@PathVariable(value="todo_no")Long todo_no,@PathVariable(value="task_no")Long task_no) throws ResourceNotFound{
        TodoTask prepareToUpdate = todoTaskRepo.findTaskById(task_no).orElseThrow(()->new ResourceNotFound("not found"));
        prepareToUpdate.setTask(updatedTask.getTask());
        prepareToUpdate.setDescription(updatedTask.getDescription());
        return ResponseEntity.ok(todoTaskRepo.update(prepareToUpdate,todo_no));
    }

    @PutMapping("/{todo_no}/updateStatus/{task_no}/{status}")
    public ResponseEntity<Integer> updateStatus(@Valid @RequestBody TodoTask updatedTask,@PathVariable(value="todo_no")Long todo_no,@PathVariable(value="task_no")Long task_no,@PathVariable(value="status")Boolean status) throws ResourceNotFound{
        TodoTask prepareToUpdate = todoTaskRepo.findTaskById(task_no).orElseThrow(()->new ResourceNotFound("not found"));
        prepareToUpdate.setStatus(updatedTask.getStatus());
        return ResponseEntity.ok(todoTaskRepo.updateStatus(prepareToUpdate,status,todo_no));
    }

    @DeleteMapping("/{todo_no}/delteTask/{task_no}")
    public Map<String,Boolean> deleteTask(@PathVariable(value="todo_no")Long todo_no,@PathVariable(value="task_no")Long task_no) throws ResourceNotFound {
        TodoTask prepareToDelete = todoTaskRepo.findTaskById(task_no).orElseThrow(()->new ResourceNotFound("not found"));
        todoTaskRepo.deleteTask(prepareToDelete);
        Map<String,Boolean> res = new HashMap<>();
        res.put("Deleted",Boolean.TRUE);
        return res;
    }
}
