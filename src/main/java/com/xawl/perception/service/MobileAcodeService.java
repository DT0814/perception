package com.xawl.perception.service;

import com.xawl.perception.dao.MobileAcodeDao;
import com.xawl.perception.dao.MobileDao;
import com.xawl.perception.pojo.Mobile;
import com.xawl.perception.pojo.MobileAcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class MobileAcodeService {
    @Autowired
    private MobileAcodeDao dao;
    @Autowired
    private MobileDao mobileDao;

    public MobileAcode findById(Integer mid) {
        Optional<MobileAcode> one = dao.findOne(Example.of(new MobileAcode(mid)));
        return one.get();
    }

    public void add(Integer mid, String acode, Date date) {
        MobileAcode mobileAcode = new MobileAcode();
        mobileAcode.setAcode(acode);
        mobileAcode.setTime(date);
        mobileAcode.setMid(mid);
        dao.saveAndFlush(mobileAcode);
    }

    public boolean out(String account, String acode) {
        Optional<Mobile> one1 = mobileDao.findOne(Example.of(new Mobile(account)));
        Mobile mobile = one1.get();
        MobileAcode one = dao.getOne(mobile.getMid());
        if (null == one || !one.getAcode().equals(acode)) {
            return false;
        } else {
            dao.delete(one);
            dao.flush();
            return true;
        }
    }
}
