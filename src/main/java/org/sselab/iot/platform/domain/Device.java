package org.sselab.iot.platform.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Device {

  String remark = "";

  @Id
  @GeneratedValue
  Long id;

}
