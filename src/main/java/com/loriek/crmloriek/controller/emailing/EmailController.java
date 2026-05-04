package com.loriek.crmloriek.controller.emailing;

import com.loriek.crmloriek.model.email.EmailRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmailController {

    private final EmailRepository emailRepository;
    public EmailController(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @GetMapping("/email/list")
    public String emailList(Model model) {
        model.addAttribute("emails",emailRepository.findAll());
        return "email/list";
    }
}
