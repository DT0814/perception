package com.xawl.perception.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xawl.perception.utils.CustomDateSerializer;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "mobile_acode")
public class MobileAcode {
    @Id
    private Integer mid;
    private String acode;
    private Date time;

    public MobileAcode(Integer mid) {
        this.mid = mid;
    }

    @Override
    public String toString() {
        return "ClientAcode{" +
                "cid=" + mid +
                ", acode='" + acode + '\'' +
                ", time=" + time +
                '}';
    }

    public MobileAcode() {
    }

    public MobileAcode(int cid, String acode, Date time) {
        this.mid = cid;
        this.acode = acode;
        this.time = time;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getAcode() {
        return acode;
    }

    public void setAcode(String acode) {
        this.acode = acode;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
