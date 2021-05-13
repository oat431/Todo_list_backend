package com.example.todolistrest.Model;

public class TodoTask {
    private int task_no;
    private int todo_no;
    private String task;
    private String description;
    private int status;

    public TodoTask(int task_no, int todo_no, String task, String description, int status) {
        this.task_no = task_no;
        this.todo_no = todo_no;
        this.task = task;
        this.description = description;
        this.status = status;
    }

    public int getTask_no() {
        return task_no;
    }

    public void setTask_no(int task_no) {
        this.task_no = task_no;
    }

    public int getTodo_no() {
        return todo_no;
    }

    public void setTodo_no(int todo_no) {
        this.todo_no = todo_no;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
