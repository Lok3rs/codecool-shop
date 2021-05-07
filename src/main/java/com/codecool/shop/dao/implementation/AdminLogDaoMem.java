package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.AdminLogDao;
import com.codecool.shop.model.LogEntry;

import java.util.ArrayList;
import java.util.List;

public class AdminLogDaoMem implements AdminLogDao {

    private static AdminLogDaoMem instance;
    private List<LogEntry> data;

    private AdminLogDaoMem(){
        this.data = new ArrayList<>();
    }

    public static AdminLogDaoMem getInstance(){
        if (instance == null){
            instance = new AdminLogDaoMem();
        }
        return instance;
    }


    public void add(LogEntry logEntry) {
        logEntry.setId(data.size() + 1);
        data.add(logEntry);
    }

    public List<LogEntry> getAll() {
        return data;
    }

    public void clearMem(){
        data.clear();
    }

}
