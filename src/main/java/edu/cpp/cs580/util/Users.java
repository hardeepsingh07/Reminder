package edu.cpp.cs580.util;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by hardeepsingh on 2/8/17.
 */
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String email;
    private String password;
    private String serviceprovider;
    private String number;
    private String vcode;
    private boolean verified;

    public Users() {}

    public Users(String name, String email, String password, String serviceprovider, String number, String vcode,
                 boolean verified) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.serviceprovider = serviceprovider;
        this.number = number;
        this.vcode = vcode;
        this.verified = verified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getServiceProvider() {
        return serviceprovider;
    }

    public void setServiceProvider(String serviceprovider) {
        this.serviceprovider = serviceprovider;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
}
