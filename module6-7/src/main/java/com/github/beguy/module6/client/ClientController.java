package com.github.beguy.module6.client;

import com.github.beguy.module6.accountType.AccountType;
import com.github.beguy.module6.accountType.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

@Controller
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    /**
     * @param predicateConditions Map with 1 or more param:
     *                            <table>
     *                            <tr>
     *                            <th>Key</th>
     *                            <th>Behavior</th>
     *                            </tr>
     *                            <tr>
     *                            <td>accountTypeName</td>
     *                            <td>c.accountType.name=:accountTypeName</td>
     *                            </tr>
     *                            <tr>
     *                            <td>accountTypeNameContains</td>
     *                            <td>c.accountType.name like '%'||:accountTypeNameContains||'%'</td>
     *                            </tr>
     *                            <tr>
     *                            <td>accountTypeNameStartsWith</td>
     *                            <td>c.accountType.name like :accountTypeNameStartsWith||'%'</td>
     *                            </tr>
     *                            <tr>
     *                            <td>accountTypeNameEndsWith</td>
     *                            <td>c.accountType.name like '%'||:accountTypeNameEndsWith</td>
     *                            </tr>
     *                            <tr>
     *                            <td>accountDate</td>
     *                            <td>c.accountDate=:accountDate</td>
     *                            </tr>
     *                            </table>
     */
    @GetMapping("/clients/search/")
    public String showAllByCriteria(
            @RequestParam Map<String, String> predicateConditions,
            Model model) {
        Client clientTmp = new Client();
        ExampleMatcher matcher = ExampleMatcher.matching();

        if (predicateConditions.containsKey("accountTypeName")) {
            String accountTypeName = predicateConditions.get("accountTypeName");
            clientTmp.setAccountType(new AccountType(accountTypeName));
            matcher.withMatcher("accountType.name", match -> match.exact());
        } else if (predicateConditions.containsKey("accountTypeNameContains")) {
            String accountTypeName = predicateConditions.get("accountTypeNameContains");
            clientTmp.setAccountType(new AccountType(accountTypeName));
            matcher.withMatcher("accountTypeNameContains", match -> match.contains());
        } else if (predicateConditions.containsKey("accountTypeNameStartsWith")) {
            String accountTypeName = predicateConditions.get("accountTypeNameStartsWith");
            clientTmp.setAccountType(new AccountType(accountTypeName));
            matcher.withMatcher("accountType.name", match -> match.startsWith());
        } else if (predicateConditions.containsKey("accountTypeNameEndsWith")) {
            String accountTypeName = predicateConditions.get("accountTypeNameEndsWith");
            clientTmp.setAccountType(new AccountType(accountTypeName));
            matcher.withMatcher("accountType.name", match -> match.endsWith());
        }

        if (predicateConditions.containsKey("accountDate")) {
            clientTmp.setAccountDate(Date.valueOf(LocalDate.parse(predicateConditions.get("accountDate"))));
            matcher.withMatcher("accountDate", match -> match.exact());
        }

        Example<Client> example = Example.of(clientTmp, matcher.withIgnoreNullValues());

        model.addAttribute("accountTypes", accountTypeRepository.findAll());
        model.addAttribute("clients", clientRepository.findAll(example));

        return "/clients";
    }


    @GetMapping("/clients")
    public String showAll(Model model) {
        model.addAttribute("accountTypes", accountTypeRepository.findAll());
        model.addAttribute("clients", clientRepository.findAll());
        return "/clients";
    }

    @GetMapping("/client/{id}/delete/")
    public String delete(@PathVariable("id") long id, Model model) {
        clientRepository.deleteById(id);
        return "redirect:/clients";
    }

    @PostMapping("/client/save")
    public String save(@Valid Client client, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "can't add this client " + client);
            return "/error";
        }

        clientRepository.save(client);
        return "redirect:/clients";
    }
}
