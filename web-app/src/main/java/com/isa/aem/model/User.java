package com.isa.aem.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User {

    private static final String UNIQUE_ID = "id";
    private static final String USER_MAPPED = "user";
    private static final String USER_NAME_GIVEN_BY_GOOGLE = "name";
    private static final String USER_EMAIL_GIVEN_BY_GOOGLE = "email";
    private static final String IS_ADMIN = "is_admin";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = UNIQUE_ID)
    private Long id;

    @Column(name = USER_NAME_GIVEN_BY_GOOGLE)
    private String userName;

    @Column(name = USER_EMAIL_GIVEN_BY_GOOGLE)
    private String email;

    @Column(name = IS_ADMIN)
    private Boolean isAdmin = false;

    @OneToMany(mappedBy = USER_MAPPED, fetch = FetchType.LAZY)
    private List<Activity> activity;

    public User() {

    }

    public User(String userName,
                String email,
                Boolean isAdmin,
                LocalDateTime loggedIn,
                LocalDateTime loggedOut) {
        this.userName = userName;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public void addActivity(Activity activity) {
        this.activity.add(activity);
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


    public List<Activity> getActivity() {
        return activity;
    }

    public void setActivity(List<Activity> activity) {
        this.activity = activity;
    }
}
