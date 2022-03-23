package com.example.springbootc51.controller;

import com.example.springbootc51.dao.inMemory.InMemoryHistoryDAO;
import com.example.springbootc51.entity.Operation;
import com.example.springbootc51.entity.User;
import com.example.springbootc51.service.HistoryService;
import com.example.springbootc51.service.СalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/calc")
public class CalculatorController {

    @Autowired
    InMemoryHistoryDAO inMemoryHistoryDAO;

    @Autowired
    HistoryService historyService;

    @Autowired
    СalculatorService сalculatorService;

    @GetMapping
    public String calc(@ModelAttribute("calcOperation") Operation operation) {
        return "calculator/calc";
    }

    @PostMapping
    public String result(@Valid @ModelAttribute("calcOperation")  Operation operation,
                         BindingResult bindingResult, HttpSession session, Model model) {

        if (bindingResult.hasErrors()) {
            return "calculator/calc";
        }

        historyService.save(session, operation);

//       model.addAttribute("msgResult", сalculatorService.getResult(operation));
        return "calculator/calc";
    }

    @GetMapping("/history")
    public String history(HttpSession session, Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/";
        } else {
            List<Operation> operationList = inMemoryHistoryDAO.findAll((User) session.getAttribute("user"));
            model.addAttribute("userHistory", operationList);
            return "calculator/history";
        }
    }
}
