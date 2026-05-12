package com.loriek.crmloriek.utils;

import com.loriek.crmloriek.model.log.View;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua_parser.Client;
import ua_parser.Parser;

import java.util.Map;

@Service
public class AnalysisService {
    private final Parser uaParser;
    private final RestTemplate restTemplate;

    public AnalysisService() {
        this.uaParser = new Parser();
        this.restTemplate = new RestTemplate();
    }

    public void analyze(View view) {
        // Parse User Agent
        if (view.getAgent() != null) {
            Client c = uaParser.parse(view.getAgent());
            view.setBrowser(c.userAgent.family + " " + c.userAgent.major);
            view.setOs(c.os.family + " " + c.os.major);
            view.setDevice(c.device.family);
        }

        // Get Geolocation
        try {
            String url = "http://ip-api.com/json/" + view.getIp();
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            if (response != null && "success".equals(response.get("status"))) {
                view.setCountry((String) response.get("country"));
                view.setCity((String) response.get("city"));
            }
        } catch (Exception e) {
            // Silently fail if geolocation is unavailable
            view.setCountry("Unknown");
            view.setCity("Unknown");
        }
    }
}
