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


    public Activity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
