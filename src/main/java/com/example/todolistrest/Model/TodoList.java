package com.example.todolistrest.Model;

import java.util.Date;

public class TodoList {
    private int todo_no;
    private String owner;
    private String description;
    private Date date;
    private String title;

    public TodoList(int todo_no, String owner, String description, Date date, String title) {
        this.todo_no = todo_no;
        this.owner = owner;
        this.description = description;
        this.date = date;
        this.title = title;
    }

    public int getTodo_no() {
        return todo_no;
    }

    public void setTodo_no(int todo_no) {
        this.todo_no = todo_no;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
