package com.loriek.crmloriek.controller.redirecting;

import com.loriek.crmloriek.model.redirect.Redirect;
import com.loriek.crmloriek.model.redirect.RedirectRepository;
import com.loriek.crmloriek.model.redirect.RedirectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BRedirectController {
    private final RedirectService redirectService;
    private final RedirectRepository redirectRepository;

    public BRedirectController(RedirectService redirectService, RedirectRepository redirectRepository) {
        this.redirectService = redirectService;
        this.redirectRepository = redirectRepository;
    }

    @GetMapping("/redirect/list")
    public String b(Model model) {
        model.addAttribute("redirect", redirectRepository.findAll());
        return "redirect/list";
    }

    @GetMapping("/redirect/new")
    public String b() {
        return "redirect/new";
    }

    @PostMapping("/redirect/new")
    public String b(String url) {

        return "redirect:/redirect/list";
    }
}
