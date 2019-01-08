package org.sselab.iot.platform.domain;

import lombok.Data;
import lombok.NonNull;
import org.springframework.core.annotation.AliasFor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Device {

  @Id
  @GeneratedValue
  Long id;            //设备id

  @NonNull
  String deviceName;  //设备名称

  @NonNull
  Long productId;     //设备所属产品id

  @NonNull
  String imei;        //IMEI 设备全球唯一识别号（15位）

  String authCode;

  @NonNull
  String IMSI;        //（15位）

  @NonNull
  Boolean status = false; //设备在离线状态

  public Device() {
  }
}
