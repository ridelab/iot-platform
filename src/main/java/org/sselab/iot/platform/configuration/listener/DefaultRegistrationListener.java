package org.sselab.iot.platform.configuration.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.eclipse.leshan.core.observation.Observation;
import org.eclipse.leshan.server.registration.Registration;
import org.eclipse.leshan.server.registration.RegistrationListener;
import org.eclipse.leshan.server.registration.RegistrationUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.sselab.iot.platform.domain.Client;
import org.sselab.iot.platform.repository.ClientRepository;

import java.util.Collection;

@Slf4j
@Primary
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultRegistrationListener implements RegistrationListener {

  private final ClientRepository clientRepository;

  private final ObjectMapper objectMapper;

  @Override
  @SneakyThrows(JsonProcessingException.class)
  public void registered(
    Registration registration,
    Registration previousRegistration,
    Collection<Observation> previousObservations
  ) {
    logger.trace("DefaultRegistrationListener#registered");
    logger.trace("registration = {}", registration);
    logger.trace("previousRegistration = {}", previousRegistration);
    logger.trace("previousObservations = {}", previousObservations);
    logger.trace("registration (stringify) = {}", objectMapper.writeValueAsString(registration));
    // TODO
    val clientOptional = clientRepository.findByRegistrationId(registration.getId());
    if (clientOptional.isPresent()) {
      val client = clientOptional.get();
      logger.trace("client (existed) = {}", client);
    } else {
      val client = new Client();
      client.setRegistrationId(registration.getId());
      client.setName(registration.getId() + "@" + registration.getEndpoint());
      clientRepository.save(client);
      logger.trace("client (new) = {}", client);
    }
  }

  @Override
  public void updated(
    RegistrationUpdate update,
    Registration updatedRegistration,
    Registration previousRegistration
  ) {
    logger.trace("DefaultRegistrationListener#updated");
    logger.trace("update = {}", update);
    logger.trace("updatedRegistration = {}", updatedRegistration);
    logger.trace("previousRegistration = {}", previousRegistration);
    // TODO
  }

  @Override
  public void unregistered(
    Registration registration,
    Collection<Observation> observations,
    boolean expired, Registration newRegistration
  ) {
    logger.trace("DefaultRegistrationListener#unregistered");
    logger.trace("registration = {}", registration);
    logger.trace("observations = {}", observations);
    logger.trace("newRegistration = {}", newRegistration);
    // TODO
    val clientOptional = clientRepository.findByRegistrationId(registration.getId());
    val client = clientOptional.orElseThrow(IllegalStateException::new);
    logger.trace("client = {}", client);
  }

}
