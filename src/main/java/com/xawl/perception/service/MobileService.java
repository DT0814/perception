package com.xawl.perception.service;

import com.xawl.perception.dao.MobileDao;
import com.xawl.perception.pojo.Client;
import com.xawl.perception.pojo.Mobile;
import com.xawl.perception.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MobileService {
    @Autowired
    private MobileDao dao;

    public Page<Mobile> findAll(Integer pageNumber, Mobile mobile) {
        Sort sort = new Sort(Sort.Direction.DESC, "mid");
        Pageable pageable = PageRequest.of(pageNumber, 8, sort);
        Example<Mobile> example = Example.of(mobile, ExampleMatcher.matching());
        Page<Mobile> all = dao.findAll(example, pageable);
        return all;
    }

    public void add(Mobile mobile) {
        dao.saveAndFlush(mobile);
    }

    public void update(Mobile mobile) {
        Optional<Mobile> byId = dao.findById(mobile.getMid());
        Mobile m = byId.get();
        if (!StringUtil.isEmpty(mobile.getMinfo())) {
            m.setMinfo(mobile.getMinfo());
        }
        if (!StringUtil.isEmpty(mobile.getName())) {
            m.setName(mobile.getName());
        }
        dao.saveAndFlush(m);
    }

    public void delete(Integer mid) {
        dao.deleteById(mid);
    }

    public Mobile findById(Integer mid) {
        Optional<Mobile> byId = dao.findById(mid);
        return byId.get();
    }

    public List<Mobile> findAll() {
        return dao.findAll();
    }

    public boolean exist(String account) {
        return dao.exists(Example.of(new Mobile(account)));
    }

    public Mobile findByAccount(String account) {
        Optional<Mobile> one = dao.findOne(Example.of(new Mobile(account)));
        return one.get();
    }
}
