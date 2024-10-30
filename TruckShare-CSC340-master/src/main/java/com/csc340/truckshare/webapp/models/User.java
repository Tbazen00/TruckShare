package com.csc340.truckshare.webapp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    private int userType;
    private boolean isBanned;

    private String username;

    private String password;

    private String firstName;
    private String lastName;

    public User() {}

    public User(int userId, int userType, boolean isBanned,
                String password, String username, String details){
        this.userId = userId;
        this.userType = userType;
        this.isBanned = isBanned;
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public void banUser(boolean ban){
        this.isBanned = ban;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

}
