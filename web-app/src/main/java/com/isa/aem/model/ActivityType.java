package com.isa.aem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACTIVITY_TYPE")
public class ActivityType {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private
}
