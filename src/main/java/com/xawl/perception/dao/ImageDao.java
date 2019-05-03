package com.xawl.perception.dao;

import com.xawl.perception.pojo.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDao extends JpaRepository<Image, Integer> {
}
