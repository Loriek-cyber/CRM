package com.loriek.crmloriek.controller.emailing;

import com.loriek.crmloriek.model.email.Email;
import com.loriek.crmloriek.model.email.EmailRepository;
import com.loriek.crmloriek.model.log.Log;
import com.loriek.crmloriek.model.log.LogRepository;
import com.loriek.crmloriek.model.log.View;
import com.loriek.crmloriek.utils.AnalysisService;
import com.loriek.crmloriek.utils.IpUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/email/track")
public class EmailTrackingController {
    private final EmailRepository emailRepository;
    private final LogRepository logRepository;
    private final AnalysisService analysisService;

    public EmailTrackingController(EmailRepository emailRepository, LogRepository logRepository, AnalysisService analysisService) {
        this.emailRepository = emailRepository;
        this.logRepository = logRepository;
        this.analysisService = analysisService;
    }

    @GetMapping("/{id}/pixel.png")
    @Transactional
    public ResponseEntity<byte[]> trackEmail(@PathVariable Long id, HttpServletRequest request) throws IOException {
        Email email = emailRepository.findById(id).orElse(null);

        if (email != null && email.getSeen() != null) {
            Log log = email.getSeen();
            String ip = IpUtils.getClientIp(request);
            String agent = request.getHeader("User-Agent");

            View view = new View(ip, agent, log);
            analysisService.analyze(view);

            log.addView(view);
            logRepository.save(log);
        }

        ClassPathResource imgFile = new ClassPathResource("static/image/1x1.png");
        byte[] bytes = imgFile.getInputStream().readAllBytes();

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(bytes);
    }
}
