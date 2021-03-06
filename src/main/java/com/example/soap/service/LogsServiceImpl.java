package com.example.soap.service;

import com.example.soap.entity.LogEntryEntity;
import com.example.soap.persistence.LogsDao;
import com.example.soap.persistence.helpers.LogEntryType;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class holds the implementation of the methods of our SOAP web service
 */
@WebService(name="LogsService", endpointInterface = "com.example.soap.service.LogsService")
public class LogsServiceImpl implements LogsService {
    private LogsDao logRepository = new LogsDao();
    private List<LogEntryEntity> logs = new ArrayList<>();

    @Override
    public List<LogEntryEntity> getAllLogs(){
        logs = logRepository.getLogEntries();
        return logs;
    }

    @Override
    public List<LogEntryEntity> getAllLogsByChange(String change){
        LogEntryType change_type = LogEntryType.valueOf(change.toUpperCase());
        logs = logRepository.filterLogsByChange(change_type.getValue());
        return logs;
    }

    @Override
    public List<LogEntryEntity> getAllLogsByDates(String from, String to){
        logs = logRepository.filterLogsByDates(from, to);
        return logs;
    }

    @Override
    public List<LogEntryEntity> getAllLogsByDatesAndByChange(String from, String to, String change){
        LogEntryType change_type = LogEntryType.valueOf(change.toUpperCase());
        logs = logRepository.filterLogsByDatesAndChange(from, to, change_type.getValue());
        return logs;
    }
}
