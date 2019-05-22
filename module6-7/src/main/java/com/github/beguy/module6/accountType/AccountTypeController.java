package com.github.beguy.module6.accountType;

import com.github.beguy.module6.bank.BankDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

@Controller
public class AccountTypeController {
    @Autowired
    private AccountTypeDao accountTypeDao;
    @Autowired
    private BankDao bankDao;

//    @GetMapping("/accountTypes")
//    public String showAll(Model model) {
//        List<AccountType> accountTypes = accountTypeDao.findAll();
//        model.addAttribute("accountTypes", accountTypes);
//        model.addAttribute("banks", bankDao.findAll());
//        return "/accountType/all";
//    }

    @GetMapping("/accountTypes")
    public String showAll(Model model) {
        List<AccountType> accountTypes = accountTypeDao.findAll();
        List<Integer> clientsAmount = accountTypeDao.clientsAmount();
        List<AccountTypeInfoDto> accountTypeInfoDtos = new LinkedList<>();
        //Decorate without creating DTO
        for (int i = 0; i < accountTypes.size(); ++i){
            AccountTypeInfoDto accountTypeInfoDto = new AccountTypeInfoDto(accountTypes.get(i), clientsAmount.get(i));
            accountTypeInfoDtos.add(accountTypeInfoDto);
        }

        model.addAttribute("accountTypeInfo", accountTypeInfoDtos);
        model.addAttribute("banks", bankDao.findAll());
        return "/accountType/all";
    }

    @GetMapping("/accountType/{id}/delete/")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        accountTypeDao.delete(id);
        return "redirect:/accountTypes";
    }

    @PostMapping("/accountType/add")
    public String addAccountType(@Valid AccountType accountType, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "can't add this accountType " + accountType);
            return "/error";
        }

        accountTypeDao.save(accountType);
        return "redirect:/accountTypes";
    }

    @PostMapping("/accountType/edit")
    public String updateAccountType(@Valid AccountType accountType, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "can't delete this accountType " + accountType);
            return "/error";
        }

        accountTypeDao.update(accountType);
        return "redirect:/accountTypes";
    }
}
