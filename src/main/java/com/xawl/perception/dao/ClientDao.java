package com.xawl.perception.dao;

import com.xawl.perception.pojo.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDao extends JpaRepository<Client, Integer> {
}
