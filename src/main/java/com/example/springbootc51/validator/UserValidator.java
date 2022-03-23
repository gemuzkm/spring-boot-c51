package com.example.springbootc51.validator;

import com.example.springbootc51.dao.inMemory.InMemoryUserDAO;
import com.example.springbootc51.dto.UserDTO;
import com.example.springbootc51.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    @Autowired
    InMemoryUserDAO inMemoryUserDAO;

    // not used
    public boolean isValid(User user) {
        return true;
    }

    public boolean isValid(UserDTO userDTO) {
        return isValidUserName(userDTO) && isValidUserPassword(userDTO);
    }

    private boolean isValidUserName(UserDTO userDTO) {
        return inMemoryUserDAO.findByUsername(userDTO.getName()) != null;
    }

    private boolean isValidUserPassword(UserDTO userDTO) {
        User user = inMemoryUserDAO.findByUsername(userDTO.getName());
        return user.getPassword().equals(userDTO.getPassword());
    }
}
