package com.isa.aem.model;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotNull
    private String userName;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "is_admin")
    @NotNull
    private Boolean isAdmin;

    @Column(name = "logged_in")
    private LocalDateTime loggedIn;

    @Column(name = "logged_out")
    private LocalDateTime loggedOut;

    @Column(name = "is_logged")
    private Boolean isLogged;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Activity> activities;

    public User() {

    }

    public User(String userName, @NotNull String email, @NotNull Boolean isAdmin, List<Activity> activities) {
        this.email = email;
        this.isAdmin = isAdmin;
        this.userName = userName;
        this.activities = activities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
