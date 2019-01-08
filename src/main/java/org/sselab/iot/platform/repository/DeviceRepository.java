package org.sselab.iot.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sselab.iot.platform.domain.Device;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {

  List<Device> findByProductId(Long ProductId);
}
