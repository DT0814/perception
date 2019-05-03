package com.xawl.perception.service;

import com.xawl.perception.dao.ClientMobileDao;
import com.xawl.perception.pojo.ClientMobile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientMobileService {
    @Autowired
    private ClientMobileDao dao;

    public List<ClientMobile> findAll(ClientMobile clientMobile) {
        Example<ClientMobile> example = Example.of(clientMobile);
        List<ClientMobile> all = dao.findAll(example);
        return all;
    }

    public void add(ClientMobile clientMobile) {
        dao.saveAndFlush(clientMobile);
    }

    public void delete(ClientMobile clientMobile) {
        dao.deleteById(clientMobile.getCmid());
    }
}
