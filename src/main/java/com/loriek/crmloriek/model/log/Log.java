package com.loriek.crmloriek.model.log;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany(mappedBy = "log", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("data DESC")
    private List<View> views;

    public Log() {
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

    public List<View> getViews() {
        return views;
    }

    public void setViews(List<View> views) {
        this.views = views;
    }
}
