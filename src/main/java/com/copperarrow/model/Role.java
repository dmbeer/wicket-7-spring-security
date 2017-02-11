package com.copperarrow.model;

/**
 * Created by dbeer on 11/02/17.
 */
public enum Role {

    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    public final String springSecurityRoleName;

    Role(String springSecurityRoleName) {
        this.springSecurityRoleName = springSecurityRoleName;
    }

    public String getSpringSecurityRoleName(){
        return springSecurityRoleName;
    }

    @Override
    public String toString() {
        return springSecurityRoleName;
    }

}
