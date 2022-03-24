package com.example.springbootc51.dao.inMemory;

import com.example.springbootc51.entity.Operation;
import com.example.springbootc51.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Repository
public class InMemoryHistoryDAO {
//    private static Map<Long, List<Operation>> mapOperation = new HashMap<>();
    private static List<Operation> listOperation = new ArrayList<>();

    public void save(Operation operation) {
       listOperation.add(operation);
    }

    public void remove(Operation operation) {

    }

    public void update(Operation operation) {

    }

    public List<Operation> findAll(User user) {
        List<Operation> userHistory = new ArrayList<>();

       for (Operation item: listOperation) {
           if (user.getId() == item.getUser().getId()) {
               userHistory.add(item);
           }
       }
       return userHistory;
    }
}
