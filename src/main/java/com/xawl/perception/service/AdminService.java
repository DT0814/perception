package com.xawl.perception.service;

import com.xawl.perception.dao.AdminDao;
import com.xawl.perception.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminDao dao;

    public Admin findByAccount(String account) {
        return getOneByAccount(account);
    }

    public Admin getOneByAccount(String account) {
        ExampleMatcher matcher = ExampleMatcher.matching().
                withIgnorePaths("aid")
                .withMatcher("account", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Admin> example = Example.of(new Admin(account), matcher);
        Optional<Admin> one = dao.findOne(example);
        boolean present = one.isPresent();
        return present ? one.get() : null;
    }

}
