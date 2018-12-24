package org.sselab.iot.platform.domain;

import lombok.Data;
import org.eclipse.leshan.server.registration.Registration;
import org.sselab.iot.platform.configuration.LwM2mServerHolder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Optional;

@Data
@Entity
public class Client {

  String name;

  String remark;

  String registrationId;

  @Id
  @GeneratedValue
  Long id;

  public Optional<Registration> getRegistration() {
    return Optional.ofNullable(LwM2mServerHolder.getServer().getRegistrationService().getById(getRegistrationId()));
  }

}
