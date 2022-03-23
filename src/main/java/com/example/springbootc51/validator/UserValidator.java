package com.example.springbootc51.validator;

import com.example.springbootc51.dto.UserDTO;
import com.example.springbootc51.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    // not used
    public boolean isValid(User user) {
        return true;
    }

    public boolean isValid(UserDTO userDTO) {
        return isValidUserName(userDTO) && isValidUserPassword(userDTO);
    }

    private boolean isValidUserName(UserDTO userDTO) {
//        return hibernateUserDAO.findAllByName(userDTO.getName()).size() != 0;
        return true;
    }

    private boolean isValidUserPassword(UserDTO userDTO) {
//        User user = hibernateUserDAO.findByUsername(userDTO.getName());
//        return user.getPassword().equals(userDTO.getPassword());
        return  true;
    }
}
