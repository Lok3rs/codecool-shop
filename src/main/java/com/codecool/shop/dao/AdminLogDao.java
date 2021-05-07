package com.codecool.shop.dao;

import com.codecool.shop.model.LogEntry;

import java.util.List;

public interface AdminLogDao {
    void add(LogEntry logEntry);
    List<LogEntry> getAll();
    void clearMem();

}
