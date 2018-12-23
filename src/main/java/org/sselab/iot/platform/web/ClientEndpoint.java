package org.sselab.iot.platform.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.eclipse.leshan.core.request.ContentFormat;
import org.eclipse.leshan.core.request.ReadRequest;
import org.eclipse.leshan.server.LwM2mServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.sselab.iot.platform.repository.ClientRepository;

@Slf4j
@RepositoryRestController
@RequestMapping("/clients")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClientEndpoint {

  private final ClientRepository clientRepository;

  private final LwM2mServer lwM2mServer;

  @PostMapping("/{id}/management/{operation}")
  public ResponseEntity manage(
    @PathVariable Long id,
    @PathVariable String operation,
    @RequestParam String path
  ) throws InterruptedException {
    logger.trace("id = {}", id);
    logger.trace("operation = {}", operation);
    logger.trace("path = {}", path);
    val client = clientRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    val registration = lwM2mServer.getRegistrationService().getById(client.getRegistrationId());
    switch (operation) {
      case "read":
        val readRequest = new ReadRequest(ContentFormat.JSON, path);
        logger.trace("read request = {}", readRequest);
        val readResponse = lwM2mServer.send(registration, readRequest);
        logger.trace("read response = {}", readResponse);
        return ResponseEntity.ok().build();
      case "write":
      case "create":
      case "delete":
      case "execute":
      case "discover":
      case "write-attributes":
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
      default:
        return ResponseEntity.badRequest().build();
    }
  }

}
