package com.loriek.crmloriek.model.redirect;

import com.loriek.crmloriek.model.log.View;
import com.loriek.crmloriek.model.log.Log;
import com.loriek.crmloriek.utils.UUIDGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class RedirectService {
    private final RedirectRepository redirectRepository;
    public RedirectService(RedirectRepository redirectRepository) {
        this.redirectRepository = redirectRepository;
    }

    public Redirect getRedirect(String uudi) {
        return redirectRepository.findById(uudi).orElse(null);
    }

    public Redirect recordView(String uuid, String ip, String userAgent) {
        Redirect redirect = getRedirect(uuid);
        if (redirect != null) {
            View view = new View();
            view.setIp(ip);
            view.setAgent(userAgent);
            view.setData(LocalDateTime.now());
            if (redirect.getLog() == null) {
                redirect.setLog(new Log());
            }
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
