package com.isa.aem.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "USERS")
public class User {

    private static final String UNIQUE_ID = "id";
    private static final String UNIQUE_ID_BY_ACTIVITY = "activity_id";
    private static final String USER_NAME_GIVEN_BY_GOOGLE = "name";
    private static final String USER_EMAIL_GIVEN_BY_GOOGLE = "email";
    private static final String IS_ADMIN = "is_admin";
    private static final String DATE_AND_TIME_WHEN_USER_LOGGED_IN = "logged_in";
    private static final String DATE_AND_TIME_WHEN_USER_LOGGED_OUT = "logged_out";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = UNIQUE_ID)
    private Long id;

    @Column(name = USER_NAME_GIVEN_BY_GOOGLE)
    private String userName;

    @Column(name = USER_EMAIL_GIVEN_BY_GOOGLE)
    private String email;

    @Column(name = IS_ADMIN)
    private Boolean isAdmin;

    @Column(name = DATE_AND_TIME_WHEN_USER_LOGGED_IN)
    private LocalDateTime loggedIn;

    @Column(name = DATE_AND_TIME_WHEN_USER_LOGGED_OUT)
    private LocalDateTime loggedOut;

    @OneToOne
    @JoinColumn(name = UNIQUE_ID_BY_ACTIVITY, unique = true)
    private Activity activity;

    public User() {

    }

    public User(String userName,
                String email,
                Boolean isAdmin,
                LocalDateTime loggedIn,
                LocalDateTime loggedOut,
                Activity activity) {
        this.userName = userName;
        this.email = email;
        this.isAdmin = isAdmin;
        this.loggedIn = loggedIn;
        this.loggedOut = loggedOut;
        this.activity = activity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public LocalDateTime getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(LocalDateTime loggedIn) {
        this.loggedIn = loggedIn;
    }

    public LocalDateTime getLoggedOut() {
        return loggedOut;
    }

    public void setLoggedOut(LocalDateTime loggedOut) {
        this.loggedOut = loggedOut;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
