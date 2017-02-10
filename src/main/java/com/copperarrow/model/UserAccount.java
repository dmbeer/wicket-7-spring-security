package com.copperarrow.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dbeer on 10/02/17.
 */
@Entity
@Table(name = "cass_users")
public class UserAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userid")
    private Long userid;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(name = "email_address", nullable = false)
    private String email;

    @Column(name = "username", unique = true, nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
//    @Size(min =8)
    private String password;

    @Column(name = "enabled")
    private int enabled;

    public UserAccount() {
    }

    public UserAccount(UserAccount userAccount) {
        this.userid = userAccount.userid;
        this.userName = userAccount.userName;
        this.firstName = userAccount.firstName;
        this.lastName = userAccount.lastName;
        this.email = userAccount.email;
        this.password = userAccount.password;
        this.enabled = userAccount.enabled;
    }

    public UserAccount(Long userid, String firstName, String lastName, String email, String userName, String password, int enabled) {
        this.userid = userid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
