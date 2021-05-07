package com.codecool.shop.dao.json;

import com.codecool.shop.model.LogEntry;

import java.util.ArrayList;
import java.util.List;

public class AdminLogFile {

    ArrayList<ArrayList<String>> allLogsList;

    public AdminLogFile(List<LogEntry> allLogs){
        this.allLogsList = logsToList(allLogs);
    }

    private ArrayList<ArrayList<String>> logsToList(List<LogEntry> allLogs){
        ArrayList<ArrayList<String>> tempAllLogList = new ArrayList<>();
        for(LogEntry log : allLogs){
            ArrayList<String> temp = new ArrayList<>();
            temp.add("Actions:");
            temp.add(log.getAction());
            temp.add("Date:");
            temp.add(log.getDate()+"");

            if(temp.size() > 0){
                tempAllLogList.add(temp);
            }
        }
        return tempAllLogList;
    }

}
