package com.example.springbootc51.dao.inMemory;

import com.example.springbootc51.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryUserDAO {
    private List<User> userList;

    //add test user
    {
        userList = new ArrayList<>();
        userList.add(new User(1, "user1", "user1", "user1@gmail.com"));
        userList.add(new User(2, "user2", "user2", "user2@gmail.com"));
        userList.add(new User(3, "user3", "user3", "user3@gmail.com"));
    }

    public List<User> getAll() {
        return userList;
    }

    public User getById(int id) {
        return userList.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    public void save(User user) {
        user.setId(userList.size() + 1);
        userList.add(user);
    }

    public void update(int id, User updatedUser) {
        User userToBeUpdated = getById(id);
        userToBeUpdated.setName(updatedUser.getName());
        userToBeUpdated.setPassword(updatedUser.getPassword());
        userToBeUpdated.setEmail(updatedUser.getEmail());
    }

    public void delete(int id) {
        userList.removeIf(p -> p.getId() == id);
    }
}
