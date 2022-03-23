package com.example.springbootc51.controller;

import com.example.springbootc51.dao.inMemory.InMemoryUserDAO;
import com.example.springbootc51.dto.UserDTO;
import com.example.springbootc51.entity.User;
import com.example.springbootc51.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    UserValidator userValidator;

    @Autowired
    InMemoryUserDAO inMemoryUserDAO;

    @GetMapping("/")
    public String index() {
        return "user/index";
    }

    @GetMapping("/users")
    public String showAllUsers(Model model) {
        model.addAttribute("users", inMemoryUserDAO.findAll());
        return "user/users";
    }

    @GetMapping("user/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("user/{id}")
    public String showById(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", inMemoryUserDAO.findById(id));
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

        inMemoryUserDAO.save(user);
        return "redirect:/";
    }

    @GetMapping("user/login")
    public String showLoginPage(@ModelAttribute("user") User user) {
        return "user/login";
    }

    @PostMapping("user/login")
    public String login(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult bindingResult,
                        HttpSession session, Model model) {

        if (bindingResult.hasErrors()) {
            return "user/login";
        } else if (userValidator.isValid(userDTO))  {
            session.setAttribute("user", inMemoryUserDAO.findByUsername(userDTO.getName()));
        } else  {
            model.addAttribute("msgerror", "invalid user/login");
            return "user/login";
        }

        return "user/index";
    }

    @GetMapping("user/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", inMemoryUserDAO.findById(id));

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
        inMemoryUserDAO.update(user);

        return "user/index";
    }

    @DeleteMapping("user/{id}")
    public String delete(@PathVariable("id") long id, HttpSession session) {
        User user = inMemoryUserDAO.findById(id);
        inMemoryUserDAO.remove(user);
        session.invalidate();
        return "user/index";
    }
}
