package com.loriek.crmloriek.model.log;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descrizione;


    @OneToMany(mappedBy = "log", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<View> views;

    public Log() {
    }

    public Log(String descrizione) {
        this.descrizione = descrizione;
        this.views = new ArrayList<>();
    }


    public void addView(View view) {
        views.add(view);
        view.setLog(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<View> getViews() {
        return views;
    }

    public void setViews(List<View> views) {
        this.views = views;
    }
}
