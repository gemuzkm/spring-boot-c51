package com.example.springbootc51.dao.inMemory;

import com.example.springbootc51.dao.DAO;
import com.example.springbootc51.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Component
@Repository
public class InMemoryUserDAO implements DAO<User> {
    private List<User> userList = new ArrayList<>();;

    //add test user
    {
        userList.add(new User(1, "user1", "user1", "user1@gmail.com"));
        userList.add(new User(2, "user2", "user2", "user2@gmail.com"));
        userList.add(new User(3, "user3", "user3", "user3@gmail.com"));
    }

    @Override
    public List<User> findAll() {
        return userList;
    }

    public User findById(long id) {
        return userList.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    @Override
    public void save(User user) {
        user.setId(userList.size() + 1);
        userList.add(user);
    }

    @Override
    public void update(User user) {
        int useUpdateId = Math.toIntExact(user.getId()) - 1;
        userList.set(useUpdateId, user);
    }

    @Override
    public void remove(User user) {
        userList.removeIf(p -> p.getId() == user.getId());
    }

    public User findByUsername(String name) {
        return userList.stream().filter(p -> p.getName().equals(name)).findAny().orElse(null);
    }
}
