package com.isa.aem.model;

import com.isa.aem.model.selects.CalculatorTracking;
import com.isa.aem.model.selects.GlobalExtremumTracking;
import com.isa.aem.model.selects.LocalExtremumTracking;

import javax.persistence.*;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinColumn(name = "calculator_id")
    private List<CalculatorTracking> calculatorTracking;

    @OneToMany
    @JoinColumn(name = "global_extremum_id")
    private List<GlobalExtremumTracking> globalTracking;

    @OneToMany
    @JoinColumn
    private List<LocalExtremumTracking> localTracking;

    public Activity(
            User user,
            List<CalculatorTracking> calculatorTracking,
            List<GlobalExtremumTracking> globalTracking,
            List<LocalExtremumTracking> localTracking) {
        this.user = user;
        this.calculatorTracking = calculatorTracking;
        this.globalTracking = globalTracking;
        this.localTracking = localTracking;
    }

    public Activity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CalculatorTracking> getCalculatorTracking() {
        return calculatorTracking;
    }

    public void setCalculatorTracking(List<CalculatorTracking> calculatorTracking) {
        this.calculatorTracking = calculatorTracking;
    }

    public List<GlobalExtremumTracking> getGlobalTracking() {
        return globalTracking;
    }

    public void setGlobalTracking(List<GlobalExtremumTracking> globalTracking) {
        this.globalTracking = globalTracking;
    }

    public List<LocalExtremumTracking> getLocalTracking() {
        return localTracking;
    }

    public void setLocalTracking(List<LocalExtremumTracking> localTracking) {
        this.localTracking = localTracking;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
