package com.github.beguy.module6.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BankController {
    @Autowired
    private BankRepository bankRepository;

    @GetMapping("/banks")
    public String showAll(Model model) {
        model.addAttribute("banks", bankRepository.findAll());
        return "/banks";
    }
}
