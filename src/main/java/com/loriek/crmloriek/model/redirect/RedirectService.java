package com.loriek.crmloriek.model.redirect;

import com.loriek.crmloriek.model.log.View;
import com.loriek.crmloriek.model.log.Log;
import com.loriek.crmloriek.utils.AnalysisService;
import com.loriek.crmloriek.utils.UUIDGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class RedirectService {
    private final RedirectRepository redirectRepository;
    private final AnalysisService analysisService;

    public RedirectService(RedirectRepository redirectRepository, AnalysisService analysisService) {
        this.redirectRepository = redirectRepository;
        this.analysisService = analysisService;
    }

    public Redirect getRedirect(String uudi) {
        return redirectRepository.findById(uudi).orElse(null);
    }

    public Redirect recordView(String uuid, String ip, String userAgent) {
        Redirect redirect = getRedirect(uuid);
        if (redirect != null) {
            if (redirect.getLog() == null) {
                redirect.setLog(new Log());
            }
            View view = new View(ip, userAgent, redirect.getLog());
            analysisService.analyze(view);

            redirect.getLog().addView(view);
            redirectRepository.save(redirect);
        }
        return redirect;
    }

    public void save(Redirect redirect) {
        if(redirect.getUudi() == null) {
            String newUuid = UUIDGenerator.generateUUID();
            while(redirectRepository.existsById(newUuid)) {
                newUuid = UUIDGenerator.generateUUID();
            }
            redirect.setUudi(newUuid);
        }

        if(redirect.getLog() == null) {
            redirect.setLog(new Log());
        }
        redirectRepository.save(redirect);
    }
}
