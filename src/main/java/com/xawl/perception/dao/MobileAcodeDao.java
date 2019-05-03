package com.xawl.perception.dao;

import com.xawl.perception.pojo.MobileAcode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileAcodeDao extends JpaRepository<MobileAcode, Integer> {
}
