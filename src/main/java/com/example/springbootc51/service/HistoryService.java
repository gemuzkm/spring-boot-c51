package com.example.springbootc51.service;

import com.example.springbootc51.dao.inMemory.InMemoryHistoryDAO;
import com.example.springbootc51.dto.OperationDTO;
import com.example.springbootc51.entity.Operation;
import com.example.springbootc51.entity.User;
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

    public void save(User user, Operation operation) {
//        List<Operation> operationList = user.getOperationList();
//        operationList.add(operation);
//        user.setOperationList(operationList);

//        jpaUserDAO.update(user);
    }

    public void save(User user, OperationDTO operationDTO) {

    }
}
