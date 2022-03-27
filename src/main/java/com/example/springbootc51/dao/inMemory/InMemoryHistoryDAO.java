package com.example.springbootc51.dao.inMemory;

import com.example.springbootc51.dao.DAO;
import com.example.springbootc51.entity.Operation;
import com.example.springbootc51.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

@Component
@Repository
public class InMemoryHistoryDAO implements DAO<Operation> {
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
        for (int i = 0; i < listOperation.size(); i++) {
            if (listOperation.get(i).getId() == operation.getId()) {
                listOperation.get(i).setValue1(operation.getValue1());
                listOperation.get(i).setValue2(operation.getValue2());
                listOperation.get(i).setResult(operation.getResult());
                listOperation.get(i).setUser(operation.getUser());
            }
        }
    }

    @Override
    public List<Operation> findAll() {
        return listOperation;
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
