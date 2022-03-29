package com.example.springbootc51.controller;

import com.example.springbootc51.dto.UserDTO;
import com.example.springbootc51.entity.User;
import com.example.springbootc51.repository.UserRepository;
import com.example.springbootc51.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {
    private static final String MSG_USER_EXITS = "user exists";
    private static final String MSG_USER_LOGIN_INVALID = "invalid user/login";

    private final UserValidator userValidator;

    private final UserRepository userRepository;

    public UserController(UserValidator userValidator, UserRepository userRepository) {
        this.userValidator = userValidator;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String index() {
        //add test data user
        if (!userRepository.findById(1L).isPresent()) {
            userRepository.save(new User("user1", "user1", "user1@gmail.com"));
            userRepository.save(new User("user2", "user2", "user2@gmail.com"));
            userRepository.save(new User("user3", "user3", "user3@gmail.com"));
        }

        return "user/index";
    }

    @GetMapping("/users")
    public String showAllUsers(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }

        model.addAttribute("users", userRepository.findAll());

        return "user/users";
    }

    @GetMapping("user/logout")
    public String logout(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }

        session.invalidate();

        return "redirect:/";
    }

    @GetMapping("user/{id}")
    public String showById(@PathVariable("id") long id, Model model) {

        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElse(null);
        model.addAttribute("user", user);

        return "user/user";
    }

    @GetMapping("user/reg")
    public String newUser(@ModelAttribute("user") User user) {
        return "user/reg";
    }

    @PostMapping("user/reg")
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "user/reg";
        }

        if (userRepository.findByName(user.getName()).isPresent()) {
            model.addAttribute("msgerror", MSG_USER_EXITS);

            return "user/reg";
        }

        userRepository.save(user);

        return "redirect:/";
    }

    @GetMapping("user/login")
    public String showLoginPage(@ModelAttribute("user") User user) {
        return "user/login";
    }

    @PostMapping("user/login")
    public String login(@ModelAttribute("user") @Valid UserDTO userDTO,
                        BindingResult bindingResult, HttpSession session, Model model) {

        if (bindingResult.hasErrors()) {
            return "user/login";
        } else if (userValidator.isValid(userDTO)) {
            Optional<User> optionalUser = userRepository.findByName(userDTO.getName());
            User user = optionalUser.orElse(null);
            session.setAttribute("user", user);
        } else {
            model.addAttribute("msgerror", MSG_USER_LOGIN_INVALID);
            return "user/login";
        }

        return "user/index";
    }

    @GetMapping("user/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        }
        Optional<User> byId = userRepository.findById(id);
        model.addAttribute("user", byId.orElse(null));

        return "user/edit";
    }

    @PatchMapping("user/{id}")
    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult, @PathVariable("id") long id,
                         HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "user/edit";
        }

        session.setAttribute("user", user);
        userRepository.save(user);

        return "user/index";
    }

    @DeleteMapping("user/{id}")
    public String delete(@PathVariable("id") long id, HttpSession session) {
        Optional<User> byId = userRepository.findById(id);
        User user = byId.orElse(null);
        if (user != null) {
            userRepository.delete(user);
            session.invalidate();
        }

        return "user/index";
    }
}
