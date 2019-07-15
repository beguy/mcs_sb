package com.github.beguy.module6.client;

import com.github.beguy.module6.accountType.AccountTypeRepository;
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
    private ClientRepository clientRepository;

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @GetMapping("/clients/search/")
    public String clientsWithAccountDate(
            @RequestParam Map<String, String> predicateConditions,
            Model model){
        model.addAttribute("accountTypes", accountTypeRepository.findAll());
        model.addAttribute("clients", clientRepository.findAll(predicateConditions));
        return "/clients";
    }


    @GetMapping("/clients")
    public String showAll(Model model) {
        model.addAttribute("accountTypes", accountTypeRepository.findAll());
        model.addAttribute("clients", clientRepository.findAll());
        return "/clients";
    }

    @GetMapping("/client/{id}/delete/")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        clientRepository.deleteById(id);
        return "redirect:/clients";
    }

    @PostMapping("/client/add")
    public String addClient(@Valid Client client, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "can't add this client " + client);
            return "/error";
        }

        clientRepository.save(client);
        return "redirect:/clients";
    }

    @PostMapping("/client/edit")
    public String updateClient(@Valid Client client, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "can't edit this client " + client);
            return "/error";
        }

        clientRepository.update(client);
        return "redirect:/clients";
    }
}
