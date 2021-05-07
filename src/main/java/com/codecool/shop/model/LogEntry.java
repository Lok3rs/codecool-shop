package com.codecool.shop.model;

import java.time.LocalDateTime;

public class LogEntry extends BaseModel{

    private String action;
    private LocalDateTime date;


    public LogEntry(String action) {
        this.action = action;
        this.date = LocalDateTime.now();
    }

    public String getAction() { return action; }

    public LocalDateTime getDate() { return date; }

}