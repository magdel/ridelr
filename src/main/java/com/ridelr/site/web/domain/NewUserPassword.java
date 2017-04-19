package com.ridelr.site.web.domain;


public class NewUserPassword {

    private String password;
    private String password2;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    @Override
    public String toString() {
        return "NewUserPassword{" +
                "password='" + password + '\'' +
                ", password2='" + password2 + '\'' +
                '}';
    }
}
