package com.loriek.crmloriek.controller.logging;

import com.loriek.crmloriek.model.log.Log;
import com.loriek.crmloriek.model.log.LogRepository;
import com.loriek.crmloriek.model.log.View;
import com.loriek.crmloriek.model.log.ViewRepository;
import com.loriek.crmloriek.utils.AnalysisService;
import com.loriek.crmloriek.utils.IpUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ViewTrackingController {
    private final LogRepository logRepository;
    private final AnalysisService analysisService;

    public ViewTrackingController(LogRepository logRepository, AnalysisService analysisService) {
        this.logRepository = logRepository;
        this.analysisService = analysisService;
    }

    @GetMapping("/log/{id}/img")
    @Transactional
    public ResponseEntity<byte[]> img(@PathVariable Long id, HttpServletRequest request) throws IOException {

        Log log = logRepository.findById(id).orElse(null);

        ClassPathResource imgFile = new ClassPathResource("static/image/1x1.png");
        byte[] bytes = imgFile.getInputStream().readAllBytes();

        if (log != null) {
            String ip = IpUtils.getClientIp(request);
            String agent = request.getHeader("User-Agent");

            View view = new View(ip, agent, log);
            analysisService.analyze(view);

            log.addView(view);
            logRepository.save(log); // usa il cascade
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(bytes);
    }
}
