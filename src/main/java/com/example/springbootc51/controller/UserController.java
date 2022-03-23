package com.example.springbootc51.controller;

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

    @GetMapping("/")
    public String index() {

        //init test data users
/*        if (jpaUserDAO.findById(1) == null) {

            User user1 = new User();
            user1.setName("user1");
            user1.setPassword("user1");
            user1.setEmail("user1@gmail.com");
//            hibernateUserDAO.save(user1);
            jpaUserDAO.save(user1);

            User user2 = new User();
            user2.setName("user2");
            user2.setPassword("user2");
            user2.setEmail("user2@gmail.com");
//            hibernateUserDAO.save(user2);
            jpaUserDAO.save(user2);

            User user3 = new User();
            user3.setName("user3");
            user3.setPassword("user3");
            user3.setEmail("user3@gmail.com");
//            hibernateUserDAO.save(user3);
            jpaUserDAO.save(user3);*/
//        }

        return "user/index";
    }

    @GetMapping("/users")
    public String showAllUsers(Model model) {
//        model.addAttribute("users", hibernateUserDAO.findAll());
//        model.addAttribute("users", jpaUserDAO.findAll());
        return "user/users";
    }

    @GetMapping("user/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("user/{id}")
    public String showById(@PathVariable("id") long id, Model model) {
//        model.addAttribute("user", hibernateUserDAO.findById(id));
//        model.addAttribute("user", jpaUserDAO.findById(id));
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

//        hibernateUserDAO.save(user);
//        jpaUserDAO.save(user);
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
//            session.setAttribute("user", hibernateUserDAO.findByUsername(userDTO.getName()));
//            session.setAttribute("user", jpaUserDAO.findByUsername(userDTO.getName()));
        } else  {
            model.addAttribute("msgerror", "invalid user/login");
            return "user/login";
        }

        return "user/index";
    }

    @GetMapping("user/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
//        model.addAttribute("user", hibernateUserDAO.findById(id));
//        model.addAttribute("user", jpaUserDAO.findById(id));
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
//        hibernateUserDAO.update(user);
//        jpaUserDAO.update(user);
        return "user/index";
    }

    @DeleteMapping("user/{id}")
    public String delete(@PathVariable("id") long id, HttpSession session) {
//        User user = hibernateUserDAO.findById(id);
//        User user = jpaUserDAO.findById(id);
//        hibernateUserDAO.remove(user);
//        jpaUserDAO.remove(user);
        session.invalidate();
        return "user/index";
    }
}
