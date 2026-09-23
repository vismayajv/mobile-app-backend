// package com.vismayajv.mobile_app_backend.auth.entity;

// public class user {

// }
package com.vismayajv.mobile_app_backend.auth.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class user {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50)
    private String role = "USER";

   @Column(name = "mobilenumber", nullable = false, length = 20)
    private String mobileNumber;

    public user() {
    }

    public user(String email, String password, String name, String role, String mobileNumber) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.mobileNumber=mobileNumber;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMobileNumber() {
    return mobileNumber;
}

public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
}

}