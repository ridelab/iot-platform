package org.sselab.iot.platform.domain;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

/**
 * @author PanTeng
 * @version 1.0
 * @file Product.java
 * @date 2019/1/7
 * @attention Copyright (C),2004-2017,SSELab, SEI, XiDian University
 */
@Data
@Entity
public class Product {

  @Id
  @GeneratedValue
  Long id;          //产品id

  @NonNull
  Long userId;      //用户id

  @NonNull
  String productName; //产品名称

  @NonNull
  Long industryId;    //行业类别

  @NonNull
  Long classifyId;    //具体产品类别

  String description; //产品描述

  @NonNull
  String connection;  //联网方式

  @NonNull
  String protocol;    //接入协议
}
