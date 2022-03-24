package com.example.springbootc51.service;

import com.example.springbootc51.dao.inMemory.InMemoryHistoryDAO;
import com.example.springbootc51.entity.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class HistoryService {

    @Autowired
    private InMemoryHistoryDAO inMemoryHistoryDAO;

    public HistoryService() {
    }

    public void save(Operation operation) {
        inMemoryHistoryDAO.save(operation);
    }
}
