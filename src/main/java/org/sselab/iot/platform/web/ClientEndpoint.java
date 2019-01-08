package org.sselab.iot.platform.web;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.eclipse.leshan.core.model.ObjectModel;
import org.eclipse.leshan.core.node.LwM2mNode;
import org.eclipse.leshan.core.request.*;
import org.eclipse.leshan.core.response.*;
import org.eclipse.leshan.server.LwM2mServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.sselab.iot.platform.configuration.LwM2mServerHolder;
import org.sselab.iot.platform.repository.ClientRepository;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RepositoryRestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClientEndpoint {

  private final ClientRepository clientRepository;

  private final LwM2mServer lwM2mServer;

  private final ContentFormat format = ContentFormat.JSON;

  @Value
  private static class LwM2mModelV {
    Map<Integer, ObjectModel> objects;
  }

  @GetMapping("/{id}/model")
  public ResponseEntity<LwM2mModelV> model(
    @PathVariable Long id
  ) {
    val client = clientRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    val registration = client.getRegistration().orElseThrow(IllegalStateException::new);
    val model = LwM2mServerHolder.getServer().getModelProvider().getObjectModel(registration);
    val modelV = new LwM2mModelV(model.getObjectModels().stream().collect(Collectors.toMap(x -> x.id, x -> x)));
    return ResponseEntity.ok(modelV);
  }

  @Data
  public static class ManagementReadInput {
    String path;
  }

  @PostMapping("/{id}/management/read")
  public ResponseEntity<ReadResponse> read(
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
  public ResponseEntity<WriteResponse> write(
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
  public ResponseEntity<ExecuteResponse> execute(
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
  public ResponseEntity<DiscoverResponse> discover(
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
