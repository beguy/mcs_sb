package com.github.beguy.module6.client;

import com.github.beguy.module6.accountType.AccountTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class ClientController {
    @Autowired
    private ClientDao clientDao;

    @Autowired
    private AccountTypeDao accountTypeDao;

    @GetMapping("/clients/filter")
    public String clientsWithAccountDate(
            @RequestParam Map<String, String> predicateConditions,
            Model model){
        model.addAttribute("accountTypes", accountTypeDao.findAll());
        model.addAttribute("clients", clientDao.findAll(predicateConditions));
        return "/client/all";
    }


    @GetMapping("/clients")
    public String showAll(Model model) {
        model.addAttribute("accountTypes", accountTypeDao.findAll());
        model.addAttribute("clients", clientDao.findAll());
        return "/client/all";
    }

    @GetMapping("/client/{id}/delete/")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        clientDao.delete(id);
        return "redirect:/clients";
    }

    @PostMapping("/client/add")
    public String addClient(@Valid Client client, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "can't add this client " + client);
            return "/error";
        }

        clientDao.save(client);
        return "redirect:/clients";
    }

    @PostMapping("/client/edit")
    public String updateClient(@Valid Client client, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "can't edit this client " + client);
            return "/error";
        }

        clientDao.update(client);
        return "redirect:/clients";
    }
}
