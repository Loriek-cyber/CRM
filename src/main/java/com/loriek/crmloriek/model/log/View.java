package com.loriek.crmloriek.model.log;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class View {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ip;
    private LocalDateTime data;
    private String agent;

    @ManyToOne
    @JoinColumn(name = "log_id")
    private Log log;

    public View() {
    }

    public View(String ip, String agent, Log log) {
        this.ip = ip;
        this.data = LocalDateTime.now();
        this.agent = agent;
        this.log = log;
    }

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }
}
