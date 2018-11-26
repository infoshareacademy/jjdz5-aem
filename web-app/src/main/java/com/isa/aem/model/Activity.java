package com.isa.aem.model;

import javax.persistence.*;

@Entity
@Table(name = "ACTIVITIES")
public class Activity {

    private static final String UNIQU_ID = "id";
    private static final String LOCAL_EXTREME_COLUMN = "local";
    private static final String GLOBAL_EXTREME_COLUMN = "global";
    private static final String CALCULATOR = "calculator";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = UNIQU_ID)
    private Long id;

    @Column(name = LOCAL_EXTREME_COLUMN)
    private String local;

    @Column(name = GLOBAL_EXTREME_COLUMN)
    private String global;

    @Column(name = CALCULATOR)
    private String calculator;

    public Activity(String local, String global, String calculator) {
        this.local = local;
        this.global = global;
        this.calculator = calculator;
    }

    public Activity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getGlobal() {
        return global;
    }

    public void setGlobal(String global) {
        this.global = global;
    }

    public String getCalculator() {
        return calculator;
    }

    public void setCalculator(String calculator) {
        this.calculator = calculator;
    }
}
