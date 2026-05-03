package com.loriek.crmloriek.controller.redirecting;

import com.loriek.crmloriek.model.log.Log;
import com.loriek.crmloriek.model.log.View;
import com.loriek.crmloriek.model.redirect.Redirect;
import com.loriek.crmloriek.model.redirect.RedirectRepository;
import com.loriek.crmloriek.model.redirect.RedirectService;
import com.loriek.crmloriek.utils.IpUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.time.LocalDateTime;

@Controller
public class RedirectController {
    private final RedirectService redirectService;
    public RedirectController(RedirectService redirectService) {
        this.redirectService = redirectService;
    }

    @GetMapping("/r/{uuid}")
    public String redirect(@PathVariable String uuid, HttpServletRequest request) {

        Redirect redirect = redirectService.getRedirect(uuid);
        if (redirect == null) {
            return "errors/404";
        }

        View view = new View();
        view.setIp(IpUtils.getClientIp(request));
        view.setAgent(request.getHeader("User-Agent"));
        view.setData(LocalDateTime.now());


        redirect.getLog().addView(view);
        redirectService.save(redirect);

        return "redirect:" + redirect.getUrl();
    }
}
