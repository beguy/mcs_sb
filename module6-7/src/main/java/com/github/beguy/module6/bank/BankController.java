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
    private BankRepository bankRepository;

    @GetMapping("/banks")
    public String showAll(Model model) {
        List<Bank> banks = bankRepository.findAll();
        model.addAttribute("banks", banks);
        return "/banks";
    }

    @GetMapping("/bank/{id}/delete/")
    public String delete(@PathVariable("id") long id, Model model) {
        bankRepository.deleteById(id);
        return "redirect:/banks";
    }

    @PostMapping("/bank/save")
    public String save(@Valid Bank bank, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "can't add this bank " + bank);
            return "/error";
        }

        bankRepository.save(bank);
        return "redirect:/banks";
    }
}
