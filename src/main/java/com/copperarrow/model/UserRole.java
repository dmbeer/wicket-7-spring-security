package com.copperarrow.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by dbeer on 10/02/17.
 */
@Entity
@Table(name = "cass_user_role")
public class UserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public UserRole() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role.toString();
    }
}
