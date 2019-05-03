package com.xawl.perception.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xawl.perception.utils.CustomDateSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue
    private Integer imid;

    private Integer cid;

    private String cAccount;

    private String mids;

    private String url;

    private Date inTime;

    public Image() {
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getInTime() {
        return inTime;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public String getcAccount() {
        return cAccount;
    }

    public void setcAccount(String cAccount) {
        this.cAccount = cAccount;
    }

    public Integer getImid() {
        return imid;
    }

    public void setImid(Integer imid) {
        this.imid = imid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getMids() {
        return mids;
    }

    public void setMids(String mids) {
        this.mids = mids;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
