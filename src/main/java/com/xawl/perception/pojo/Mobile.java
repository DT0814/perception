package com.xawl.perception.pojo;

import com.xawl.perception.utils.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.format.annotation.DateTimeFormat;

import javax.jws.soap.SOAPBinding;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "mobile")
public class Mobile {
    @Id
    @GeneratedValue
    private Integer mid;

    private String minfo;

    private String name;

    private Date inTime;

    private String pass;

    private String account;

    public Mobile() {
    }

    public Mobile(Integer mid) {
        this.mid = mid;
    }

    public Mobile(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Mobile{" +
                "mid='" + mid + '\'' +
                ", minfo='" + minfo + '\'' +
                ", name='" + name + '\'' +
                ", inTime=" + inTime +
                ", pass='" + pass + '\'' +
                ", account='" + account + '\'' +
                '}';
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getInTime() {
        return inTime;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getMinfo() {
        return minfo;
    }

    public void setMinfo(String minfo) {
        this.minfo = minfo;
    }
}
