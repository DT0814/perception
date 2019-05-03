package com.xawl.perception.dao;

import com.xawl.perception.pojo.ClientMobile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientMobileDao extends JpaRepository<ClientMobile, Integer> {
}
