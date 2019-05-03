package com.xawl.perception.service;

import com.xawl.perception.dao.ClientDao;
import com.xawl.perception.pojo.Client;
import com.xawl.perception.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientDao dao;

    public void add(Client client) {
        dao.saveAndFlush(client);
    }

    public Client findById(Integer cid) throws EntityNotFoundException {
        if (dao.existsById(cid)) {
            Client one = dao.getOne(cid);
            return one;
        } else return null;

    }

    public boolean exist(Integer cid) {
        return dao.existsById(cid);
    }

    public List<Client> findAll() {
        return dao.findAll();
    }

    public Page<Client> findAll(Integer pageNumber, Client client) {
        Sort sort = new Sort(Sort.Direction.DESC, "cid");
        Pageable pageable = PageRequest.of(pageNumber, 8, sort);
        Example<Client> example = Example.of(client, ExampleMatcher.matching());
        Page<Client> all = dao.findAll(example, pageable);
        return all;
    }

    public void delete(Integer cid) {
        dao.deleteById(cid);
    }

    public void update(Client client) {
        Optional<Client> byId = dao.findById(client.getCid());
        Client c = byId.get();
        if (!StringUtil.isEmpty(client.getInfo())) {
            c.setInfo(client.getInfo());
        }
        if (!StringUtil.isEmpty(client.getName())) {
            c.setName(client.getName());
        }
        dao.saveAndFlush(c);
    }
}
