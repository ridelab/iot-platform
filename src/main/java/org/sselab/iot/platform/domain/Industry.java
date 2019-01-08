package org.sselab.iot.platform.domain;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author PanTeng
 * @version 1.0
 * @file Industry.java
 * @date 2019/1/7
 * @attention Copyright (C),2004-2017,SSELab, SEI, XiDian University
 */
@Data
@Entity
public class Industry {

  @Id
  @GeneratedValue
  Long industryId;      //行业id

  @NonNull
  String industryName;  //行业名称
}
