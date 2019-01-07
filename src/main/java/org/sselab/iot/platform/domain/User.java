package org.sselab.iot.platform.domain;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author PanTeng
 * @version 1.0
 * @file User.java
 * @date 2019/1/7
 * @attention Copyright (C),2004-2017,SSELab, SEI, XiDian University
 */
@Data
@Entity
public class User {

  @Id
  @GeneratedValue
  Long id;            //用户id

  @NonNull
  String userName;    //用户姓名

  @NonNull
  String account;     //登录账号

  @NonNull
  String password;    //登录密码
}
