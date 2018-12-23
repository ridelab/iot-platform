package org.sselab.iot.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sselab.iot.platform.domain.Client;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

  Optional<Client> findByRegistrationId(String registrationId);

}
