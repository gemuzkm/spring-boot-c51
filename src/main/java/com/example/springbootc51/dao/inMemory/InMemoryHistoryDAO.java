package com.example.springbootc51.dao.inMemory;

import com.example.springbootc51.entity.Operation;
import com.example.springbootc51.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Repository
public class InMemoryHistoryDAO {
    private Map<Long, List<Operation>> mapOperation = new HashMap<>();

    public void save(User user, Operation operation) {
        List<Operation> litUserOperation = findAll(user);
        litUserOperation.add(operation);
        mapOperation.put(user.getId(), litUserOperation);
    }

    public void remove(User user, Operation operation) {
        List<Operation> litUserOperation = findAll(user);
        litUserOperation.removeIf(p -> p.getId() == operation.getId());
        mapOperation.put(user.getId(), litUserOperation);
    }

    public void update(User user, Operation operation) {
        List<Operation> litUserOperation = findAll(user);
        litUserOperation.add(operation);
        mapOperation.put(user.getId(), litUserOperation);
    }

    public List<Operation> findAll(User user) {
        return mapOperation.get(user.getId());
    }
}
