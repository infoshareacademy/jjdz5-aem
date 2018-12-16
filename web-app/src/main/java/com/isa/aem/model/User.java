package com.isa.aem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Entity
@Table(name = "USERS")
@Transactional
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "is_admin")
    private Boolean isAdmin = false;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Activity> activities;

    public User() {

    }

    public User(String userName,
                String email,
                Boolean isAdmin) {
        this.name = userName;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String userName) {
        this.name = userName;
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


    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activity) {
        this.activities = activity;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", isAdmin=").append(isAdmin);
        sb.append(", activities=").append(activities.stream().map(Activity::getId).collect(toList()));
        sb.append('}');
        return sb.toString();
    }
}
