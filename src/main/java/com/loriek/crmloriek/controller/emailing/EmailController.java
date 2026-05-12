package com.loriek.crmloriek.controller.emailing;

import com.loriek.crmloriek.model.email.Email;
import com.loriek.crmloriek.model.email.EmailRepository;
import com.loriek.crmloriek.model.log.Log;
import com.loriek.crmloriek.model.log.LogRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmailController {

    private final EmailRepository emailRepository;
    private final LogRepository logRepository;

    public EmailController(EmailRepository emailRepository, LogRepository logRepository) {
        this.emailRepository = emailRepository;
        this.logRepository = logRepository;
    }

    @GetMapping("/email/list")
    public String emailList(Model model) {
        model.addAttribute("emails", emailRepository.findAll());
        return "email/list";
    }

    @GetMapping("/email/new")
    public String newEmailForm() {
        return "email/new";
    }

    @PostMapping("/email/new")
    public String createEmail(String sender, String receiver, String subject, String body) {
        Email email = new Email(sender, receiver, subject, body);
        emailRepository.save(email);
        return "redirect:/email/list";
    }
}
