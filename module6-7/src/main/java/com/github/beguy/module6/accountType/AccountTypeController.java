package com.github.beguy.module6.accountType;

import com.github.beguy.module6.bank.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AccountTypeController {
    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Autowired
    private BankRepository bankRepository;

    @GetMapping("/accountTypes")
    public String showAll(Model model) {
        model.addAttribute("accountTypeInfo", accountTypeRepository.getAllInfo());
        model.addAttribute("banks", bankRepository.findAll());
        return "accountTypes";
    }

    @GetMapping("/accountType/{id}/delete/")
    public String delete(@PathVariable("id") long id, Model model) {
        accountTypeRepository.deleteById(id);
        return "redirect:/accountTypes";
    }

    @PostMapping("/accountType/save")
    public String save(@Valid AccountType accountType, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "can't add this accountType " + accountType);
            return "error";
        }

        accountTypeRepository.save(accountType);
        return "redirect:/accountTypes";
    }
}
