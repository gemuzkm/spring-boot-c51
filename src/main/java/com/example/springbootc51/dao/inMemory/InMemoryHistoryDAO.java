package com.example.springbootc51.dao.inMemory;

import com.example.springbootc51.dao.DAO;
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
public class InMemoryHistoryDAO implements DAO<Operation> {
//    private static Map<Long, List<Operation>> mapOperation = new HashMap<>();
    private static List<Operation> listOperation = new ArrayList<>();

    @Override
    public void save(Operation operation) {
       listOperation.add(operation);
    }

    @Override
    public void remove(Operation operation) {
        listOperation.removeIf(o -> (o.getUser() == operation.getUser()) && (o.getId() == operation.getId()));
    }

    @Override
    public void update(Operation operation) {

    }

    @Override
    public List<Operation> findAll() {
        return null;
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
