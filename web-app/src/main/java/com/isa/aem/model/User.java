package com.isa.aem.model;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user")
    @NotNull
    private String userName;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "status")
    @NotNull
    private Boolean isAdmin;

    @Column(name = "date_time")
    private LocalDate localDate;


    public User() {

    }

    public User(String userName, @NotNull String email, @NotNull Boolean isAdmin) {
        this.email = email;
        this.isAdmin = isAdmin;
        this.userName = userName;
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
