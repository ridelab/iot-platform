package org.sselab.iot.platform.web;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.eclipse.leshan.core.node.LwM2mNode;
import org.eclipse.leshan.core.request.*;
import org.eclipse.leshan.core.response.LwM2mResponse;
import org.eclipse.leshan.server.LwM2mServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sselab.iot.platform.repository.ClientRepository;

@Slf4j
@RepositoryRestController
@RequestMapping("/clients")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClientEndpoint {

  private final ClientRepository clientRepository;

  private final LwM2mServer lwM2mServer;

  private final ContentFormat format = ContentFormat.JSON;

  @Data
  public static class ManagementReadInput {
    String path;
  }

  @PostMapping("/{id}/management/read")
  public ResponseEntity read(
    @PathVariable Long id,
    @RequestBody ManagementReadInput input
  ) throws InterruptedException {
    val request = new ReadRequest(format, input.getPath());
    return ResponseEntity.ok(manage(id, request));
  }

  @Data
  public static class ManagementWriteInput {
    String path;
    LwM2mNode data;
    WriteRequest.Mode mode = WriteRequest.Mode.REPLACE;
  }

  @PostMapping("/{id}/management/write")
  public ResponseEntity write(
    @PathVariable Long id,
    @RequestBody ManagementWriteInput input
  ) throws InterruptedException {
    val request = new WriteRequest(input.getMode(), format, input.getPath(), input.getData());
    return ResponseEntity.ok(manage(id, request));
  }

  @Data
  public static class ManagementExecuteInput {
    String path;
    String parameters;
  }

  @PostMapping("/{id}/management/execute")
  public ResponseEntity execute(
    @PathVariable Long id,
    @RequestBody ManagementExecuteInput input
  ) throws InterruptedException {
    val request = new ExecuteRequest(input.getPath(), input.getParameters());
    return ResponseEntity.ok(manage(id, request));
  }

  @Data
  public static class ManagementDiscoverInput {
    String path;
  }

  @PostMapping("/{id}/management/discover")
  public ResponseEntity discover(
    @PathVariable Long id,
    @RequestBody ManagementDiscoverInput input
  ) throws InterruptedException {
    val request = new DiscoverRequest(input.getPath());
    return ResponseEntity.ok(manage(id, request));
  }

  private <T extends LwM2mResponse> T manage(Long id, DownlinkRequest<T> request) throws InterruptedException {
    val client = clientRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    val registration = client.getRegistration().orElseThrow(IllegalStateException::new);
    return lwM2mServer.send(registration, request);
  }

}
