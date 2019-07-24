package com.github.beguy.module6.core.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping
    public String page(Authentication currentUserAuthentication, Model model) {
        if (currentUserAuthentication != null) {
            Set<String> roles = currentUserAuthentication.getAuthorities().stream()
                    .map(r -> r.getAuthority()).collect(Collectors.toSet());
            model.addAttribute("user", currentUserAuthentication.getName());
            model.addAttribute("roles", roles);
        }
        return "login";
    }

    @PostMapping
    public String authenticate(@RequestParam Map<String, String> map) throws Exception {
        Authentication result = new UsernamePasswordAuthenticationToken(
                map.get("username"), map.get("password"),
                AuthorityUtils.commaSeparatedStringToAuthorityList("MANAGER"));
        SecurityContextHolder.getContext().setAuthentication(result);
        return "login";
    }
}
