package com.xawl.perception.dao;

import com.xawl.perception.pojo.Mobile;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MobileDao extends JpaRepository<Mobile, Integer> {
}