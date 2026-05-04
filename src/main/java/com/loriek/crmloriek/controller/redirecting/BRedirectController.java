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

    @GetMapping("/redirects/list")
    public String b(Model model) {
        model.addAttribute("redirects", redirectRepository.findAll());
        return "redirects/list";
    }

    @GetMapping("/redirects/new")
    public String b() {
        return "redirects/new";
    }

    @PostMapping("/redirects/new")
    public String b(String url) {
        redirectService.save(new Redirect(url));
        return "redirect:/redirects/list";
    }
}
