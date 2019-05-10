package com.github.beguy.module6.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BankController {
    @Autowired
    private BankDao bankDao;

    @GetMapping("/banks")
    public String showAll(Model model) {
        List<Bank> banks = bankDao.findAll();
        model.addAttribute("banks", banks);
        return "/bank/all";
    }

    @GetMapping("/bank/{id}/delete/")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        bankDao.delete(id);
        return "redirect:/banks";
    }

    @PostMapping("/bank/add")
    public String addBank(@Valid Bank bank, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "can't add this bank " + bank);
            return "/error";
        }

        bankDao.save(bank);
        return "redirect:/banks";
    }

    @PostMapping("/bank/edit")
    public String updateBank(@Valid Bank bank, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "can't edit this bank " + bank);
            return "/error";
        }

        bankDao.update(bank);
        return "redirect:/banks";
    }
}
