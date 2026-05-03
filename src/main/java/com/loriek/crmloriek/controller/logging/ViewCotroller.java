package com.loriek.crmloriek.controller.logging;

import com.loriek.crmloriek.model.log.Log;
import com.loriek.crmloriek.model.log.LogRepository;
import com.loriek.crmloriek.model.log.View;
import com.loriek.crmloriek.model.log.ViewRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ViewCotroller {
    private final LogRepository logRepository;
    private final ViewRepository viewRepository;
    public ViewCotroller(LogRepository logRepository, ViewRepository viewRepository) {
        this.logRepository = logRepository;
        this.viewRepository = viewRepository;
    }


    @GetMapping("/log/{id}/img")
    public ResponseEntity<byte[]> img(@PathVariable Long id, HttpServletRequest request) throws IOException {

        Log log = logRepository.findById(id).orElse(null);

        ClassPathResource imgFile = new ClassPathResource("static/image/1x1.png");
        byte[] bytes = imgFile.getInputStream().readAllBytes();

        if (log != null) {
            String ip = getClientIp(request);
            String agent = request.getHeader("User-Agent");

            View view = new View(ip, agent, log);

            log.addView(view);
            logRepository.save(log); // usa il cascade
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(bytes);
    }

    private String getClientIp(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}
