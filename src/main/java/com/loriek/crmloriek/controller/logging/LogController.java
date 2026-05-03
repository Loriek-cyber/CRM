package com.loriek.crmloriek.controller.logging;

import com.loriek.crmloriek.model.log.Log;
import com.loriek.crmloriek.model.log.LogRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogController {

    private final LogRepository logRepository;

    public LogController(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @GetMapping("/logs")
    public String logsPage(Model model) {
        model.addAttribute("logs", logRepository.findAll());
        return "log/listLog";
    }

    @GetMapping("/new-log")
    public String logs() {
        return "log/newLog";
    }

    @PostMapping("/new-log")
    public String newLog(@Param("descrizione") String descrizione) {
        Log log = new Log(descrizione);
        logRepository.save(log);
        return "redirect:/logs";
    }
}
