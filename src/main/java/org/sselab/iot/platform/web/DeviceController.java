package org.sselab.iot.platform.web;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sselab.iot.platform.repository.DeviceRepository;

/**
 * @author PanTeng
 * @version 1.0
 * @file DeviceController.java
 * @date 2019/1/8
 * @attention Copyright (C),2004-2019,SSELab, SEI, XiDian University
 */
@Slf4j
@RepositoryRestController
@RequestMapping("/api/device")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DeviceController {

  private final DeviceRepository deviceRepository;

  @Value
  private static class Response{
    Object responseBody;
  }

  @GetMapping("/{productId}/getDevicesByProductId")
  public ResponseEntity<Response> getDevicesByProductId(
    @PathVariable Long productId
  ){
    val result = deviceRepository.findByProductId(productId);
    val response = new Response(result);
    return ResponseEntity.ok(response);
  }
}
