package com.github.beguy.module6.accountType;

import com.github.beguy.module6.bank.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


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
        return "/accountTypes";
    }
}
