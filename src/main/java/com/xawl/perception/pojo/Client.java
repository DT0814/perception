package com.xawl.perception.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue
    private Integer cid;
    private String account;
    private String pass;
    private String info;
    private String name;

    public Client() {
    }

    public Client(Integer cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "Client{" +
                "cid=" + cid +
                ", account='" + account + '\'' +
                ", pass='" + pass + '\'' +
                ", info='" + info + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
