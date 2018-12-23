package org.sselab.iot.platform.domain;

import lombok.Data;
import org.eclipse.leshan.server.registration.Registration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Data
@Entity
public class Client {

  String name;

  String remark;

  String registrationId;

  @Transient
  Registration registration;

  @Id
  @GeneratedValue
  Long id;

}
