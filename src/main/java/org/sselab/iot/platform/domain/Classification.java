package org.sselab.iot.platform.domain;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author PanTeng
 * @version 1.0
 * @file Classification.java
 * @date 2019/1/7
 * @attention Copyright (C),2004-2017,SSELab, SEI, XiDian University
 */
@Data
@Entity
public class Classification {

  @Id
  @GeneratedValue
  Long id;                    //产品类别id

  @NonNull
  Long industryId;            //产品类别所属行业（领域）

  @NonNull
  String classificationName;  //产品类别名称
}
