package org.sselab.iot.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sselab.iot.platform.domain.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
