package com.loriek.crmloriek.model.redirect;

import com.loriek.crmloriek.model.log.Log;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Redirect {
    @Id
    public String uudi;
    public String url;
    @OneToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Log log;

    public Redirect(String uudi, String url, Log log) {
        this.uudi = uudi;
        this.url = url;
        this.log = log;
    }


    public Redirect() {

    }

    public Redirect(String url) {
        this.url = url;
    }

    public String getUudi() {
        return uudi;
    }

    public void setUudi(String uudi) {
        this.uudi = uudi;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }
}
