package com.xawl.perception.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue
    private Integer aid;
    private String account;
    private String pass;

    public Admin() {
    }

    public Admin(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "aid=" + aid +
                ", account='" + account + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
