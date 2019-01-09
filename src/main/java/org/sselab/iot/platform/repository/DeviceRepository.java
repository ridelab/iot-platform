package org.sselab.iot.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.sselab.iot.platform.domain.Device;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {

  List<Device> findByProductId(@Param("productId") Long productId);

  Device findByImei(@Param("imei") String IMEI);
}
