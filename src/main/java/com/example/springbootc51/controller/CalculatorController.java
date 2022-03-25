package com.example.springbootc51.controller;

import com.example.springbootc51.converter.OperationDTOConverter;
import com.example.springbootc51.dao.inMemory.InMemoryHistoryDAO;
import com.example.springbootc51.dto.OperationDTO;
import com.example.springbootc51.entity.Operation;
import com.example.springbootc51.entity.User;
import com.example.springbootc51.service.HistoryService;
import com.example.springbootc51.service.СalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/calc")
public class CalculatorController {

    @Autowired
    private InMemoryHistoryDAO inMemoryHistoryDAO;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private СalculatorService сalculatorService;

    @Autowired
    OperationDTOConverter operationDTOConverter;

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
        Operation operation = new Operation();
        operation = operationDTOConverter.operationDTOtoOperation(operationDTO);
        operation.setResult(сalculatorService.getResult(operation));
        operation.setId(inMemoryHistoryDAO.findAll(user).size() + 1);
        operation.setUser(user);

        historyService.save(operation);

        model.addAttribute("msgResult", operation.getResult());
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
