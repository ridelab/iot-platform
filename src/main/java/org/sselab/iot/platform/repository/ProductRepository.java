package org.sselab.iot.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.sselab.iot.platform.domain.Product;

import java.util.List;

/**
 * @author PanTeng
 * @version 1.0
 * @file ProductRepository.java
 * @date 2019/1/7
 * @attention Copyright (C),2004-2017,SSELab, SEI, XiDian University
 */
public interface ProductRepository extends JpaRepository<Product,Long> {

  List<Product> findByUserId(@Param("userId") Long userId);
}
