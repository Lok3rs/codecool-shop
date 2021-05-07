
package com.codecool.shop.service;

import com.codecool.shop.dao.AdminLogDao;
import com.codecool.shop.dao.json.AdminLogFile;
import com.codecool.shop.dao.json.SerializationMenager;
import com.codecool.shop.model.LogEntry;

import java.util.List;

public class AdminLogService {

    private AdminLogDao adminLogDao;
    private final SerializationMenager serializationMenager;

    public AdminLogService(AdminLogDao adminLogDao) {
        this.adminLogDao = adminLogDao;
        this.serializationMenager = new SerializationMenager();
    }

    public void addLog(String action){ adminLogDao.add(new LogEntry(action)); }

    public void saveLogs(int orderId){
        List<LogEntry> allLogs = adminLogDao.getAll();
        AdminLogFile adminLogFile = new AdminLogFile(allLogs);
        serializationMenager.saveAdminLog(adminLogFile, orderId);
        adminLogDao.clearMem();

    }


}