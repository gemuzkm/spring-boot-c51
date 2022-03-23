package com.example.springbootc51.service;

import com.example.springbootc51.entity.Operation;
import com.example.springbootc51.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Component
public class HistoryService {

    public HistoryService() {
    }

    public void save(HttpSession session, Operation operation) {
        User user = (User) session.getAttribute("user");
        List<Operation> operationList = user.getOperationList();
        operationList.add(operation);
        user.setOperationList(operationList);

//        jpaUserDAO.update(user);
    }
}
