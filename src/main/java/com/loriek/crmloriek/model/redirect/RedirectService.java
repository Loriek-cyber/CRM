package com.loriek.crmloriek.model.redirect;

import com.loriek.crmloriek.model.log.Log;
import com.loriek.crmloriek.utils.UUIDGenerator;
import org.springframework.stereotype.Service;

@Service
public class RedirectService {
    private final RedirectRepository redirectRepository;
    public RedirectService(RedirectRepository redirectRepository) {
        this.redirectRepository = redirectRepository;
    }

    public Redirect getRedirect(String uudi) {
        return redirectRepository.findById(uudi).orElse(null);
    }

    public void save(Redirect redirect) {
        if(redirect.getUudi() == null) {
            redirect.setUudi(UUIDGenerator.generateUUID());
        }
        //da migliorare

        while(redirectRepository.findById(redirect.getUudi()).isPresent()) {
            redirect.setUudi(UUIDGenerator.generateUUID());
        }
        if(redirect.getLog() == null) {
            redirect.setLog(new Log(redirect.getUudi()));
        }
        redirectRepository.save(redirect);
    }
}
