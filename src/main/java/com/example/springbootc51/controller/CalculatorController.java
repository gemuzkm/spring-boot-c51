package com.example.springbootc51.controller;

import com.example.springbootc51.converter.OperationDTOConverter;
import com.example.springbootc51.dto.OperationDTO;
import com.example.springbootc51.entity.Operation;
import com.example.springbootc51.entity.User;
import com.example.springbootc51.service.OperationService;
import com.example.springbootc51.service.СalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/calc")
public class CalculatorController {

    @Autowired
    private OperationService operationService;

    @Autowired
    private СalculatorService сalculatorService;

    @Autowired
    private OperationDTOConverter operationDTOConverter;

    @GetMapping
    public String calc(@ModelAttribute("calcOperation") Operation operation, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        } else {
            return "calculator/calc";
        }
    }

    @PostMapping
    public String result(@Valid @ModelAttribute("calcOperation") OperationDTO operationDTO,
                         BindingResult bindingResult, HttpSession session, Model model) {

        if (session.getAttribute("user") == null) {
            return "redirect:/";
        } else if (bindingResult.hasErrors()) {
            return "calculator/calc";
        }

        User user = (User) session.getAttribute("user");
        Operation operation = operationDTOConverter.operationDTOtoOperation(operationDTO);
        operation.setResult(сalculatorService.getResult(operation));
        operation.setUser(user);

        operationService.save(user, operation);

        model.addAttribute("msgResult", operation.getResult());

        return "calculator/calc";
    }

    @GetMapping("/history")
    public String history(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        } else {
            User user = (User) session.getAttribute("user");
            model.addAttribute("userHistory", user.getOperationList());

            return "calculator/history";
        }
    }
}
